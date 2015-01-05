package com.sevenart.artasynctask;

import com.sevenart.application.ArtApplication;
import com.sevenart.webServe.PostArtHttp;

import android.os.AsyncTask;
import android.widget.Toast;

public class ArtCollectionTask extends AsyncTask<Void, Void, Void> {

	private String aid;
	private int code;

	public ArtCollectionTask(String aid) {
		// TODO Auto-generated constructor stub
		this.aid = aid;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		PostArtHttp postArtHttp = new PostArtHttp();
		code = postArtHttp.postCollectionHttp(aid);
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		if (code == 0) {
			Toast toast = Toast.makeText(ArtApplication.getArtApplication(),
					"收藏成功", Toast.LENGTH_SHORT);
			toast.show();
		} else {
			Toast toast = Toast.makeText(ArtApplication.getArtApplication(),
					"收藏失败", Toast.LENGTH_SHORT);
			toast.show();
		}

		super.onPostExecute(result);
	}
}
