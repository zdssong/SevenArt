package com.sevenart;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sevenart.adapter.AuctionDetailAdapter;
import com.sevenart.application.ArtApplication;
import com.sevenart.baseActicity.BaseActivity;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.Details;
import com.sevenart.models.Details.ShowAndAuctionDetails;
import com.sevenart.webServe.GetArtHttp;
public class ArtAuctionActivity extends BaseActivity implements
		OnItemClickListener, OnClickListener {

	private GetArtHttp mGetArtHttp;

	private TextView mTitleTextView;
	private TextView mTimeTextView;
	private TextView mPublishTimeTextView;
	private TextView mTitleTextView2;
	private TextView mAddressTextView;
	private TextView mContextTextView;
	private GridView mGridView;

	private AuctionDetailAdapter mAdapter;

	private LinearLayout mInitLayout;
	private RelativeLayout mContentLayout;
	private TextView mBack;

	// 传递过来的参数
	private String idString;

	private Details mDetails;
	private ShowAndAuctionDetails mAuctionDetails;
	private String[] wids;
	private String[] aids;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.detail_auction);

		idString = getIntent().getStringExtra(
				ArtContants.ZHAN_LAN_OR_PAI_MAI_AID);

		initView();
		initData();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if (ArtApplication.OpenNew) {
			Intent intent = new Intent();
			intent.setClass(ArtAuctionActivity.this, MainActivity.class);
			startActivity(intent);
			ArtApplication.OpenNew = false;
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			this.finish();
		} else {
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(ArtAuctionActivity.this, AuctionActivity.class);
		intent.putExtra(ArtContants.CURRENT_PAGER, position);
		intent.putExtra(ArtContants.PICTURES_WIDS, wids);
		intent.putExtra(ArtContants.PICTURES_AIDS, aids);
		startActivity(intent);
	}

	private void initView() {
		mGridView = (GridView) findViewById(R.id.gridView);
		mTitleTextView = (TextView) findViewById(R.id.title);
		mTitleTextView2 = (TextView) findViewById(R.id.title2);
		mTimeTextView = (TextView) findViewById(R.id.time);
		mPublishTimeTextView = (TextView) findViewById(R.id.publish_time);
		mAddressTextView = (TextView) findViewById(R.id.address);
		mContextTextView = (TextView) findViewById(R.id.context);
		mInitLayout = (LinearLayout) findViewById(R.id.initLayout);
		mContentLayout = (RelativeLayout) findViewById(R.id.contentlayout);
		mBack = (TextView) findViewById(R.id.back);
		mBack.setOnClickListener(this);
		mGridView.setOnItemClickListener(this);
	}

	private void initData() {
		mGetArtHttp = new GetArtHttp();
		mDetails = new Details();
		mAuctionDetails = mDetails.getShowDetails();
		new getAuctionTask().execute();
	}

	private void setData() {
		mTitleTextView.setText(mAuctionDetails.getTitle());
		mPublishTimeTextView.setText(mAuctionDetails.getStartTime());
		mTitleTextView2.setText(mAuctionDetails.getTitle());
		mAddressTextView.setText(mAuctionDetails.getAddress());
		mTimeTextView.setText(mAuctionDetails.getStartTime() + "-"
				+ mAuctionDetails.getEndTime());
		mContextTextView.setText(mAuctionDetails.getDescription());
		mAdapter = new AuctionDetailAdapter(mAuctionDetails.getPictures1(),
				this);
		mGridView.setAdapter(mAdapter);

		wids = new String[mAuctionDetails.getPictures1().size()];
		aids = new String[mAuctionDetails.getPictures1().size()];
		for (int i = 0; i < mAuctionDetails.getPictures1().size(); i++) {
			wids[i] = mAuctionDetails.getPictures1().get(i).getwId();
			aids[i] = mAuctionDetails.getPictures1().get(i).getaId();
			mAuctionDetails.getId();
		}
	}

	private class getAuctionTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			mGetArtHttp.getAuctionDetails(idString);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			mAuctionDetails = mGetArtHttp.getM_AuctionDetail();
			setData();
			mInitLayout.setVisibility(View.GONE);
			mContentLayout.setVisibility(View.VISIBLE);
			super.onPostExecute(result);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		onBackPressed();
	}

}
