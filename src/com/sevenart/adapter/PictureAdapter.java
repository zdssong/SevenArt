package com.sevenart.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sevenart.R;
import com.sevenart.models.Details.PictureDetails;
import com.sevenart.view.touchgallery.GalleryViewPager;
import com.sevenart.view.touchgallery.TouchImageView;

public class PictureAdapter extends PagerAdapter {
	//
	protected final List<PictureDetails> mResources;
	protected final Context mContext;
	protected int mCurrentPosition = -1;
	protected OnItemChangeListener mOnItemChangeListener;
	public OnItemClickListener mOnItemClickListener;

	public static final String FILE_HEADER = "file://";
	private View finalView;

	public PictureAdapter() {
		mResources = null;
		mContext = null;
	}

	public PictureAdapter(Context context, List<PictureDetails> resources) {
		this.mResources = resources;
		this.mContext = context;
		finalView = LayoutInflater.from(mContext).inflate(
				R.layout.picture_details, null);
	}
	
	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		super.setPrimaryItem(container, position, object);
		if (position == mResources.size()) {
			return;
		}
		if (mCurrentPosition == position)
			return;
		GalleryViewPager galleryContainer = ((GalleryViewPager) container);
		if (galleryContainer.mCurrentView != null)
			galleryContainer.mCurrentView.resetScale();

		mCurrentPosition = position;
		if (mOnItemChangeListener != null)
			mOnItemChangeListener.onItemChange(mCurrentPosition);

		((GalleryViewPager) container).mCurrentView = ((TouchImageView) object);
	}

	@Override
	public Object instantiateItem(ViewGroup collection, int position) {
		if (position == mResources.size()) {
			collection.addView(finalView, 0);
			return finalView;
		}
		final TouchImageView iv = new TouchImageView(mContext);
		iv.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));

		System.out.println(mResources.get(position).getImg());
		ImageLoader.getInstance().displayImage(mResources.get(position).getImg(), iv);

		final int _positionForOnClick = position;

		if (mOnItemClickListener != null) {
			System.out.println("mOnItemClickListener != null");
			iv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					System.out.println("iv.setOnClickListener");
					mOnItemClickListener
							.onItemChange(view, _positionForOnClick);
				}
			});
		}else{
			System.out.println("mOnItemClickListener == null");
		}

		collection.addView(iv, 0);
		return iv;
	}

	@Override
	public void destroyItem(ViewGroup collection, int position, Object view) {
		collection.removeView((View) view);
	}

	@Override
	public int getCount() {
		return mResources.size() + 1;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	public int getCurrentPosition() {
		return mCurrentPosition;
	}

	public View getFinalView() {
		return finalView;
	}

	public void setOnItemChangeListener(OnItemChangeListener listener) {
		mOnItemChangeListener = listener;
	}

	public static interface OnItemChangeListener {
		public void onItemChange(int currentPosition);
	}

	public static interface OnItemClickListener {
		public void onItemChange(View view, int position);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		mOnItemClickListener = listener;
	}

	
}
