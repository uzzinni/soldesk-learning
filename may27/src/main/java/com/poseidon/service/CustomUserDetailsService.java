package com.poseidon.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poseidon.entity.Member;
import com.poseidon.repository.JpamemberRepository;

import lombok.RequiredArgsConstructor;

//실제 로그인 작업을 하는 서비스
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	//데이터베이스에서 해당 ID의 모든 정보 가져오기
	//private final LoginDAO loginDAO;
	private final JpamemberRepository jpamemberRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Optional<Member> userData = jpamemberRepository.findByMid(id);
		//System.out.println("CustomUserDetailsService >>> " + userData.isEmpty());
		//System.out.println("CustomUserDetailsService >>> " + userData.isPresent());
		//System.out.println("CustomUserDetailsService >>> " + userData.toString());
		
		if(userData.isPresent()) { //이렇게 하시는게....
			return new CustomUserDetails(userData.get()); //추출
		}		
		//return null;
		throw new UsernameNotFoundException(id + "라는 회원은 없습니다.");
	}

}
