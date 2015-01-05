package com.sevenart;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sevenart.adapter.ArtViewPagerAdapter;
import com.sevenart.contants.ArtContants;
import com.sevenart.fragment.AboutFragment;
import com.sevenart.fragment.ArtGuanWangFragment;
import com.sevenart.fragment.ArtPictureFragment;
import com.sevenart.models.Commend;
import com.sevenart.models.PersonalPage;
import com.sevenart.models.PersonalPage.About;
import com.sevenart.models.PersonalPage.Cmtype;
import com.sevenart.tools.BaseTools;
import com.sevenart.view.ArtTextView;
import com.sevenart.view.ColumnHorizontalScrollView;
import com.sevenart.webServe.GetArtHttp;

public class ArtMemberActivity extends FragmentActivity implements
		OnPageChangeListener, OnClickListener {

	private ImageView mHead;
	private ImageView mMore;
	private TextView mName;

	private GetArtHttp mGetArtHttp;
	private List<Commend> mCommends;

	private ColumnHorizontalScrollView mColumnHorizontalScrollView;
	private RelativeLayout mTitleGroudLayout;
	private TextView mNothing;
	private LinearLayout mTitleContentLayout;
	private ImageView mShadeLeft;
	private ImageView mShadeRight;
	private int mScreenWidth;
	private int mItemWidth;
	private int mColumnSelectIndex;
	private List<Cmtype> mCmtypes;
	private About mAbout;

	private ViewPager mPictureViewPager;
	private ViewPager mViewPager;
	private ArtViewPagerAdapter mAdapter;
	private TextView mBack;

	private List<Fragment> mArtPictureFragments;
	private List<Fragment> mArtGuanWangFragments;

	private String mid;
	private String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wei_guang_wang);

		mid = getIntent().getStringExtra(ArtContants.MID);
		name = getIntent().getStringExtra(ArtContants.NAME);

		mScreenWidth = BaseTools.getWindowsWidth(this);
		mItemWidth = (mScreenWidth / 7) * 2;

		initWidget();
		initListener();
		initData();

		mName.setText(name);

		new getCmtypeTask().execute();
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
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	private void initWidget() {
		mHead = (ImageView) findViewById(R.id.top_head);
		mMore = (ImageView) findViewById(R.id.more);
		mName = (TextView) findViewById(R.id.name);
		mBack = (TextView) findViewById(R.id.back);

		mPictureViewPager = (ViewPager) findViewById(R.id.viewPager);
		mViewPager = (ViewPager) findViewById(R.id.viewPager1);

		mShadeRight = (ImageView) findViewById(R.id.shade_right);
		mShadeLeft = (ImageView) findViewById(R.id.shade_left);
		mTitleGroudLayout = (RelativeLayout) findViewById(R.id.rl_column);
		mTitleContentLayout = (LinearLayout) findViewById(R.id.mRadioGroup_content);
		mColumnHorizontalScrollView = (ColumnHorizontalScrollView) findViewById(R.id.mColumnHorizontalScrollView);
		mNothing = (TextView) findViewById(R.id.nothing);
	}

	private void initData() {
		mArtPictureFragments = new ArrayList<Fragment>();
		mArtGuanWangFragments = new ArrayList<Fragment>();
		mCmtypes = new ArrayList<PersonalPage.Cmtype>();
		mGetArtHttp = new GetArtHttp();
	}

	private void initListener() {
		mViewPager.setOnPageChangeListener(this);
		mHead.setOnClickListener(this);
		mMore.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}

	private void initPictureViewPager() {
		for (int i = 0; i < mCommends.size(); i++) {
			ArtPictureFragment artPictureFragment = new ArtPictureFragment(
					mCommends.get(i).getLitpic(), mCommends.get(i).getTitle(),
					i + 1, mCommends.size(), mCommends.get(i));
			mArtPictureFragments.add(artPictureFragment);
		}
		mAdapter = new ArtViewPagerAdapter(getSupportFragmentManager(),
				mArtPictureFragments);
		mPictureViewPager.setAdapter(mAdapter);
	}

	private void initViewPager() {
		for (int i = 0; i < mCmtypes.size(); i++) {
			if (i == mCmtypes.size() - 1) {
				AboutFragment about = new AboutFragment(mAbout);
				mArtGuanWangFragments.add(about);
			} else {
				ArtGuanWangFragment artGuanWangFragment = new ArtGuanWangFragment(
						mCmtypes.get(i).getMid(), mCmtypes.get(i).getMtypeId());
				mArtGuanWangFragments.add(artGuanWangFragment);
			}
		}
		mAdapter = new ArtViewPagerAdapter(getSupportFragmentManager(),
				mArtGuanWangFragments);
		mViewPager.setAdapter(mAdapter);
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
		mColumnHorizontalScrollView.setParam(this, mScreenWidth,
				mTitleContentLayout, mShadeLeft, mShadeRight, mNothing,
				mTitleGroudLayout);
		for (int i = 0; i < count; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			params.width = mItemWidth;
			ArtTextView columnTextView = new ArtTextView(this);
			columnTextView.setTextAppearance(this,
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
							mViewPager.setCurrentItem(i);
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
			mGetArtHttp.getCmtype(mid);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			mCmtypes = mGetArtHttp.getM_Cmtypes();
			mCommends = mGetArtHttp.getM_CommendList();
			mAbout = mGetArtHttp.getM_Abouts();
			initTabColumn();

			initPictureViewPager();
			initViewPager();
			super.onPostExecute(result);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.top_head:
			break;
		case R.id.more:
			this.finish();
			break;
		case R.id.back:
			onBackPressed();
			break;
		}
	}

}
