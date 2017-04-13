package com.hokol.test;

import android.view.View;

import com.hokol.test.common.BaseTestFragment;
import com.hokol.test.http.area.TestAreaActivity;
import com.hokol.test.http.dynamic.TestDynamicActivity;
import com.hokol.test.http.home.TestHomeActivity;
import com.hokol.test.http.login.TestLoginActivity;
import com.hokol.test.http.mine.TestUserActivity;
import com.hokol.test.http.news.TestNewsActivity;
import com.hokol.test.http.setting.TestSettingActivity;
import com.hokol.test.http.task.TestTaskActivity;

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

		addButton("主页", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TestHomeActivity.actionStart(getContext());
			}
		});

		addButton("任务", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TestTaskActivity.actionStart(getContext());
			}
		});

		addButton("我的页面", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TestUserActivity.actionStart(getContext());
			}
		});

		addButton("设置", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TestSettingActivity.actionStart(getContext());
			}
		});

		addButton("地区", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TestAreaActivity.actionStart(getContext());
			}
		});
	}
}
