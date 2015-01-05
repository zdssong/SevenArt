package com.sevenart;

import com.sevenart.baseActicity.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SettingsActivity extends BaseActivity implements OnClickListener {
	TextView font_size_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		initView();
	}

	private void initView() {
		font_size_text = (TextView) findViewById(R.id.font_size_text);
		font_size_text.setOnClickListener(this);
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
		case R.id.font_size_text:

			break;
		default:
			break;
		}
	}
}
