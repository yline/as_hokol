package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.recycler.holder.ViewHolder;

/**
 * 账号安全
 *
 * @author yline 2017/5/31 -- 14:44
 * @version 1.0.0
 */
public class UserSettingAccountSafetyActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_setting_account_safety);

		viewHolder = new ViewHolder(this);
		initView();
	}

	private void initView()
	{
		// 结束
		viewHolder.setOnClickListener(R.id.iv_user_setting_account_safety_finish, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		viewHolder.setOnClickListener(R.id.btn_user_setting_account_safety_update_phone, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EnterUpdatePhoneActivity.actionStart(UserSettingAccountSafetyActivity.this);
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserSettingAccountSafetyActivity.class));
	}
}
