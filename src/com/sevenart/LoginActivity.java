package com.sevenart;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sevenart.application.ArtApplication;
import com.sevenart.baseActicity.BaseActivity;
import com.sevenart.contants.ArtContants;
import com.sevenart.tools.ViewTools;
import com.sevenart.view.DrawerView;
import com.sevenart.webServe.PostArtHttp;

import android.R.integer;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private DisplayImageOptions mOptions;
	private ImageLoadingListener mLoadingListener;

	private Button login_button;
	private EditText m_AccountEdit;
	private EditText m_PassworkEdit;

	private PostArtHttp m_PostArtHttp;

	private TextView forgetPassword;
	private TextView register;
	private String photo = null;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		mOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.nopicture)
				.showImageForEmptyUri(R.drawable.nopicture)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		mLoadingListener = new LoadingListener();

		initView();

		setNeedBackGesture(true);
	}

	private void initView() {
		m_PostArtHttp = new PostArtHttp();

		login_button = (Button) findViewById(R.id.login_button);
		m_AccountEdit = (EditText) findViewById(R.id.accountEditText);
		m_PassworkEdit = (EditText) findViewById(R.id.passwordEditText);
		login_button.setOnClickListener(this);

		forgetPassword = (TextView) findViewById(R.id.forget_password);
		forgetPassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		forgetPassword.setOnClickListener(this);
		register = (TextView) findViewById(R.id.register);
		register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		register.setOnClickListener(this);
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setCanceledOnTouchOutside(false);
			// progressDialog.setCancelable(false);
		}
	}

	private void destroyActivity() {
		this.finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_button:
			String account = m_AccountEdit.getText().toString();
			if (account == null || account.equals("")) {
				Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
				break;
			}
			String userpwd = m_PassworkEdit.getText().toString();
			if (userpwd == null || userpwd.equals("")) {
				Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
				break;
			}
			new loginTask(account, userpwd).execute();
			break;
		case R.id.forget_password:
			Intent intent = new Intent(this, RegisterResetActivity.class);
			intent.putExtra("type", RegisterResetActivity.RESET);
			startActivity(intent);
			break;
		case R.id.register:
			Intent intent2 = new Intent(this, RegisterResetActivity.class);
			intent2.putExtra("type", RegisterResetActivity.REGISTER);
			startActivityForResult(intent2, RegisterResetActivity.RESULT);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK) {
			if (requestCode == RegisterResetActivity.RESULT) {
				String phone = data.getStringExtra("phone");
				String password = data.getStringExtra("password");
				photo = data.getStringExtra("photo");
				m_AccountEdit.setText(phone);
				m_PassworkEdit.setText(password);
				if (phone == null || phone.equals("")) {
					Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				if (password == null || password.equals("")) {
					Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				new loginTask(phone, password).execute();
			}
		} else
			super.onActivityResult(requestCode, resultCode, data);
	}

	private void sendBroadcast() {
		Intent intent = new Intent();
		intent.setAction(ArtContants.LOGIN_BROADCAST);
		sendBroadcast(intent);
	}

	private class loginTask extends AsyncTask<Void, integer, Void> {

		private String account;
		private String userpwd;
		private int tag;

		public loginTask(String account, String userpwd) {
			this.account = account;
			this.userpwd = userpwd;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			ViewTools.showDialog(progressDialog);
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			tag = m_PostArtHttp.postLogin(account, userpwd);
			return null;
		}

		@SuppressWarnings("static-access")
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			ViewTools.dismissDialog(progressDialog);
			if (tag == 1 && ArtApplication.mLogin != null) {
				Toast toast = Toast.makeText(
						ArtApplication.getArtApplication(), "登陆成功",
						Toast.LENGTH_SHORT);
				toast.show();
				sendBroadcast();
				DrawerView.mHead.setBackgroundResource(R.color.left_drawer_item_bg_normal);
				if (photo != null && !photo.equals(""))
					ImageLoader.getInstance().displayImage("file://" + photo,
							DrawerView.mHead, mOptions, mLoadingListener);
				else
					ImageLoader.getInstance().displayImage(
							ArtApplication.mLogin.getFace(), DrawerView.mHead,
							mOptions, mLoadingListener);
				DrawerView.mLogin.setText(ArtApplication.mLogin.getUname());
				destroyActivity();
			} else {
				Toast.makeText(ArtApplication.getArtApplication(),
						m_PostArtHttp.msg, Toast.LENGTH_SHORT).show();
			}
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
}
