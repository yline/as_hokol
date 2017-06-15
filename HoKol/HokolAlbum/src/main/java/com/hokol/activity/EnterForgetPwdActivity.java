package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.util.TextDecorateUtil;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.recycler.holder.ViewHolder;

public class EnterForgetPwdActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_forget_pwd);

		viewHolder = new ViewHolder(this);

		initView();
		initViewClick();
	}

	private void initView()
	{
		EditText editTextPhone = viewHolder.get(R.id.et_enter_forget_pwd_username);
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

		EditText editTextIdentify = viewHolder.get(R.id.et_enter_forget_identify);
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

		EditText editTextPwd = viewHolder.get(R.id.et_enter_forget_pwd_new_pwd);
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
		viewHolder.setOnClickListener(R.id.iv_enter_forget_pwd_cancel, new View.OnClickListener()
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
		context.startActivity(new Intent(context, EnterForgetPwdActivity.class));
	}
}
