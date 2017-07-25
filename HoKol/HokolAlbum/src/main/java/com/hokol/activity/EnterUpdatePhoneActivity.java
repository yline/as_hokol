package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.WEnterCodeUpdatePhoneBean;
import com.hokol.medium.http.bean.WEnterPhoneUpdateBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.recycler.holder.ViewHolder;
import com.yline.view.text.helper.PhoneICodeHelper;
import com.yline.view.text.helper.PhonePwdHelper;
import com.yline.view.text.helper.TextDecorateUtil;

/**
 * 更换手机号
 *
 * @author yline 2017/5/31 -- 14:52
 * @version 1.0.0
 */
public class EnterUpdatePhoneActivity extends BaseAppCompatActivity
{
	private static final String KeyPhoneUserId = "UserId";

	private ViewHolder viewHolder;

	private String userId;

	private PhoneICodeHelper phoneICodeHelper;

	private PhonePwdHelper phonePwdHelper;

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, EnterUpdatePhoneActivity.class).putExtra(KeyPhoneUserId, userId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_update_phone);

		viewHolder = new ViewHolder(this);
		userId = getIntent().getStringExtra(KeyPhoneUserId);

		initView();
	}

	private void initView()
	{
		// 返回按钮
		viewHolder.setOnClickListener(R.id.iv_enter_update_phone_finish, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 更换手机号
		EditText etPhone = viewHolder.get(R.id.et_enter_update_phone_username);
		EditText etPwd = viewHolder.get(R.id.et_register_phone_identify);
		final TextView tvSendCode = viewHolder.get(R.id.tv_register_phone_identify);

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
						tvSendCode.setTextColor(ContextCompat.getColor(EnterUpdatePhoneActivity.this, R.color.hokolGray));
						tvSendCode.setText(String.format("重新发送(%d)", restTime));
					}
				}
				else
				{
					if (isLegal)
					{
						tvSendCode.setTextColor(ContextCompat.getColor(EnterUpdatePhoneActivity.this, R.color.hokolRed));
						tvSendCode.setText("获取验证码");
					}
					else
					{
						tvSendCode.setTextColor(ContextCompat.getColor(EnterUpdatePhoneActivity.this, R.color.hokolGray));
						tvSendCode.setText("获取验证码");
					}
				}
			}

			@Override
			public void onIdentifyClick(View view, boolean isMatch, boolean isCountDown)
			{
				String phoneNumber = viewHolder.getText(R.id.et_enter_update_phone_username);
				XHttpUtil.doEnterCodeUpdatePhone(new WEnterCodeUpdatePhoneBean(phoneNumber), new HokolAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						SDKManager.toast("验证码已发送");
					}
				});
			}
		});

		phonePwdHelper = new PhonePwdHelper(etPhone, etPwd)
		{
			@Override
			protected boolean onPwdTextChanged(String inputString, int start, int before, int count)
			{
				return TextDecorateUtil.isIdentifyCodeMatch6(inputString);
			}
		};
		phonePwdHelper.setOnCheckResultListener(new PhonePwdHelper.OnCheckResultListener()
		{
			@Override
			public void onChecked(boolean isMatch)
			{
				if (isMatch)
				{
					viewHolder.setText(R.id.btn_register_phone_action_next, "完成").setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_redhokol);
				}
				else
				{
					viewHolder.setText(R.id.btn_register_phone_action_next, "完成").setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_pinkhokol);
				}
			}
		});

		// 完成按钮
		viewHolder.setOnClickListener(R.id.btn_register_phone_action_next, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (phonePwdHelper.isResultMatch())
				{
					String phoneNumber = viewHolder.getText(R.id.et_enter_update_phone_username);
					String code = viewHolder.getText(R.id.et_register_phone_identify);

					XHttpUtil.doEnterPhoneUpdate(new WEnterPhoneUpdateBean(userId, phoneNumber, code), new HokolAdapter<String>()
					{
						@Override
						public void onSuccess(String s)
						{
							SDKManager.toast("修改成功, 重新登录");
							EnterChoiceActivity.actionStartJump(EnterUpdatePhoneActivity.this);
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
