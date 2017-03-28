package com.hokol.test.http.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.test.common.BaseTestActivity;

/**
 * 主页接口
 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
 * 请求用户粉丝的信息（多条） --> user_fans_info --> url_home_main --> WHomeMainBean - VHomeMainBean --> OK
 */
public class TestUserActivity extends BaseTestActivity
{
	private void testuser_fans_info()
	{
		addButton("请求用户粉丝的信息（多条）", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				/*new XHttp<VUserFansAllBean>()
				{
					@Override
					public void onSuccess(VUserFansAllBean vUserFansAllBean)
					{

					}
				}.doRequest();*/
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);


	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestUserActivity.class));
	}
}
