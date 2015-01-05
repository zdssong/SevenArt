package com.sevenart.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class ArtAutionFragmentAdapter extends FragmentPagerAdapter {

	private List<Fragment> m_FragmentList;

	public ArtAutionFragmentAdapter(FragmentManager fm,
			List<Fragment> fragmentList) {
		super(fm);
		this.m_FragmentList = fragmentList;
	}

	public ArtAutionFragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return m_FragmentList.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_FragmentList.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		return super.instantiateItem(container, position);
	}

}
