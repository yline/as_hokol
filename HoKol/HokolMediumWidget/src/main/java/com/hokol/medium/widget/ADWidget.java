package com.hokol.medium.widget;

import android.content.Context;
import android.view.View;

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
}
