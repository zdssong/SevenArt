package com.sevenart.receiver;

import org.json.JSONException;
import org.json.JSONObject;

import com.sevenart.ArtAuctionActivity;
import com.sevenart.ArtDetailsActivity;
import com.sevenart.ArtShowActivity;
import com.sevenart.PictureActivity;
import com.sevenart.application.ArtApplication;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.Details;
import com.sevenart.models.Details.OrdinaryEssayDetails;
import com.sevenart.models.Details.PictureSetDetails;
import com.sevenart.models.Details.ShowAndAuctionDetails;
import com.sevenart.webServe.GetArtHttp;

import cn.jpush.android.api.JPushInterface;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class ArtPushReceiver extends BroadcastReceiver {

	private GetArtHttp getArtHttp;

	private PictureSetDetails mPictureSetDetails;
	private ShowAndAuctionDetails mShowAndAuctionDetails;
	private OrdinaryEssayDetails essayDetail;
	private Details details;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (JPushInterface.ACTION_NOTIFICATION_OPENED
				.equals(intent.getAction())) {
			initDate();
			Bundle bundle = intent.getExtras();
			String type = bundle.getString(JPushInterface.EXTRA_EXTRA);
			String aid = null;
			String channel = null;
			try {
				JSONObject object = new JSONObject(type);
				aid = object.getString("aid");
				channel = object.getString("channel");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(type);
			new getReceive(aid, channel).execute();
		}
	}

	private void initDate() {
		getArtHttp = new GetArtHttp();
		details = new Details();
		mPictureSetDetails = details.getPictureSetDetails();
		mShowAndAuctionDetails = details.getShowDetails();
		essayDetail = details.getEssayDetails();
	}

	private class getReceive extends AsyncTask<Void, Void, Void> {

		private String aid;
		private String channel;

		public getReceive(String aid, String channel) {
			// TODO Auto-generated constructor stub
			this.aid = aid;
			this.channel = channel;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if (channel.equals(ArtContants.ESSAY_CHANNEL)
					|| channel == ArtContants.ESSAY_CHANNEL) {
				getArtHttp.getEssayDetails(aid);
			}
			if (channel == ArtContants.AUCTION_CHANNEL
					|| channel.equals(ArtContants.AUCTION_CHANNEL)) {
				getArtHttp.getAuctionDetails(aid);
			}
			if (channel == ArtContants.SHOW_CHANNEL
					|| channel.equals(ArtContants.SHOW_CHANNEL)) {
				getArtHttp.getShowDetails(aid);
			}
			if (channel == ArtContants.PICTURE_CHANNEL
					|| channel.equals(ArtContants.PICTURE_CHANNEL)) {
				getArtHttp.getPictureSetDetails(aid);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			ArtApplication.OpenNew = true;
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			if (channel.equals(ArtContants.ESSAY_CHANNEL)
					|| channel == ArtContants.ESSAY_CHANNEL) {
				essayDetail = getArtHttp.getM_EssayDetail();
				if (essayDetail.getChannel() != null
						|| essayDetail.getChannel() != ""
						|| !essayDetail.getChannel().equals("")) {
					intent.putExtra(ArtContants.ID, essayDetail.getId());
					intent.putExtra(ArtContants.TITLE, essayDetail.getTitle());
					intent.putExtra(ArtContants.CHANNEL,
							essayDetail.getChannel());
					intent.putExtra(ArtContants.CHANNEL_NAME,
							essayDetail.getChannelName());
					intent.setClass(ArtApplication.getArtApplication(),
							ArtDetailsActivity.class);
					ArtApplication.getArtApplication().startActivity(intent);
				}
			}
			if (channel == ArtContants.AUCTION_CHANNEL
					|| channel.equals(ArtContants.AUCTION_CHANNEL)) {
				mShowAndAuctionDetails = getArtHttp.getM_AuctionDetail();
				if (mShowAndAuctionDetails.getChannel() != null
						|| mShowAndAuctionDetails.getChannel() != ""
						|| !mShowAndAuctionDetails.getChannel().equals("")) {
					intent.setClass(ArtApplication.getArtApplication(),
							ArtAuctionActivity.class);
					intent.putExtra(ArtContants.ZHAN_LAN_OR_PAI_MAI_AID,
							mShowAndAuctionDetails.getId());
					ArtApplication.getArtApplication().startActivity(intent);
				}
			}
			if (channel == ArtContants.SHOW_CHANNEL
					|| channel.equals(ArtContants.SHOW_CHANNEL)) {
				mShowAndAuctionDetails = getArtHttp.getM_ShowDetail();
				if (mShowAndAuctionDetails.getChannel() != null
						|| mShowAndAuctionDetails.getChannel() != ""
						|| !mShowAndAuctionDetails.getChannel().equals("")) {
					intent.setClass(ArtApplication.getArtApplication(),
							ArtShowActivity.class);
					intent.putExtra(ArtContants.ID,
							mShowAndAuctionDetails.getId());
					intent.putExtra(ArtContants.TITLE,
							mShowAndAuctionDetails.getTitle());
					intent.putExtra(ArtContants.CHANNEL,
							mShowAndAuctionDetails.getChannel());
					intent.putExtra(ArtContants.CHANNEL_NAME,
							mShowAndAuctionDetails.getChannelName());
					ArtApplication.getArtApplication().startActivity(intent);
				}
			}
			if (channel == ArtContants.PICTURE_CHANNEL
					|| channel.equals(ArtContants.PICTURE_CHANNEL)) {
				mPictureSetDetails = getArtHttp.getM_PictureSetDetail();
				if (mPictureSetDetails.getChannel() != null
						|| mPictureSetDetails.getChannel() != ""
						|| !mPictureSetDetails.getChannel().equals("")) {
					intent.setClass(ArtApplication.getArtApplication(),
							PictureActivity.class);
					intent.putExtra(ArtContants.ID, mPictureSetDetails.getId());
					intent.putExtra(ArtContants.TITLE,
							mPictureSetDetails.getTitle());
					intent.putExtra(ArtContants.CHANNEL,
							mPictureSetDetails.getChannel());
					intent.putExtra(ArtContants.CHANNEL_NAME,
							mPictureSetDetails.getChannelName());
					ArtApplication.getArtApplication().startActivity(intent);
				}
			}
			super.onPostExecute(result);
		}

	}

}
