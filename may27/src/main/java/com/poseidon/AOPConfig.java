package com.poseidon;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.poseidon.service.IPService;
import com.poseidon.util.Util;

import lombok.RequiredArgsConstructor;

//AOP
@Aspect
@Component
@RequiredArgsConstructor
public class AOPConfig {
	private final IPService ipService;
	private final Util util;

	
	@Pointcut("execution(* com.poseidon.controller.*.*(..))")
	public void cut() {}
	
	@Before("cut()")
	public void before(JoinPoint joinPoint) {
		//System.out.println("before >>>>>>>>>>>>>>> ");
		//실행되는 함수 이름을 가져오기
		//MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		//Method method = methodSignature.getMethod();
		//System.out.println("메소드 이름 : " + method.getName());
		//System.out.println("URI : " + util.getURL());
		
		// 파라미터 확인하기
		Object[] args = joinPoint.getArgs();
		//System.out.println("args : " + Arrays.toString(args));
		//저장하기
		ipService.save(util.getURL(), args);
	}
	
	/*
	 * @After("cut()") public void after() {
	 * System.out.println("after >>>>>>>>>>>>>> "); }
	 */
}
