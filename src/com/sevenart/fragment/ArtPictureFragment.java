package com.sevenart.fragment;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sevenart.ArtAuctionActivity;
import com.sevenart.ArtDetailsActivity;
import com.sevenart.ArtMemberActivity;
import com.sevenart.ArtShowActivity;
import com.sevenart.PictureActivity;
import com.sevenart.R;
import com.sevenart.application.ArtApplication;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.Commend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ArtPictureFragment extends Fragment implements OnClickListener {

	private DisplayImageOptions mOptions;
	private ImageLoadingListener mLoadingListener;

	private LayoutInflater m_Inflater;
	private View m_MainView;

	private ImageView m_ImageView;
	private TextView mTitle;
	private TextView mNum;
	private String title;
	private int nowNum;
	private int total;
	private Commend mCommend;

	private String mImageUrl;

	public ArtPictureFragment(String url, String title, int num, int total,
			Commend commend) {
		this.mImageUrl = url;
		this.title = title;
		this.nowNum = num;
		this.total = total;
		this.mCommend = commend;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		m_Inflater = LayoutInflater.from(ArtApplication.getArtApplication());
		m_MainView = m_Inflater.inflate(R.layout.head_layout,
				(ViewGroup) getActivity().findViewById(R.layout.activity_main),
				false);

		mLoadingListener = new ImageLoadingListener();

		initWidget();
		initData();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup p = (ViewGroup) m_MainView.getParent();
		if (p != null) {
			p.removeAllViewsInLayout();
		}
		return m_MainView;
	}

	private void initWidget() {
		m_ImageView = (ImageView) m_MainView.findViewById(R.id.imageview);
		mTitle = (TextView) m_MainView.findViewById(R.id.title);
		mNum = (TextView) m_MainView.findViewById(R.id.num);
		m_ImageView.setOnClickListener(this);
		ImageLoader.getInstance().displayImage(mImageUrl, m_ImageView,
				mOptions, mLoadingListener);
	}

	private void initData() {
		mTitle.setText(title);
		mNum.setText(nowNum + "/" + total);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		if (mCommend.getChannel() == ArtContants.ESSAY_CHANNEL
				|| mCommend.getChannel().equals(ArtContants.ESSAY_CHANNEL)) {
			intent.putExtra(ArtContants.ID, mCommend.getId());
			intent.putExtra(ArtContants.TITLE, mCommend.getTitle());
			intent.putExtra(ArtContants.CHANNEL, mCommend.getChannel());
			intent.putExtra(ArtContants.CHANNEL_NAME, mCommend.getChannelName());
			intent.setClass(getActivity(), ArtDetailsActivity.class);
			startActivity(intent);
		}
		if (mCommend.getChannel() == ArtContants.PICTURE_CHANNEL
				|| mCommend.getChannel().equals(ArtContants.PICTURE_CHANNEL)) {
			intent.setClass(getActivity(), PictureActivity.class);
			intent.putExtra(ArtContants.ID, mCommend.getId());
			intent.putExtra(ArtContants.TITLE, mCommend.getTitle());
			intent.putExtra(ArtContants.CHANNEL, mCommend.getChannel());
			intent.putExtra(ArtContants.CHANNEL_NAME, mCommend.getChannelName());
			startActivity(intent);
		}
		if (mCommend.getChannel() == ArtContants.MEMBER_CHANNEL
				|| mCommend.getChannel().equals(ArtContants.MEMBER_CHANNEL)) {
			intent.setClass(getActivity(), ArtMemberActivity.class);

		}
		if (mCommend.getChannel() == ArtContants.SHOW_CHANNEL
				|| mCommend.getChannel().equals(ArtContants.SHOW_CHANNEL)) {
			intent.setClass(getActivity(), ArtShowActivity.class);
			intent.putExtra(ArtContants.ID, mCommend.getId());
			intent.putExtra(ArtContants.TITLE, mCommend.getTitle());
			intent.putExtra(ArtContants.CHANNEL, mCommend.getChannel());
			intent.putExtra(ArtContants.CHANNEL_NAME, mCommend.getChannelName());
			startActivity(intent);
		}
		if (mCommend.getChannel() == ArtContants.AUCTION_CHANNEL
				|| mCommend.getChannel().equals(ArtContants.AUCTION_CHANNEL)) {
			intent.setClass(getActivity(), ArtAuctionActivity.class);
			intent.putExtra(ArtContants.ZHAN_LAN_OR_PAI_MAI_AID,
					mCommend.getId());
			startActivity(intent);
		}
	}

	private static class ImageLoadingListener extends
			SimpleImageLoadingListener {

		static final List<String> displayImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			// TODO Auto-generated method stub
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean isFristDisplay = !displayImages.contains(imageUri);
				if (isFristDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayImages.add(imageUri);
				}
			}
		}

	}
}
