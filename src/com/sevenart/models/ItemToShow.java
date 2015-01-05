package com.sevenart.models;

import java.io.Serializable;
import java.util.List;

/**
 * 拍品展示实体类的
 * */
public class ItemToShow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3196834891664524261L;
	private String wId;
	private String aId;
	private String tyoeId;
	private String introduce;
	private String pagepicnum;
	private String[] imgurls;
	private String basePrice;
	private String amplitude;
	private String cashdeposit;
	private String curPrice;
	private String addtime;
	private String successmid;
	private String successuname;
	private String userip;
	private String number;
	private String endtimestamp;
	private String lefttimestamp;
	private List<Records> records;

	public ItemToShow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemToShow(String wId, String aId, String tyoeId, String introduce,
			String pagepicnum, String[] imgurls, String basePrice,
			String amplitude, String cashdeposit, String curPrice,
			String addtime, String successmid, String successuname,
			String userip, String number, String endtimestamp,
			String lefttimestamp, List<Records> records) {
		super();
		this.wId = wId;
		this.aId = aId;
		this.tyoeId = tyoeId;
		this.introduce = introduce;
		this.pagepicnum = pagepicnum;
		this.imgurls = imgurls;
		this.basePrice = basePrice;
		this.amplitude = amplitude;
		this.cashdeposit = cashdeposit;
		this.curPrice = curPrice;
		this.addtime = addtime;
		this.successmid = successmid;
		this.successuname = successuname;
		this.userip = userip;
		this.number = number;
		this.endtimestamp = endtimestamp;
		this.lefttimestamp = lefttimestamp;
		this.records = records;
	}

	public String getwId() {
		return wId;
	}

	public void setwId(String wId) {
		this.wId = wId;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getTyoeId() {
		return tyoeId;
	}

	public void setTyoeId(String tyoeId) {
		this.tyoeId = tyoeId;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getPagepicnum() {
		return pagepicnum;
	}

	public void setPagepicnum(String pagepicnum) {
		this.pagepicnum = pagepicnum;
	}

	public String[] getImgurls() {
		return imgurls;
	}

	public void setImgurls(String[] imgurls) {
		this.imgurls = imgurls;
	}

	public String getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	public String getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(String amplitude) {
		this.amplitude = amplitude;
	}

	public String getCashdeposit() {
		return cashdeposit;
	}

	public void setCashdeposit(String cashdeposit) {
		this.cashdeposit = cashdeposit;
	}

	public String getCurPrice() {
		return curPrice;
	}

	public void setCurPrice(String curPrice) {
		this.curPrice = curPrice;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getSuccessmid() {
		return successmid;
	}

	public void setSuccessmid(String successmid) {
		this.successmid = successmid;
	}

	public String getSuccessuname() {
		return successuname;
	}

	public void setSuccessuname(String successuname) {
		this.successuname = successuname;
	}

	public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEndtimestamp() {
		return endtimestamp;
	}

	public void setEndtimestamp(String endtimestamp) {
		this.endtimestamp = endtimestamp;
	}

	public String getLefttimestamp() {
		return lefttimestamp;
	}

	public void setLefttimestamp(String lefttimestamp) {
		this.lefttimestamp = lefttimestamp;
	}

	public List<Records> getRecords() {
		return records;
	}

	public void setRecords(List<Records> records) {
		this.records = records;
	}

}
