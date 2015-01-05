package com.sevenart.models;

import java.io.Serializable;

public class MyCare implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mMid;
	private String mUserId;
	private String mUname;
	private String mRank;
	private String mFace;

	public MyCare() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyCare(String mMid, String mUserId, String mUname, String mRank,
			String mFace) {
		super();
		this.mMid = mMid;
		this.mUserId = mUserId;
		this.mUname = mUname;
		this.mRank = mRank;
		this.mFace = mFace;
	}

	public String getmMid() {
		return mMid;
	}

	public void setmMid(String mMid) {
		this.mMid = mMid;
	}

	public String getmUserId() {
		return mUserId;
	}

	public void setmUserId(String mUserId) {
		this.mUserId = mUserId;
	}

	public String getmUname() {
		return mUname;
	}

	public void setmUname(String mUname) {
		this.mUname = mUname;
	}

	public String getmRank() {
		return mRank;
	}

	public void setmRank(String mRank) {
		this.mRank = mRank;
	}

	public String getmFace() {
		return mFace;
	}

	public void setmFace(String mFace) {
		this.mFace = mFace;
	}

}
