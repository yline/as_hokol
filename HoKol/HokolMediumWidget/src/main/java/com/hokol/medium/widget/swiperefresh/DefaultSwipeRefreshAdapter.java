package com.hokol.medium.widget.swiperefresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.hokol.medium.widget.R;
import com.hokol.medium.widget.progressbar.CircleProgressBar;

/**
 * 默认的Adapter; 不给外界用
 *
 * @author yline 2017/3/17 --> 18:12
 * @version 1.0.0
 */
class DefaultSwipeRefreshAdapter extends BaseSwipeRefreshAdapter
{
	private CircleProgressBar circleProgressBar;

	public DefaultSwipeRefreshAdapter(Context context)
	{
		super(context);
	}

	@Override
	protected void onCreate(float dragDistance, float targetDistance)
	{
		float progress = dragDistance / targetDistance;
		progress = progress < 0.0f ? 0.0f : progress;
		progress = progress > 1.0f ? 1.0f : progress;
		circleProgressBar.setProgressRotation(progress);
	}

	@Override
	protected void onStart(boolean enable)
	{
		if (enable)
		{
			circleProgressBar.setProgressRotation(0);
			circleProgressBar.startProgress();
		}
	}

	@NonNull
	@Override
	protected View getView(Context context)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.widget_super_swipe_default, null);
		circleProgressBar = (CircleProgressBar) view.findViewById(R.id.widget_progress_bar);
		circleProgressBar.setProgressAutoStart(false);
		circleProgressBar.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
		return view;
	}
}
