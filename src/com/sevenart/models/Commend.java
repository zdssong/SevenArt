package com.sevenart.models;

import java.io.Serializable;

/**
 * 推荐的实体类
 * */

public class Commend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private String typeId;
	private String flag; //属性定义，属性可以定义多个，由英文逗号","分割.如 flag=h,j 表示 头条、跳转
	private String channel;//决定数据列表的结构，及点击之后的处理方式
	private String arcRank;
	private String title; // 标题
	private String litpic; // 图片地址
	private String channelName;
	private String redireCuturl;

	public Commend() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commend(String id, String typeId, String flag, String channel,
			String arcRank, String title, String litpic, String channelName,
			String redireCuturl) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.flag = flag;
		this.channel = channel;
		this.arcRank = arcRank;
		this.title = title;
		this.litpic = litpic;
		this.channelName = channelName;
		this.redireCuturl = redireCuturl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getArcRank() {
		return arcRank;
	}

	public void setArcRank(String arcRank) {
		this.arcRank = arcRank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLitpic() {
		return litpic;
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getRedireCuturl() {
		return redireCuturl;
	}

	public void setRedireCuturl(String redireCuturl) {
		this.redireCuturl = redireCuturl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
