package com.hokol.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.WEnterCodeRegisterBean;
import com.hokol.medium.http.bean.WEnterRegisterBean;
import com.hokol.medium.widget.DialogIosWidget;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.holder.ViewHolder;
import com.yline.view.text.helper.PhoneICodeHelper;
import com.yline.view.text.helper.PhonePwdHelper;

public class EnterRegisterPhoneActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	/* 手机号 + 密码 + 协议 校验 */
	private PhonePwdHelper phonePwdHelper;

	/* 手机号 + 发送验证码 + 协议 校验 */
	private PhoneICodeHelper phoneICodeHelper;

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, EnterRegisterPhoneActivity.class));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_register_phone);

		viewHolder = new ViewHolder(this);
		initCheckView();

		// 结束按钮
		viewHolder.setOnClickListener(R.id.iv_enter_register, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 登录按钮
		viewHolder.setOnClickListener(R.id.btn_register_phone_action_next, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (phonePwdHelper.isResultMatch())
				{
					final String phoneNumber = viewHolder.getText(R.id.et_enter_register_phone_username);
					String identifyCode = viewHolder.getText(R.id.et_register_phone_password);

					XHttpUtil.doEnterRegister(new WEnterRegisterBean(phoneNumber, identifyCode), new XHttpAdapter<String>()
					{
						@Override
						public void onSuccess(String s)
						{
							EnterRegisterCompleteInfoActivity.actionStart(EnterRegisterPhoneActivity.this, phoneNumber);
						}

						@Override
						public void onSuccess(int code, String jsonContent, Class<String> defaultClazz) throws Exception
						{
							super.onSuccess(code, jsonContent, defaultClazz);
							if (code != REQUEST_SUCCESS_CODE)
							{
								IApplication.toast("填写信息错误");
							}
						}
					});
				}
			}
		});
	}

	private void initCheckView()
	{
		EditText etPhone = viewHolder.get(R.id.et_enter_register_phone_username);
		EditText etCode = viewHolder.get(R.id.et_register_phone_password);
		final TextView tvSendCode = viewHolder.get(R.id.tv_register_phone_identify);
		CheckBox checkBox = viewHolder.get(R.id.checkBox_enter_register_phone);

		// 手机号 + 短信验证码 校验
		phonePwdHelper = new PhonePwdHelper(etPhone, etCode, checkBox.isChecked())
		{
			@Override
			protected boolean onPwdTextChanged(String inputString, int start, int before, int count)
			{
				return com.yline.view.text.helper.TextDecorateUtil.isIdentifyCodeMatch6(inputString);
			}
		};
		phonePwdHelper.setOnCheckResultListener(new PhonePwdHelper.OnCheckResultListener()
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

		// 手机号 + 发送验证码 校验
		phoneICodeHelper = new PhoneICodeHelper(etPhone, tvSendCode, checkBox.isChecked());
		phoneICodeHelper.setOnIdentifyCodeListener(new PhoneICodeHelper.OnIdentifyCodeListener()
		{
			@Override
			public void onIdentifyStateChange(TextView tvIdentify, boolean isLegal, boolean isCountDown, int restTime)
			{
				if (isCountDown)
				{
					if (restTime != -1)
					{
						tvSendCode.setTextColor(ContextCompat.getColor(EnterRegisterPhoneActivity.this, R.color.hokolGray));
						tvSendCode.setText(String.format("重新发送(%d)", restTime));
					}
				}
				else
				{
					if (isLegal)
					{
						tvSendCode.setTextColor(ContextCompat.getColor(EnterRegisterPhoneActivity.this, R.color.hokolRed));
						tvSendCode.setText("获取验证码");
					}
					else
					{
						tvSendCode.setTextColor(ContextCompat.getColor(EnterRegisterPhoneActivity.this, R.color.hokolGray));
						tvSendCode.setText("获取验证码");
					}
				}
			}

			@Override
			public void onIdentifyClick(View view, boolean isMatch, boolean isCountDown)
			{
				if (isMatch && !isCountDown)
				{
					String phoneNumber = viewHolder.getText(R.id.et_enter_register_phone_username);
					XHttpUtil.doEnterCodeRegister(new WEnterCodeRegisterBean(phoneNumber), new XHttpAdapter<String>()
					{
						@Override
						public void onSuccess(String s)
						{
							IApplication.toast("请查看手机短信");
						}

						@Override
						public void onSuccess(int code, String jsonContent, Class<String> defaultClazz) throws Exception
						{
							super.onSuccess(code, jsonContent, defaultClazz);
							if (3001 == code)
							{
								new DialogIosWidget(EnterRegisterPhoneActivity.this)
								{
									@Override
									protected void initXView(TextView tvTitle, TextView tvMsg, Button btnNegative, Button btnPositive, Dialog dialog)
									{
										super.initXView(tvTitle, tvMsg, btnNegative, btnPositive, dialog);
										tvTitle.setText("您已经注册过红客了\n请直接登陆吧");
										btnPositive.setText("立即登陆");
									}
								}.show();
							}
						}
					});
				}
			}
		});

		// 协议
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				phonePwdHelper.setProtocolChecked(isChecked); // 协议关联，手机号 + 密码校验
				phoneICodeHelper.setProtocolChecked(isChecked); // 协议关联，手机号 + 发送验证码
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
