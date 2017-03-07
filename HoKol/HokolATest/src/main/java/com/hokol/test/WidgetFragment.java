package com.hokol.test;

import android.view.View;

import com.hokol.test.widget.ADActivity;

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
	}
}
