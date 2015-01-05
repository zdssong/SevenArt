package com.sevenart.models;

import java.io.Serializable;
import java.util.List;

public class Auction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String widString;
	private String aidString;
	private String typeidString;
	private String introductString;
	private int pagepicnum;
	private String[] imgurlStrings;
	private int basePrice;
	private int amplitude;
	private String cashdepositString;
	private String curpriceString;
	private String addtimeString;
	private String successmidString;
	private String successunameString;
	private String useripString;
	private int number;
	private int endTimesTamp;
	private int leftTimesTamp;
	private List<Records> records;

	public Auction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getWidString() {
		return widString;
	}

	public void setWidString(String widString) {
		this.widString = widString;
	}

	public String getAidString() {
		return aidString;
	}

	public void setAidString(String aidString) {
		this.aidString = aidString;
	}

	public String getTypeidString() {
		return typeidString;
	}

	public void setTypeidString(String typeidString) {
		this.typeidString = typeidString;
	}

	public String getIntroductString() {
		return introductString;
	}

	public void setIntroductString(String introductString) {
		this.introductString = introductString;
	}

	public int getPagepicnum() {
		return pagepicnum;
	}

	public void setPagepicnum(int pagepicnum) {
		this.pagepicnum = pagepicnum;
	}

	public String[] getImgurlStrings() {
		return imgurlStrings;
	}

	public void setImgurlStrings(String[] imgurlStrings) {
		this.imgurlStrings = imgurlStrings;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	public int getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(int amplitude) {
		this.amplitude = amplitude;
	}

	public String getCashdepositString() {
		return cashdepositString;
	}

	public void setCashdepositString(String cashdepositString) {
		this.cashdepositString = cashdepositString;
	}

	public String getCurpriceString() {
		return curpriceString;
	}

	public void setCurpriceString(String curpriceString) {
		this.curpriceString = curpriceString;
	}

	public String getAddtimeString() {
		return addtimeString;
	}

	public void setAddtimeString(String addtimeString) {
		this.addtimeString = addtimeString;
	}

	public String getSuccessmidString() {
		return successmidString;
	}

	public void setSuccessmidString(String successmidString) {
		this.successmidString = successmidString;
	}

	public String getSuccessunameString() {
		return successunameString;
	}

	public void setSuccessunameString(String successunameString) {
		this.successunameString = successunameString;
	}

	public String getUseripString() {
		return useripString;
	}

	public void setUseripString(String useripString) {
		this.useripString = useripString;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getEndTimesTamp() {
		return endTimesTamp;
	}

	public void setEndTimesTamp(int endTimesTamp) {
		this.endTimesTamp = endTimesTamp;
	}

	public int getLeftTimesTamp() {
		return leftTimesTamp;
	}

	public void setLeftTimesTamp(int leftTimesTamp) {
		this.leftTimesTamp = leftTimesTamp;
	}

	public List<Records> getRecords() {
		return records;
	}

	public void setRecords(List<Records> records) {
		this.records = records;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
