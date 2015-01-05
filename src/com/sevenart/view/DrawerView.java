package com.sevenart.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.sevenart.CareActivity;
import com.sevenart.CollectActivity;
import com.sevenart.LoginActivity;
import com.sevenart.PaimaiActivity;
import com.sevenart.R;
import com.sevenart.contants.ArtContants;

/**
 * 自定义SlidingMenu 测拉菜单类
 * */
public class DrawerView implements OnClickListener {
	private final Activity activity;
	private static SlidingMenu localSlidingMenu;
	private static View setting_btn, guanzhu_btn, shoucang_btn, chengjiao_btn,
			lingxian_btn, weichujia_btn, luohou_btn, guanyu_btn;
	public static View login_btn;
	public static ImageView mHead;
	public static TextView mLogin;

	public DrawerView(Activity activity) {
		this.activity = activity;
	}

	public SlidingMenu initSlidingMenu() {
		localSlidingMenu = new SlidingMenu(activity);
		localSlidingMenu.setMode(SlidingMenu.LEFT);// 设置左右滑菜单
		localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);// 设置要使菜单滑动，触碰屏幕的范围
		// localSlidingMenu.setTouchModeBehind(SlidingMenu.SLIDING_CONTENT);//设置了这个会获取不到菜单里面的焦点，所以先注释掉
		localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);// 设置阴影图片的宽度
		localSlidingMenu.setShadowDrawable(R.drawable.shadow);// 设置阴影图片
		localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);// SlidingMenu划出时主页面显示的剩余宽度
		localSlidingMenu.setFadeDegree(0.35F);// SlidingMenu滑动时的渐变程度
		localSlidingMenu.attachToActivity(activity, SlidingMenu.LEFT);// 使SlidingMenu附加在Activity右边
		// localSlidingMenu.setBehindWidthRes(R.dimen.left_drawer_avatar_size);//设置SlidingMenu菜单的宽度
		localSlidingMenu.setMenu(R.layout.left_drawer_fragment);// 设置menu的布局文件
		// localSlidingMenu.toggle();//动态判断自动关闭或开启SlidingMenu
		// localSlidingMenu.setSecondaryMenu(R.layout.channel);
		// localSlidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);
		localSlidingMenu
				.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
					public void onOpened() {

					}
				});
		localSlidingMenu.setOnClosedListener(new OnClosedListener() {

			@Override
			public void onClosed() {
				// TODO Auto-generated method stub

			}
		});
		initView1();
		initView2();
		initView3();
		initView4();
		initView5();
		initView6();
		initView7();
		initView8();
		initView9();
		return localSlidingMenu;
	}

	private void initView1() {
		setting_btn = localSlidingMenu.findViewById(R.id.setting_btn);
		setting_btn.setOnClickListener(this);
	}

	private void initView2() {
		guanzhu_btn = localSlidingMenu.findViewById(R.id.guanzhu_btn);
		guanzhu_btn.setOnClickListener(this);
	}

	private void initView3() {
		shoucang_btn = localSlidingMenu.findViewById(R.id.shoucang_btn);
		shoucang_btn.setOnClickListener(this);
	}

	private void initView4() {
		chengjiao_btn = localSlidingMenu.findViewById(R.id.chengjiao_btn);
		chengjiao_btn.setOnClickListener(this);
	}

	private void initView5() {
		lingxian_btn = localSlidingMenu.findViewById(R.id.lingxian_btn);
		lingxian_btn.setOnClickListener(this);
	}

	private void initView6() {
		weichujia_btn = localSlidingMenu.findViewById(R.id.weichujia_btn);
		weichujia_btn.setOnClickListener(this);
	}

	private void initView7() {
		luohou_btn = localSlidingMenu.findViewById(R.id.luohou_btn);
		luohou_btn.setOnClickListener(this);
	}

	private void initView8() {
		login_btn = localSlidingMenu.findViewById(R.id.login_btn);
		mHead = (ImageView) localSlidingMenu.findViewById(R.id.touxiang);
		mHead.setBackgroundResource(R.drawable.default_round_head);
		mLogin = (TextView) localSlidingMenu.findViewById(R.id.login);
		login_btn.setOnClickListener(this);
	}

	public static void setLogin() {

	}

	private void initView9() {
		guanyu_btn = localSlidingMenu.findViewById(R.id.guanyu_btn);
		guanyu_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {

		case R.id.setting_btn:
			// activity.startActivity(new Intent(activity,
			// SettingsActivity.class));
			// activity.overridePendingTransition(R.anim.slide_in_right,
			// R.anim.slide_out_left);
			break;
		case R.id.guanzhu_btn:
			activity.startActivity(new Intent(activity, CareActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.shoucang_btn:
			activity.startActivity(new Intent(activity, CollectActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.chengjiao_btn:
			intent = new Intent(activity, PaimaiActivity.class);
			intent.putExtra(ArtContants.ACTIVITY_TAG, ArtContants.CHENG_JIAO);
			activity.startActivity(intent);
			activity.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.lingxian_btn:
			intent = new Intent(activity, PaimaiActivity.class);
			intent.putExtra(ArtContants.ACTIVITY_TAG, ArtContants.LING_XIAN);
			activity.startActivity(intent);
			activity.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.weichujia_btn:
			intent = new Intent(activity, PaimaiActivity.class);
			intent.putExtra(ArtContants.ACTIVITY_TAG, ArtContants.MEI_CHU_JIA);
			activity.startActivity(intent);
			activity.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.luohou_btn:
			intent = new Intent(activity, PaimaiActivity.class);
			intent.putExtra(ArtContants.ACTIVITY_TAG, ArtContants.LUO_HOU);
			activity.startActivity(intent);
			activity.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.login_btn:
			intent = new Intent(activity, LoginActivity.class);
			activity.startActivity(intent);
			activity.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		case R.id.guanyu_btn:
			// activity.startActivity(new Intent(activity,
			// AboutActivity.class));
			// activity.overridePendingTransition(R.anim.slide_in_right,
			// R.anim.slide_out_left);
			break;
		default:
			break;
		}
	}
}
