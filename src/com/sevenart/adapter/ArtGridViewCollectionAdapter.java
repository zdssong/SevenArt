package com.sevenart.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sevenart.R;
import com.sevenart.application.ArtApplication;
import com.sevenart.models.Information;

public class ArtGridViewCollectionAdapter extends BaseAdapter {

	private DisplayImageOptions mOptions;
	private ImageLoadingListener mLoadingListener;

	private LayoutInflater inflater;
	private List<Information> mInformations;

	public ArtGridViewCollectionAdapter(List<Information> informations,
			Context context) {
		super();
		this.mInformations = informations;
		inflater = LayoutInflater.from(context);

		mOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.nopicture)
				.showImageForEmptyUri(R.drawable.nopicture)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		mLoadingListener = new LoadingListener();
	}

	@Override
	public int getCount() {
		if (null != mInformations) {
			return mInformations.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return mInformations.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.my_care_textView1);
			viewHolder.image = (ImageView) convertView
					.findViewById(R.id.my_cara_imageView1);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.title.setText(mInformations.get(position).getTitle());
		ImageLoader.getInstance().displayImage(
				mInformations.get(position).getLitpic(), viewHolder.image,
				mOptions, mLoadingListener);
		return convertView;
	}

	private static class LoadingListener extends SimpleImageLoadingListener {

		private static List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingStarted(String imageUri, View view) {
			// TODO Auto-generated method stub
			super.onLoadingStarted(imageUri, view);
		}

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean isFristDisplay = !displayedImages.contains(imageUri);
				if (isFristDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}

	}
}

class ViewHolder {
	public TextView title;
	public ImageView image;
}