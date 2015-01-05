package com.sevenart.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.sevenart.ArtDetailsActivity;
import com.sevenart.CommentActivity;
import com.sevenart.R;
import com.sevenart.adapter.ArtAutionFragmentAdapter;
import com.sevenart.adapter.ChatListViewAdapter;
import com.sevenart.application.ArtApplication;
import com.sevenart.artasynctask.ArtAddCareTask;
import com.sevenart.artasynctask.ArtCollectionTask;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.Auction;
import com.sevenart.models.Bid;
import com.sevenart.models.ChatMsgEntity;
import com.sevenart.view.ArtAuctionListView;
import com.sevenart.webServe.GetArtHttp;
import com.sevenart.webServe.PostArtHttp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ArtAuctionFrament extends Fragment implements OnClickListener {
	private ScrollView mScrollView;

	private boolean isFrist = true;

	private GetArtHttp mGetArtHttp;
	private PostArtHttp mPostArtHttp;
	private Auction mAuction;

	private ViewPager mViewPager;
	private ArtOpusFragment mOpusFragment;
	private List<Fragment> mFragmentList;
	private ArtAutionFragmentAdapter mAutionFragmentAdapter;

	private EditText mPriceEditText;
	private TextView mIntroduce;
	private ImageView mRefresh;
	private TextView mNowPriceTextView;
	private TextView mStartPriceTextView;
	private TextView mAddPriceTextView;
	private TextView mTimeTextView;
	private ImageView mAddImageView;
	private ImageView mReduceImageView;
	private Button mBidSureButton;

	private ArtAuctionListView mChatListView;

	private View mMainView;
	private LayoutInflater mInflater;

	private String aid;
	private String wid;
	private int priceShowOnEdit;
	private int addPrice;
	private int priceNow;
	private int leftTime;
	private Bid mBid;

	private Timer timer;

//	private TextView mBack;
//	private TextView mShare;
//	private TextView mCollection;
//	private TextView mComment;
//	private TextView mErWeiMa;
//	private float start_y = 0;
//	private boolean isDisappear = false;

	public ArtAuctionFrament(String aid, String wid) {
		this.aid = aid;
		this.wid = wid;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mInflater = LayoutInflater.from(getActivity());
		mMainView = mInflater.inflate(R.layout.auction_fragment,
				(ViewGroup) getActivity().findViewById(R.layout.activity_main),
				false);

		initView();
		initData();
		initListener();

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

//	private void buttonAppearAnimation() {
//		Animation backInAnimation = AnimationUtils.loadAnimation(getActivity(),
//				R.anim.slide_in_left);
//		backInAnimation.setFillAfter(true);
//		backInAnimation.setAnimationListener(new AnimationListener() {
//
//			@Override
//			public void onAnimationStart(Animation animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				// TODO Auto-generated method stub
//				mBack.setClickable(true);
//			}
//		});
//		Animation otherInAnimation = AnimationUtils.loadAnimation(
//				getActivity(), R.anim.slide_in_right);
//		otherInAnimation.setFillAfter(true);
//		otherInAnimation.setAnimationListener(new AnimationListener() {
//
//			@Override
//			public void onAnimationStart(Animation animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				// TODO Auto-generated method stub
//				mCollection.setClickable(true);
//				mComment.setClickable(true);
//				mShare.setClickable(true);
//				mErWeiMa.setClickable(true);
//			}
//		});
//		mBack.setAnimation(backInAnimation);
//		mCollection.setAnimation(otherInAnimation);
//		mComment.setAnimation(otherInAnimation);
//		mShare.setAnimation(otherInAnimation);
//		mErWeiMa.setAnimation(otherInAnimation);
//	}
//
//	private void buttonDisappearAnimation() {
//		Animation backOutAnimation = AnimationUtils.loadAnimation(
//				getActivity(), R.anim.slide_out_left);
//		backOutAnimation.setFillAfter(true);
//		backOutAnimation.setAnimationListener(new AnimationListener() {
//
//			@Override
//			public void onAnimationStart(Animation animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				// TODO Auto-generated method stub
//				mBack.setClickable(false);
//			}
//		});
//		Animation otherOutAnimation = AnimationUtils.loadAnimation(
//				getActivity(), R.anim.slide_out_right);
//		otherOutAnimation.setFillAfter(true);
//		otherOutAnimation.setAnimationListener(new AnimationListener() {
//
//			@Override
//			public void onAnimationStart(Animation animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				// TODO Auto-generated method stub
//				mCollection.setClickable(true);
//				mComment.setClickable(true);
//				mShare.setClickable(true);
//				mErWeiMa.setClickable(true);
//			}
//		});
//		mBack.setAnimation(backOutAnimation);
//		mCollection.setAnimation(otherOutAnimation);
//		mComment.setAnimation(otherOutAnimation);
//		mShare.setAnimation(otherOutAnimation);
//		mErWeiMa.setAnimation(otherOutAnimation);
//
//	}

	private void initView() {
//		mBack = (TextView) mMainView.findViewById(R.id.back);
//		mShare = (TextView) mMainView.findViewById(R.id.share);
//		mCollection = (TextView) mMainView.findViewById(R.id.collection);
//		mComment = (TextView) mMainView.findViewById(R.id.action_comment_count);
//		mErWeiMa = (TextView) mMainView.findViewById(R.id.erweima);
		mPriceEditText = (EditText) mMainView.findViewById(R.id.price);
		mIntroduce = (TextView) mMainView.findViewById(R.id.author);
		mRefresh = (ImageView) mMainView.findViewById(R.id.refresh);
		mNowPriceTextView = (TextView) mMainView.findViewById(R.id.nowprice);
		mStartPriceTextView = (TextView) mMainView
				.findViewById(R.id.startprice);
		mAddPriceTextView = (TextView) mMainView.findViewById(R.id.addprice);
		mTimeTextView = (TextView) mMainView.findViewById(R.id.time);
		mReduceImageView = (ImageView) mMainView.findViewById(R.id.reduce);
		mAddImageView = (ImageView) mMainView.findViewById(R.id.add);
		mBidSureButton = (Button) mMainView.findViewById(R.id.bid);
		mChatListView = (ArtAuctionListView) mMainView
				.findViewById(R.id.auction_record);
		timer = new Timer();
		timer.schedule(task, 1000, 1000);
//		mScrollView = (ScrollView) mMainView.findViewById(R.id.scrollView);
//		mScrollView.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				if (event.getAction() == MotionEvent.ACTION_DOWN) {
//					start_y = event.getY();
//				}
//				if (event.getAction() == MotionEvent.ACTION_MOVE
//						&& start_y != 0) {
//					if (event.getY() - start_y < 0 && !isDisappear) {
//						buttonDisappearAnimation();
//						isDisappear = true;
//						return false;
//					}
//					if (event.getY() - start_y > 0 && isDisappear) {
//						buttonAppearAnimation();
//						isDisappear = false;
//					}
//				}
//				return false;
//			}
//		});
	}

	private void initData() {
		mGetArtHttp = new GetArtHttp();
		mPostArtHttp = new PostArtHttp();
		mAuction = new Auction();
		new getAuctionTask().execute();
	}

	private void initListener() {
		mBidSureButton.setOnClickListener(this);
		mAddImageView.setOnClickListener(this);
		mReduceImageView.setOnClickListener(this);
		mRefresh.setOnClickListener(this);
	}

	private void initOpusImage() {
		mViewPager = (ViewPager) mMainView.findViewById(R.id.viewpager);
		mFragmentList = new ArrayList<Fragment>();
		for (int i = 0; i < mAuction.getImgurlStrings().length; i++) {
			mOpusFragment = new ArtOpusFragment(mAuction.getImgurlStrings()[i]);
			mFragmentList.add(mOpusFragment);
		}
		mAutionFragmentAdapter = new ArtAutionFragmentAdapter(
				getChildFragmentManager(), mFragmentList);
		mViewPager.setAdapter(mAutionFragmentAdapter);
	}

	private int count;
	private ChatListViewAdapter mAdapter;
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();

	private void initChatListView() {
		count = mAuction.getRecords().size();
		for (int i = 0; i < count; i++) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(mAuction.getRecords().get(i).getTime());
			System.out.println(mAuction.getRecords().get(i).getTime());
			entity.setName(mAuction.getRecords().get(i).getUserId());
			if (ArtApplication.mLogin != null) {
				System.out.println("yi deng lu");
				if (mAuction.getRecords().get(i).getMid() == ArtApplication.mLogin
						.getMid()
						|| mAuction.getRecords().get(i).getMid()
								.equals(ArtApplication.mLogin.getMid()))
					entity.setMsgType(false);
				else
					entity.setMsgType(true);
			} else
				entity.setMsgType(false);
			entity.setText(mAuction.getRecords().get(i).getPrice() + "");
			mDataArrays.add(entity);
		}

		mAdapter = new ChatListViewAdapter(getActivity(), mDataArrays);
		mChatListView.setAdapter(mAdapter);
	}

	private void send(String contString) {
		if (contString.length() > 0) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(getDate());
			entity.setName("我");
			entity.setMsgType(false);
			entity.setText(contString);

			mDataArrays.add(entity);
			mAdapter.notifyDataSetChanged();
			mChatListView.setSelection(mChatListView.getCount() - 1);
		}
	}

	private String getDate() {
		Calendar c = Calendar.getInstance();

		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH));
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));
		String second = String.valueOf(c.get(Calendar.SECOND));

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "/" + month + "/" + day + " " + hour + ":"
				+ mins + ":" + second);

		return sbBuffer.toString();
	}

	private void setData() {
		mStartPriceTextView.setText(mAuction.getBasePrice() + "");
		mNowPriceTextView.setText(mAuction.getCurpriceString() + "");
		mAddPriceTextView.setText(mAuction.getAmplitude() + "");
		mTimeTextView.setText(getTime(mAuction.getLeftTimesTamp()));
		leftTime = mAuction.getLeftTimesTamp();
		mPriceEditText.setText(mAuction.getCurpriceString() + "");
		priceShowOnEdit = Integer.parseInt(mAuction.getCurpriceString());
		mIntroduce.setText(mAuction.getIntroductString());
		priceNow = priceShowOnEdit;
		addPrice = mAuction.getAmplitude();
	}

	private String getTime(int seconds) {
		int hours;
		int min;
		int second;
		second = seconds % 60;
		min = ((seconds - second) / 60) % 60;
		hours = ((seconds - second) / 60 - min) / 60;
		return hours + ":" + min + ":" + second;
	}

	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				mTimeTextView.setText(getTime(leftTime));
				leftTime--;
				break;
			}
			super.handleMessage(msg);
		}
	};

	TimerTask task = new TimerTask() {
		public void run() {
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);
		}
	};

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (view.getId()) {
//		case R.id.back:
//			onBackPressed();
//			break;
//		case R.id.share:
//			break;
//		case R.id.collection:
//			new ArtCollectionTask(mAuction.get).execute();
//			break;
//		case R.id.action_comment_count:
//			intent.putExtra(ArtContants.COMMENT_ID, idString);
//			intent.setClass(getActivity(), CommentActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.erweima:
//			break;
//		case R.id.care:
//			new ArtAddCareTask(mEssayDetails.getmId()).execute();
//			break;
		case R.id.reduce:
			priceShowOnEdit = Integer.valueOf(mPriceEditText.getText()
					.toString());
			priceShowOnEdit = priceShowOnEdit - addPrice;
			if (priceShowOnEdit < 0) {
				Toast.makeText(ArtApplication.getArtApplication(), "价格不能小于0",
						Toast.LENGTH_SHORT);
			} else
				mPriceEditText.setText(priceShowOnEdit + "");
			break;
		case R.id.add:
			priceShowOnEdit = Integer.valueOf(mPriceEditText.getText()
					.toString());
			priceShowOnEdit = priceShowOnEdit + addPrice;
			mPriceEditText.setText(priceShowOnEdit + "");
			break;
		case R.id.bid:
			String contString = mPriceEditText.getText().toString();
			int bidPrice = Integer.valueOf(contString);
			if (bidPrice <= priceNow) {
				Toast.makeText(ArtApplication.getArtApplication(), "出价小于当前价格",
						Toast.LENGTH_SHORT).show();
			} else {
				new bidTask(contString).execute();
				// priceShowOnEdit = 0;
				mPriceEditText.setText(priceShowOnEdit + "");
			}
			break;
		case R.id.refresh:
			new getAuctionTask().execute();
			break;
		}
	}

	private class getAuctionTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			mAuction = mGetArtHttp.getAuction(aid, wid);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			if (isFrist == true) {
				initOpusImage();
				setData();
				initChatListView();
				isFrist = false;
			}
			if (isFrist == false) {
				initOpusImage();
				setData();
				count = mAuction.getRecords().size();
				mDataArrays.clear();
				for (int i = 0; i < count; i++) {
					ChatMsgEntity entity = new ChatMsgEntity();
					entity.setDate(mAuction.getRecords().get(i).getTime());
					entity.setName(mAuction.getRecords().get(i).getUserId());
					if (ArtApplication.mLogin != null) {
						if (mAuction.getRecords().get(i).getMid() == ArtApplication.mLogin
								.getMid()
								|| mAuction.getRecords().get(i).getMid()
										.equals(ArtApplication.mLogin.getMid()))
							entity.setMsgType(false);
						else
							entity.setMsgType(true);
					} else {
						entity.setMsgType(true);
					}
					entity.setText(mAuction.getRecords().get(i).getPrice() + "");
					mDataArrays.add(entity);
				}
				mAdapter.notifyDataSetChanged();
				mChatListView.setSelection(mChatListView.getCount() - 1);
			}
			super.onPostExecute(result);
		}

	}

	private class bidTask extends AsyncTask<Void, Void, Void> {
		private String price;

		public bidTask(String price) {
			// TODO Auto-generated constructor stub
			this.price = price;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			mBid = mPostArtHttp.postPriceHttp(aid, wid, price);
			return null;
		}

		@SuppressWarnings("static-access")
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			if (mBid != null)
				send(price);
			else
				Toast.makeText(ArtApplication.getArtApplication(),
						mPostArtHttp.msg, Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}

	}
}
