package cn.test;


import android.os.Bundle;
import android.view.View;

import cn.test.login.TestLoginActivity;
import cn.test.news.TestNewsActivity;

/**
 * 测试入口程序
 *
 * @author yline 2017/3/3 --> 15:19
 * @version 1.0.0
 */
public class TestInnerActivity extends BaseTestActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		addButton("Login 测试", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TestLoginActivity.actionStart(TestInnerActivity.this);
			}
		});

		addButton("News 测试", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TestNewsActivity.actionStart(TestInnerActivity.this);
			}
		});
	}
}
