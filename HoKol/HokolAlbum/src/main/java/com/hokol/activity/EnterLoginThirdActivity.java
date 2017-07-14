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

public class EnterLoginThirdActivity extends BaseAppCompatActivity
{
	private static final String KeyUserId = "UserId";
	
	private ViewHolder viewHolder;
	
	private String userId;
	
	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, EnterLoginThirdActivity.class).putExtra(KeyUserId, userId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_login_third);

		viewHolder = new ViewHolder(this);
		userId = getIntent().getStringExtra(KeyUserId);

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
		viewHolder.setOnClickListener(R.id.btn_register_phone_action_next, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				viewHolder.getText(R.id.et_enter_login_third_username);
			}
		});
	}
}
