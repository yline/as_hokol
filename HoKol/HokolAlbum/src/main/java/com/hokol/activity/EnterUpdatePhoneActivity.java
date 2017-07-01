package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;

import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.WEnterCodeUpdatePhoneBean;
import com.hokol.medium.http.bean.WEnterPhoneUpdateBean;
import com.hokol.util.TextDecorateUtil;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.holder.ViewHolder;

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

	private ClassState classState;

	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_update_phone);

		viewHolder = new ViewHolder(this);
		initView();
		initData();
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
		TextDecorateUtil.isPhoneMatch(etPhone, new TextDecorateUtil.OnEditMatchCallback()
		{
			@Override
			public void onTextChange(boolean isMatch)
			{
				if (isMatch)
				{
					classState.setPhoneMatch(true);
				}
				else
				{
					classState.setPhoneMatch(false);
				}
			}
		});

		// 密码
		EditText etPwd = viewHolder.get(R.id.et_register_phone_identify);
		TextDecorateUtil.isIdentifyMatch(etPwd, new TextDecorateUtil.OnEditMatchCallback()
		{
			@Override
			public void onTextChange(boolean isMatch)
			{
				if (isMatch)
				{
					classState.setIdentifyMatch(true);
				}
				else
				{
					classState.setIdentifyMatch(false);
				}
			}
		});

		// 验证码
		viewHolder.setOnClickListener(R.id.tv_register_phone_identify, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (classState.isIdentifyClickable())
				{
					new IdentifyCountDownTask().execute();

					String phoneNumber = viewHolder.getText(R.id.et_enter_update_phone_username);
					XHttpUtil.doEnterCodeUpdatePhone(new WEnterCodeUpdatePhoneBean(phoneNumber), new XHttpAdapter<String>()
					{
						@Override
						public void onSuccess(String s)
						{
							SDKManager.toast("验证码已发送");
						}
					});
				}
			}
		});

		// 完成按钮
		viewHolder.setOnClickListener(R.id.btn_register_phone_action_next, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (classState.isFinishClickable())
				{
					String phoneNumber = viewHolder.getText(R.id.et_enter_update_phone_username);
					String code = viewHolder.getText(R.id.et_register_phone_identify);

					XHttpUtil.doEnterPhoneUpdate(new WEnterPhoneUpdateBean(userId, phoneNumber, code), new XHttpAdapter<String>()
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

	private void initData()
	{
		classState = new ClassState();

		userId = getIntent().getStringExtra(KeyPhoneUserId);
	}

	private class ClassState
	{
		private boolean oldPhoneMatch;

		private boolean oldIdentifyMatch;

		private boolean oldIsIdentifyCountDown;

		public void setPhoneMatch(boolean phoneMatch)
		{
			if (this.oldPhoneMatch != phoneMatch)
			{
				this.oldPhoneMatch = phoneMatch;
				if (oldPhoneMatch)
				{
					if (!oldIsIdentifyCountDown)
					{
						viewHolder.setText(R.id.tv_register_phone_identify, "获取验证码").setTextColor(ContextCompat.getColor(EnterUpdatePhoneActivity.this, R.color.hokolRed));
					}

					if (oldIdentifyMatch)
					{
						viewHolder.setText(R.id.btn_register_phone_action_next, "完成").setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_redhokol);
					}
				}
				else
				{
					if (!oldIsIdentifyCountDown)
					{
						viewHolder.setText(R.id.tv_register_phone_identify, "获取验证码").setTextColor(ContextCompat.getColor(EnterUpdatePhoneActivity.this, R.color.hokolGray));
					}

					if (oldIdentifyMatch)
					{
						viewHolder.setText(R.id.btn_register_phone_action_next, "完成").setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_pinkhokol);
					}
				}
			}
		}

		public void setIdentifyMatch(boolean pwdMatch)
		{
			if (this.oldIdentifyMatch != pwdMatch)
			{
				this.oldIdentifyMatch = pwdMatch;
				if (oldIdentifyMatch)
				{
					if (oldPhoneMatch)
					{
						viewHolder.setText(R.id.btn_register_phone_action_next, "完成").setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_redhokol);
					}
				}
				else
				{
					if (oldPhoneMatch)
					{
						viewHolder.setText(R.id.btn_register_phone_action_next, "完成").setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_pinkhokol);
					}
				}
			}
		}

		public void setIdentifyCountDown(boolean identifyCountDown)
		{
			if (this.oldIsIdentifyCountDown != identifyCountDown)
			{
				this.oldIsIdentifyCountDown = identifyCountDown;
				if (oldIsIdentifyCountDown)
				{
					if (oldPhoneMatch)
					{
						viewHolder.setText(R.id.tv_register_phone_identify, "重新获取(60)").setTextColor(ContextCompat.getColor(EnterUpdatePhoneActivity.this, R.color.hokolGray));
					}
				}
				else
				{
					if (oldPhoneMatch)
					{
						viewHolder.setText(R.id.tv_register_phone_identify, "获取验证码").setTextColor(ContextCompat.getColor(EnterUpdatePhoneActivity.this, R.color.hokolRed));
					}
				}
			}
		}

		public boolean isIdentifyClickable()
		{
			return (oldPhoneMatch && !oldIsIdentifyCountDown);
		}

		public boolean isFinishClickable()
		{
			return (oldPhoneMatch && oldIdentifyMatch);
		}
	}

	private class IdentifyCountDownTask extends AsyncTask<Void, String, Void>
	{
		private int maxNumber;

		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			maxNumber = 60;
			classState.setIdentifyCountDown(true);
		}

		@Override
		protected Void doInBackground(Void... params)
		{
			try
			{
				while (maxNumber > 0)
				{
					maxNumber--;
					publishProgress(String.format("重新发送(%d)", maxNumber));
					Thread.sleep(1000);
				}
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values)
		{
			super.onProgressUpdate(values);
			viewHolder.setText(R.id.tv_register_phone_identify, values[0]);
		}

		@Override
		protected void onPostExecute(Void aVoid)
		{
			super.onPostExecute(aVoid);

			maxNumber = 60;
			classState.setIdentifyCountDown(false);
		}
	}

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, EnterUpdatePhoneActivity.class).putExtra(KeyPhoneUserId, userId));
	}
}
