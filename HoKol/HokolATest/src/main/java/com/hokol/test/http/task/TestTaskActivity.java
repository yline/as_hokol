package com.hokol.test.http.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hokol.test.common.BaseTestActivity;

public class TestTaskActivity extends BaseTestActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestTaskActivity.class));
	}
}
