package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.common.ViewHolder;

public class UserSettingActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_setting);

		viewHolder = new ViewHolder(this);
		initViewClick();
	}

	private void initViewClick()
	{
		viewHolder.setOnClickListener(R.id.iv_user_setting_finish, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		viewHolder.setOnClickListener(R.id.ll_user_setting_account, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserSettingAccountActivity.actionStart(UserSettingActivity.this);
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserSettingActivity.class));
	}
}
