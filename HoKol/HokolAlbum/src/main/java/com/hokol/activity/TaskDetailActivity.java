package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;

/**
 * 任务详情
 *
 * @author yline 2017/4/1 -- 17:54
 * @version 1.0.0
 */
public class TaskDetailActivity extends BaseAppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_detail);
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TaskDetailActivity.class));
	}
}
