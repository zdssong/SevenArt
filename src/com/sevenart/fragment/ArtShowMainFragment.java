package com.sevenart.fragment;

import java.util.ArrayList;
import java.util.List;

import com.sevenart.R;
import com.sevenart.adapter.ArtShowFragmentAdapter;
import com.sevenart.application.ArtApplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class ArtShowMainFragment extends Fragment implements OnClickListener,
		OnPageChangeListener {

	private View m_MainView;
	private LayoutInflater m_Inflater;

	private List<Fragment> m_Fragments;
	private ArtshowFragment m_Fragment;

	private ViewPager m_ViewPager;
	private int mTag;

	public ArtShowMainFragment(int tag) {
		this.mTag = tag;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		m_Inflater = LayoutInflater.from(ArtApplication.getArtApplication());
		m_MainView = m_Inflater.inflate(R.layout.art_show_fragment,
				(ViewGroup) getActivity().findViewById(R.layout.activity_main),
				false);
		initView();
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

	private void initView() {

		m_ViewPager = (ViewPager) m_MainView.findViewById(R.id.viewpager);
		// m_WillShowLayout = (LinearLayout) m_MainView
		// .findViewById(R.id.will_show_layout);
		// m_OnShowLayout = (LinearLayout) m_MainView
		// .findViewById(R.id.showing_layout);
		//
		// m_OnShowLayout.setVisibility(View.VISIBLE);
		// m_WillShowLayout.setVisibility(View.GONE);

		m_Fragments = new ArrayList<Fragment>();

		for (int i = 0; i < 2; i++) {
			m_Fragment = new ArtshowFragment(i, mTag);
			m_Fragments.add(m_Fragment);
		}

		m_ViewPager.setAdapter(new ArtShowFragmentAdapter(
				getChildFragmentManager(), m_Fragments));

		// m_OnShowLayout.setOnClickListener(this);
		// m_WillShowLayout.setOnClickListener(this);
		m_ViewPager.setOnPageChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// switch (v.getId()) {
		// case R.id.will_show_layout:
		// m_WillShowLayout.setVisibility(View.GONE);
		// m_OnShowLayout.setVisibility(View.VISIBLE);
		// m_ViewPager.setCurrentItem(0);
		// break;
		// case R.id.showing_layout:
		// m_WillShowLayout.setVisibility(View.VISIBLE);
		// m_OnShowLayout.setVisibility(View.GONE);
		// m_ViewPager.setCurrentItem(1);
		// break;
		// }
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		// if (arg0 == 0) {
		// m_WillShowLayout.setVisibility(View.GONE);
		// m_OnShowLayout.setVisibility(View.VISIBLE);
		// }
		// if (arg0 == 1) {
		// m_WillShowLayout.setVisibility(View.VISIBLE);
		// m_OnShowLayout.setVisibility(View.GONE);
		// }
	}

}
