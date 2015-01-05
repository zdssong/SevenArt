package com.sevenart.models;

import java.io.Serializable;

public class Bid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int curprice;
	private int price;
	private String userId;
	private String face;

	public Bid() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public int getCurprice() {
		return curprice;
	}

	public void setCurprice(int curprice) {
		this.curprice = curprice;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
