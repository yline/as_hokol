package cn.test.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.test.BaseTestActivity;

public class TestLoginActivity extends BaseTestActivity
{
	private TestLoginHelper testLoginHelper = new TestLoginHelper();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// 手机号+密码登陆
		addButton("手机号+密码登陆", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String username = "13656786656";
				String password = "lj123456";
				testLoginHelper.doPhoneLogin(username, password);
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestLoginActivity.class));
	}
}
