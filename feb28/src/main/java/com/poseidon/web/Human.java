package com.poseidon.web;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class Human {
	private int age;
	private String name;
	
	public void myInfo() {
		System.out.println("내 나이는 " + age + "살 입니다.");
		System.out.println("내 이름은 " + name + "입니다.");
	}
}
