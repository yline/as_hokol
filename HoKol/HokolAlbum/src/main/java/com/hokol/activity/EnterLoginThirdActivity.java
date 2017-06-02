package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.util.TextDecorateUtil;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.common.ViewHolder;

public class EnterLoginThirdActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_login_third);
		
		viewHolder = new ViewHolder(this);
		
		initView();
		initViewClick();
	}
	
	private void initView()
	{
		EditText editTextPhone = viewHolder.get(R.id.et_enter_login_third_username);
		TextDecorateUtil.isPhoneMatch(editTextPhone, new TextDecorateUtil.OnEditMatchCallback()
		{
			@Override
			public void onTextChange(boolean isMatch)
			{
				if (isMatch)
				{
					IApplication.toast("手机号符合");
				}
			}
		});
		
		EditText editTextIdentify = viewHolder.get(R.id.et_enter_login_third_identify);
		TextDecorateUtil.isIdentifyMatch(editTextIdentify, new TextDecorateUtil.OnEditMatchCallback()
		{
			@Override
			public void onTextChange(boolean isMatch)
			{
				if (isMatch)
				{
					IApplication.toast("验证码符合");
				}
			}
		});
		
		EditText editTextPwd = viewHolder.get(R.id.et_enter_login_third_new_pwd);
		TextDecorateUtil.isPhonePwdMatch(editTextPwd, new TextDecorateUtil.OnEditMatchCallback()
		{
			@Override
			public void onTextChange(boolean isMatch)
			{
				if (isMatch)
				{
					IApplication.toast("登录密码符合");
				}
			}
		});
	}
	
	private void initViewClick()
	{
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, EnterLoginThirdActivity.class));
	}
}
