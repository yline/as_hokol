package com.hokol.test;

import android.view.View;

import com.hokol.test.common.BaseTestFragment;
import com.hokol.test.widget.ADActivity;
import com.hokol.test.widget.GlideActivity;
import com.hokol.test.widget.RecycleActivity;

public class WidgetFragment extends BaseTestFragment
{
	@Override
	protected void test()
	{
		addButton("AD 广告", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				ADActivity.actionStart(getContext());
			}
		});

		addButton("Recycler", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				RecycleActivity.actionStart(getContext());
			}
		});

		addButton("Glide", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				GlideActivity.actionStart(getContext());
			}
		});
	}
}
