package com.hokol.test;

import android.view.View;

import com.hokol.test.http.login.TestLoginActivity;
import com.hokol.test.http.news.TestNewsActivity;

public class HttpFragment extends BaseTestFragment
{
	@Override
	protected void test()
	{
		addButton("Login", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TestLoginActivity.actionStart(getContext());
			}
		});

		addButton("News", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TestNewsActivity.actionStart(getContext());
			}
		});
	}
}
