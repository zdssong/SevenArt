package com.sevenart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sevenart.adapter.CommentListAdapter;
import com.sevenart.adapter.NewsListAdapter;
import com.sevenart.application.ArtApplication;
import com.sevenart.artasynctask.ArtAddCareTask;
import com.sevenart.artasynctask.ArtCollectionTask;
import com.sevenart.baseActicity.BaseActivity;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.Comment;
import com.sevenart.models.Details;
import com.sevenart.models.Details.PictureDetails;
import com.sevenart.models.Details.PictureSetDetails;
import com.sevenart.models.Information;
import com.sevenart.models.Member;
import com.sevenart.view.ArtListView;
import com.sevenart.view.touchgallery.GalleryViewPager;
import com.sevenart.webServe.GetArtHttp;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

public class PictureActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener {

	private ScrollView mScrollView;
	private LinearLayout mInitLayout;

	private TextView mCollection;
	private TextView mComment;
	private TextView mBack;
	private TextView mShare;
	private TextView mCommentNum;
	private float start_y = 0;
	private boolean isDisappear = false;

	private ImageView mNewImage;
	private TextView mTitleTextView;
	private ImageView mNew2;
	private String mAdString;
	private ImageView mAdView;
	private ArtListView mAboutNews;
	private ArtListView mAboutComment;
	private LinearLayout mCareLayout;
	private LinearLayout mErweimaLayout;

	private CommentListAdapter mCommentAdapter;
	private NewsListAdapter mNewAdapter;

	private List<Comment> mComments;
	private PictureSetDetails mPictureSetDetail;
	private List<String> newList;
	private List<Member> mMembers;
	private List<Information> mInformations;

	private DisplayImageOptions mOptions;
	private ImageLoadingListener mLoadingListener;

	private GetArtHttp mGetArtHttp;

	//
	private boolean isFull = true;
	private GalleryViewPager viewer;
	private View titleBar;
	private View bottomBar;
	private com.sevenart.adapter.PictureAdapter adapter;

	private TextView countText;
	private TextView title;
	private TextView content;
	private TextView countTextBottom;
	private TextView titleComment;

	// 传过来的参数
	private String idString;
	private String titleString;
	private String channelString;
	private String channelnameString;

	// private ArrayList<String> items;
	private int position;

