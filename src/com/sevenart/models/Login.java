package com.sevenart.models;

import java.io.Serializable;

/**
 * 登录的实体类
 * */
public class Login implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7242154933755467829L;
	private String mid;
	private String userId;
	private String uname;
	private String face;
	private String loginTime;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Login(String mid, String userId, String uname, String face,
			String loginTime) {
		super();
		this.mid = mid;
		this.userId = userId;
		this.uname = uname;
		this.face = face;
		this.loginTime = loginTime;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

}
