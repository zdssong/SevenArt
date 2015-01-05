package com.sevenart.fragment;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sevenart.ArtDetailsActivity;
import com.sevenart.ArtShowTdcodeActivity;
import com.sevenart.R;
import com.sevenart.application.ArtApplication;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.PersonalPage.About;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutFragment extends Fragment implements OnClickListener {

	private LayoutInflater mInflater;
	private View mMainView;

	private TextView mTitleTextView;
	private TextView mContextTextView;
	private TextView mAddressTextView;
	private TextView mContacTextView;
	private TextView mEmailTextView;
	private ImageView mErweimaImageView;

	private About mAbout;

	public AboutFragment(About about) {
		this.mAbout = about;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mInflater = LayoutInflater.from(ArtApplication.getArtApplication());
		mMainView = mInflater.inflate(R.layout.about, (ViewGroup) getActivity()
				.findViewById(R.layout.wei_guang_wang), false);
		initWidget();
		initData();
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

	private void initWidget() {
		mTitleTextView = (TextView) mMainView.findViewById(R.id.title);
		mContextTextView = (TextView) mMainView.findViewById(R.id.context);
		mAddressTextView = (TextView) mMainView.findViewById(R.id.address);
		mContacTextView = (TextView) mMainView.findViewById(R.id.contact);
		mEmailTextView = (TextView) mMainView.findViewById(R.id.email);
		mErweimaImageView = (ImageView) mMainView.findViewById(R.id.image);
		mErweimaImageView.setOnClickListener(this);
	}

	private void initData() {
		mTitleTextView.setText(mAbout.getTitle());
		mContextTextView.setText(mAbout.getDescription());
		mAddressTextView.setText(mAbout.getAddress());
		mContacTextView.setText(mAbout.getTelephone());
		mEmailTextView.setText(mAbout.getEmail());
		ImageLoader.getInstance().displayImage(mAbout.getQrcode(),
				mErweimaImageView);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.putExtra(ArtContants.ER_WEI_MA, mAbout.getQrcode());
		intent.setClass(getActivity(), ArtShowTdcodeActivity.class);
		startActivity(intent);
	}

}
