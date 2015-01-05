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
import com.sevenart.tools.SortModel;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class ArtGridViewAdapter extends BaseAdapter implements SectionIndexer {

	private List<SortModel> m_NameList;
	private LayoutInflater m_Inflater;

	private TextView m_TextView;
	private ImageView m_ImageView;

	private ImageLoadingListener loadingListener = new AnimateFirstDisplayListener();
	private DisplayImageOptions m_Options;

	public ArtGridViewAdapter(Context context, List<SortModel> nameList) {
		this.m_NameList = nameList;
		this.m_Inflater = LayoutInflater.from(context);

		m_Options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.nopicture)
				.showImageForEmptyUri(R.drawable.nopicture)
				.showImageOnFail(R.drawable.nopicture).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(0)).build();
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
			view = m_Inflater.inflate(R.layout.grid_item, null);
		}
		initView(view);
		m_TextView.setText(m_NameList.get(position).getName());
		ImageLoader.getInstance().displayImage(
				m_NameList.get(position).getImageUrl(), m_ImageView, m_Options,
				loadingListener);
		return view;
	}

	private void initView(View view) {
		m_TextView = (TextView) view.findViewById(R.id.name);
		m_ImageView = (ImageView) view.findViewById(R.id.gridImage);
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

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPositionForSection(int section) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getCount(); i++) {
			String sortStr = m_NameList.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}

	@Override
	public int getSectionForPosition(int position) {
		// TODO Auto-generated method stub
		return m_NameList.get(position).getSortLetters().charAt(0);
	}

}
