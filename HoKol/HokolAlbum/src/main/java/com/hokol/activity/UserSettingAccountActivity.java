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
		// 结束
		viewHolder.setOnClickListener(R.id.iv_user_setting_account_finish, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 账号安全
		viewHolder.setOnClickListener(R.id.ll_user_setting_safety, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserSettingAccountSafetyActivity.actionStart(UserSettingAccountActivity.this);
			}
		});

		// 账号绑定
		viewHolder.setOnClickListener(R.id.ll_user_setting_account_bind, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserSettingAccountBindActivity.actionStart(UserSettingAccountActivity.this);
			}
		});

		// 修改密码
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
