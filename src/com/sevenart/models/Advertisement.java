package com.sevenart.models;

import java.io.Serializable;

/*
 * 广告的实体类
 * */
public class Advertisement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String img;
	private String url;

	public Advertisement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Advertisement(String img, String url) {
		super();
		this.img = img;
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
