package com.poseidon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true) //메소드 제어
public class SecurityConfig {
	// 접속 허용리스트 = 누구나 다 접근 가능
	private static final String[] ALLOW_LIST = {
			"/", "/index", "/join", "/error", "/checkId", "/board2",
			"/ajaxBoard" };
	// 리소스 리스트 = 누구나 다 접근 가능
	private static final String[] SOURCE_LIST = { "/img/**", "/css/**", "/js/**" };
	// 로그인 한 사용자
	private static final String[] USER_LIST = { "/board",  "/write" };
	// 로그인 한 관리자
	private static final String[] ADMIN_LIST = { "/admin/**" }; // localhost/admin/모든거 없습니다.

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers(ADMIN_LIST).hasRole("ADMIN") // 관리자만 접근 가능
				.requestMatchers(USER_LIST).hasAnyRole("USER", "ADMIN") // 로그인한 USER, ADMIN만 접근 가능
				.requestMatchers(ALLOW_LIST).permitAll() // 허용 리스트
				.requestMatchers(SOURCE_LIST).permitAll() // 소스 리스트
		);

		// 로그인 페이지 설정
		http.formLogin(login -> login.loginPage("/login") // 우리가 만든 로그인 페이지
				.loginProcessingUrl("/login") // 입력된 데이터 처리 로직
				.defaultSuccessUrl("/board") // 로그인 성공시 이동할 페이지
				.failureUrl("/error") // 실패시 이동할 페이지
				.usernameParameter("id") // 사용자 id 변수명
				.passwordParameter("pw") // 사용자 pw 변수명
				.permitAll());
		
		// 로그아웃 설정
		http.logout(logout -> logout
				.logoutUrl("/logout") // 로그아웃 페이지.
				.invalidateHttpSession(true) // 세션 무효화
				.logoutSuccessUrl("/login") // 로그아웃 성공하고 어디로 갈거야?
				.permitAll() // 누구나 다 들어와
		);

		// CSRF
		http.csrf(auth -> auth.disable());

		return http.build();
	}

	// login.html --->

	// 회원 가입 ---> 저장

	// 암호화 도구
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(); // BCrypt 해시 함수를 사용합니다.
	}
	/*
	 * BCryptPasswordEncoder 생성자의 파라미터를 통해 해시 강도를 조절할 수 있음.
	 * bCryptPasswordEncoder.encode(평문) => SHA-1, 8bit로 만든 해쉬 + 솔트 지원. 반환타입은 암호문
	 * bCryptPasswordEncoder.machers(평문, 암호문) => 패스워드 일치 여부를 확인해 줌. 반환타입은 boolean
	 */
}
