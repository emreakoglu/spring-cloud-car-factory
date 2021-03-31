package com.car.factory.auth.model;

import java.util.Date;

public class JwtToken {
	
	private String token;
	
	private Date tokenExpireDate;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public Date getTokenExpireDate() {
		return tokenExpireDate;
	}
	
	public void setTokenExpireDate(Date tokenExpireDate) {
		this.tokenExpireDate = tokenExpireDate;
	}
	
}
