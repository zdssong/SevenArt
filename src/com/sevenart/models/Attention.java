package com.sevenart.models;

import java.io.Serializable;

/*
 * 关注的实体类
 * */
public class Attention implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mId;
	private String userId;
	private String uName;
	private String rank;
	private String face;

	public Attention() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attention(String mId, String userId, String uName, String rank,
			String face) {
		super();
		this.mId = mId;
		this.userId = userId;
		this.uName = uName;
		this.rank = rank;
		this.face = face;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
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
