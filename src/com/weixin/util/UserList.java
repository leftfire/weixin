package com.weixin.util;

/**
 * 用户总数和用户openid信息
 * @author Tony
 *
 */
public class UserList {
	private String total;
	private String[] open_id;
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String[] getOpen_id() {
		return open_id;
	}
	public void setOpen_id(String[] open_id) {
		this.open_id = open_id;
	}
	
}
