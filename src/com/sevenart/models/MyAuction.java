package com.sevenart.models;

import java.io.Serializable;

public class MyAuction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String wid;
	private String aid;
	private String introduce;
	private String basePrice;
	private String curPrice;
	private String addTime;
	private String imgurl;

	public MyAuction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyAuction(String wid, String aid, String introduce,
			String basePrice, String curPrice, String addTime, String imgurl) {
		super();
		this.wid = wid;
		this.aid = aid;
		this.introduce = introduce;
		this.basePrice = basePrice;
		this.curPrice = curPrice;
		this.addTime = addTime;
		this.imgurl = imgurl;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	public String getCurPrice() {
		return curPrice;
	}

	public void setCurPrice(String curPrice) {
		this.curPrice = curPrice;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
