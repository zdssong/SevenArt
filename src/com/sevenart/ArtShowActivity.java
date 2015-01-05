package com.sevenart;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sevenart.adapter.CommentListAdapter;
import com.sevenart.adapter.NewsListAdapter;
import com.sevenart.application.ArtApplication;
import com.sevenart.artasynctask.ArtAddCareTask;
import com.sevenart.artasynctask.ArtCollectionTask;
import com.sevenart.baseActicity.BaseActivity;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.Details.ShowAndAuctionDetails;
import com.sevenart.models.Comment;
import com.sevenart.models.Information;
import com.sevenart.models.Member;
import com.sevenart.view.ArtListView;
import com.sevenart.webServe.GetArtHttp;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

public class ArtShowActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener {

	private GetArtHttp mGetArtHttp;
	private ShowAndAuctionDetails mShowDetail;

	private ScrollView mScrollView;
	private LinearLayout mInitLayout;

	private TextView mTitleTextView;
	private TextView mTimeTextView;
	private TextView mPublishTimeTextView;
	private TextView mCommentTextView;
	private TextView mZhanguangTextView;
	private TextView mAddressTextView;
	private TextView mContextTextView;
	private ImageView mShowPictureImageView;

	//
	private TextView mCommentNum;
	private ImageView mNewImage;
	private TextView mWriter;
	private ImageView mNew2;
	private ImageView mPictureDetail;
	private String mAdString;
	private ImageView mAdView;
	private ArtListView mAboutNews;
	private ArtListView mAboutComment;
	private LinearLayout mCareLayout;
	private LinearLayout mErweimaLayout;

	private CommentListAdapter mCommentAdapter;
	private NewsListAdapter mNewAdapter;
	private List<String> newList;
	private List<Comment> mComments;
	private List<Member> mMembers;
	private List<Information> mInformations;

	private TextView mBack;
	private TextView mShare;
	private TextView mCollection;
	private TextView mComment;
	private TextView mErWeiMa;
	private float start_y = 0;
	private boolean isDisappear = false;

	// 传过来的参数
	private String idString;
	private String titleString;
	private String channelString;
	private String channelnameString;

