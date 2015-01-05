package com.sevenart.view;

import com.sevenart.R;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

public class BottomPopDialog {
	public static Dialog getDialog(Context context, View contentView,
			Dialog tmpDialog) {
		if (tmpDialog != null && tmpDialog.isShowing()) {
			return tmpDialog;
		}
		Dialog dialog = new Dialog(context, R.style.popup_dialog_anim);
		Window window = dialog.getWindow();
		WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		windowParams.x = 0;
		windowParams.y = dm.heightPixels;
		window.setAttributes(windowParams);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(contentView);
		dialog.getWindow().setLayout(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		// dialog.show();
		return dialog;
	}

}
