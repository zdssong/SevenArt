package com.sevenart.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sevenart.R;
import com.sevenart.contants.ArtContants;
import com.sevenart.models.Information;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ArtMainListAdapter extends BaseAdapter {

	private Information m_information;
	private String m_PistureUrl;

	private List<Information> m_Informations;
	private LayoutInflater m_Inflater;

	private ImageLoadingListener animateFirstListener;
	private DisplayImageOptions options;

	private boolean[] m_IiFrist;

	public ArtMainListAdapter(List<Information> informations, Context context) {
		this.m_Informations = informations;
		m_Inflater = LayoutInflater.from(context);

		m_IiFrist = new boolean[informations.size()];
		for (int i = 0; i < m_IiFrist.length; i++) {
			m_IiFrist[i] = true;
		}

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.nopicture)
				.showImageForEmptyUri(R.drawable.nopicture)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(0)).build();

		animateFirstListener = new LoadingListener();

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_Informations == null ? 0 : m_Informations.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return m_Informations.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		if (view == null) {
			view = m_Inflater.inflate(R.layout.list_item, null);
			viewHolder = new ViewHolder();
			initWidget(viewHolder, view);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		m_information = m_Informations.get(position);
		m_PistureUrl = m_information.getLitpic();
		// 获取position对应的数据
		viewHolder.comment_count.setText("评论" + m_information.getFc());
		viewHolder.publish_time.setText(m_information.getPubdate());
		viewHolder.comment_count.setVisibility(View.VISIBLE);
		String channel = m_information.getChannel();
		if (channel == ArtContants.PICTURE_CHANNEL
				|| channel.equals(ArtContants.PICTURE_CHANNEL)
				|| channel == ArtContants.SHOW_CHANNEL
				|| channel.equals(ArtContants.SHOW_CHANNEL)
				|| channel == ArtContants.AUCTION_CHANNEL
				|| channel.equals(ArtContants.AUCTION_CHANNEL)) {
			viewHolder.item_title1.setVisibility(View.VISIBLE);
			viewHolder.item_title1.setText(m_information.getTitle());
			viewHolder.right_image.setVisibility(View.GONE);
			viewHolder.item_title.setVisibility(View.GONE);
			viewHolder.item_image_layout.setVisibility(View.VISIBLE);
			int length = m_information.getImgurls().length;
			if (length == 1) {
				ImageLoader.getInstance().displayImage(
						m_information.getImgurls()[0], viewHolder.item_image_0,
						options, animateFirstListener);
				viewHolder.item_image_1.setVisibility(View.GONE);
				viewHolder.item_image_2.setVisibility(View.GONE);
			}
			if (length == 2) {
				ImageLoader.getInstance().displayImage(
						m_information.getImgurls()[0], viewHolder.item_image_0,
						options, animateFirstListener);
				ImageLoader.getInstance().displayImage(
						m_information.getImgurls()[1], viewHolder.item_image_1,
						options, animateFirstListener);
				viewHolder.item_image_2.setVisibility(View.GONE);
			}
			if (length == 3) {
				ImageLoader.getInstance().displayImage(
						m_information.getImgurls()[0], viewHolder.item_image_0,
						options, animateFirstListener);
				ImageLoader.getInstance().displayImage(
						m_information.getImgurls()[1], viewHolder.item_image_1,
						options, animateFirstListener);
				ImageLoader.getInstance().displayImage(
						m_information.getImgurls()[2], viewHolder.item_image_2,
						options, animateFirstListener);
			}
		} else {
			viewHolder.item_title.setVisibility(View.VISIBLE);
			viewHolder.item_title1.setVisibility(View.GONE);
			viewHolder.item_title.setText(m_information.getTitle());
			viewHolder.item_image_layout.setVisibility(View.GONE);
			viewHolder.right_image.setVisibility(View.VISIBLE);
			ImageLoader.getInstance().displayImage(m_PistureUrl,
					viewHolder.right_image, options, animateFirstListener);
		}
		return view;
	}

	private void initWidget(ViewHolder viewHolder, View view) {
		viewHolder.item_title = (TextView) view.findViewById(R.id.item_title);
		viewHolder.item_title1 = (TextView) view.findViewById(R.id.item_title1);
		viewHolder.comment_count = (TextView) view
				.findViewById(R.id.comment_count);
		viewHolder.publish_time = (TextView) view
				.findViewById(R.id.publish_time);
		viewHolder.right_image = (ImageView) view
				.findViewById(R.id.right_image);
		viewHolder.item_image_layout = (LinearLayout) view
				.findViewById(R.id.item_image_layout);
		viewHolder.item_image_0 = (ImageView) view
				.findViewById(R.id.item_image_0);
		viewHolder.item_image_1 = (ImageView) view
				.findViewById(R.id.item_image_1);
		viewHolder.item_image_2 = (ImageView) view
				.findViewById(R.id.item_image_2);
	}

	public List<Information> getM_Informations() {
		return m_Informations;
	}

	public void setM_Informations(List<Information> m_Informations) {
		// this.m_Informations.clear();
		this.m_Informations = m_Informations;
	}

	private static class ViewHolder {
		// title
		public TextView item_title;
		public TextView item_title1;
		// 评论数量
		public TextView comment_count;
		// 发布时间
		public TextView publish_time;
		// 右边图片
		public ImageView right_image;
		// 3张图片布局
		public LinearLayout item_image_layout; // 3张图片时候的布局
		public ImageView item_image_0;
		public ImageView item_image_1;
		public ImageView item_image_2;
	}

	private static class LoadingListener extends SimpleImageLoadingListener {

		private static List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingStarted(String imageUri, View view) {
			// TODO Auto-generated method stub
			super.onLoadingStarted(imageUri, view);
		}

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean isFristDisplay = !displayedImages.contains(imageUri);
				if (isFristDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}

	}

}
