package com.hokol.base.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TabWidget;

import com.hokol.base.application.BaseApplication;
import com.hokol.base.log.LogFileUtil;


public class BaseTabWidget extends TabWidget
{

	public BaseTabWidget(Context context)
	{
		this(context, null);
	}

	public BaseTabWidget(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public BaseTabWidget(Context context, AttributeSet attrs, int defStyleAttr)
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
