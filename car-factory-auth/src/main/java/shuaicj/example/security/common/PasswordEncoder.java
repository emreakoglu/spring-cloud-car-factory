package shuaicj.example.security.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	
	private BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
	
	private static PasswordEncoder passwordEncoder;
	
	private PasswordEncoder() {}
	
	public static PasswordEncoder getInstance() {
		if (passwordEncoder == null) {
			passwordEncoder = new PasswordEncoder();
		}
		return passwordEncoder;
	}
	
	public BCryptPasswordEncoder getCryptPasswordEncoder() {
		return cryptPasswordEncoder;
	}

}
