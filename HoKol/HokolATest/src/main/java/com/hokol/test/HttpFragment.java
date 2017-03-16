package com.hokol.test;

import android.view.View;

import com.hokol.test.common.BaseTestFragment;
import com.hokol.test.http.dynamic.TestDynamicActivity;
import com.hokol.test.http.login.TestLoginActivity;
import com.hokol.test.http.news.TestNewsActivity;

public class HttpFragment extends BaseTestFragment
{
	@Override
	protected void test()
	{
		addButton("登录", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TestLoginActivity.actionStart(getContext());
			}
		});

		addButton("新闻", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TestNewsActivity.actionStart(getContext());
			}
		});

		addButton("动态", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TestDynamicActivity.actionStart(getContext());
			}
		});
	}
}
