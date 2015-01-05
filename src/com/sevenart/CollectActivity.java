package com.sevenart;

import java.util.ArrayList;
import java.util.List;

import com.sevenart.adapter.ArtMainListAdapter;
import com.sevenart.application.ArtApplication;
import com.sevenart.baseActicity.BaseActivity;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.Information;
import com.sevenart.webServe.GetArtHttp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CollectActivity extends BaseActivity implements
		OnItemClickListener, OnClickListener {
	private GetArtHttp mGetArtHttp;

	private List<Information> mInformations;

	private ListView mListView;
	private ArtMainListAdapter mAdapter;
	private TextView mTextView;
	private RelativeLayout mLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection_activity);

		mGetArtHttp = new GetArtHttp();
		mInformations = new ArrayList<Information>();

		mListView = (ListView) findViewById(R.id.listView);
		mListView.setOnItemClickListener(this);
		mTextView = (TextView) findViewById(R.id.textview);
		mLayout = (RelativeLayout) findViewById(R.id.layout);
		mTextView.setOnClickListener(this);
		if (ArtApplication.mLogin != null) {
			mLayout.setVisibility(View.GONE);
			mListView.setVisibility(View.VISIBLE);
			new CollectionTask().execute();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		if (mInformations.get(position).getChannel() == ArtContants.ESSAY_CHANNEL
				|| mInformations.get(position).getChannel()
						.equals(ArtContants.ESSAY_CHANNEL)) {
			intent.putExtra(ArtContants.INGORMATION,
					mInformations.get(position));
			intent.setClass(CollectActivity.this, ArtDetailsActivity.class);
			startActivity(intent);
		}
		if (mInformations.get(position).getChannel() == ArtContants.PICTURE_CHANNEL
				|| mInformations.get(position).getChannel()
						.equals(ArtContants.PICTURE_CHANNEL)) {
			intent.setClass(CollectActivity.this, PictureActivity.class);
			intent.putExtra(ArtContants.INGORMATION,
					mInformations.get(position));
			startActivity(intent);
		}
		if (mInformations.get(position).getChannel() == ArtContants.MEMBER_CHANNEL
				|| mInformations.get(position).getChannel()
						.equals(ArtContants.MEMBER_CHANNEL)) {

		}
		if (mInformations.get(position).getChannel() == ArtContants.SHOW_CHANNEL
				|| mInformations.get(position).getChannel()
						.equals(ArtContants.SHOW_CHANNEL)) {
			intent.setClass(CollectActivity.this, ArtShowActivity.class);
			intent.putExtra(ArtContants.ZHAN_LAN_OR_PAI_MAI_AID,
					mInformations.get(position));
			startActivity(intent);

		}
		if (mInformations.get(position).getChannel() == ArtContants.AUCTION_CHANNEL
				|| mInformations.get(position).getChannel()
						.equals(ArtContants.AUCTION_CHANNEL)) {
			intent.setClass(CollectActivity.this, ArtAuctionActivity.class);
			intent.putExtra(ArtContants.ZHAN_LAN_OR_PAI_MAI_AID,
					mInformations.get(position));
			startActivity(intent);

		}
	}

	private class CollectionTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			mGetArtHttp.getCollection();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			mInformations = mGetArtHttp.getM_InformationList();
			mAdapter = new ArtMainListAdapter(mInformations,
					ArtApplication.getArtApplication());
			mListView.setAdapter(mAdapter);
			super.onPostExecute(result);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(CollectActivity.this, LoginActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		this.finish();
	}
}
