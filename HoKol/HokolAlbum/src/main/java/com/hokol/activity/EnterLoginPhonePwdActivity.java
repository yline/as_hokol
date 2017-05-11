package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VLoginPhonePwdBean;
import com.hokol.medium.http.bean.WLoginPhonePwdBean;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;

/**
 * 登入流程，手机号+ 密码登录
 *
 * @author yline 2017/3/20 -- 15:02
 * @version 1.0.0
 */
public class EnterLoginPhonePwdActivity extends BaseAppCompatActivity
{
	private EditText etUserName;

	private EditText etPassWord;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_login_phone_pwd);

		initView();
	}

	private void initView()
	{
		etUserName = (EditText) findViewById(R.id.et_enter_login_phone_username);
		etPassWord = (EditText) findViewById(R.id.et_enter_login_phone_password);

		// 跳转到注册
		findViewById(R.id.tv_enter_login_phone_register).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EnterRegisterPhoneActivity.actionStart(EnterLoginPhonePwdActivity.this);
			}
		});

		// 忘记密码
		findViewById(R.id.et_enter_login_phone_pwd_forget).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EnterForgetPwdActivity.actionStart(EnterLoginPhonePwdActivity.this);
			}
		});

		// 登录
		findViewById(R.id.btn_enter_login_phone_login).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("正在登录...");

				String username = etUserName.getText().toString().trim();
				String password = etPassWord.getText().toString().trim();

				String httpUrl = HttpConstant.url_login_pwd;

				doPost(httpUrl, new WLoginPhonePwdBean(username, password));
			}
		});
	}

	private void doPost(String httpUrl, final WLoginPhonePwdBean requestBean)
	{
		XHttpUtil.doLoginPhonePwd(requestBean, new XHttpAdapter<VLoginPhonePwdBean>()
		{
			@Override
			public void onSuccess(VLoginPhonePwdBean vLoginPhonePwdBean)
			{
				MainActivity.actionStart(EnterLoginPhonePwdActivity.this);
				IApplication.finishActivity();
			}
		});
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, EnterLoginPhonePwdActivity.class));
	}
}
