package com.poseidon.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.poseidon.entity.Member;
import com.poseidon.service.CustomUserDetails;

@Component
public class Util {
	// 로그인 한 객체의 사용자 ID뽑아오기 
	public String getId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails cuds = (CustomUserDetails) authentication.getPrincipal();
		return cuds.getID();
	}
	//Member entity 바로 뽑아내기
	public Member getMember() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails cuds = (CustomUserDetails) authentication.getPrincipal();
		return cuds.getMember();
	}
}
