package com.sevenart.models;

import java.io.Serializable;

/*
 * 评论的实体类
 * */
public class Comment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String aId;
	private String typeId;
	private String userName;
	private String ip;
	private String dTime;
	private String mId;
	private String bad;
	private String good;
	private String fType;
	private String msg;
	private String userId;
	private String mFace;
	private String spacesta;
	private String dcores;
	private String sex;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String id, String aId, String typeId, String userName,
			String ip, String dTime, String mId, String bad, String good,
			String fType, String msg, String userId, String mFace,
			String spacesta, String dcores, String sex) {
		super();
		this.id = id;
		this.aId = aId;
		this.typeId = typeId;
		this.userName = userName;
		this.ip = ip;
		this.dTime = dTime;
		this.mId = mId;
		this.bad = bad;
		this.good = good;
		this.fType = fType;
		this.msg = msg;
		this.userId = userId;
		this.mFace = mFace;
		this.spacesta = spacesta;
		this.dcores = dcores;
		this.sex = sex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getdTime() {
		return dTime;
	}

	public void setdTime(String dTime) {
		this.dTime = dTime;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getBad() {
		return bad;
	}

	public void setBad(String bad) {
		this.bad = bad;
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public String getfType() {
		return fType;
	}

	public void setfType(String fType) {
		this.fType = fType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getmFace() {
		return mFace;
	}

	public void setmFace(String mFace) {
		this.mFace = mFace;
	}

	public String getSpacesta() {
		return spacesta;
	}

	public void setSpacesta(String spacesta) {
		this.spacesta = spacesta;
	}

	public String getDcores() {
		return dcores;
	}

	public void setDcores(String dcores) {
		this.dcores = dcores;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
