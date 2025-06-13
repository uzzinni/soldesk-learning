package com.poseidon.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.poseidon.entity.Member;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails{
	//LoginDTO
	//private final LoginDTO loginDTO;
	// Member Entity를 사용하는 것으로 변경합니다.
	private final Member member;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(new GrantedAuthority() {
			
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
	
}