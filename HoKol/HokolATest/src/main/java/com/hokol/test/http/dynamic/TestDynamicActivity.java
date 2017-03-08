package com.hokol.test.http.dynamic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.test.BaseTestActivity;

public class TestDynamicActivity extends BaseTestActivity
{
	private TestDynamicHelper dynamicHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		dynamicHelper = new TestDynamicHelper();

		addButton("点赞", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = "";
				String dynamicId = "";
				boolean praiseAction = true;
				dynamicHelper.doDynamicPraise(userId, dynamicId, praiseAction);
			}
		});

		addButton("关注的所有人的动态总和", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = "";
				int start = 0;
				int end = 1;
				dynamicHelper.doDynamicCare(userId, start, end);
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestDynamicActivity.class));
	}
}
