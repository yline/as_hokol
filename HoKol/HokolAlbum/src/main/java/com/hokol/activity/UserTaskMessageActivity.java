package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;

public class UserTaskMessageActivity extends BaseAppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_task_message);

		findViewById(R.id.rl_task_message).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserMessageSystemActivity.actionStart(UserTaskMessageActivity.this);
			}
		});

		findViewById(R.id.iv_task_message).setOnClickListener(new View.OnClickListener()
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
		context.startActivity(new Intent(context, UserTaskMessageActivity.class));
	}
}
