package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;

/**
 * 用户信用 界面
 *
 * @author yline 2017/6/3 -- 10:21
 * @version 1.0.0
 */
public class UserInfoCreditActivity extends BaseAppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_credit);
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserInfoCreditActivity.class));
	}
}
