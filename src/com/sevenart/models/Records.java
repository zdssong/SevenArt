package com.sevenart.models;

import java.io.Serializable;

/**
 * 记录的实体类，是拍品展示实体类的辅助类
 * */
public class Records implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3794897370460744806L;
	private String id;
	private String aid;
	private String wid;
	private String mid;
	private String userId;
	private int price;
	private String face;
	private String time;
	private String status;

	public Records() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Records(String id, String aid, String wid, String mid,
			String userId, int price, String face, String time, String status) {
		super();
		this.id = id;
		this.aid = aid;
		this.wid = wid;
		this.mid = mid;
		this.userId = userId;
		this.price = price;
		this.face = face;
		this.time = time;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
