package com.sevenart.fragment;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sevenart.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ArtOpusFragment extends Fragment {

	private View mMainView;
	private LayoutInflater mInflater;

	private ImageView mImageView;
	private String mUriString;

	public ArtOpusFragment(String uri) {
		this.mUriString = uri;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mInflater = LayoutInflater.from(getActivity());
		mMainView = mInflater.inflate(
				R.layout.opus_item,
				(ViewGroup) getActivity().findViewById(
						R.layout.activity_auction), false);

		initView();
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

	private void initView() {
		mImageView = (ImageView) mMainView.findViewById(R.id.opusImage);
		ImageLoader.getInstance().displayImage(mUriString, mImageView);
	}
}
