package com.sevenart.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sevenart.R;
import com.sevenart.models.Details.PictureDetails1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AuctionDetailAdapter extends BaseAdapter {

	private LayoutInflater mInflater;

	private ImageView mImageView;
	private TextView mTextView;

	private List<PictureDetails1> mDetails;

	public AuctionDetailAdapter(List<PictureDetails1> details, Context context) {
		// TODO Auto-generated constructor stub
		this.mDetails = details;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDetails == null ? 0 : mDetails.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDetails.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (view == null) {
			view = mInflater.inflate(R.layout.auction_detail_item, null);
		}
		mImageView = (ImageView) view.findViewById(R.id.imageView);
		mTextView = (TextView) view.findViewById(R.id.title);
		mTextView.setText("(" + mDetails.get(position).getwId() + ") "
				+ mDetails.get(position).getBasePrice() + "RMB");
		ImageLoader.getInstance().displayImage(
				mDetails.get(position).getUmgurls(), mImageView);
		return view;
	}

}
