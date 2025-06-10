package com.poseidon.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.poseidon.dto.LoginDTO;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails{
	//LoginDTO
	private final LoginDTO loginDTO; 

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return loginDTO.getRole();
			}
		});
		return collection;
	}

	@Override
	public String getPassword() {
		return loginDTO.getPw();
	}

	@Override
	public String getUsername() {
		return loginDTO.getId();
	}

}