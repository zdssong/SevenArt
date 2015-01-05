package com.sevenart.tools;

import android.app.Dialog;

public class ViewTools {

	public static void showDialog(Dialog dialog) {
		if (dialog != null && !dialog.isShowing())
			dialog.show();
	}

	public static void dismissDialog(Dialog dialog) {
		if (dialog != null && dialog.isShowing())
			dialog.dismiss();
	}
}
