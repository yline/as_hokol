package com.hokol.medium.widget;

import android.content.Context;
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
	protected String getResultTitle(String first, List<String> second)
	{
		return super.getResultTitle(first, second);
	}
}
