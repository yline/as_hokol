package com.hokol.test.http.area;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VAreaAllBean;
import com.hokol.test.common.BaseTestActivity;
import com.lib.http.XHttpAdapter;


public class TestAreaActivity extends BaseTestActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		addButton("获取地区", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				XHttpUtil.doAreaAll(new XHttpAdapter<VAreaAllBean>()
				{
					@Override
					public void onSuccess(VAreaAllBean vAreaAllBean)
					{

					}
				});
			}
		});
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestAreaActivity.class));
	}
}
