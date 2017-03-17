package com.hokol.medium.widget.swiperefresh;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.hokol.base.log.LogFileUtil;

public class CircleSwipeAdapter extends BaseSwipeRefreshAdapter
{
	private CircleProgressView defaultProgressView;

	public CircleSwipeAdapter(Context context)
	{
		super(context);
	}

	@Override
	protected void onCreate(float dragDistance, float targetDistance)
	{
		LogFileUtil.v("dragDistance = " + dragDistance + ",targetDistance = " + targetDistance);

		float alpha = dragDistance / targetDistance;
		if (alpha >= 1.0f)
		{
			alpha = 1.0f;
		}
		ViewCompat.setScaleX(defaultProgressView, alpha);
		ViewCompat.setScaleY(defaultProgressView, alpha);
		ViewCompat.setAlpha(defaultProgressView, alpha);
	}

	@Override
	protected void onStart(boolean enable)
	{
	}

	@Override
	public void animating()
	{
		ViewCompat.setAlpha(defaultProgressView, 1.0f);
		defaultProgressView.setOnDraw(true);
		new Thread(defaultProgressView).start();
		super.animating();
	}

	@Override
	protected View getView(Context context)
	{
		defaultProgressView = new CircleProgressView(context);
		defaultProgressView.setVisibility(View.VISIBLE);
		defaultProgressView.setOnDraw(false);
		return defaultProgressView;
	}
}
