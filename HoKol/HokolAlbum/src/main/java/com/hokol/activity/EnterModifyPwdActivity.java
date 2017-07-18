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
import com.hokol.util.ArraysUtil;
import com.hokol.util.TextDecorateUtil;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.holder.ViewHolder;

public class EnterModifyPwdActivity extends BaseAppCompatActivity
{
	private static final String KeyModifyPwdUserId = "modifyPwdUserId";

	private ViewHolder viewHolder;

	private ClassState classState;

	private boolean isNewPwdVisible = true, isDoublePwdVisible = true;

	private String userId;

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
		initView();
		initViewClick();
		initData();
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

		// 当前密码
		EditText oldPwdEditText = viewHolder.get(R.id.et_enter_forget_pwd_old);
		TextDecorateUtil.isPhonePwdMatch(oldPwdEditText, new TextDecorateUtil.OnEditMatchCallback()
		{
			@Override
			public void onTextChange(boolean isMatch)
			{
				classState.setOldPwdMatch(isMatch);
			}
		});

		// 新密码
		EditText newPwdEditText = viewHolder.get(R.id.et_enter_forget_pwd_new);
		TextDecorateUtil.isPhonePwdMatch(newPwdEditText, new TextDecorateUtil.OnEditMatchCallback()
		{
			@Override
			public void onTextChange(boolean isMatch)
			{
				classState.setNewPwdMatch(isMatch);
			}
		});

		// 提交
		viewHolder.setOnClickListener(R.id.btn_register_phone_action_next, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SDKManager.toast("提交成功");
				if (!classState.isOldPwdMatch())
				{
					SDKManager.toast("旧密码格式错误");
					return;
				}

				if (!classState.isNewPwdMatch())
				{
					SDKManager.toast("新密码格式错误");
					return;
				}

				String oldPwdStr = viewHolder.getText(R.id.et_enter_forget_pwd_old);
				String newPwdStr = viewHolder.getText(R.id.et_enter_forget_pwd_new);
				String doublePwdStr = viewHolder.getText(R.id.et_enter_forget_pwd_new_sure);

				if (!ArraysUtil.compare(newPwdStr, doublePwdStr))
				{
					SDKManager.toast("两次输入的密码不想同");
					return;
				}

				if (!ArraysUtil.compare(oldPwdStr, newPwdStr))
				{
					SDKManager.toast("新密码不能与旧密码相同");
					return;
				}

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
		});
	}

	private void initData()
	{
		userId = getIntent().getStringExtra(KeyModifyPwdUserId);

		classState = new ClassState();
	}

	private class ClassState
	{
		private boolean isOldPwdMatch;

		private boolean isNewPwdMatch;

		public boolean isOldPwdMatch()
		{
			return isOldPwdMatch;
		}

		public void setOldPwdMatch(boolean oldPwdMatch)
		{
			if (this.isOldPwdMatch != oldPwdMatch)
			{
				this.isOldPwdMatch = oldPwdMatch;
				if (isOldPwdMatch)
				{
					if (isNewPwdMatch)
					{
						viewHolder.setText(R.id.btn_register_phone_action_next, "提交").setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_redhokol);
					}
				}
				else
				{
					if (isNewPwdMatch)
					{
						viewHolder.setText(R.id.btn_register_phone_action_next, "提交").setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_pinkhokol);
					}
				}
			}
		}

		public boolean isNewPwdMatch()
		{
			return isNewPwdMatch;
		}

		public void setNewPwdMatch(boolean newPwdMatch)
		{
			if (this.isNewPwdMatch != newPwdMatch)
			{
				this.isNewPwdMatch = newPwdMatch;
				if (isNewPwdMatch)
				{
					if (isOldPwdMatch)
					{
						viewHolder.setText(R.id.btn_register_phone_action_next, "提交").setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_redhokol);
					}
				}
				else
				{
					if (isOldPwdMatch)
					{
						viewHolder.setText(R.id.btn_register_phone_action_next, "提交").setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_pinkhokol);
					}
				}
			}
		}
	}
}
