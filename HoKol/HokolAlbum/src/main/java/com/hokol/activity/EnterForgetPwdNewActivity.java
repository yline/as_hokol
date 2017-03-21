package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.base.common.BaseAppCompatActivity;

/**
 * 设置新密码
 *
 * @author yline 2017/3/20 -- 18:36
 * @version 1.0.0
 */
public class EnterForgetPwdNewActivity extends BaseAppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_forget_pwd_new);

		findViewById(R.id.btn_enter_forget_pwd_new_commit).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("设置成功，进入主页面");
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, EnterForgetPwdNewActivity.class));
	}
}
