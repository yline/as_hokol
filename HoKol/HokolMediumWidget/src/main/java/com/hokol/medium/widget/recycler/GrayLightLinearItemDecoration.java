package com.hokol.medium.widget.recycler;

import android.content.Context;

import com.hokol.medium.widget.R;
import com.yline.view.apply.SimpleLinearItemDecoration;

public class GrayLightLinearItemDecoration extends SimpleLinearItemDecoration
{
	public GrayLightLinearItemDecoration(Context context)
	{
		super(context);
	}

	@Override
	protected int getDivideResourceId()
	{
		return R.drawable.widget_solid_graylight_size_medium;
	}
}
