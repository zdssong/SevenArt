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
import com.sevenart.application.ArtApplication;
import com.sevenart.models.Comment;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CommentListAdapter extends BaseAdapter {

	private DisplayImageOptions mOptions;
	private ImageLoadingListener mLoadingListener;

	private List<Comment> m_CommentList;
	private LayoutInflater m_Inflater;

	private ImageView m_HeadImageView;
	private TextView m_NickNameTextView;
	private ImageView m_GoodImageView;
	private TextView m_GoodNumTextView;
	private TextView m_CommentContextTextView;

	public CommentListAdapter(List<Comment> commentList, Context context) {
		this.m_CommentList = commentList;
		m_Inflater = LayoutInflater.from(context);

		mOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.nopicture)
				.showImageForEmptyUri(R.drawable.nopicture)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();
		mLoadingListener = new LoadingListener();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_CommentList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return m_CommentList.get(position);
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
			view = m_Inflater.inflate(R.layout.comment_item, null);
		}
		InitView(view);
		ImageLoader.getInstance().displayImage(
				m_CommentList.get(position).getmFace(), m_HeadImageView,
				mOptions, mLoadingListener);
		m_NickNameTextView.setText(m_CommentList.get(position).getUserName());
		m_GoodImageView.setBackgroundResource(R.drawable.nopicture);
		m_GoodNumTextView.setText(m_CommentList.get(position).getGood());
		m_CommentContextTextView.setText(m_CommentList.get(position).getMsg());
		return view;
	}

	private void InitView(View view) {
		m_CommentContextTextView = (TextView) view
				.findViewById(R.id.commentContext);
		m_HeadImageView = (ImageView) view.findViewById(R.id.headImage);
		m_NickNameTextView = (TextView) view.findViewById(R.id.nickName);
		m_GoodImageView = (ImageView) view.findViewById(R.id.good);
		m_GoodNumTextView = (TextView) view.findViewById(R.id.goodNum);
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
