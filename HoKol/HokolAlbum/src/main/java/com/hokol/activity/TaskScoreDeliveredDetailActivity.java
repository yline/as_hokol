package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;

public class TaskScoreDeliveredDetailActivity extends BaseAppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_score_delivered_detail);

		findViewById(R.id.iv_task_score_delivered_detail_cancel).setOnClickListener(new View.OnClickListener()
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
		context.startActivity(new Intent(context, TaskScoreDeliveredDetailActivity.class));
	}
}
