package com.sevenart.adapter;

import java.util.List;

import com.sevenart.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsListAdapter extends BaseAdapter {

	private List<String> m_NewsList;
	private LayoutInflater m_Inflater;
	private TextView m_TextView;

	public NewsListAdapter(List<String> newsList, Context context) {
		this.m_NewsList = newsList;
		m_Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_NewsList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return m_NewsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (view == null) {
			view = m_Inflater.inflate(R.layout.news_item, null);
		}
		m_TextView = (TextView) view.findViewById(R.id.newsItem);
		m_TextView.setText(m_NewsList.get(position));
		return view;
	}

}
