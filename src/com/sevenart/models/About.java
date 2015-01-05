package com.sevenart.models;

import java.io.Serializable;

public class About implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mTitle;
	private String mDescription;
	private String mEmail;
	private String mAddress;
	private String mQcode;

	public About() {
		super();
		// TODO Auto-generated constructor stub
	}

	public About(String mTitle, String mDescription, String mEmail,
			String mAddress, String mQcode) {
		super();
		this.mTitle = mTitle;
		this.mDescription = mDescription;
		this.mEmail = mEmail;
		this.mAddress = mAddress;
		this.mQcode = mQcode;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getmDescription() {
		return mDescription;
	}

	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	public String getmQcode() {
		return mQcode;
	}

	public void setmQcode(String mQcode) {
		this.mQcode = mQcode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
