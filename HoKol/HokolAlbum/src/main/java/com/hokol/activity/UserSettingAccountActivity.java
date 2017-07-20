package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.recycler.holder.ViewHolder;

public class UserSettingAccountActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserSettingAccountActivity.class));
	}

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
				String phoneNumber = AppStateManager.getInstance().getUserTel(UserSettingAccountActivity.this);
				if (com.yline.view.text.helper.TextDecorateUtil.isPhonePwdMatch(phoneNumber))
				{
					UserSettingAccountSafetyActivity.actionStart(UserSettingAccountActivity.this, phoneNumber);
				}
				else
				{
					SDKManager.toast("您还未绑定手机号");
				}
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
				String userId = AppStateManager.getInstance().getUserLoginId(UserSettingAccountActivity.this);
				if (!TextUtils.isEmpty(userId))
				{
					EnterModifyPwdActivity.actionStart(UserSettingAccountActivity.this, userId);
				}
				else
				{
					SDKManager.toast("您还未登录");
				}
			}
		});
	}
}
