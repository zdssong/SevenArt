package com.sevenart.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.trinea.android.common.view.DropDownListView;
import cn.trinea.android.common.view.DropDownListView.OnDropDownListener;

import com.sevenart.ArtAuctionActivity;
import com.sevenart.ArtDetailsActivity;
import com.sevenart.ArtShowActivity;
import com.sevenart.PictureActivity;
import com.sevenart.R;
import com.sevenart.adapter.ArtMainListAdapter;
import com.sevenart.application.ArtApplication;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.Information;
import com.sevenart.webServe.GetArtHttp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ArtGuanWangFragment extends Fragment implements
		OnItemClickListener, OnClickListener, OnDropDownListener {

	private GetArtHttp mGetArtHttp;
	private List<Information> mInformations;

	private LayoutInflater mInflater;
	private View mMainView;

	private DropDownListView mListView;
	private ArtMainListAdapter mListAdapter;
	private LinearLayout mInitLayout;
	private TextView failTextView;

	private boolean isFrist;

	private String mMid;
	private String mMtypeid;

	public ArtGuanWangFragment(String mid, String mtypeid) {
		this.mMid = mid;
		this.mMtypeid = mtypeid;
		isFrist = true;
		mGetArtHttp = new GetArtHttp();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mInflater = LayoutInflater.from(ArtApplication.getArtApplication());
		mMainView = mInflater
				.inflate(R.layout.imageview_item, (ViewGroup) getActivity()
						.findViewById(R.layout.wei_guang_wang), false);

		initWidget();
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
		mInitLayout = (LinearLayout) mMainView.findViewById(R.id.initLayout);
		failTextView = (TextView) mMainView.findViewById(R.id.fail);
		mListView = (DropDownListView) mMainView.findViewById(R.id.listView);

		mListView.setOnItemClickListener(this);
		mListView.setOnDropDownListener(this);
		mListView.setOnBottomListener(this);
		new MemberTask().executeOnExecutor((ExecutorService) Executors
				.newFixedThreadPool(5));
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		if (mInformations.get(position).getChannel() == ArtContants.ESSAY_CHANNEL
				|| mInformations.get(position).getChannel()
						.equals(ArtContants.ESSAY_CHANNEL)) {
			intent.putExtra(ArtContants.ID, mInformations.get(position).getId());
			intent.putExtra(ArtContants.TITLE, mInformations.get(position)
					.getTitle());
			intent.putExtra(ArtContants.CHANNEL, mInformations.get(position)
					.getChannel());
			intent.putExtra(ArtContants.CHANNEL_NAME,
					mInformations.get(position).getChannelName());
			intent.setClass(getActivity(), ArtDetailsActivity.class);
			startActivity(intent);
		}
		if (mInformations.get(position).getChannel() == ArtContants.PICTURE_CHANNEL
				|| mInformations.get(position).getChannel()
						.equals(ArtContants.PICTURE_CHANNEL)) {
			intent.setClass(getActivity(), PictureActivity.class);
			intent.putExtra(ArtContants.ID, mInformations.get(position).getId());
			intent.putExtra(ArtContants.TITLE, mInformations.get(position)
					.getTitle());
			intent.putExtra(ArtContants.CHANNEL, mInformations.get(position)
					.getChannel());
			intent.putExtra(ArtContants.CHANNEL_NAME,
					mInformations.get(position).getChannelName());
			startActivity(intent);
		}
		if (mInformations.get(position).getChannel() == ArtContants.MEMBER_CHANNEL
				|| mInformations.get(position).getChannel()
						.equals(ArtContants.MEMBER_CHANNEL)) {

		}
		if (mInformations.get(position).getChannel() == ArtContants.SHOW_CHANNEL
				|| mInformations.get(position).getChannel()
						.equals(ArtContants.SHOW_CHANNEL)) {
			intent.setClass(getActivity(), ArtShowActivity.class);
			intent.putExtra(ArtContants.ID, mInformations.get(position).getId());
			intent.putExtra(ArtContants.TITLE, mInformations.get(position)
					.getTitle());
			intent.putExtra(ArtContants.CHANNEL, mInformations.get(position)
					.getChannel());
			intent.putExtra(ArtContants.CHANNEL_NAME,
					mInformations.get(position).getChannelName());
			startActivity(intent);

		}
		if (mInformations.get(position).getChannel() == ArtContants.AUCTION_CHANNEL
				|| mInformations.get(position).getChannel()
						.equals(ArtContants.AUCTION_CHANNEL)) {
			intent.setClass(getActivity(), ArtAuctionActivity.class);
			intent.putExtra(ArtContants.ZHAN_LAN_OR_PAI_MAI_AID, mInformations
					.get(position).getId());
			startActivity(intent);

		}

	}

	private class MemberTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			mGetArtHttp.getMemberInformationHttp(mMid, mMtypeid);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			mInformations = new ArrayList<Information>();
			mInformations = mGetArtHttp.getM_InformationList();

			mListView.setDropDownStyle(false);
			mListView.setOnBottomStyle(false);
			if (mInformations.size() != 0) {
				mInitLayout.setVisibility(View.GONE);
				mListView.setVisibility(View.VISIBLE);
				if (isFrist) {
					if (mInformations.size() <= 3) {
						mListView.setDropDownStyle(false);
					}
					mListAdapter = new ArtMainListAdapter(mInformations,
							ArtApplication.getArtApplication());
					mListView.setAdapter(mListAdapter);
					isFrist = false;
				} else {
					mListAdapter.setM_Informations(mInformations);
					mListAdapter.notifyDataSetChanged();
					Toast toast = Toast.makeText(getActivity(), "Ë¢ÐÂ³É¹¦",
							Toast.LENGTH_SHORT);
					toast.show();
				}
			}
			if (mInformations.size() == 0) {
				failTextView.setVisibility(View.VISIBLE);
				mInitLayout.setVisibility(View.GONE);
			}
			super.onPostExecute(result);
		}
	}

	@Override
	public void onDropDown() {
		// TODO Auto-generated method stub
		new MemberTask().execute();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
