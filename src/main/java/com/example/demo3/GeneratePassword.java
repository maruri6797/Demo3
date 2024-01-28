package com.example.demo3;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {
	public static void main(String[] args) {
		String rawPassword = "admin";
		String password = getEncodePassword(rawPassword);
		System.out.println(password);
	}
	
	private static String getEncodePassword(String rawPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(rawPassword)
;	}
}
