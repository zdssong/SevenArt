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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ArtHeaderFragment extends Fragment implements OnClickListener {

	private DisplayImageOptions mOptions;
	private ImageLoadingListener mLoadingListener;

	private LayoutInflater mInflater;
	private View mMainView;
	private ImageView mImageView;

	private Commend mCommend;

	public ArtHeaderFragment(Commend commend) {
		this.mCommend = commend;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mInflater = LayoutInflater.from(ArtApplication.getArtApplication());
		mMainView = mInflater.inflate(
				R.layout.head_layout,
				(ViewGroup) getActivity().findViewById(
						R.layout.art_main_fragment), false);
		initDate();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup p = (ViewGroup) mMainView.getParent();
		if (p != null) {
			p.removeAllViewsInLayout();
		}
		return mMainView;
	}

	private void initDate() {
		mImageView = (ImageView) mMainView.findViewById(R.id.imageview);
		ImageLoader.getInstance()
				.displayImage(mCommend.getLitpic(), mImageView);
		mImageView.setOnClickListener(this);
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
			intent.putExtra(ArtContants.ZHAN_LAN_OR_PAI_MAI_AID, mCommend.getId());
			startActivity(intent);
		}
	}
}
