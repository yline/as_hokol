package com.hokol.medium.viewcustom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.yline.view.custom.ViewCircle;

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
	public ScaleType getScaleType()
	{
		return super.getScaleType();
	}

	@Override
	public void setScaleType(ScaleType scaleType)
	{
		super.setScaleType(scaleType);
	}

	@Override
	public int getBorderColor()
	{
		return super.getBorderColor();
	}

	@Override
	public void setBorderColor(int borderColor)
	{
		super.setBorderColor(borderColor);
	}

	@Override
	public int getBorderWidth()
	{
		return super.getBorderWidth();
	}

	@Override
	public void setBorderWidth(int borderWidth)
	{
		super.setBorderWidth(borderWidth);
	}

	@Override
	public void setImageBitmap(Bitmap bm)
	{
		super.setImageBitmap(bm);
	}

	@Override
	public void setImageDrawable(Drawable drawable)
	{
		super.setImageDrawable(drawable);
	}

	@Override
	public void setImageResource(int resId)
	{
		super.setImageResource(resId);
	}
}
