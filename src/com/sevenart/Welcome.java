package com.sevenart;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sevenart.R;
import com.sevenart.application.ArtApplication;
import com.sevenart.db.DatabaseManager;
import com.sevenart.models.Column;
import com.sevenart.webServe.GetArtHttp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class Welcome extends Activity {

	private AlphaAnimation start_anima;
	private View view;

	private DatabaseManager mDatabaseManger;
	private GetArtHttp mGetArtHttp;
	private List<Column> mColumns;
	private String mImei;

	private ImageView imageView;
	private String adImageString;

	private List<Column> mUserChannelList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		view = View.inflate(this, R.layout.welcome, null);
		setContentView(view);

		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		mImei = telephonyManager.getDeviceId();
		initData();
		new AdDataTask().execute();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	private void initData() {
		mDatabaseManger = ArtApplication.getArtApplication()
				.getDatabaseManger();
		mUserChannelList = new ArrayList<Column>();
		mGetArtHttp = ArtApplication.getArtApplication().getGetArtHttp();
		mColumns = new ArrayList<Column>();
		imageView = (ImageView) findViewById(R.id.image);
		start_anima = new AlphaAnimation(0.3f, 1.0f);
		start_anima.setDuration(4000);
		view.startAnimation(start_anima);
		start_anima.setAnimationListener(new AnimationListener() {

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
				redirectTo();
			}
		});

	}

	private void redirectTo() {
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		finish();
	}

	private class AdDataTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			// mGetArtHttp.getColumnHttp(mImei);
			adImageString = mGetArtHttp.getAdHttp("56");
			return "OK";
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			ImageLoader.getInstance().displayImage(adImageString, imageView);
			// mUserChannelList = ArtApplication.getArtApplication()
			// .getDatabaseManger().queryColumnBySelected(1);
			// if ("OK".equals(result)) {
			// List<Column> columns = new ArrayList<Column>();
			// mColumns = mGetArtHttp.getM_ColumnList();
			// for (int i = 0; i < mColumns.size(); i++) {
			// Column column = mColumns.get(i);
			// if (mUserChannelList.size() != 0) {
			// ArtApplication.mUserChannelList = mUserChannelList;
			// if (i < 2)
			// column.setSelected(1);
			// else
			// for (int j = 0; j < mUserChannelList.size(); j++) {
			// if (column.getChannelName().equals(
			// mUserChannelList.get(j)
			// .getChannelName())) {
			// column.setSelected(1);
			// break;
			// }
			// column.setSelected(0);
			// }
			// } else {
			// if (i < 8) {
			// column.setSelected(1);
			// ArtApplication.mUserChannelList.add(column);
			// } else
			// column.setSelected(0);
			// }
			// column.setOrderId(i);
			// columns.add(column);
			// }
			// if (columns.size() > 0 && mDatabaseManger.isNull()) {
			// mDatabaseManger.deleteColumn();
			// mDatabaseManger.addColumn(columns);
			// }
			// }
			super.onPostExecute(result);
		}

	}

}
