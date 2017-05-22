package com.hokol.medium.widget.progressbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;

import com.yline.view.custom.progress.ViewCircleProgressBar;

public class CircleProgressBar extends ViewCircleProgressBar
{
	public CircleProgressBar(Context context)
	{
		super(context);
	}

	public CircleProgressBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 给外界设置的量 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	
	@Override
	public void updateSize()
	{
		super.updateSize();
	}

	@Override
	public void setOnAnimationListener(Animation.AnimationListener listener)
	{
		super.setOnAnimationListener(listener);
	}

	@Override
	public void setTextDraw(boolean isTextDraw)
	{
		super.setTextDraw(isTextDraw);
	}

	@Override
	public boolean isArrowShow()
	{
		return super.isArrowShow();
	}

	@Override
	public void setArrowScale(float scale)
	{
		super.setArrowScale(scale);
	}

	@Override
	public void setRingAlpha(int alpha)
	{
		super.setRingAlpha(alpha);
	}

	@Override
	public void start()
	{
		super.start();
	}

	@Override
	public void stop()
	{
		super.stop();
	}

	@Override
	public void setProgressRotation(float rotation)
	{
		super.setProgressRotation(rotation);
	}

	@Override
	public void setStartEndTrim(float startAngle, float endAngle)
	{
		super.setStartEndTrim(startAngle, endAngle);
	}

	@Override
	public void setColorSchemeResources(int... colorResIds)
	{
		super.setColorSchemeResources(colorResIds);
	}

	@Override
	public void setColorSchemeColors(int... colors)
	{
		super.setColorSchemeColors(colors);
	}

	@Override
	public void setBackgroundColor(int color)
	{
		super.setBackgroundColor(color);
	}
}
