package com.hokol.test.http.dynamic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hokol.test.BaseTestActivity;

public class TestDynamicActivity extends BaseTestActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestDynamicActivity.class));
	}
}
