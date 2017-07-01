package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hokol.R;
import com.hokol.application.AppStateManager;
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
	private static final String KeySafetyPhoneNumber = "PhoneNumber";

	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_setting_account_safety);

		viewHolder = new ViewHolder(this);
		initView();
		initData();
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

		// 更换手机号
		viewHolder.setOnClickListener(R.id.btn_user_setting_account_safety_update_phone, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = AppStateManager.getInstance().getUserLoginId(UserSettingAccountSafetyActivity.this);
				if (!TextUtils.isEmpty(userId))
				{
					EnterUpdatePhoneActivity.actionStart(UserSettingAccountSafetyActivity.this, userId);
				}
			}
		});
	}

	private void initData()
	{
		String phoneNumber = getIntent().getStringExtra(KeySafetyPhoneNumber);
		if (phoneNumber.length() == 11)
		{
			viewHolder.setText(R.id.tv_user_setting_account_safety_update_phone, String.format("已绑定手机号%s*****%s", phoneNumber.substring(0, 3), phoneNumber.substring(8, 11)));
		}
	}
	
	public static void actionStart(Context context, String phoneNumber)
	{
		context.startActivity(new Intent(context, UserSettingAccountSafetyActivity.class).putExtra(KeySafetyPhoneNumber, phoneNumber));
	}
}
