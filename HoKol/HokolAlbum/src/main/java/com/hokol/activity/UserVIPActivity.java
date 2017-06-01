package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.common.ViewHolder;

public class UserVIPActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_vip);

		viewHolder = new ViewHolder(this);
		initView();
	}

	private void initView()
	{
		// 返回按钮
		viewHolder.setOnClickListener(R.id.iv_user_setting_account_bind_finish, new View.OnClickListener()
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
		context.startActivity(new Intent(context, UserVIPActivity.class));
	}
}
