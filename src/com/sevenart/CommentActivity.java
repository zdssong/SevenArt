package com.sevenart;

import com.sevenart.application.ArtApplication;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.Comment;
import com.sevenart.webServe.PostArtHttp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class CommentActivity extends Activity implements OnClickListener {

	private PostArtHttp mPostArtHttp;
	private EditText mContent;

	private String mMsg;
	private String mAid;

	private Comment mComment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_comment);

		mAid = getIntent().getStringExtra(ArtContants.COMMENT_ID);

		initView();
	}

	private void initView() {
		mComment = new Comment();
		mPostArtHttp = new PostArtHttp();

		findViewById(R.id.confirm).setOnClickListener(this);
		findViewById(R.id.cancel).setOnClickListener(this);
		mContent = (EditText) findViewById(R.id.comment);
	}

	private void destroyActivity() {
		this.finish();
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.confirm:
			mMsg = mContent.getText().toString();
			if (mMsg == null || mMsg.equals("")) {
				Toast toast = Toast.makeText(
						ArtApplication.getArtApplication(), "评论内容为空",
						Toast.LENGTH_LONG);
				toast.show();
			} else {
				new CommentTask().execute();
			}
			break;
		case R.id.cancel:
			destroyActivity();
			break;
		}
	}

	private class CommentTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			mPostArtHttp.postCommentHttp("comments", mAid, null, mMsg);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			mComment = mPostArtHttp.getmComment();
			if (mComment != null) {
				Toast toast = Toast.makeText(
						ArtApplication.getArtApplication(), "评论成功",
						Toast.LENGTH_LONG);
				toast.show();
				destroyActivity();
			} else {
				Toast toast = Toast.makeText(
						ArtApplication.getArtApplication(), "评论失败",
						Toast.LENGTH_LONG);
				toast.show();
			}
			super.onPostExecute(result);
		}
	}
}
