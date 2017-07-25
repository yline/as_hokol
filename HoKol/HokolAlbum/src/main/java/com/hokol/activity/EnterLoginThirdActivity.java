package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VWeChatRegisterInfoBean;
import com.hokol.medium.http.bean.WWeChatRegisterICodeBean;
import com.hokol.medium.http.bean.WWeChatRegisterInfoBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.recycler.holder.ViewHolder;
import com.yline.view.text.helper.PhoneICodeHelper;
import com.yline.view.text.helper.PhonePwdCodeHelper;

public class EnterLoginThirdActivity extends BaseAppCompatActivity
{
	private static final String KeyUserId = "UserId";
	
	private ViewHolder viewHolder;
	
	private String userId;

	private PhonePwdCodeHelper phonePwdCodeHelper;

	private PhoneICodeHelper phoneICodeHelper;

	private boolean isPasswordVisible = true;
	
	private WWeChatRegisterInfoBean registerInfoBean;

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
		registerInfoBean = new WWeChatRegisterInfoBean(userId);

		initView();
		initViewClick();
	}

	private void initView()
	{
		final EditText etPhone = viewHolder.get(R.id.et_enter_login_third_username);
		EditText etCode = viewHolder.get(R.id.et_enter_login_third_identify);
		final TextView tvSendCode = viewHolder.get(R.id.tv_enter_login_third_identify);
		final EditText etPwd = viewHolder.get(R.id.et_enter_login_third_new_pwd);

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
						tvSendCode.setTextColor(ContextCompat.getColor(EnterLoginThirdActivity.this, R.color.hokolGray));
						tvSendCode.setText(String.format("重新发送(%d)", restTime));
					}
				}
				else
				{
					if (isLegal)
					{
						tvSendCode.setTextColor(ContextCompat.getColor(EnterLoginThirdActivity.this, R.color.hokolRed));
						tvSendCode.setText("获取验证码");
					}
					else
					{
						tvSendCode.setTextColor(ContextCompat.getColor(EnterLoginThirdActivity.this, R.color.hokolGray));
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
					XHttpUtil.doWeChatRegisterICode(new WWeChatRegisterICodeBean(phoneNumber), new HokolAdapter<String>()
					{
						@Override
						public void onSuccess(String s)
						{
							SDKManager.toast("获取验证码成功");
						}

						@Override
						public void onSuccess(int code, String data)
						{
							super.onSuccess(code, data);
							if (code == 2001)
							{
								SDKManager.toast("该用户不存在");
							}
						}
					});
				}
			}
		});

		// 眼睛
		viewHolder.setOnClickListener(R.id.iv_enter_login_third_new_pwd, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (isPasswordVisible)
				{
					etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_login_third_new_pwd, R.drawable.global_ward_close);
					isPasswordVisible = !isPasswordVisible;
				}
				else
				{
					etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_login_third_new_pwd, R.drawable.global_ward_open);
					isPasswordVisible = !isPasswordVisible;
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
				if (phonePwdCodeHelper.isResultMatch())
				{
					String userTel = viewHolder.getText(R.id.et_enter_login_third_username);
					String userCode = viewHolder.getText(R.id.et_enter_login_third_identify);
					String userPwd = viewHolder.getText(R.id.et_enter_login_third_new_pwd);
					registerInfoBean.setUser_tel(userTel);
					registerInfoBean.setCheck_code(userCode);
					registerInfoBean.setUser_pwd(userPwd);
					
					XHttpUtil.doWeChatRegisterInfo(registerInfoBean, new HokolAdapter<VWeChatRegisterInfoBean>()
					{
						@Override
						public void onSuccess(VWeChatRegisterInfoBean vWeChatRegisterInfoBean)
						{
							MainActivity.actionStart(EnterLoginThirdActivity.this, vWeChatRegisterInfoBean);
							IApplication.finishActivity();
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
