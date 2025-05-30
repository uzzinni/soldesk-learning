package com.poseidon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 시큐리티 설정
// 접근 설정
// 권한 설정
// 로그인/로그아웃 설정 등

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	// 접속 허용 리스트 = 누구나 접근 가능
	private static final String[] ALLOW_LIST = {"/", "index", "/join"};
	// 리소스 리스트 = 누구나 접근 가능
	private static final String[] SOURCE_LIST = {"/img/**"};
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth
				.requestMatchers(ALLOW_LIST).permitAll()	//허용 리스트
				.requestMatchers(SOURCE_LIST).permitAll()	//소스 리스트
				);
		
		//로그인 페이지 설정
		http.formLogin(login -> login
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/board")
				.failureUrl("/error")
				.usernameParameter("id")
				.passwordParameter("pw")
				.permitAll()
				);
		
		return http.build();
	}
	
	// login.html ---> UserDetail
	
	// 회원 가입 ---> 저장
	
	// 암호화 도구
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();		// BCrypt 해서 함수를 사용합니다.
	}
	

}
