package com.sevenart.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sevenart.R;
import com.sevenart.application.ArtApplication;
import com.sevenart.models.MyAuction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArtMyAuctionAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<MyAuction> mMyAuctions;

	public ArtMyAuctionAdapter(List<MyAuction> myAuctions) {
		super();
		mMyAuctions = myAuctions;
		inflater = LayoutInflater.from(ArtApplication.getArtApplication());
	}

	@Override
	public int getCount() {
		if (null != mMyAuctions) {
			return mMyAuctions.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		if (null != mMyAuctions) {
			return mMyAuctions.get(position);
		} else {
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item_care, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.my_care_textView1);
			viewHolder.image = (ImageView) convertView
					.findViewById(R.id.my_cara_imageView1);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.title.setText("当前价格："
				+ mMyAuctions.get(position).getCurPrice() + "RMB");
		ImageLoader.getInstance().displayImage(
				mMyAuctions.get(position).getImgurl(), viewHolder.image);
		return convertView;
	}

	private class ViewHolder {
		public TextView title;
		public ImageView image;
	}
}
