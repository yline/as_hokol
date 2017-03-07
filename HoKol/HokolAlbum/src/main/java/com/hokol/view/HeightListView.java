package com.hokol.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.hokol.base.utils.UIScreenUtil;

public class HeightListView extends ListView
{
	public HeightListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		// 设置最大高度
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(UIScreenUtil.getScreenHeight(getContext()) / 2, MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
