package com.sevenart;

import java.util.ArrayList;
import java.util.List;

import com.sevenart.adapter.ArtAutionFragmentAdapter;
import com.sevenart.contants.ArtContants;
import com.sevenart.fragment.ArtAuctionFrament;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

public class AuctionActivity extends FragmentActivity implements
		OnClickListener, OnPageChangeListener {
	// ²à»¬²Ëµ¥Ïî
	// private SlidingMenu m_SlidingMenu;

	private ViewPager mViewPager;
	private List<Fragment> mFragmentList;
	private ArtAuctionFrament mAuctionFrament;
	private ArtAutionFragmentAdapter mAdapter;

	private ImageView mPre;
	private ImageView mNext;
	private EditText mAuctionNum;
	private ImageView mMore;
	private ImageView mMenu;

	private int currentPager;
	private String[] wids;
	private String[] aids;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_auction);
		currentPager = getIntent().getIntExtra(ArtContants.CURRENT_PAGER, 0);
		wids = getIntent().getStringArrayExtra(ArtContants.PICTURES_WIDS);
		aids = getIntent().getStringArrayExtra(ArtContants.PICTURES_AIDS);
		initView();
		initData();
		initListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.pre:
			currentPager--;
			mViewPager.setCurrentItem(currentPager);
			break;
		case R.id.next:
			currentPager++;
			mViewPager.setCurrentItem(currentPager);
			break;
		case R.id.guidance_one:
			// if (m_SlidingMenu.isMenuShowing())
			// m_SlidingMenu.showContent();
			// else
			// m_SlidingMenu.showMenu();
			break;
		case R.id.guidance_three:
			// Intent intent_channel = new Intent(getApplicationContext(),
			// ChannelActivity.class);
			// startActivityForResult(intent_channel,
			// ArtContants.CHANNEL_REQUEST);
			// overridePendingTransition(R.anim.slide_in_right,
			// R.anim.slide_out_left);
			this.finish();
			break;
		}
	}

	private void initListener() {
		mPre.setOnClickListener(this);
		mNext.setOnClickListener(this);
		mMenu.setOnClickListener(this);
		mMore.setOnClickListener(this);
		mViewPager.setOnPageChangeListener(this);
	}

	private void initData() {
	}

	private void initView() {
		mPre = (ImageView) findViewById(R.id.pre);
		mNext = (ImageView) findViewById(R.id.next);
		mAuctionNum = (EditText) findViewById(R.id.edittext);
		mMenu = (ImageView) findViewById(R.id.guidance_one);
		mMore = (ImageView) findViewById(R.id.guidance_three);

		mViewPager = (ViewPager) findViewById(R.id.viewpager);

		mFragmentList = new ArrayList<Fragment>();
		for (int i = 0; i < wids.length; i++) {
			mAuctionFrament = new ArtAuctionFrament(aids[i], wids[i]);
			mFragmentList.add(mAuctionFrament);
		}
		mAdapter = new ArtAutionFragmentAdapter(getSupportFragmentManager(),
				mFragmentList);
		mViewPager.setAdapter(mAdapter);
		mAuctionNum.setText((currentPager + 1) + "");
		mViewPager.setCurrentItem(currentPager);
	}

	@Override
	public void onPageScrollStateChanged(int position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		currentPager = position;
		mAuctionNum.setText((currentPager + 1) + "");
	}
}