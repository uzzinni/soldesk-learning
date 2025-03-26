package com.poseidon.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poseidon.service.AopService;
import com.poseidon.util.Util;

@Aspect
@Component
public class AopConfig {

	@Autowired
	private AopService aopService;
	
	@Autowired
	private Util util;
	
	@Pointcut("execution(* com.poseidon.controller.*.*(..))")
	public void cut() {}
	
	@Before("cut()")
	public void before(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.out.println("AOP : " + Arrays.toString(args)); //파라미터를 배열형태로 출력하기
		
		//실행되는 메소드명 가져오기
		MethodSignature ms = (MethodSignature)joinPoint.getSignature();
		Method method = ms.getMethod();
		System.out.println("실행되는 메소드 명 : " + method.getName()); //boardList() 메소드명 출력
		
		//파라미터 확인하기2
		for (Object object : args) {
			System.out.print("type : " + object.getClass().getSimpleName()); //데이터 타입 출력
			System.out.println(" / value : " + object); // 값 출력
			
		}
		
		//util.getIP()
		System.out.println("ip : " + util.getIP());
		//map만들기 iip, iurl, icomment
		Map<String, Object> map = new HashMap<>();
		map.put("iip", util.getIP());
		map.put("iurl", method.getName());
		map.put("icomment", Arrays.toString(args));
		
		aopService.sevelog(map);
	}
	
}
/*
 접속한 클라이언트의 IP수집하기
 ip를 얻어와서 데이터베이스에 저장합니다.
 util... getIP()
 AopService -> AopDAO -> mapper -> DB
 
 테이블 iplog
 ino PK NN AI
 idate datatime  
 iip VARCHAR 15
 iurl VARCHAR 15
 idata TEXT
 



*/