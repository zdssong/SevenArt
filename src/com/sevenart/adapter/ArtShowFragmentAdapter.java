package com.sevenart.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class ArtShowFragmentAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;
	private FragmentManager fm;

	public ArtShowFragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.fm = fm;
	}

	public ArtShowFragmentAdapter(FragmentManager fm,
			List<Fragment> fragments) {
		super(fm);
		this.fm = fm;
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragments.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		return super.instantiateItem(container, position);
	}

}
