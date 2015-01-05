package com.sevenart.webServe;

import org.apache.http.NameValuePair;

import android.os.AsyncTask;
import android.text.TextUtils;

public class HttpTask extends AsyncTask<NameValuePair, Void, String> {

	private String url;
	private OnCallBack onCallBack;

	public HttpTask(String url, OnCallBack onCallBack) {
		this.url = url;
		this.onCallBack = onCallBack;
	}

	public interface OnCallBack {
		public void onPre();

		public void onError();

		public void OnSuccess(String result);
	}

	@Override
	protected String doInBackground(NameValuePair... params) {
		// TODO Auto-generated method stub
		try {
			return HttpUtil.post(url, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		if (onCallBack != null) {
			onCallBack.onPre();
		}
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (onCallBack != null) {
			if (result == null || TextUtils.isEmpty(result))
				onCallBack.onError();
			else
				onCallBack.OnSuccess(result);
		}
	}

}
