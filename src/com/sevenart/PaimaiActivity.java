package com.sevenart;

import java.util.ArrayList;
import java.util.List;

import com.sevenart.adapter.ArtMyAuctionAdapter;
import com.sevenart.application.ArtApplication;
import com.sevenart.baseActicity.BaseActivity;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.MyAuction;
import com.sevenart.view.MyGridView;
import com.sevenart.webServe.PostArtHttp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PaimaiActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener {
	private MyGridView gridView;

	private PostArtHttp mPostArtHttp;
	private List<MyAuction> mMyAuctions;
	private TextView mTextView;
	private RelativeLayout mLayout;

	private String tag;// 用来标记是哪个功能
	private String[] wids;
	private String[] aids;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.carecare);
		Intent intent = getIntent();
		tag = intent.getStringExtra(ArtContants.ACTIVITY_TAG);

		initView();
		initData();
		initListener();
	}

	private void initData() {
		mPostArtHttp = new PostArtHttp();
		mMyAuctions = new ArrayList<MyAuction>();

		if (ArtApplication.mLogin != null) {
			mLayout.setVisibility(View.GONE);
			gridView.setVisibility(View.VISIBLE);
			new MyAcutionTask().execute();
		} else {

		}
	}

	private void initView() {
		gridView = (MyGridView) findViewById(R.id.carecare_gridview);
		mTextView = (TextView) findViewById(R.id.textview);
		mLayout = (RelativeLayout) findViewById(R.id.layout);
	}

	private void initListener() {
		mTextView.setOnClickListener(this);
		gridView.setOnItemClickListener(this);
	}

	private void setData() {
		ArtMyAuctionAdapter adapter = new ArtMyAuctionAdapter(mMyAuctions);
		gridView.setAdapter(adapter);
		wids = new String[mMyAuctions.size()];
		aids = new String[mMyAuctions.size()];
		for (int i = 0; i < mMyAuctions.size(); i++) {
			wids[i] = mMyAuctions.get(i).getWid();
			aids[i] = mMyAuctions.get(i).getAid();
		}
	}

	private class MyAcutionTask extends AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if (tag == ArtContants.LING_XIAN
					|| tag.equals(ArtContants.LING_XIAN)) {
				mMyAuctions = mPostArtHttp.postMyAuction("lead");
			}
			if (tag == ArtContants.MEI_CHU_JIA
					|| tag.equals(ArtContants.LING_XIAN)) {
				mMyAuctions = mPostArtHttp.postMyAuction("collect");
			}
			if (tag == ArtContants.LUO_HOU || tag.equals(ArtContants.LING_XIAN)) {
				mMyAuctions = mPostArtHttp.postMyAuction("behind");
			}
			if (tag == ArtContants.CHENG_JIAO
					|| tag.equals(ArtContants.LING_XIAN)) {
				mMyAuctions = mPostArtHttp.postMyAuction("deal");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			setData();
			super.onPostExecute(result);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(PaimaiActivity.this, LoginActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		this.finish();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(PaimaiActivity.this, AuctionActivity.class);
		intent.putExtra(ArtContants.CURRENT_PAGER, position);
		intent.putExtra(ArtContants.PICTURES_WIDS, wids);
		intent.putExtra(ArtContants.PICTURES_AIDS, aids);
		startActivity(intent);
	}
}
