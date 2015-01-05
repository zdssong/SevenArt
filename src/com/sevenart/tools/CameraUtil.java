package com.sevenart.tools;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

public class CameraUtil {

	private static final String tag = "CameraUtil";

	public static String getFileName() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_hhmmss");
		return formatter.format(new java.util.Date()) + ".jpg";
	}

	public static File creatPhotoFile() {
		String filename = getFileName();
		File photo = new File(Environment.getExternalStorageDirectory() + "/"
				+ filename);
		if (!photo.isFile()) {
			try {
				photo.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return photo;
	}

	public static File reqCamera(Activity context, int requestCode) {

		File photo = null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			photo = creatPhotoFile();
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
			context.startActivityForResult(intent, requestCode);
		} else {
			Toast.makeText(context, "打开相机失败", Toast.LENGTH_SHORT).show();
		}
		return photo;
	}

	public static String returnCamera(File photo) {
		if (photo == null)
			return null;
		Uri originalUri = Uri.fromFile(photo);
		return originalUri.toString().replace("file://", "");
	}

	public static String returnPic(Intent data, Activity context) {
		if (data != null) {
			Uri originalUri = data.getData();
			Log.d(tag, "originalUri:" + originalUri.toString());
			if (originalUri.toString().startsWith("content://")) {
				String[] proj = { MediaStore.Images.Media.DATA };
				Cursor actualimagecursor = context.managedQuery(originalUri,
						proj, null, null, null);
				int actual_image_column_index = actualimagecursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				actualimagecursor.moveToFirst();

				String img_path = actualimagecursor
						.getString(actual_image_column_index);
				// File file = new File(img_path);
				// Uri fileUri = Uri.fromFile(file);
				Log.d(tag, "img_path:" + img_path);
				return img_path;
			} else {
				String lastpath = originalUri.toString().replace("file://", "");
				return lastpath;

			}
		} else {
			return null;
		}
	}

	/**
	 * 获取剪切后的图片
	 */
	public static void getImageClipIntent(Activity context, int requestCode,
			Uri uri) {
		Intent intent = null;
		if (uri == null) {
			intent = new Intent(Intent.ACTION_GET_CONTENT, null);
			intent.setType("image/*");
		} else {
			intent = new Intent("com.android.camera.action.CROP", null);
			intent.setDataAndType(uri, "image/*");
		}
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);// 裁剪框比例
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 250);// 输出图片大小
		intent.putExtra("outputY", 250);
		intent.putExtra("return-data", true);
		context.startActivityForResult(intent, requestCode);
	}
}
