package com.sevenart.lestener;

import com.sevenart.ArtMemberActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ArtMemberGridViewItemListener implements OnItemClickListener {

	private Context mContext;

	public ArtMemberGridViewItemListener(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(mContext, ArtMemberActivity.class);
		mContext.startActivity(intent);
	}
}
