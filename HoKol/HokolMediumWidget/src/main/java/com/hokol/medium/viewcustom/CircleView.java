package com.hokol.medium.viewcustom;

import android.content.Context;
import android.util.AttributeSet;

import com.yline.view.circle.ViewCircle;

public class CircleView extends ViewCircle
{
	public CircleView(Context context)
	{
		super(context);
	}

	public CircleView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public CircleView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public void setXBorderColor(int borderColor)
	{
		super.setXBorderColor(borderColor);
	}

	@Override
	public void setXBorderWidth(int borderWidth)
	{
		super.setXBorderWidth(borderWidth);
	}
}
