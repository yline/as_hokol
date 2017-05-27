package com.hokol.test.http.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.medium.http.HttpEnum;
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

import java.util.Arrays;

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
		final EditText etCompletePhone = addEditNumber("手机号", "15958148487");
		final EditText etCompletePwd = addEditNumber("密码", "123456");
		final EditText etCompleteSex = addEditNumber("性别", "1");
		addButton("用户信息完善", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String tel = etCompletePhone.getText().toString().trim();
				String pwd = etCompletePwd.getText().toString().trim();
				int sex = parseInt(etCompleteSex, 1);

				WEnterRegisterCompleteInfoBean completeInfoBean = new WEnterRegisterCompleteInfoBean(tel, pwd, sex);
				completeInfoBean.setUser_tag(Arrays.asList(HttpEnum.UserTag.Other.getIndex()));
				XHttpUtil.doEnterRegisterCompleteInfo(completeInfoBean, new XHttpAdapter<VEnterLoginPhonePwdBean>()
				{
					@Override
					public void onSuccess(VEnterLoginPhonePwdBean vEnterLoginPhonePwdBean)
					{

					}
				});
			}
		});

		// 忘记密码 之 获取密码
		final EditText etForgetPhone = addEditNumber("手机号", "15958148487");
		addButton("忘记密码 之 获取密码", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String tel = etForgetPhone.getText().toString().trim();

				WEnterCodeRegisterBean registerBean = new WEnterCodeRegisterBean(tel);
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
		final EditText etResetPhone = addEditNumber("手机号", "15958148487");
		final EditText etResetPwd = addEditNumber("密码", "123456");
		final EditText etResetSex = addEditNumber("手机验证码", "123456");

		addButton("忘记密码 之 重置密码", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String tel = etResetPhone.getText().toString().trim();
				String pwd = etResetPwd.getText().toString().trim();
				String code = etResetSex.getText().toString().trim();

				WEnterResetPwdBean resetPwdBean = new WEnterResetPwdBean(tel, pwd, code);
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
