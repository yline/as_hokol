package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;

/**
 * 确认交易 界面
 *
 * @author yline 2017/6/2 -- 16:42
 * @version 1.0.0
 */
public class TaskAssignedTradeSureDetailActivity extends BaseAppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assigned_trade_sure_detail);

		findViewById(R.id.iv_task_assigned_trade_sure_detail_cancel).setOnClickListener(new View.OnClickListener()
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
		context.startActivity(new Intent(context, TaskAssignedTradeSureDetailActivity.class));
	}
}
