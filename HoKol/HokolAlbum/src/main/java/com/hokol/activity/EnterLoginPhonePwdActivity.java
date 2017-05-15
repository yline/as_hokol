package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VLoginPhonePwdBean;
import com.hokol.medium.http.bean.WLoginPhonePwdBean;
import com.hokol.util.TextDecorateUtil;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.common.ViewHolder;

/**
 * 登入流程，手机号+ 密码登录
 *
 * @author yline 2017/3/20 -- 15:02
 * @version 1.0.0
 */
public class EnterLoginPhonePwdActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	private boolean isPasswordVisible = true;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_login_phone_pwd);

		viewHolder = new ViewHolder(this);

		initView();
		initViewClick();
	}

	private void initView()
	{
		EditText editTextPhone = viewHolder.get(R.id.et_enter_login_phone_username);
		TextDecorateUtil.isPhoneMatch(editTextPhone, new TextDecorateUtil.OnEditMatchCallback()
		{
			@Override
			public void onTextChange(boolean isMatch)
			{
				if (isMatch)
				{
					IApplication.toast("手机号符合规则");
				}
			}
		});

		EditText editTextPwd = viewHolder.get(R.id.et_enter_login_phone_password);
		TextDecorateUtil.isPhonePwdMatch(editTextPwd, new TextDecorateUtil.OnEditMatchCallback()
		{
			@Override
			public void onTextChange(boolean isMatch)
			{
				if (isMatch)
				{
					IApplication.toast("密码符合规则");
				}
			}
		});
	}

	private void initViewClick()
	{
		// 返回
		viewHolder.setOnClickListener(R.id.iv_enter_login_phone_pwd_register, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
		// 跳转到注册
		viewHolder.setOnClickListener(R.id.tv_enter_login_phone_register, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EnterRegisterPhoneActivity.actionStart(EnterLoginPhonePwdActivity.this);
			}
		});
		// 忘记密码
		viewHolder.setOnClickListener(R.id.et_enter_login_phone_pwd_forget, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EnterForgetPwdActivity.actionStart(EnterLoginPhonePwdActivity.this);
			}
		});
		// 登录
		viewHolder.setOnClickListener(R.id.btn_enter_login_phone_login, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("正在登录...");

				String username = viewHolder.getText(R.id.et_enter_login_phone_username);
				String password = viewHolder.getText(R.id.et_enter_login_phone_password);

				String httpUrl = HttpConstant.url_login_pwd;

				doPost(httpUrl, new WLoginPhonePwdBean(username, password));
			}
		});
		// 眼睛
		viewHolder.setOnClickListener(R.id.iv_enter_login_phone_password, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EditText editText = viewHolder.get(R.id.et_enter_login_phone_password);
				if (isPasswordVisible)
				{
					editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_login_phone_password, R.drawable.global_ward_open);
					isPasswordVisible = !isPasswordVisible;
				}
				else
				{
					editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_login_phone_password, R.drawable.global_ward_close);
					isPasswordVisible = !isPasswordVisible;
				}
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
