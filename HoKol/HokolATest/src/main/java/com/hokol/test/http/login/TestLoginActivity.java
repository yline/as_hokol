package com.hokol.test.http.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VEnterLoginPhonePwdBean;
import com.hokol.medium.http.bean.WEnterCodeRegisterBean;
import com.hokol.medium.http.bean.WEnterLoginPhonePwdBean;
import com.hokol.medium.http.bean.WEnterRegisterBean;
import com.hokol.medium.http.bean.WEnterRegisterCompleteInfoBean;
import com.hokol.medium.http.bean.WEnterResetPwdBean;
import com.hokol.test.common.BaseTestActivity;
import com.hokol.test.httpattach.AvatarActivity;
import com.yline.http.XHttpAdapter;

public class TestLoginActivity extends BaseTestActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// 手机号 + 密码登陆
		final EditText etPhonePwdOne = addEditNumber("手机号", "13656786656");
		final EditText etPhonePwdTwo = addEditNumber("密码", "lj123456");
		addButton("手机号+密码登陆", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String username = etPhonePwdOne.getText().toString().trim();
				String password = etPhonePwdTwo.getText().toString().trim();

				final WEnterLoginPhonePwdBean requestBean = new WEnterLoginPhonePwdBean(username, password);

				// 这样的方法,并不会被执行
				XHttpUtil.doEnterLoginPhonePwd(requestBean, new XHttpAdapter<VEnterLoginPhonePwdBean>()
				{
					@Override
					public void onSuccess(VEnterLoginPhonePwdBean vEnterLoginPhonePwdBean)
					{
						AvatarActivity.actionStart(TestLoginActivity.this, vEnterLoginPhonePwdBean.getUser_logo());
					}
				});
			}
		});

		// 获取 注册验证码
		final EditText etRegisterCode = addEditNumber("手机号", "15958148487");
		addButton("获取注册验证码", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String phoneNumber = etRegisterCode.getText().toString().trim();
				XHttpUtil.doEnterCodeRegister(new WEnterCodeRegisterBean(phoneNumber), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});

		// 用户注册
		final EditText etRegisterPhone = addEditNumber("手机号", "15958148487");
		final EditText etRegisterIdentify = addEditNumber("手机验证码", "222222");
		addButton("用户注册", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String phoneNumber = etRegisterPhone.getText().toString().trim();
				String phoneCode = etRegisterIdentify.getText().toString().trim();

				XHttpUtil.doEnterRegister(new WEnterRegisterBean(phoneNumber, phoneCode), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});

		// 用户信息完善
		addButton("用户信息完善", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				WEnterRegisterCompleteInfoBean completeInfoBean = null; // new WEnterRegisterCompleteInfoBean();
				XHttpUtil.doEnterRegisterCompleteInfo(completeInfoBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});

		// 忘记密码 之 获取密码
		addButton("忘记密码 之 获取密码", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				WEnterCodeRegisterBean registerBean = null; // new WEnterCodeRegisterBean()
				XHttpUtil.doEnterCodeForgetPwd(registerBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});

		// 忘记密码 之 重置密码
		addButton("忘记密码 之 重置密码", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				WEnterResetPwdBean resetPwdBean = null; // new WEnterResetPwdBean();
				XHttpUtil.doEnterResetPwd(resetPwdBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestLoginActivity.class));
	}
}
