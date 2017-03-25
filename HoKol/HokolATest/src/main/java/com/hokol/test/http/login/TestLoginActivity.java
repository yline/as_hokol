package com.hokol.test.http.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttp;
import com.hokol.medium.http.bean.VLoginPhonePasswordBean;
import com.hokol.medium.http.bean.WLoginPhonePasswordBean;
import com.hokol.test.common.BaseTestActivity;

/**
 * 登录接口
 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
 * 手机号+密码登陆 --> login --> url_login_pwd --> WLoginPhonePasswordBean - VLoginPhonePasswordBean --> OK
 */
public class TestLoginActivity extends BaseTestActivity
{
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
				String httpUrl = HttpConstant.url_login_pwd;

				final WLoginPhonePasswordBean requestBean = new WLoginPhonePasswordBean(username, password);
				// 这样的方法,并不会被执行
				new XHttp<VLoginPhonePasswordBean>()
				{
					@Override
					protected Object getRequestPostParam()
					{
						return requestBean;
					}

					@Override
					public void onSuccess(VLoginPhonePasswordBean responsePhoneLoginBean)
					{

					}
				}.doRequest(httpUrl, VLoginPhonePasswordBean.class);
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestLoginActivity.class));
	}
}
