package com.hokol.medium.widget;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.yline.widget.menu.secondary.WidgetSecondary;

import java.util.List;

public class SecondaryWidget extends WidgetSecondary
{
	public SecondaryWidget(Context context, List<View> viewList)
	{
		super(context, viewList);
	}

	public SecondaryWidget(Context context, ViewGroup viewGroup)
	{
		super(context, viewGroup);
	}

	/* ---------------------------------------------------- 从这里开始设置参数；这些参数都是可以被重写的 ---------------------------------------------------- */
	@Override
	protected int getWidgetResourceId()
	{
		return R.layout.widget_secondary;
	}

	@Override
	protected String getFirstHeadItemContent()
	{
		return DefaultFirst;
	}

	@Override
	@LayoutRes
	protected int getFirstItemResource()
	{
		return R.layout.widget_secondary_item_first;
	}

	@Override
	@ColorInt
	protected int getFirstColorBgSelected()
	{
		return 0xfff2f2f2;
	}

	@Override
	@ColorInt
	protected int getFirstColorBgUnselected()
	{
		return ContextCompat.getColor(sContext, android.R.color.white);
	}

	@Override
	@ColorInt
	protected int getFirstColorTextSelected()
	{
		return ContextCompat.getColor(sContext, R.color.hokolRed);
	}

	@Override
	@ColorInt
	protected int getFirstColorTextUnselected()
	{
		return ContextCompat.getColor(sContext, R.color.hokolGrayDrak);
	}

	@Override
	protected String getSecondHeadItemContent()
	{
		return DefaultFirst;
	}

	@Override
	@LayoutRes
	protected int getSecondItemResource()
	{
		return R.layout.widget_secondary_item_second;
	}

	@Override
	@ColorInt
	protected int getSecondColorBgSelected()
	{
		return ContextCompat.getColor(sContext, android.R.color.transparent);
	}

	@Override
	@ColorInt
	protected int getSecondColorBgUnselected()
	{
		return ContextCompat.getColor(sContext, android.R.color.transparent);
	}

	@Override
	@ColorInt
	protected int getSecondColorTextSelected()
	{
		return ContextCompat.getColor(sContext, R.color.hokolRed);
	}

	@Override
	@ColorInt
	protected int getSecondColorTextUnselected()
	{
		return ContextCompat.getColor(sContext, R.color.hokolGrayDrak);
	}

	@Override
	@DrawableRes
	protected int getSecondDrawableSelected()
	{
		return R.drawable.widget_item_secondary_second_selected;
	}

	@Override
	@DrawableRes
	protected int getSecondDrawableUnselected()
	{
		return R.drawable.widget_item_secondary_second_unselected;
	}

	@Override
	protected String getResultTitle(String first, List<String> second)
	{
		if (TextUtils.isEmpty(first) || first.equals(getSecondHeadItemContent()))
		{
			return DefaultTitle;
		}

		if (null == second || second.size() == 0)
		{
			return first;
		}
		else if (second.size() == 1)
		{
			if (second.get(0).equals(getSecondHeadItemContent()))
			{
				return first;
			}
			return second.get(0);
		}
		else
		{
			return String.format("%s(%d)", first, second.size());
		}
	}
}
