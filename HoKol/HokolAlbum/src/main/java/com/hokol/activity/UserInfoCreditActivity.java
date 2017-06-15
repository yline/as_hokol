package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.recycler.holder.ViewHolder;

/**
 * 用户信用 界面
 *
 * @author yline 2017/6/3 -- 10:21
 * @version 1.0.0
 */
public class UserInfoCreditActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_credit);

		viewHolder = new ViewHolder(this);
		initView();
	}
	
	private void initView()
	{
		viewHolder.setOnClickListener(R.id.iv_task_assigned_cancel, new View.OnClickListener()
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
		context.startActivity(new Intent(context, UserInfoCreditActivity.class));
	}
}