	final UMSocialService mController = UMServiceFactory
			.getUMSocialService("com.umeng.share");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture_activity);

		Intent intent = getIntent();
		position = intent.getIntExtra("position", 0);
		titleString = intent.getStringExtra(ArtContants.TITLE);
		channelString = intent.getStringExtra(ArtContants.CHANNEL);
		channelnameString = intent.getStringExtra(ArtContants.CHANNEL_NAME);
		idString = intent.getStringExtra(ArtContants.ID);
		initListener();
		initData();

		removePlatform();
		addQQPlatform();
		addWXPlatform();

		new getAboutNewTask().execute();
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
		// mController.openShare(this, false);
	}

	private void removePlatform() {
		mController.getConfig().removePlatform(SHARE_MEDIA.SINA,
				SHARE_MEDIA.TENCENT);
	}

	private void initViewer(final List<PictureDetails> items, int position) {
		if (items == null || position < 0) {
			Toast.makeText(this, "查看失败", Toast.LENGTH_LONG).show();
			finish();
			return;
		}
		mCommentNum = (TextView) findViewById(R.id.bottom_btn_1);
		mCommentNum.setText("(" + mPictureSetDetail.getFc() + ")");
		titleBar = findViewById(R.id.title_bar);
		bottomBar = findViewById(R.id.bottom_bar);
		viewer = (GalleryViewPager) findViewById(R.id.viewer);
		countText = (TextView) findViewById(R.id.count_text);
		countText.setText((position + 1) + "/" + items.size());
		title = (TextView) findViewById(R.id.title);
		content = (TextView) findViewById(R.id.content);
		countTextBottom = (TextView) findViewById(R.id.count_text_bottom);
		countTextBottom.setText((position + 1) + "/" + items.size());
		content.setText(items.get(0).getText());
		titleComment = (TextView) findViewById(R.id.title_comment);
		// Test
		titleComment.setText((position + 1) + "");
		title.setText(titleString);
		this.position = position;

		viewer.setOffscreenPageLimit(3);
		adapter = new com.sevenart.adapter.PictureAdapter(this, items);

		countText.setVisibility(View.GONE);
		titleBar.setVisibility(View.VISIBLE);
		bottomBar.setVisibility(View.VISIBLE);
		isFull = false;

		adapter.setOnItemClickListener(new com.sevenart.adapter.PictureAdapter.OnItemClickListener() {

			@Override
			public void onItemChange(View view, int position) {
				// TODO Auto-generated method stub
				if (isFull) {
					countText.setVisibility(View.GONE);
					titleBar.setVisibility(View.VISIBLE);
					bottomBar.setVisibility(View.VISIBLE);
					isFull = false;
				} else {
					titleBar.setVisibility(View.GONE);
					bottomBar.setVisibility(View.GONE);
					countText.setVisibility(View.VISIBLE);
					isFull = true;
				}
			}
		});
		viewer.setAdapter(adapter);
		viewer.setCurrentItem(position);
		viewer.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				if (position >= items.size()) {
					return;
				}
				countText.setText((position + 1) + "/" + items.size());
				countTextBottom.setText("[" + (position + 1) + "/"
						+ items.size() + "]");
				title.setText(titleString);
				if (mPictureSetDetail != null)
					if (mPictureSetDetail.getPictures().size() >= position
							&& mPictureSetDetail.getPictures().size() > 0)
						content.setText(items.get(position).getText());
				// Test
				titleComment.setText((position + 1) + "");
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub
				if (position == items.size() - 1) {
					titleBar.scrollTo(positionOffsetPixels, 0);
					countText.scrollTo(positionOffsetPixels, 0);
					bottomBar.scrollTo(positionOffsetPixels, 0);
				}
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void initListener() {
		findViewById(R.id.bottom_btn_4).setOnClickListener(this);
		findViewById(R.id.bottom_btn_2).setOnClickListener(this);
		findViewById(R.id.bottom_btn_3).setOnClickListener(this);
		findViewById(R.id.bottom_btn_back).setOnClickListener(this);
	}

	private void initFinalView() {
		View finalView = adapter.getFinalView();

		mNewImage = (ImageView) finalView.findViewById(R.id.newsImage);
		mTitleTextView = (TextView) finalView.findViewById(R.id.title1);
		mNew2 = (ImageView) finalView.findViewById(R.id.new2);
		mAdView = (ImageView) finalView.findViewById(R.id.adImage);
		mAboutNews = (ArtListView) finalView.findViewById(R.id.newsListLayout);
		mAboutComment = (ArtListView) finalView
				.findViewById(R.id.commentListLayout);

		mErweimaLayout = (LinearLayout) finalView
				.findViewById(R.id.erweimalayout);
		mErweimaLayout.setOnClickListener(this);
		mCareLayout = (LinearLayout) finalView.findViewById(R.id.care);
		mCareLayout.setOnClickListener(this);

		mBack = (TextView) finalView.findViewById(R.id.back);
		mCollection = (TextView) finalView.findViewById(R.id.collection);
		mShare = (TextView) finalView.findViewById(R.id.share);
		mComment = (TextView) finalView.findViewById(R.id.action_comment_count);

		mShare.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mCollection.setOnClickListener(this);
		mComment.setOnClickListener(this);

		mInitLayout = (LinearLayout) finalView.findViewById(R.id.initLayout);
		mScrollView = (ScrollView) finalView.findViewById(R.id.scrollView);
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
						mCollection.setVisibility(View.GONE);
						mComment.setVisibility(View.GONE);
						mBack.setVisibility(View.GONE);
						mShare.setVisibility(View.GONE);
						isDisappear = true;
						return false;
					}
					if (event.getY() - start_y > 0 && isDisappear) {
						mCollection.setVisibility(View.VISIBLE);
						mComment.setVisibility(View.VISIBLE);
						mBack.setVisibility(View.VISIBLE);
						mBack.setVisibility(View.VISIBLE);
						buttonAppearAnimation();
						isDisappear = false;
					}
				}
				return false;
			}
		});
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
			}
		});
		mCollection.setAnimation(otherInAnimation);
		mComment.setAnimation(otherInAnimation);
		mBack.setAnimation(backInAnimation);
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
				mCollection.setClickable(false);
				mComment.setClickable(false);
			}
		});
		mCollection.setAnimation(otherOutAnimation);
		mComment.setAnimation(otherOutAnimation);
		mBack.setAnimation(backOutAnimation);

	}

	private void initData() {
		mGetArtHttp = new GetArtHttp();
		mComments = new ArrayList<Comment>();
		mPictureSetDetail = new Details().getPictureSetDetails();
		mMembers = new ArrayList<Member>();
		mInformations = new ArrayList<Information>();
		newList = new ArrayList<String>();

		mOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.nopicture)
				.showImageForEmptyUri(R.drawable.nopicture)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		mLoadingListener = new LoadingListener();
	}

	private void setData() {
		ImageLoader.getInstance().displayImage(mPictureSetDetail.getFace(),
				mNewImage, mOptions, mLoadingListener);
		mTitleTextView.setText(mPictureSetDetail.getWriter());
		ImageLoader.getInstance().displayImage(mPictureSetDetail.getQrcode(),
				mNew2, mOptions, mLoadingListener);
		ImageLoader.getInstance().displayImage(mAdString, mAdView, mOptions,
				mLoadingListener);
		mNewImage.setOnClickListener(this);
	}

	private void initCommentList() {
		mCommentAdapter = new CommentListAdapter(mComments,
				ArtApplication.getArtApplication());
		mAboutComment.setAdapter(mCommentAdapter);
	}

	private void initNewList() {
		if (channelString == "-2" || "-2".equals(channelString)) {
			if (mMembers.size() >= 2) {
				for (int i = 0; i < 2; i++) {
					newList.add(mMembers.get(i).getUname());
				}
			} else {
				for (int i = 0; i < mMembers.size(); i++) {
					newList.add(mMembers.get(i).getUname());
				}
			}
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
		mNewAdapter = new NewsListAdapter(newList,
				ArtApplication.getArtApplication());
		mAboutNews.setAdapter(mNewAdapter);
		mAboutNews.setOnItemClickListener(this);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if (ArtApplication.OpenNew) {
			Intent intent = new Intent();
			intent.setClass(PictureActivity.this, MainActivity.class);
			startActivity(intent);
			ArtApplication.OpenNew = false;
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			this.finish();
		} else {
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.newsImage:
			intent.putExtra(ArtContants.NAME, mPictureSetDetail.getWriter());
			intent.putExtra(ArtContants.MID, mPictureSetDetail.getmId());
			intent.setClass(this, ArtMemberActivity.class);
			startActivity(intent);
			break;
		case R.id.back:
			onBackPressed();
		case R.id.bottom_btn_back:
			finish();
			break;
		case R.id.bottom_btn_1:
			break;
		case R.id.bottom_btn_2:
			intent = new Intent();
			intent.putExtra(ArtContants.COMMENT_ID, idString);
			intent.setClass(PictureActivity.this, CommentActivity.class);
			startActivity(intent);
			break;
		case R.id.bottom_btn_3:
			new ArtCollectionTask(idString).execute();
			break;
		case R.id.bottom_btn_4:
			mController.setShareContent(mPictureSetDetail.getTitle() + "\n"
					+ mPictureSetDetail.getDescription() + "\n"
					+ mPictureSetDetail.getShareUrl());
			mController.openShare(this, false);
			break;
		case R.id.collection:
			new ArtCollectionTask(idString).execute();
			break;
		case R.id.action_comment_count:
			intent.putExtra(ArtContants.COMMENT_ID, idString);
			intent.setClass(PictureActivity.this, CommentActivity.class);
			startActivity(intent);
			break;
		case R.id.care:
			new ArtAddCareTask(mPictureSetDetail.getmId()).execute();
			break;
		case R.id.erweimalayout:
			intent.putExtra(ArtContants.ER_WEI_MA,
					mPictureSetDetail.getQrcode());
			intent.setClass(PictureActivity.this, ArtShowTdcodeActivity.class);
			startActivity(intent);
			break;
		case R.id.share:
			mController.setShareContent(mPictureSetDetail.getTitle() + "\n"
					+ mPictureSetDetail.getDescription() + "\n"
					+ mPictureSetDetail.getShareUrl());
			mController.openShare(this, false);
			break;
		}
	}

	private class getAboutNewTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			mGetArtHttp.getCommentsHttp(idString);
			mGetArtHttp.getPictureSetDetails(idString);
			mGetArtHttp.getNewsHttp(channelString, channelnameString);
			mAdString = mGetArtHttp.getAdHttp(ArtContants.AD_ID);
			return null;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			mComments = mGetArtHttp.getmComments();
			mPictureSetDetail = mGetArtHttp.getM_PictureSetDetail();
			if (channelString == "-2" || "-2".equals(channelString)) {
				mMembers = mGetArtHttp.getM_Members();
			} else {
				mInformations = mGetArtHttp.getM_InformationList();
			}
			initViewer(mPictureSetDetail.getPictures(), position);
			initFinalView();
			initCommentList();
			setData();
			initNewList();
			mInitLayout.setVisibility(View.GONE);
			mScrollView.setVisibility(View.VISIBLE);
			super.onPostExecute(result);
		}

	}

	private static class LoadingListener extends SimpleImageLoadingListener {

		private static List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingStarted(String imageUri, View view) {
			// TODO Auto-generated method stub
			super.onLoadingStarted(imageUri, view);
		}

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean isFristDisplay = !displayedImages.contains(imageUri);
				if (isFristDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
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
		}
	}
}
