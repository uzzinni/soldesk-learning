package com.poseidon.util;

import java.util.Random;

public class Util {
	//랜덤한 숫자 뽑기
	public static String randomNumber() {
		Random r = new Random();
		//nextInt() : value between 0 : 랜덤한 정수값을 만들어줍니다.
		//괄호 안에 숫자가 있으면 거기까지 랜덤한 숫자를 만들어줍니다.
		//nextInt(10) -> 0 ~ 10 사이의 숫자를 만들어줍니다.
		//(최대값 - 최소값) + 최소값;
		//최소값 <= r >= 최대값
		
		int num = r.nextInt(999999 - 100000) + 100000;
		return Integer.toString(num);
	}
}
