package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;

/**
 * 商家发布任务，评价
 *
 * @author yline 2017/6/1 -- 16:43
 * @version 1.0.0
 */
public class TaskAssignedEvaluateActivity extends BaseAppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assigned_evaluate);
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TaskAssignedEvaluateActivity.class));
	}
}
