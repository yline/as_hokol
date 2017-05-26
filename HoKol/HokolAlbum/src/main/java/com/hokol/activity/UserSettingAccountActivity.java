package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.common.ViewHolder;

public class UserSettingAccountActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_setting_account);

		viewHolder = new ViewHolder(this);
		initView();
	}

	private void initView()
	{
		viewHolder.setOnClickListener(R.id.iv_user_setting_account_finish, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		viewHolder.setOnClickListener(R.id.ll_user_setting_account_bind, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserSettingAccountBindActivity.actionStart(UserSettingAccountActivity.this);
			}
		});

		viewHolder.setOnClickListener(R.id.ll_user_setting_account_key, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EnterModifyPwdActivity.actionStart(UserSettingAccountActivity.this);
			}
		});
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserSettingAccountActivity.class));
	}
}
