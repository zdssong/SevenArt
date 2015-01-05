package com.sevenart.fragment;

import java.util.ArrayList;
import java.util.List;

import com.sevenart.R;
import com.sevenart.adapter.ArtViewPagerAdapter;
import com.sevenart.application.ArtApplication;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.Commend;
import com.sevenart.models.PersonalPage;
import com.sevenart.models.PersonalPage.About;
import com.sevenart.models.PersonalPage.Cmtype;
import com.sevenart.tools.BaseTools;
import com.sevenart.view.ArtTextView;
import com.sevenart.view.ColumnHorizontalScrollView;
import com.sevenart.webServe.GetArtHttp;
import com.sevenart.webServe.GetArtJson;

import android.R.integer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ArtWeiGuangWangFragment extends Fragment implements
		OnPageChangeListener, OnClickListener {

	private GetArtHttp mGetArtHttp;

	private List<Commend> mCommends;

	private ColumnHorizontalScrollView mColumnHorizontalScrollView;
	private RelativeLayout mTitleGroudLayout;
	private TextView mNothing;
	private LinearLayout mTitleContentLayout;
	private ImageView mShadeLeft;
	private ImageView mShadeRight;
	private TextView mBack;
	private int mScreenWidth;
	private int mItemWidth;
	private int mColumnSelectIndex;
	private List<Cmtype> mCmtypes;
	private About mAbout;

	private LayoutInflater m_Inflater;
	private View mMainView;

	private ViewPager m_PictureViewPager;
	private ViewPager m_ViewPager;
	private ArtViewPagerAdapter m_Adapter;

	private List<Fragment> m_ArtPictureFragments;
	private List<Fragment> m_ArtGuanWangFragments;

	private LinearLayout mInitLayout;
	private TextView failTextView;
	private LinearLayout mContent;

	private int mTag;
	private String mid;

	public ArtWeiGuangWangFragment(int tag,String mid) {
		this.mTag = tag;
		this.mid = mid;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		m_Inflater = LayoutInflater.from(ArtApplication.getArtApplication());
		mMainView = m_Inflater.inflate(R.layout.wei_guang_wang,
				(ViewGroup) getActivity().findViewById(R.layout.activity_main),
				false);
		mMainView.findViewById(R.id.titlelayout).setVisibility(View.GONE);

		mScreenWidth = BaseTools.getWindowsWidth(getActivity());
		mItemWidth = (mScreenWidth / 7) * 2;

		initWidget();
		initListener();
		initData();

		new getCmtypeTask().execute();
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
		m_PictureViewPager = (ViewPager) mMainView.findViewById(R.id.viewPager);
		m_ViewPager = (ViewPager) mMainView.findViewById(R.id.viewPager1);

		mShadeRight = (ImageView) mMainView.findViewById(R.id.shade_right);
		mShadeLeft = (ImageView) mMainView.findViewById(R.id.shade_left);
		mTitleGroudLayout = (RelativeLayout) mMainView
				.findViewById(R.id.rl_column);
		mTitleContentLayout = (LinearLayout) mMainView
				.findViewById(R.id.mRadioGroup_content);
		mColumnHorizontalScrollView = (ColumnHorizontalScrollView) mMainView
				.findViewById(R.id.mColumnHorizontalScrollView);
		mNothing = (TextView) mMainView.findViewById(R.id.nothing);
		mBack = (TextView) mMainView.findViewById(R.id.back);
		mInitLayout = (LinearLayout) mMainView.findViewById(R.id.initLayout);
		failTextView = (TextView) mMainView.findViewById(R.id.fail);
		mContent = (LinearLayout) mMainView.findViewById(R.id.content);
		mBack.setVisibility(View.GONE);

	}

	private void initData() {
		m_ArtPictureFragments = new ArrayList<Fragment>();
		m_ArtGuanWangFragments = new ArrayList<Fragment>();
		mCmtypes = new ArrayList<PersonalPage.Cmtype>();
		mGetArtHttp = new GetArtHttp();
	}

	private void initListener() {
		m_ViewPager.setOnPageChangeListener(this);
		failTextView.setOnClickListener(this);
	}

	private void initPictureViewPager() {
		for (int i = 0; i < mCommends.size(); i++) {
			ArtPictureFragment artPictureFragment = new ArtPictureFragment(
					mCommends.get(i).getLitpic(), mCommends.get(i).getTitle(),
					i + 1, mCommends.size(), mCommends.get(i));
			m_ArtPictureFragments.add(artPictureFragment);
		}
		m_Adapter = new ArtViewPagerAdapter(getChildFragmentManager(),
				m_ArtPictureFragments);
		m_PictureViewPager.setAdapter(m_Adapter);
	}

	private void initViewPager() {
		for (int i = 0; i < mCmtypes.size(); i++) {
			if (i == mCmtypes.size() - 1) {
				AboutFragment about = new AboutFragment(mAbout);
				m_ArtGuanWangFragments.add(about);
			} else {
				ArtGuanWangFragment artGuanWangFragment = new ArtGuanWangFragment(
						mCmtypes.get(i).getMid(), mCmtypes.get(i).getMtypeId());
				m_ArtGuanWangFragments.add(artGuanWangFragment);
			}
		}
		m_Adapter = new ArtViewPagerAdapter(getChildFragmentManager(),
				m_ArtGuanWangFragments);
		m_ViewPager.setAdapter(m_Adapter);
	}

	// 选择的Column里面的Tab
	private void selectTab(int tab_postion) {
		mColumnSelectIndex = tab_postion;
		for (int i = 0; i < mTitleContentLayout.getChildCount(); i++) {
			View checkView = mTitleContentLayout.getChildAt(tab_postion);
			int k = checkView.getMeasuredWidth();
			int l = checkView.getLeft();
			int i2 = l + k / 2 - mScreenWidth / 2;
			mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
		}
		// 判断是否选中
		for (int j = 0; j < mTitleContentLayout.getChildCount(); j++) {
			View checkView = mTitleContentLayout.getChildAt(j);
			if (j == tab_postion) {
				checkView.setSelected(true);
				checkView.setBackgroundResource(R.color.guang_wang_slecter);
			} else {
				checkView.setSelected(false);
				checkView.setBackgroundResource(R.color.guang_wang);
			}
		}
	}

	// 初始化Column栏目项
	private void initTabColumn() {
		mTitleContentLayout.removeAllViews();
		int count = mCmtypes.size();
		mColumnHorizontalScrollView.setParam(getActivity(), mScreenWidth,
				mTitleContentLayout, mShadeLeft, mShadeRight, mNothing,
				mTitleGroudLayout);
		for (int i = 0; i < count; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			params.width = mItemWidth;
			ArtTextView columnTextView = new ArtTextView(getActivity());
			columnTextView.setTextAppearance(getActivity(),
					R.style.top_category_scroll_view_item_text);
			columnTextView.setBackgroundResource(R.color.guang_wang);
			columnTextView.setGravity(Gravity.CENTER);
			columnTextView.setPadding(5, 5, 5, 5);
			columnTextView.setId(i);
			columnTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
			columnTextView.setText(mCmtypes.get(i).getMtypeName());
			columnTextView.setTextColor(getResources().getColorStateList(
					R.color.wei_guan_wang_seleter));
			if (mColumnSelectIndex == i) {
				columnTextView.setSelected(true);
				columnTextView
						.setBackgroundResource(R.color.guang_wang_slecter);
			}
			columnTextView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					for (int i = 0; i < mTitleContentLayout.getChildCount(); i++) {
						View localView = mTitleContentLayout.getChildAt(i);
						if (localView != v) {
							localView.setSelected(false);
							localView.setBackgroundResource(R.color.guang_wang);
						} else {
							localView.setSelected(true);
							localView
									.setBackgroundResource(R.color.guang_wang_slecter);
							m_ViewPager.setCurrentItem(i);
						}
					}
				}
			});
			mTitleContentLayout.addView(columnTextView, i, params);
		}
	}

	private class getCmtypeTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if (mTag == ArtContants.WEI_GUAN_WANG)
				mGetArtHttp.getCmtype(mid);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			mCmtypes = mGetArtHttp.getM_Cmtypes();
			mCommends = mGetArtHttp.getM_CommendList();
			mAbout = mGetArtHttp.getM_Abouts();
			if (mCmtypes.size() != 0) {
				mInitLayout.setVisibility(View.GONE);
				mContent.setVisibility(View.VISIBLE);
				initTabColumn();
				initPictureViewPager();
				initViewPager();
			}
			if (mCmtypes.size() == 0) {
				failTextView.setVisibility(View.VISIBLE);
				mInitLayout.setVisibility(View.GONE);
			}
			super.onPostExecute(result);
		}
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
	public void onPageSelected(int index) {
		// TODO Auto-generated method stub
		selectTab(index);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		new getCmtypeTask().execute();
	}

}
