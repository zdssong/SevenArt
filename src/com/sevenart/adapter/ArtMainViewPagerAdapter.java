package com.sevenart.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

public class ArtMainViewPagerAdapter extends FragmentPagerAdapter {

	private FragmentManager fm;
	private List<Fragment> fragments;

	public ArtMainViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.fm = fm;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	public ArtMainViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
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

	public void setPagerItems(List<Fragment> items) {
		if (items != null) {
			for (int i = 0; i < fragments.size(); i++) {
				fm.beginTransaction().remove(fragments.get(i))
						.commitAllowingStateLoss();
			}
			fragments = items;
		}
	}
}
