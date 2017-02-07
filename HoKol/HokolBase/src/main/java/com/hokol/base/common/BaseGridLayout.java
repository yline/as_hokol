package com.hokol.base.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

import com.hokol.base.application.BaseApplication;
import com.hokol.base.log.LogFileUtil;


@SuppressLint("NewApi")
public class BaseGridLayout extends GridLayout
{
	public BaseGridLayout(Context context)
	{
		this(context, null);
	}

	public BaseGridLayout(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public BaseGridLayout(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		BaseApplication.addViewForRecord(this);
	}

	@Override
	protected void onFinishInflate()
	{
		super.onFinishInflate();
		LogFileUtil.m("finishInflate:" + getClass().getSimpleName());
	}

	@Override
	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		BaseApplication.removeViewForRecord(this);
	}
}
