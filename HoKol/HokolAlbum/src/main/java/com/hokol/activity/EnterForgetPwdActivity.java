package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonParseException;
import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.WEnterCodeRegisterBean;
import com.hokol.medium.http.bean.WEnterResetPwdBean;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.holder.ViewHolder;
import com.yline.view.text.helper.PhoneICodeHelper;
import com.yline.view.text.helper.PhonePwdCodeHelper;

import org.json.JSONException;

public class EnterForgetPwdActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	private PhonePwdCodeHelper phonePwdCodeHelper;

	private PhoneICodeHelper phoneICodeHelper;

	private boolean isPasswordVisible = true;

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, EnterForgetPwdActivity.class));
	}

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
		final EditText etPhone = viewHolder.get(R.id.et_enter_forget_pwd_username);
		EditText etCode = viewHolder.get(R.id.et_enter_forget_identify);
		final TextView tvSendCode = viewHolder.get(R.id.tv_enter_forget_pwd_identify);
		final EditText etPwd = viewHolder.get(R.id.et_enter_forget_pwd_new_pwd);

		phonePwdCodeHelper = new PhonePwdCodeHelper(etPhone, etCode, etPwd);
		phonePwdCodeHelper.setOnCheckResultListener(new PhonePwdCodeHelper.OnCheckResultListener()
		{
			@Override
			public void onChecked(boolean isMatch)
			{
				if (isMatch)
				{
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_redhokol);
				}
				else
				{
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_pinkhokol);
				}
			}
		});

		phoneICodeHelper = new PhoneICodeHelper(etPhone, tvSendCode);
		phoneICodeHelper.setOnIdentifyCodeListener(new PhoneICodeHelper.OnIdentifyCodeListener()
		{
			@Override
			public void onIdentifyStateChange(TextView tvIdentify, boolean isLegal, boolean isCountDown, int restTime)
			{
				if (isCountDown)
				{
					if (restTime != -1)
					{
						tvSendCode.setTextColor(ContextCompat.getColor(EnterForgetPwdActivity.this, R.color.hokolGray));
						tvSendCode.setText(String.format("重新发送(%d)", restTime));
					}
				}
				else
				{
					if (isLegal)
					{
						tvSendCode.setTextColor(ContextCompat.getColor(EnterForgetPwdActivity.this, R.color.hokolRed));
						tvSendCode.setText("获取验证码");
					}
					else
					{
						tvSendCode.setTextColor(ContextCompat.getColor(EnterForgetPwdActivity.this, R.color.hokolGray));
						tvSendCode.setText("获取验证码");
					}
				}
			}

			@Override
			public void onIdentifyClick(View view, boolean isMatch, boolean isCountDown)
			{
				if (isMatch && !isCountDown)
				{
					String phoneNumber = etPhone.getText().toString().trim();
					XHttpUtil.doEnterCodeForgetPwd(new WEnterCodeRegisterBean(phoneNumber), new XHttpAdapter<String>()
					{
						@Override
						public void onSuccess(int code, String data) throws JSONException, JsonParseException
						{
							super.onSuccess(code, data);
							if (code == 2001)
							{
								SDKManager.toast("该用户不存在");
							}
						}

						@Override
						public void onSuccess(String s)
						{
							SDKManager.toast("获取验证码成功");
						}
					});
				}
			}
		});

		// 眼睛
		viewHolder.setOnClickListener(R.id.iv_enter_forget_pwd_new_pwd, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (isPasswordVisible)
				{
					etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_forget_pwd_new_pwd, R.drawable.global_ward_close);
					isPasswordVisible = !isPasswordVisible;
				}
				else
				{
					etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_forget_pwd_new_pwd, R.drawable.global_ward_open);
					isPasswordVisible = !isPasswordVisible;
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

		viewHolder.setOnClickListener(R.id.btn_register_phone_action_next, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (phonePwdCodeHelper.isResultMatch())
				{
					final String userPhone = viewHolder.getText(R.id.et_enter_forget_pwd_username);
					String userCode = viewHolder.getText(R.id.et_enter_forget_identify);
					final String userPwd = viewHolder.getText(R.id.et_enter_forget_pwd_new_pwd);

					XHttpUtil.doEnterResetPwd(new WEnterResetPwdBean(userPhone, userCode, userPwd), new XHttpAdapter<String>()
					{
						@Override
						public void onSuccess(String s)
						{
							EnterLoginPhonePwdActivity.actionStart(EnterForgetPwdActivity.this, userPhone, userPwd);
							finish();
						}

						@Override
						public void onSuccess(int code, String data) throws JSONException, JsonParseException
						{
							super.onSuccess(code, data);
							if (code != REQUEST_SUCCESS_CODE)
							{
								SDKManager.toast("参数错误");
							}
						}
					});
				}
			}
		});
	}

	@Override
	protected void onDestroy()
	{
		phoneICodeHelper.onDestroy();
		super.onDestroy();
	}
}
