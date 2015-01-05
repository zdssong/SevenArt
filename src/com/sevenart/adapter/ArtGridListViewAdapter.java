package com.sevenart.adapter;

import java.util.List;

import com.sevenart.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ArtGridListViewAdapter extends BaseAdapter {

	private TextView m_TextView;
	private List<String> m_NameList;
	private LayoutInflater m_Inflater;

	public ArtGridListViewAdapter(Context context, List<String> nameList) {
		this.m_Inflater = LayoutInflater.from(context);
		this.m_NameList = nameList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_NameList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return m_NameList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (view == null) {
			view = m_Inflater.inflate(R.layout.grid_list_item, null);
		}

		initView(view);

		m_TextView.setText(m_NameList.get(position));

		return view;
	}

	private void initView(View view) {
		m_TextView = (TextView) view.findViewById(R.id.textView);
	}
}
