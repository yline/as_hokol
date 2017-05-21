package com.hokol.medium.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.yline.widget.ad.WidgetAD;

/**
 * 支持定制 + 动态加载图片
 *
 * @author yline 2017/2/14 --> 15:08
 * @version 1.0.0
 */
public class ADWidget extends WidgetAD
{
	@Override
	public View start(Context context, int count)
	{
		return super.start(context, count);
	}

	@Override
	public void setListener(OnPageListener listener)
	{
		super.setListener(listener);
	}

	/* ---------------------------------------------------- 从这里开始设置参数；这些参数都是可以被重写的 ---------------------------------------------------- */

	@Override
	protected boolean isRecycle()
	{
		return true;
	}

	@Override
	protected boolean isRecycleRight()
	{
		return true;
	}

	@Override
	protected boolean isAutoRecycle()
	{
		return true;
	}

	@Override
	protected int getRecycleAutoTime()
	{
		return 4500;
	}

	@Override
	protected int getPointSizeAfter()
	{
		return 15;
	}

	@Override
	protected int getPointSizeBefore()
	{
		return 15;
	}

	@Override
	protected int getPointLeftMargin()
	{
		return 20;
	}

	@Override
	protected int getPointRightMargin()
	{
		return 0;
	}

	@Override
	protected int getPointTopMargin()
	{
		return 0;
	}

	@Override
	protected int getPointBottomMargin()
	{
		return 0;
	}

	@Override
	protected int getStartPosition()
	{
		return 0;
	}

	@Override
	protected int getViewPagerWidth()
	{
		return ViewGroup.LayoutParams.MATCH_PARENT;
	}

	@Override
	protected int getViewPagerHeight()
	{
		return ViewGroup.LayoutParams.WRAP_CONTENT;
	}

	@Override
	protected int getPointResource()
	{
		return R.drawable.widget_ad_point;
	}

	@Override
	protected int getLayoutResource()
	{
		return R.layout.widget_ad;
	}
}
