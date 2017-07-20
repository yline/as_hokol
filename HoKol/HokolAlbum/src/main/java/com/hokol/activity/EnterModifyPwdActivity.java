package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.WSettingResetPwdBean;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.view.recycler.holder.ViewHolder;
import com.yline.view.text.helper.PhonePwdCodeHelper;

public class EnterModifyPwdActivity extends BaseAppCompatActivity
{
	private static final String KeyModifyPwdUserId = "modifyPwdUserId";

	private ViewHolder viewHolder;

	private boolean isNewPwdVisible = true, isDoublePwdVisible = true;

	private String userId;

	private PhonePwdCodeHelper phonePwdCodeHelper;

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, EnterModifyPwdActivity.class).putExtra(KeyModifyPwdUserId, userId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_modify_pwd);

		viewHolder = new ViewHolder(this);
		userId = getIntent().getStringExtra(KeyModifyPwdUserId);

		initView();
		initViewClick();
	}

	private void initView()
	{
		// 新密码，是否可见
		viewHolder.setOnClickListener(R.id.iv_enter_forget_pwd_new, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EditText newPwdEditText = viewHolder.get(R.id.et_enter_forget_pwd_new);
				if (isNewPwdVisible)
				{
					newPwdEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_forget_pwd_new, R.drawable.global_ward_close);
					isNewPwdVisible = !isNewPwdVisible;
				}
				else
				{
					newPwdEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_forget_pwd_new, R.drawable.global_ward_open);
					isNewPwdVisible = !isNewPwdVisible;
				}
			}
		});

		// 重复密码是否可见
		viewHolder.setOnClickListener(R.id.iv_enter_forget_pwd_new_sure, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EditText doublePwdEditText = viewHolder.get(R.id.et_enter_forget_pwd_new_sure);
				if (isDoublePwdVisible)
				{
					doublePwdEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_forget_pwd_new_sure, R.drawable.global_ward_close);
					isDoublePwdVisible = !isDoublePwdVisible;
				}
				else
				{
					doublePwdEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_forget_pwd_new_sure, R.drawable.global_ward_open);
					isDoublePwdVisible = !isDoublePwdVisible;
				}
			}
		});

		// 当前密码
		EditText oldPwdEditText = viewHolder.get(R.id.et_enter_forget_pwd_old);
		// 新密码
		final EditText newPwdEditText = viewHolder.get(R.id.et_enter_forget_pwd_new);
		// 重复密码
		final EditText doublePwdEditText = viewHolder.get(R.id.et_enter_forget_pwd_new_sure);

		phonePwdCodeHelper = new PhonePwdCodeHelper(oldPwdEditText, newPwdEditText, doublePwdEditText)
		{
			@Override
			protected boolean onPhoneTextChanged(String inputString, int start, int before, int count)
			{
				return com.yline.view.text.helper.TextDecorateUtil.isPhonePwdMatch(inputString);
			}

			@Override
			protected boolean onCodeTextChanged(String inputString, int start, int before, int count)
			{
				return com.yline.view.text.helper.TextDecorateUtil.isPhonePwdMatch(inputString);
			}

			@Override
			protected boolean onPwdTextChanged(String inputString, int start, int before, int count)
			{
				return com.yline.view.text.helper.TextDecorateUtil.isPhonePwdMatch(inputString);
			}
		};
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
	}

	private void initViewClick()
	{
		// 返回
		viewHolder.setOnClickListener(R.id.iv_user_setting_account_modify_pwd_finish, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 提交
		viewHolder.setOnClickListener(R.id.btn_register_phone_action_next, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String oldPwdStr = viewHolder.getText(R.id.et_enter_forget_pwd_old);
				String newPwdStr = viewHolder.getText(R.id.et_enter_forget_pwd_new);
				String doublePwdStr = viewHolder.getText(R.id.et_enter_forget_pwd_new_sure);

				if (phonePwdCodeHelper.isResultMatch())
				{
					if (newPwdStr.equals(doublePwdStr))
					{
						XHttpUtil.doSettingResetPwd(new WSettingResetPwdBean(userId, oldPwdStr, newPwdStr), new XHttpAdapter<String>()
						{
							@Override
							public void onSuccess(int code, String jsonContent, Class<String> defaultClazz) throws Exception
							{
								super.onSuccess(code, jsonContent, defaultClazz);
								if (code != REQUEST_SUCCESS_CODE)
								{
									SDKManager.toast("密码错误");
								}
							}

							@Override
							public void onSuccess(String s)
							{
								SDKManager.toast("修改密码成功");
								finish();
							}
						});
					}
					else
					{
						SDKManager.toast("两次输入的新密码不相同");
					}
				}
				else
				{
					LogFileUtil.v("EnterModifyPwdActivity 输入 内容 不正确");
				}
			}
		});
	}
}
