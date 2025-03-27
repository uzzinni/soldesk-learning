package com.poseidon.aop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.poseidon.service.AopService;
import com.poseidon.util.Util;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.RequiredArgsConstructor;

// 2025-03-27 웹 시스템 구축 - AOP, 트랜젝션, 시큐리티

@Aspect		// AOP사용
@Component	// 객체 등록
@RequiredArgsConstructor
public class AopConfig {
	
	// @Autowired -> 생성자 주입방식으로 변경
	private final Util util;
	private final AopService aopService;

	@Before("execution(* com.poseidon.controller.*.*(..))") // 모든 컨트롤러에 있는 모든 메소드 
	public void before(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.err.println("AOP가 동작합니다. : " + Arrays.toString(args));
		
		for (Object obj : args) {
			System.out.print("타입 : " + obj.getClass().getSimpleName());
			System.out.println(" / 값 : " + obj);
		}
		
		//URL뽑기
		String uri = util.getURI();
		System.out.println("URI : " + uri);
		
		//IP
		System.out.println("ip : " + util.getIP());
		
		Map<String, Object> map = new HashMap<>();
		map.put("ip", util.getIP());
		map.put("uri", util.getURI());
		map.put("data", Arrays.toString(args));
		
		aopService.saveLog(map);
	}

}

/*
 * AOP 용어들
 * 관점지향 프로그래밍 : 각 작업마다 반복된 기능을 통일해서 한번에 작성.
 * 
 * JoinPoint : AOP를 적용할 특정 지점을 이야기합니다.
 * 			   스프링이 관리하고 있는 객체에 적용 가능합니다.
 * 			   JoinPoint.getArgs() : JoinPoint에 전달된 파라미터를 배열로 가져오기
 * 			   JoinPoint.getThis() : AOP 프록시 객체를 반환
 * 			   JoinPoint.getSignature() : 조인되는 메소드 설명 반환 (시그니처 : 리턴, 이름, 파라미터 등)
 * 
 * Target : 기능을 담은 모듈, Advice가 적용될 객체 (순수 비즈니스 로직)
 * 
 * Advice : 특정 JoinPoint에서 실행될 기능
 * 			@Before				: JoinPoint를 호출하기 전 실행
 * 			@After Returning	: 정상동작 했을 때 실행
 * 			@After Throwing		: 예외가 발생했을 때 실행
 * 			@After				: JoinPoint 동작과 관계없이 메소드 실행 후 실행
 * 			@Around				: 메소드 호출 전, 후, 예외발생 시점에 공통적으로 실행
 * 
 * PointCut : Advice가 적용되는 위치를 표시합니다. 메소드 실행 지점
 * 
 * PointCut 지시자
 * 		execution	: 메소드를 기준으로 PointCut을 실행
 * 		within		: 특정 타입 내의 조인 포인트
 * 		args		: 특정 파라미터를 가지고 있는 대상
 * 		this		: 주어진 인터페이스를 구현하고 있는 객체를 대상
 * 		target		: Target 객체 대상
 * 		annotation 	: 어노테이션을 가지고 있으면 대상
 * 
 * PointCut 표현
 * 		@Before("execution(* com.poseidon.controller.*.*(..))")
 * 		패키지 앞 * : 접근제어자
 * 		*(..) : 모든 메소드
 * 		(..) : 파라미터
 * 		(*) : 파라미터가 반드시 1개 있는 메소드만
 * 		get*(..) : 메소드가 get으로 시작하는 모든 메소드
 * 						@Before("execution(* get*(..))")
 * 		서비스패키지 하위 모든 메소드
 * 						@Before("execution(* com.poseidon.service.*.*(..))")
 * 
 * 기타
 * Weaving : PointCut으로 결정한 핵심 기능의 JoinPoint에 Advice를 적용하는것
 * Advisor : 하나의 Advice와 PointCut으로 구성되어 있는 코드
 * */