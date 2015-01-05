package com.sevenart;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sevenart.baseActicity.BaseActivity;
import com.sevenart.tools.CameraUtil;
import com.sevenart.tools.DataTools;
import com.sevenart.tools.ViewTools;
import com.sevenart.view.BottomPopDialog;
import com.sevenart.webServe.HttpTask;
import com.sevenart.webServe.HttpTask.OnCallBack;
import com.sevenart.webServe.UploadUtil.OnUploadProcessListener;

public class RegisterResetActivity extends BaseActivity implements
		OnClickListener, OnUploadProcessListener {

	private static final String requestURL = "http://m.yi71.com/plus/mobile/apps/signup.php";

	public static final int RESULT = 8;
	public static final int REGISTER = 1;
	public static final int RESET = 2;

	private EditText phone;
	private EditText verification;
	private EditText password;
	private EditText confirmPassword;
	private EditText nickname;

	private TextView titleText;
	private TextView sendVerification;

	private Button cancle;
	private Button sure;

	private ImageView portrait;

	private int time = 60;
	private Handler handler;

	private int activityType;
	private ProgressDialog progressDialog;
	public static final int CAMERA = 0;
	public static final int ALBUM = 1;
	Dialog pickerDialog;
	private File photo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_reset_activity);
		setNeedBackGesture(true);
		initView();
		initHandler();

		Intent intent = getIntent();
		activityType = intent.getIntExtra("type", 1);
		if (activityType == RESET) {
			portrait.setVisibility(View.GONE);
			nickname.setVisibility(View.GONE);
			findViewById(R.id.nickname_text).setVisibility(View.GONE);
			titleText.setText(getResources().getString(R.string.reset));
		} else {
			initPickDialog();
		}
	}

	private void initView() {
		phone = (EditText) findViewById(R.id.phone);
		verification = (EditText) findViewById(R.id.verification);
		password = (EditText) findViewById(R.id.password);
		confirmPassword = (EditText) findViewById(R.id.confirm_password);
		nickname = (EditText) findViewById(R.id.nickname);

		titleText = (TextView) findViewById(R.id.title_text);
		sendVerification = (TextView) findViewById(R.id.send_verification);
		sendVerification.setOnClickListener(this);

		portrait = (ImageView) findViewById(R.id.portrait);
		portrait.setOnClickListener(this);

		cancle = (Button) findViewById(R.id.cancle);
		cancle.setOnClickListener(this);
		sure = (Button) findViewById(R.id.sure);
		sure.setOnClickListener(this);
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setCanceledOnTouchOutside(false);
			// progressDialog.setCancelable(false);
		}
	}

	private void initHandler() {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 0) {
					time--;

					if (time > 0) {
						sendVerification.setText(getResources().getString(
								R.string.resend_verification)
								+ " (" + time + ")");
						handler.sendEmptyMessageDelayed(0, 1000);
					} else {
						sendVerification.setText(getResources().getString(
								R.string.resend_verification));
						sendVerification.setEnabled(true);
					}
				}
			}
		};
	}

	private void initPickDialog() {
		View view = LayoutInflater.from(this).inflate(
				R.layout.dialog_pick_photo, null);
		view.findViewById(R.id.camera).setOnClickListener(this);
		view.findViewById(R.id.photoalbum).setOnClickListener(this);
		view.findViewById(R.id.dialog_cancel).setOnClickListener(this);
		pickerDialog = BottomPopDialog.getDialog(this, view, pickerDialog);
	}

	private boolean isEditNull(EditText editText) {
		return TextUtils.isEmpty(editText.getText());
	}

	private void toast(String str) {
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	private boolean adjustContent() {
		String result = null;
		boolean flag = true;
		if (!DataTools.isMobileNumber(phone.getText().toString())) {
			result = getResources().getString(R.string.phone_error);
			flag = false;
		} else if (isEditNull(phone)) {
			result = getResources().getString(R.string.phone_null);
			flag = false;
		} else if (isEditNull(password)) {
			result = getResources().getString(R.string.password_null);
			flag = false;
		} else if (isEditNull(confirmPassword)) {
			result = getResources().getString(R.string.confirm_password_null);
			flag = false;
		} else if (isEditNull(nickname) && activityType == REGISTER) {
			result = getResources().getString(R.string.nickname_null);
			flag = false;
		} else if (!TextUtils.equals(password.getText(),
				confirmPassword.getText())) {
			result = getResources().getString(R.string.password_error);
			flag = false;
		}
		if (result != null && !result.equals(""))
			toast(result);
		System.out.println("result=" + result);
		return flag;
	}

	private void sendVerification() {
		HttpTask verification = new HttpTask("", new OnCallBack() {

			@Override
			public void onPre() {
				// TODO Auto-generated method stub
				ViewTools.showDialog(progressDialog);
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub
			}

			@Override
			public void OnSuccess(String result) {
				// TODO Auto-generated method stub
			}
		});
		NameValuePair[] params = new NameValuePair[2];
		params[0] = new BasicNameValuePair("act", "identify");
		params[1] = new BasicNameValuePair("phone", phone.getText().toString());
		verification.execute(params);
	}

	private void register() {
		if (!adjustContent()) {
			return;
		}
		HttpTask register = new HttpTask(requestURL, new OnCallBack() {

			@Override
			public void onPre() {
				// TODO Auto-generated method stub
				ViewTools.showDialog(progressDialog);
			}

			@Override
			public void onError() {
				// TODO Auto-generated method stub
				ViewTools.dismissDialog(progressDialog);
				toast("×¢²áÊ§°Ü");
			}

			@Override
			public void OnSuccess(String result) {
				// TODO Auto-generated method stub
				ViewTools.dismissDialog(progressDialog);
				// System.out.println(result);
				System.out.println(DataTools.unicodeToString(result));
				try {
					JSONObject object = new JSONObject(result);
					int code = object.getInt("code");
					System.out.println("int code=" + code);
					if (code == 0) {
						toast("×¢²á³É¹¦");
						Intent data = new Intent();
						data.putExtra("phone", phone.getText().toString());
						data.putExtra("password", password.getText().toString());
						setResult(Activity.RESULT_OK, data);
						finish();
					} else {
						String msg = object.getString("msg");
						toast(msg);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Intent data = new Intent();
					data.putExtra("phone", phone.getText().toString());
					data.putExtra("password", password.getText().toString());
					if (photo != null)
						data.putExtra("photo", photo.getPath());
					setResult(Activity.RESULT_OK, data);
					finish();
				}
			}
		});

		NameValuePair[] param = null;
		if (photo == null)
			param = new NameValuePair[5];
		else {
			param = new NameValuePair[6];
			param[5] = new BasicNameValuePair("face", photo.getPath());
		}
		param[0] = new BasicNameValuePair("act", "confirm");
		param[1] = new BasicNameValuePair("phone", phone.getText().toString());
		param[2] = new BasicNameValuePair("uname", nickname.getText()
				.toString());
		param[3] = new BasicNameValuePair("userpwd", password.getText()
				.toString());
		param[4] = new BasicNameValuePair("userpwdok", confirmPassword
				.getText().toString());
		register.execute(param);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.cancle:
			finish();
			break;
		case R.id.sure:
			register();
			break;
		case R.id.portrait:
			pickerDialog.show();
			break;
		case R.id.send_verification:
			sendVerification();
			time = 60;
			sendVerification.setEnabled(false);
			handler.sendEmptyMessageDelayed(0, 500);
			break;
		case R.id.dialog_cancel:
			pickerDialog.dismiss();
			break;
		case R.id.camera:
			photo = CameraUtil.reqCamera(this, CAMERA);
			pickerDialog.dismiss();
			break;
		case R.id.photoalbum:
			CameraUtil.getImageClipIntent(this, ALBUM, null);
			pickerDialog.dismiss();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String path = null;
		if (requestCode == ALBUM) {
			try {
				Bitmap bmp = data.getParcelableExtra("data");
				photo = CameraUtil.creatPhotoFile();
				FileOutputStream mFileOutputStream = new FileOutputStream(photo);
				bmp.compress(CompressFormat.JPEG, 100, mFileOutputStream);
				portrait.setImageBitmap(bmp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (requestCode == CAMERA) {
			path = CameraUtil.returnCamera(photo);
			// if (requestCode == AppConfig.REQ_CODE_PICTURES) {
			// path = CameraUtil.returnPic(data, this);
			// } else if (requestCode == CAMERA) {
			// path = CameraUtil.returnCamera(photo);
			// } F
			if (path != null) {
				photo = new File(path);
				CameraUtil.getImageClipIntent(this, ALBUM, Uri.fromFile(photo));
			}

		}
		if (photo != null)
			System.out.println(photo.getName());
	}

	@Override
	public void onUploadDone(int responseCode, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUploadProcess(int uploadSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initUpload(int fileSize) {
		// TODO Auto-generated method stub

	}
}
