package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VEnterLoginPhonePwdBean;
import com.hokol.medium.http.bean.WEnterLoginPhonePwdBean;
import com.hokol.util.TextDecorateUtil;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.holder.ViewHolder;

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

	private boolean isPhoneMatch, isPwdMatch;

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
				updatePhoneMatch(isMatch);
			}
		});

		EditText editTextPwd = viewHolder.get(R.id.et_enter_login_phone_password);
		TextDecorateUtil.isPhonePwdMatch(editTextPwd, new TextDecorateUtil.OnEditMatchCallback()
		{
			@Override
			public void onTextChange(boolean isMatch)
			{
				updatePwdMatch(isMatch);
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
				if (isPhoneMatch && isPwdMatch)
				{
					String username = viewHolder.getText(R.id.et_enter_login_phone_username);
					String password = viewHolder.getText(R.id.et_enter_login_phone_password);
					doPost(username, password);
				}
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
					viewHolder.setImageResource(R.id.iv_enter_login_phone_password, R.drawable.global_ward_close);
					isPasswordVisible = !isPasswordVisible;
				}
				else
				{
					editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_login_phone_password, R.drawable.global_ward_open);
					isPasswordVisible = !isPasswordVisible;
				}
			}
		});
	}

	private void updatePhoneMatch(boolean isMatch)
	{
		if (isPhoneMatch == isMatch)
		{
			return;
		}

		this.isPhoneMatch = isMatch;
		if (isMatch)
		{
			if (isPwdMatch)
			{
				viewHolder.get(R.id.btn_enter_login_phone_login).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_redhokol);
			}
		}
		else
		{
			if (isPwdMatch)
			{
				viewHolder.get(R.id.btn_enter_login_phone_login).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_pinkhokol);
			}
		}
	}

	private void updatePwdMatch(boolean isMatch)
	{
		if (isPwdMatch == isMatch)
		{
			return;
		}

		this.isPwdMatch = isMatch;
		if (isMatch)
		{
			if (isPhoneMatch)
			{
				viewHolder.get(R.id.btn_enter_login_phone_login).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_redhokol);
			}
		}
		else
		{
			if (isPhoneMatch)
			{
				viewHolder.get(R.id.btn_enter_login_phone_login).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_pinkhokol);
			}
		}
	}

	private void doPost(String username, String password)
	{
		WEnterLoginPhonePwdBean loginPhonePwdBean = new WEnterLoginPhonePwdBean(username, password);
		XHttpUtil.doEnterLoginPhonePwd(loginPhonePwdBean, new XHttpAdapter<VEnterLoginPhonePwdBean>()
		{
			@Override
			public void onSuccess(VEnterLoginPhonePwdBean vEnterLoginPhonePwdBean)
			{
				MainActivity.actionStart(EnterLoginPhonePwdActivity.this, vEnterLoginPhonePwdBean);
				IApplication.finishActivity();
			}

			@Override
			public void onFailureCode(int code)
			{
				super.onFailureCode(code);
				SDKManager.toast("用户名或密码错误");
			}

			@Override
			public void onFailure(Exception ex)
			{
				super.onFailure(ex);
				SDKManager.toast("登陆失败");
			}
		});
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, EnterLoginPhonePwdActivity.class));
	}
}
