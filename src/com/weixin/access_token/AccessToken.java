package com.weixin.access_token;

public class AccessToken {
	//获取到的凭证
	private String token;
	//凭证有效时间，单位：秒
	private String expiresIn;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
}
