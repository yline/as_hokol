package com.hokol.activity;

import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.yline.base.BaseAppCompatActivity;

/**
 * 登入流程；登录和注册选择界面
 *
 * @author yline 2017/2/8 --> 10:47
 * @version 1.0.0
 */
public class EnterChoiceActivity extends BaseAppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_choice);

		initView();
	}

	private void initView()
	{
		findViewById(R.id.btn_enter_choice_register).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EnterRegisterPhoneActivity.actionStart(EnterChoiceActivity.this);
			}
		});

		findViewById(R.id.btn_enter_choice_login).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EnterLoginPhonePwdActivity.actionStart(EnterChoiceActivity.this);
			}
		});

		findViewById(R.id.circle_enter_choice_wechat).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("we chat");
			}
		});
	}

}
