package com.sevenart;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sevenart.application.ArtApplication;
import com.sevenart.baseActicity.BaseActivity;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.Member;
import com.sevenart.view.MyGridView;
import com.sevenart.webServe.GetArtHttp;

import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;

public class CareActivity extends BaseActivity implements OnItemClickListener,
		OnClickListener {

	private GetArtHttp mGetArtHttp;
	private List<Member> mMembers;
	private MyGridView gridView;
	private TextView mTextView;
	private RelativeLayout mLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.carecare);
		initView();
		initDate();
		initListener();
	}

	private void initDate() {
		mGetArtHttp = new GetArtHttp();
		mMembers = new ArrayList<Member>();

		if (ArtApplication.mLogin != null) {
			mLayout.setVisibility(View.GONE);
			gridView.setVisibility(View.VISIBLE);
			new getCareTask().execute();
		}
	}

	private void setDate() {
		PictureAdapter adapter = new PictureAdapter(mMembers);
		gridView.setAdapter(adapter);
	}

	private void initView() {
		gridView = (MyGridView) findViewById(R.id.carecare_gridview);
		mTextView = (TextView) findViewById(R.id.textview);
		mLayout = (RelativeLayout) findViewById(R.id.layout);
	}

	private void initListener() {
		gridView.setOnItemClickListener(this);
		mTextView.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.putExtra(ArtContants.NAME, mMembers.get(position).getUname());
		intent.putExtra(ArtContants.MID, mMembers.get(position).getMid());
		intent.setClass(this, ArtMemberActivity.class);
		startActivity(intent);
	}

	private class getCareTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			mMembers = mGetArtHttp.getCareHttp();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			setDate();
			super.onPostExecute(result);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(CareActivity.this, LoginActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		this.finish();
	}
}

// ◊‘∂®“Â  ≈‰∆˜
class PictureAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<Member> mMembers;

	public PictureAdapter(List<Member> members) {
		super();
		this.mMembers = members;
		inflater = LayoutInflater.from(ArtApplication.getArtApplication());
	}

	@Override
	public int getCount() {
		if (null != mMembers) {
			return mMembers.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return mMembers.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item_care, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.my_care_textView1);
			viewHolder.image = (ImageView) convertView
					.findViewById(R.id.my_cara_imageView1);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.title.setText(mMembers.get(position).getUname());
		ImageLoader.getInstance().displayImage(
				mMembers.get(position).getFace(), viewHolder.image);
		return convertView;
	}

}

class ViewHolder {
	public TextView title;
	public ImageView image;
}

class Picture {
	private String title;
	private int imageId;

	public Picture() {
		super();
	}

	public Picture(String title, int imageId) {
		super();
		this.title = title;
		this.imageId = imageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
}
