package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.common.ViewHolder;

/**
 * 任务详情
 *
 * @author yline 2017/4/1 -- 17:54
 * @version 1.0.0
 */
public class TaskDetailActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_detail);

		viewHolder = new ViewHolder(this);
		initView();
	}

	private void initView()
	{
		viewHolder.setOnClickListener(R.id.iv_task_detail_back, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TaskDetailActivity.class));
	}
}
