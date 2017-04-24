package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;

/**
 * 发布任务
 *
 * @author yline 2017/4/1 -- 17:53
 * @version 1.0.0
 */
public class TaskPublishActivity extends BaseAppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_publish);
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TaskPublishActivity.class));
	}
}
