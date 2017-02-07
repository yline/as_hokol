package com.hokol.base.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TabHost;

import com.hokol.base.application.BaseApplication;
import com.hokol.base.log.LogFileUtil;


public class BaseTabHost extends TabHost
{
	public BaseTabHost(Context context)
	{
		this(context, null);
	}

	public BaseTabHost(Context context, AttributeSet attrs)
	{
		super(context, attrs);
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
