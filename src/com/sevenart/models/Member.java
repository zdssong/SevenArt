package com.sevenart.models;

import java.io.Serializable;

/*
 * 会员列表的实体类
 * */
public class Member implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5750863235853686465L;
	private String mid;
	private String userid;
	private String uname;
	private String rank;
	private String face;
	private String alpha;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String mid, String userid, String uname, String rank,
			String face, String alpha) {
		super();
		this.mid = mid;
		this.userid = userid;
		this.uname = uname;
		this.rank = rank;
		this.face = face;
		this.alpha = alpha;
	}

	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

}
