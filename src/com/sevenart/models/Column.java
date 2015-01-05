package com.sevenart.models;

import java.io.Serializable;

/**
 * 栏目的实体类
 * */

public class Column implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int typeId;
	private String typeName;
	private int channel;
	private String channelName;
	private int orderId;
	private int selected;

	public Column() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Column(int typeId, String typeName, int channel, String channelName,
			int orderId, int selected) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.channel = channel;
		this.channelName = channelName;
		this.orderId = orderId;
		this.selected = selected;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
