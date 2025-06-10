package com.poseidon.service;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.poseidon.dao.LoginDAO;
import com.poseidon.dto.LoginDTO;

import lombok.RequiredArgsConstructor;

//실제 로그인 작업을 하는 서비스
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	//데이터베이스에서 해당 ID의 모든 정보 가져오기
	private final LoginDAO loginDAO;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Optional<LoginDTO> userData = loginDAO.login(id);
		//mapper만들어야해요.
		System.out.println(userData.isEmpty());
		System.out.println(userData.isPresent());
		System.out.println(userData.toString());
		
		if(userData.isPresent()) { //이렇게 하시는게....
			return new CustomUserDetails(userData.get()); //추출
		}		
		return null;
	}

}
