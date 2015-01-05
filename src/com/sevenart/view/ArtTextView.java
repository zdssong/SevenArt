package com.sevenart.view;

import com.sevenart.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 自定义的TextView，用来显示课表，是带边框的TextView
 */

public class ArtTextView extends TextView {

	private Paint mBorderPaint;
	private boolean mTopBorder;
	private boolean mBottomBorder;
	private boolean mLeftBorder;
	private boolean mRightBorder;

	public ArtTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initZsxyTextView();
		mLeftBorder = true;
		mRightBorder = true;
	}

	@SuppressLint("Recycle")
	public ArtTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initZsxyTextView();
		// 对于我们自定义的类中，我们需要使用一个名为obtainStyledAttributes的方法来获取我们的定义。
		TypedArray params = context.obtainStyledAttributes(attrs,
				R.styleable.TestView);
		// 得到自定义控件的属性值。

		mTopBorder = params.getBoolean(R.styleable.TestView_topBorder, true);
		mLeftBorder = params.getBoolean(R.styleable.TestView_leftBorder, true);
		mBottomBorder = params.getBoolean(R.styleable.TestView_bottomBorder,
				true);
		mRightBorder = params
				.getBoolean(R.styleable.TestView_rightBorder, true);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mTopBorder == true)
			canvas.drawLine(0, 0, this.getWidth() - 1, 0, mBorderPaint);
		if (mRightBorder == true)
			canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1,
					this.getHeight() - 1, mBorderPaint);
		if (mBottomBorder == true)
			canvas.drawLine(this.getWidth() - 1, this.getHeight() - 1, 0,
					this.getHeight() - 1, mBorderPaint);
		if (mLeftBorder == true)
			canvas.drawLine(0, this.getHeight() - 1, 0, 0, mBorderPaint);
	}

	// 初始化相应的变量
	public void initZsxyTextView() {
		mBorderPaint = new Paint();
		mBorderPaint.setColor(0XFFFFFFFF);
	}

	// 设置上边界
	public void setTopBorder(boolean mTopBorder) {
		this.mTopBorder = mTopBorder;
	}

	// 设置下边界
	public void setBottomBorder(boolean mBottomBorder) {
		this.mBottomBorder = mBottomBorder;
	}

	// 设置左边界
	public void setLeftBorder(boolean mLeftBorder) {
		this.mLeftBorder = mLeftBorder;
	}

	// 设置右边界
	public void setRightBorder(boolean mRightBorder) {
		this.mRightBorder = mRightBorder;
	}

}
