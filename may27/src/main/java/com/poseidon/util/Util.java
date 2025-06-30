package com.poseidon.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.poseidon.entity.Member;
import com.poseidon.service.CustomUserDetails;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class Util {
	
	//url가져오기
	public String getURL() {
		HttpServletRequest hsr = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return hsr.getRequestURI();  // /login   /board
	}
	
	
	public String ipMasking(String ip) {
		String[] ipArr = getIP().split("[.]");
		return ipArr[0]+".❤️."+ipArr[2]+"."+ipArr[3];
	}
	
	public String ipMasking() {
		String[] ip = getIP().split("\\.");
		return ip[0]+".❤️."+ip[2]+"."+ip[3];
	}

	// ip
	public String getIP() {
		// import java.net.InetAddress;
		// import java.net.UnknownHostException;
		// import org.springframework.web.context.request.RequestContextHolder;
		// import org.springframework.web.context.request.ServletRequestAttributes;
		// import jakarta.servlet.http.HttpServletRequest;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		if (ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
			InetAddress address = null;
			try {
				address = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			ip = address.getHostName() + "/" + address.getHostAddress();
		}
		return ip;
	}

	// 아이디가 동일한지 검사하기
	public boolean idCheck(Member member) {
		if ((member.getMid()).equals(getId())) {
			return true;
		} else {
			return false;
		}
	}

	// 로그인 한 객체의 사용자 ID뽑아오기
	public String getId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails cuds = (CustomUserDetails) authentication.getPrincipal();
		return cuds.getID();
	}

	// Member entity 바로 뽑아내기
	public Member getMember() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails cuds = (CustomUserDetails) authentication.getPrincipal();
		return cuds.getMember();
	}
}
