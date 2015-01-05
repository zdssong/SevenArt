package com.sevenart.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sevenart.R;
import com.sevenart.models.Information;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArtShowListAdapter extends BaseAdapter {

	private DisplayImageOptions m_Options;
	private ImageLoadingListener m_LoadingListener;

	private LayoutInflater m_Inflater;

	private List<Information> m_InformationList;

	private ImageView m_ShowImage;
	private TextView m_Time;
	private TextView m_Name;
	private TextView m_Date;

	public ArtShowListAdapter(List<Information> informationList, Context context) {
		this.m_InformationList = informationList;
		m_Inflater = LayoutInflater.from(context);

		m_LoadingListener = new AnimateFirstDisplayListener();
		m_Options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.nopicture)
				.showImageOnFail(R.drawable.nopicture)
				.showImageOnLoading(R.drawable.nopicture).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(0)).build();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_InformationList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return m_InformationList.get(arg0);
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
			view = m_Inflater.inflate(R.layout.art_show_item, null);
		}
		initView(view);
		m_Name.setText(m_InformationList.get(position).getTitle());
		ImageLoader.getInstance().displayImage(
				m_InformationList.get(position).getLitpic(), m_ShowImage,
				m_Options, m_LoadingListener);
		m_Date.setText(m_InformationList.get(position).getStartTime() + "-"
				+ m_InformationList.get(position).getEndTime());
		m_Time.setText(m_InformationList.get(position).getLeftTime());
		return view;
	}

	public void setM_Informations(List<Information> m_Informations) {
		// this.m_Informations.clear();
		this.m_InformationList = m_Informations;
	}

	private void initView(View view) {
		m_ShowImage = (ImageView) view.findViewById(R.id.showImage);
		m_Time = (TextView) view.findViewById(R.id.time);
		m_Name = (TextView) view.findViewById(R.id.name);
		m_Date = (TextView) view.findViewById(R.id.date);
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}
}
