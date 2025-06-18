package com.poseidon.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.poseidon.dao.LoginDAO;
import com.poseidon.dto.JoinDTO;
import com.poseidon.dto.LoginDTO;
import com.poseidon.entity.Member;
import com.poseidon.repository.JpamemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final LoginDAO loginDAO;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final JpamemberRepository jpamemberRepository;

	/*
	 * public Map<String, Object> login(Map<String, Object> login) { Map<String,
	 * Object> result = loginDAO.login(login); boolean check =
	 * bCryptPasswordEncoder.matches((String)login.get("pw"),
	 * (String)result.get("user_pw")); //System.out.println("check >>> " + check);
	 * return result; }
	 */

	public void join(JoinDTO dto) {
		dto.setPw(bCryptPasswordEncoder.encode(dto.getPw()));// 평문 암호를 암호화 하는 문장.
		Member member = Member.builder() // dto -> entity
				.mid(dto.getId())
				.mpw(dto.getPw())
				.mname(dto.getName())
				.memail(dto.getEmail())
				.mrole("ROLE_USER")
				.build();
		member = jpamemberRepository.save(member);
		//System.out.println("loginService >>> " + member.getMno());
	}

	public boolean checkId(String id) {
		int result = loginDAO.checkId(id);
		if(result == 0) {
			return true;
		} else {			
			return false;
		}
	}

	public LoginDTO info(String id) {
		Optional<Member> member = jpamemberRepository.findByMid(id);
		LoginDTO dto = new LoginDTO();
		dto.setUno(member.get().getMno());
		dto.setName(member.get().getMname());
		dto.setId(member.get().getMid());
		dto.setEmail(member.get().getMemail());
		dto.setRole(member.get().getMrole());
		//가입일은.......
		return dto;
	}


}