	final UMSocialService mController = UMServiceFactory
			.getUMSocialService("com.umeng.share");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.details_show);

		Intent intent = getIntent();
		titleString = intent.getStringExtra(ArtContants.TITLE);
		channelString = intent.getStringExtra(ArtContants.CHANNEL);
		channelnameString = intent.getStringExtra(ArtContants.CHANNEL_NAME);
		idString = intent.getStringExtra(ArtContants.ID);

		initView();
		initData();
		initListener();
		setheadData();

		removePlatform();
		addQQPlatform();
		addWXPlatform();
	}

	@SuppressWarnings("deprecation")
	private void addWXPlatform() {
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(this, ArtContants.WXAPPID);
		wxHandler.addToSocialSDK();
		// 支持微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(this, ArtContants.WXAPPID);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
	}

	private void addQQPlatform() {
		// 添加QQ支持, 并且设置QQ分享内容的target url

		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this,
				"100424468", "c7394704798a158208a74ab60104f0ba");
		qZoneSsoHandler.addToSocialSDK();

		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, "100424468",
				"c7394704798a158208a74ab60104f0ba");
		qqSsoHandler.setTargetUrl("http://www.umeng.com");
		qqSsoHandler.addToSocialSDK();
	}

	private void removePlatform() {
		mController.getConfig().removePlatform(SHARE_MEDIA.SINA,
				SHARE_MEDIA.TENCENT);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if (ArtApplication.OpenNew) {
			Intent intent = new Intent();
			intent.setClass(ArtShowActivity.this, MainActivity.class);
			startActivity(intent);
			ArtApplication.OpenNew = false;
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			this.finish();
		} else {
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		}
	}

	private void initView() {
		mTitleTextView = (TextView) findViewById(R.id.title);
		mZhanguangTextView = (TextView) findViewById(R.id.zhangguang);
		mTimeTextView = (TextView) findViewById(R.id.time);
		mPublishTimeTextView = (TextView) findViewById(R.id.publish_time);
		mAddressTextView = (TextView) findViewById(R.id.dizhi);
		mContextTextView = (TextView) findViewById(R.id.context);
		mShowPictureImageView = (ImageView) findViewById(R.id.pic_details);

		mNewImage = (ImageView) findViewById(R.id.newsImage);
		mCommentNum = (TextView) findViewById(R.id.comment_count);
		mWriter = (TextView) findViewById(R.id.title1);
		mPictureDetail = (ImageView) findViewById(R.id.pic_details);
		mNew2 = (ImageView) findViewById(R.id.new2);
		mAdView = (ImageView) findViewById(R.id.adImage);
		mCareLayout = (LinearLayout) findViewById(R.id.care);
		mErweimaLayout = (LinearLayout) findViewById(R.id.erweimalayout);
		mAboutNews = (ArtListView) findViewById(R.id.newsListLayout);
		mAboutComment = (ArtListView) findViewById(R.id.commentListLayout);

		mBack = (TextView) findViewById(R.id.back);
		mShare = (TextView) findViewById(R.id.share);
		mCollection = (TextView) findViewById(R.id.collection);
		mComment = (TextView) findViewById(R.id.action_comment_count);
		mErWeiMa = (TextView) findViewById(R.id.erweima);

		mInitLayout = (LinearLayout) findViewById(R.id.initLayout);
		mScrollView = (ScrollView) findViewById(R.id.scrollView);
		mScrollView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					start_y = event.getY();
				}
				if (event.getAction() == MotionEvent.ACTION_MOVE
						&& start_y != 0) {
					if (event.getY() - start_y < 0 && !isDisappear) {
						buttonDisappearAnimation();
						isDisappear = true;
						return false;
					}
					if (event.getY() - start_y > 0 && isDisappear) {
						buttonAppearAnimation();
						isDisappear = false;
					}
				}
				return false;
			}
		});
	}

	private void initCommentList() {
		mCommentAdapter = new CommentListAdapter(mComments, this);
		mAboutComment.setAdapter(mCommentAdapter);
	}

	private void initNewList() {
		if (channelString == "-2" || "-2".equals(channelString)) {
			// if (mMembers.size() >= 2) {
			// for (int i = 0; i < 2; i++) {
			// newList.add(mMembers.get(i).getUname());
			// }
			// } else {
			// for (int i = 0; i < mMembers.size(); i++) {
			// newList.add(mMembers.get(i).getUname());
			// }
			// }
		} else {
			if (mInformations.size() >= 2) {
				for (int i = 0; i < 2; i++) {
					newList.add(mInformations.get(i).getTitle());
				}
			} else {
				for (int i = 0; i < mInformations.size(); i++) {
					newList.add(mInformations.get(i).getTitle());
				}
			}
		}
		mNewAdapter = new NewsListAdapter(newList, this);
		mAboutNews.setAdapter(mNewAdapter);
		mAboutNews.setOnItemClickListener(this);
	}

	private void initData() {
		mGetArtHttp = new GetArtHttp();
		mComments = new ArrayList<Comment>();
		// mShowDetail = new ShowAndAuctionDetails();
		mMembers = new ArrayList<Member>();
		mInformations = new ArrayList<Information>();
		newList = new ArrayList<String>();

		new getShowDetail().execute();
	}

	private void setheadData() {
		mTitleTextView.setText(titleString);
	}

	private void setData() {
		mPublishTimeTextView.setText(mShowDetail.getPubDate());
		mCommentNum.setText(mShowDetail.getFc());

		mTimeTextView.setText(mShowDetail.getStartTime() + " - "
				+ mShowDetail.getEndTime());
		mZhanguangTextView.setText(mShowDetail.getTitle());
		mAddressTextView.setText(mShowDetail.getAddress());
		mContextTextView.setText(mShowDetail.getDescription());
		mWriter.setText(mShowDetail.getWriter());

	}

	private void setPicture() {
		ImageLoader.getInstance()
				.displayImage(mShowDetail.getFace(), mNewImage);
		ImageLoader.getInstance().displayImage(mShowDetail.getLitpic(),
				mShowPictureImageView);
		mShowPictureImageView.setBackgroundResource(R.color.white);
		ImageLoader.getInstance().displayImage(mAdString, mAdView);
		mInitLayout.setVisibility(View.GONE);
		mScrollView.setVisibility(View.VISIBLE);
	}

	private void initListener() {
		mBack.setOnClickListener(this);
		mCareLayout.setOnClickListener(this);
		mErweimaLayout.setOnClickListener(this);
		mShare.setOnClickListener(this);
		mCollection.setOnClickListener(this);
		mComment.setOnClickListener(this);
		mPictureDetail.setOnClickListener(this);
		mNewImage.setOnClickListener(this);
	}

	private void buttonAppearAnimation() {
		Animation backInAnimation = AnimationUtils.loadAnimation(this,
				R.anim.slide_in_left);
		backInAnimation.setFillAfter(true);
		backInAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mBack.setClickable(true);
			}
		});
		Animation otherInAnimation = AnimationUtils.loadAnimation(this,
				R.anim.slide_in_right);
		otherInAnimation.setFillAfter(true);
		otherInAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mCollection.setClickable(true);
				mComment.setClickable(true);
				mShare.setClickable(true);
				mErWeiMa.setClickable(true);
			}
		});
		mBack.setAnimation(backInAnimation);
		mCollection.setAnimation(otherInAnimation);
		mComment.setAnimation(otherInAnimation);
		mShare.setAnimation(otherInAnimation);
		mErWeiMa.setAnimation(otherInAnimation);
	}

	private void buttonDisappearAnimation() {
		Animation backOutAnimation = AnimationUtils.loadAnimation(this,
				R.anim.slide_out_left);
		backOutAnimation.setFillAfter(true);
		backOutAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mBack.setClickable(false);
			}
		});
		Animation otherOutAnimation = AnimationUtils.loadAnimation(this,
				R.anim.slide_out_right);
		otherOutAnimation.setFillAfter(true);
		otherOutAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mCollection.setClickable(true);
				mComment.setClickable(true);
				mShare.setClickable(true);
				mErWeiMa.setClickable(true);
			}
		});
		mBack.setAnimation(backOutAnimation);
		mCollection.setAnimation(otherOutAnimation);
		mComment.setAnimation(otherOutAnimation);
		mShare.setAnimation(otherOutAnimation);
		mErWeiMa.setAnimation(otherOutAnimation);

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (view.getId()) {
		case R.id.newsImage:
			intent.putExtra(ArtContants.NAME, mShowDetail.getWriter());
			intent.putExtra(ArtContants.MID, mShowDetail.getmId());
			intent.setClass(this, ArtMemberActivity.class);
			startActivity(intent);
			break;
		case R.id.back:
			onBackPressed();
			break;
		case R.id.share:
			mController.setShareContent(mShowDetail.getTitle() + "\n"
					+ mShowDetail.getDescription() + "\n"
					+ mShowDetail.getShareUrl());
			mController.openShare(this, false);
			break;
		case R.id.collection:
			new ArtCollectionTask(mShowDetail.getId()).execute();
			break;
		case R.id.action_comment_count:
			intent.putExtra(ArtContants.COMMENT_ID, mShowDetail.getId());
			intent.setClass(ArtShowActivity.this, CommentActivity.class);
			startActivity(intent);
			break;
		case R.id.erweima:
			break;
		case R.id.care:
			new ArtAddCareTask(mShowDetail.getmId()).execute();
			break;
		case R.id.erweimalayout:
			intent.putExtra(ArtContants.ER_WEI_MA, mShowDetail.getQrcode());
			intent.setClass(ArtShowActivity.this, ArtShowTdcodeActivity.class);
			startActivity(intent);
			break;
		case R.id.pic_details:
			intent.setClass(ArtShowActivity.this, PictureActivity.class);
			intent.putExtra(ArtContants.ID, mShowDetail.getId());
			intent.putExtra(ArtContants.TITLE, mShowDetail.getTitle());
			intent.putExtra(ArtContants.CHANNEL, mShowDetail.getChannel());
			intent.putExtra(ArtContants.CHANNEL_NAME,
					mShowDetail.getChannelName());
			startActivity(intent);
			break;
		}
	}

	private class getShowDetail extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			mGetArtHttp.getShowDetails(idString);
			mGetArtHttp.getCommentsHttp(idString);
			mGetArtHttp.getNewsHttp(channelString, channelnameString);
			mAdString = mGetArtHttp.getAdHttp(ArtContants.AD_ID);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			mComments = mGetArtHttp.getmComments();
			mShowDetail = mGetArtHttp.getM_ShowDetail();
			if (channelString == "-2" || "-2".equals(channelString)) {
				mMembers = mGetArtHttp.getM_Members();
			} else {
				mInformations = mGetArtHttp.getM_InformationList();
			}
			initCommentList();
			initNewList();
			setData();
			setPicture();

			super.onPostExecute(result);
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		String channel;
		if (channelString == "-2" || "-2".equals(channelString)) {
			// Intent intent = new Intent();
			// if (mMembers.get(position)..equals(ArtContants.ESSAY_CHANNEL)
			// || channel == ArtContants.ESSAY_CHANNEL) {
			// essayDetail = getArtHttp.getM_EssayDetail();
			// if (essayDetail.getChannel() != null
			// || essayDetail.getChannel() != ""
			// || !essayDetail.getChannel().equals("")) {
			// intent.putExtra(ArtContants.ID, essayDetail.getId());
			// intent.putExtra(ArtContants.TITLE, essayDetail.getTitle());
			// intent.putExtra(ArtContants.CHANNEL,
			// essayDetail.getChannel());
			// intent.putExtra(ArtContants.CHANNEL_NAME,
			// essayDetail.getChannelName());
			// intent.setClass(ArtApplication.getArtApplication(),
			// ArtDetailsActivity.class);
			// ArtApplication.getArtApplication().startActivity(intent);
			// }
			// }
			// if (channel == ArtContants.AUCTION_CHANNEL
			// || channel.equals(ArtContants.AUCTION_CHANNEL)) {
			// mShowAndAuctionDetails = getArtHttp.getM_AuctionDetail();
			// if (mShowAndAuctionDetails.getChannel() != null
			// || mShowAndAuctionDetails.getChannel() != ""
			// || !mShowAndAuctionDetails.getChannel().equals("")) {
			// intent.setClass(ArtApplication.getArtApplication(),
			// ArtAuctionActivity.class);
			// intent.putExtra(ArtContants.ZHAN_LAN_OR_PAI_MAI_AID,
			// mShowAndAuctionDetails.getId());
			// ArtApplication.getArtApplication().startActivity(intent);
			// }
			// }
			// if (channel == ArtContants.SHOW_CHANNEL
			// || channel.equals(ArtContants.SHOW_CHANNEL)) {
			// mShowAndAuctionDetails = getArtHttp.getM_ShowDetail();
			// if (mShowAndAuctionDetails.getChannel() != null
			// || mShowAndAuctionDetails.getChannel() != ""
			// || !mShowAndAuctionDetails.getChannel().equals("")) {
			// intent.setClass(ArtApplication.getArtApplication(),
			// ArtShowActivity.class);
			// intent.putExtra(ArtContants.ID,
			// mShowAndAuctionDetails.getId());
			// intent.putExtra(ArtContants.TITLE,
			// mShowAndAuctionDetails.getTitle());
			// intent.putExtra(ArtContants.CHANNEL,
			// mShowAndAuctionDetails.getChannel());
			// intent.putExtra(ArtContants.CHANNEL_NAME,
			// mShowAndAuctionDetails.getChannelName());
			// ArtApplication.getArtApplication().startActivity(intent);
			// }
			// }
			// if (channel == ArtContants.PICTURE_CHANNEL
			// || channel.equals(ArtContants.PICTURE_CHANNEL)) {
			// mPictureSetDetails = getArtHttp.getM_PictureSetDetail();
			// if (mPictureSetDetails.getChannel() != null
			// || mPictureSetDetails.getChannel() != ""
			// || !mPictureSetDetails.getChannel().equals("")) {
			// intent.setClass(ArtApplication.getArtApplication(),
			// PictureActivity.class);
			// intent.putExtra(ArtContants.ID, mPictureSetDetails.getId());
			// intent.putExtra(ArtContants.TITLE,
			// mPictureSetDetails.getTitle());
			// intent.putExtra(ArtContants.CHANNEL,
			// mPictureSetDetails.getChannel());
			// intent.putExtra(ArtContants.CHANNEL_NAME,
			// mPictureSetDetails.getChannelName());
			// ArtApplication.getArtApplication().startActivity(intent);
			// }
			// }
		} else {
			channel = mInformations.get(position).getChannel();
			Intent intent = new Intent();
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			if (channel.equals(ArtContants.ESSAY_CHANNEL)
					|| channel == ArtContants.ESSAY_CHANNEL) {
				intent.putExtra(ArtContants.ID, mInformations.get(position)
						.getId());
				intent.putExtra(ArtContants.TITLE, mInformations.get(position)
						.getTitle());
				intent.putExtra(ArtContants.CHANNEL, mInformations
						.get(position).getChannel());
				intent.putExtra(ArtContants.CHANNEL_NAME,
						mInformations.get(position).getChannelName());
				intent.setClass(ArtApplication.getArtApplication(),
						ArtDetailsActivity.class);
				ArtApplication.getArtApplication().startActivity(intent);
			}
			if (channel == ArtContants.AUCTION_CHANNEL
					|| channel.equals(ArtContants.AUCTION_CHANNEL)) {
				intent.setClass(ArtApplication.getArtApplication(),
						ArtAuctionActivity.class);
				intent.putExtra(ArtContants.ZHAN_LAN_OR_PAI_MAI_AID,
						mInformations.get(position).getId());
				ArtApplication.getArtApplication().startActivity(intent);
			}
			if (channel == ArtContants.SHOW_CHANNEL
					|| channel.equals(ArtContants.SHOW_CHANNEL)) {
				intent.setClass(ArtApplication.getArtApplication(),
						ArtShowActivity.class);
				intent.putExtra(ArtContants.ID, mInformations.get(position)
						.getId());
				intent.putExtra(ArtContants.TITLE, mInformations.get(position)
						.getTitle());
				intent.putExtra(ArtContants.CHANNEL, mInformations
						.get(position).getChannel());
				intent.putExtra(ArtContants.CHANNEL_NAME,
						mInformations.get(position).getChannelName());
				ArtApplication.getArtApplication().startActivity(intent);
			}
			if (channel == ArtContants.PICTURE_CHANNEL
					|| channel.equals(ArtContants.PICTURE_CHANNEL)) {
				intent.setClass(ArtApplication.getArtApplication(),
						PictureActivity.class);
				intent.putExtra(ArtContants.ID, mInformations.get(position)
						.getId());
				intent.putExtra(ArtContants.TITLE, mInformations.get(position)
						.getTitle());
				intent.putExtra(ArtContants.CHANNEL, mInformations
						.get(position).getChannel());
				intent.putExtra(ArtContants.CHANNEL_NAME,
						mInformations.get(position).getChannelName());
				ArtApplication.getArtApplication().startActivity(intent);
			}
			this.finish();
		}

	}
}
