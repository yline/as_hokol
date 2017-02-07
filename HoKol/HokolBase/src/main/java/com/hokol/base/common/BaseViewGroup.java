package com.hokol.base.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.hokol.base.application.BaseApplication;
import com.hokol.base.log.LogFileUtil;


public class BaseViewGroup extends ViewGroup
{
	public BaseViewGroup(Context context)
	{
		this(context, null);
	}

	public BaseViewGroup(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public BaseViewGroup(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		BaseApplication.addViewForRecord(this);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		// TODO Auto-generated method stub
		super.layout(l, t, r, b);
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
