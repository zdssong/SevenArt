package com.sevenart.tools;

import java.util.ArrayList;
import java.util.List;

import com.sevenart.application.ArtApplication;
import com.sevenart.db.DatabaseManager;
import com.sevenart.models.Column;

import android.content.Context;
import android.util.Log;

public class ChannelManage {

	private List<Column> m_Columns;
	private DatabaseManager m_DatabaseManager;
	/**
	 * 默认的用户选择频道列表
	 * */
	public static List<Column> defaultUserChannels;
	/**
	 * 默认的其他频道列表
	 * */
	public static List<Column> defaultOtherChannels;
	/** 判断数据库中是否存在用户数据 */
	private boolean userExist = false;

	public ChannelManage(Context context) {
		m_DatabaseManager = ArtApplication.getArtApplication().getDatabaseManger();
		m_Columns = new ArrayList<Column>();
		defaultUserChannels = new ArrayList<Column>();
		defaultOtherChannels = new ArrayList<Column>();

		getChannel();
	}

	private void getChannel() {
		m_Columns = m_DatabaseManager.queryAllColumns();
		if (m_Columns.size() == 0) {
			Log.e("size", m_Columns.size() + " 1");
		} else {
			initUserChannel();
			initOtherChannel();
		}
	}

	private void initUserChannel() {
		for (int i = 0; i < m_Columns.size() - 1; i++) {
			Column column = new Column();
			column = m_Columns.get(i);
			column.setSelected(1);
			column.setOrderId(i);
			defaultUserChannels.add(column);
		}
	}

	private void initOtherChannel() {
		for (int i = m_Columns.size() - 1; i < m_Columns.size(); i++) {
			Column column = new Column();
			column = m_Columns.get(i);
			column.setSelected(0);
			column.setOrderId(i);
			defaultOtherChannels.add(column);
		}
	}

	/**
	 * 获取其他的频道
	 * 
	 * @return 数据库存在用户配置 ? 数据库内的用户选择频道 : 默认用户选择频道 ;
	 */
	public List<Column> getUserChannel() {
		List<Column> userChannel = new ArrayList<Column>();
		userChannel = m_DatabaseManager.queryColumnBySelected(1);
		if (userChannel.size() != 0) {
			userExist = true;
			return userChannel;
		}
		initDefaultChannel();
		return defaultUserChannels;
	}

	/**
	 * 获取其他的频道
	 * 
	 * @return 数据库存在用户配置 ? 数据库内的其它频道 : 默认其它频道 ;
	 */
	public List<Column> getOtherChannel() {
		List<Column> otherChannel = new ArrayList<Column>();
		otherChannel = m_DatabaseManager.queryColumnBySelected(0);
		if (otherChannel.size() != 0) {
			return otherChannel;
		}
		if (userExist) {
			return otherChannel;
		}
		return defaultOtherChannels;
	}

	public void saveUserChannel(List<Column> userChannel) {
		List<Column> columns = new ArrayList<Column>();
		for (int i = 0; i < userChannel.size(); i++) {
			Column column = userChannel.get(i);
			column.setOrderId(i);
			column.setSelected(1);
			columns.add(column);
		}
		m_DatabaseManager.deleteColumn();
		m_DatabaseManager.addColumn(columns);
	}

	public void saveOtherChannel(List<Column> otherChannel) {
		List<Column> columns = new ArrayList<Column>();
		for (int i = 0; i < otherChannel.size(); i++) {
			Column column = otherChannel.get(i);
			column.setOrderId(i);
			column.setSelected(0);
			columns.add(column);
		}
		m_DatabaseManager.addColumn(columns);
	}

	/**
	 * 初始化数据库内的频道数据
	 */
	private void initDefaultChannel() {
		m_DatabaseManager.deleteColumn();
		saveUserChannel(defaultUserChannels);
		saveOtherChannel(defaultOtherChannels);
	}
}
