package com.weixin.access_token;

public class AccessToken {
	//��ȡ����ƾ֤
	private String token;
	//ƾ֤��Чʱ�䣬��λ����
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
