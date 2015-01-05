package com.sevenart.artasynctask;

import com.sevenart.application.ArtApplication;
import com.sevenart.webServe.PostArtHttp;

import android.os.AsyncTask;
import android.widget.Toast;

public class ArtAddCareTask extends AsyncTask<Void, Void, Void> {

	private String fmid;
	private int code;

	public ArtAddCareTask(String fmid) {
		// TODO Auto-generated constructor stub
		this.fmid = fmid;
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		PostArtHttp postArtHttp = new PostArtHttp();
		code = postArtHttp.postAddCareHttp(fmid);
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		if (code == 0) {
			Toast toast = Toast.makeText(ArtApplication.getArtApplication(),
					"¹Ø×¢³É¹¦", Toast.LENGTH_SHORT);
			toast.show();
		} else {
			Toast toast = Toast.makeText(ArtApplication.getArtApplication(),
					PostArtHttp.msg, Toast.LENGTH_SHORT);
			toast.show();
		}
		super.onPostExecute(result);
	}

}
