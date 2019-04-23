package com.job;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String pass="123456789";
		for (int i=0;i<pass.length();i++) {
			;
			System.out.println(bCryptPasswordEncoder.encode(pass));
		}
		
	}

}
