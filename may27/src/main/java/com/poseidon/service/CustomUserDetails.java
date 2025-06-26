package com.poseidon.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.poseidon.entity.Member;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails{
	/**
	 * 직렬화된 객체가 클래스의 버전과 호환되는지 확인하는 고유 번호
	 * 직렬화    객체를 파일에 저장하거나 네트워크로 전송할 수 있게 만드는 과정
	 * 역직렬화  복원하는 과정 
	 */
	private static final long serialVersionUID = 1L;
	//LoginDTO
	//private final LoginDTO loginDTO;
	// Member Entity를 사용하는 것으로 변경합니다.
	private final Member member;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(new GrantedAuthority() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return member.getMrole(); // 권한 가져오기
			}
		});
		return collection;
	}

	@Override
	public String getPassword() {
		return member.getMpw(); //암호 가져오기 = 로그인
	}

	@Override
	public String getUsername() {
		return member.getMname(); // 사용자의 이름 가져오기
	}

	// 아이디 가져오기 = 신규
	public String getID() {
		return member.getMid(); // 사용자 아이디 가져오기
	}
	
	// 신규
	public Member getMember() { // 로그인한 사용자 객체 던지기
		return member;
	}
	
}