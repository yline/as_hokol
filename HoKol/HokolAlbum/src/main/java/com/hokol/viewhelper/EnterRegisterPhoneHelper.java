package com.hokol.viewhelper;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.util.TextDecorateUtil;
import com.yline.log.LogFileUtil;
import com.yline.view.recycler.holder.ViewHolder;

public class EnterRegisterPhoneHelper
{
	private ViewHolder viewHolder;

	private Context sContext;

	private RegisterPhoneStateManager stateManager;

	public EnterRegisterPhoneHelper(Context context, ViewHolder viewHolder)
	{
		this.sContext = context;
		this.viewHolder = viewHolder;
		stateManager = new RegisterPhoneStateManager();
	}

	/**
	 * 返回按钮
	 *
	 * @param listener
	 */
	public void setOnFinishListener(View.OnClickListener listener)
	{
		viewHolder.setOnClickListener(R.id.iv_enter_register, listener);
	}

	/**
	 * 完成注册按钮
	 *
	 * @param listener
	 */
	public void setOnNextListener(final OnNextButtonClickListener listener)
	{
		viewHolder.setOnClickListener(R.id.btn_register_phone_action_next, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (stateManager.isNextBtnAble())
				{
					if (null != listener)
					{
						String mobileString = viewHolder.getText(R.id.et_enter_register_phone_username);
						String identifyString = viewHolder.getText(R.id.et_register_phone_password);
						listener.onClick(v, mobileString, identifyString);
					}
				}
			}
		});
	}

	/**
	 * 发送验证码
	 *
	 * @param listener
	 */
	public void setOnIdentifyCodeListener(final View.OnClickListener listener)
	{
		viewHolder.setOnClickListener(R.id.tv_register_phone_identify, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (stateManager.isIdentifyClickable())
				{
					new IdentifyCountDownTask().execute();
					if (null != listener)
					{
						listener.onClick(v);
					}
				}
			}
		});
	}

	/**
	 * 初始化输入框的特性
	 */
	public void initInputView()
	{
		// 手机号
		EditText editUsername = viewHolder.get(R.id.et_enter_register_phone_username);
		TextDecorateUtil.isPhoneMatch(editUsername, new TextDecorateUtil.OnEditMatchCallback()
		{
			@Override
			public void onTextChange(boolean isMatch)
			{
				stateManager.setMobileMatch(isMatch);
			}
		});

		// 验证码
		EditText editIdentify = viewHolder.get(R.id.et_register_phone_password);
		TextDecorateUtil.isIdentifyMatch(editIdentify, new TextDecorateUtil.OnEditMatchCallback()
		{
			@Override
			public void onTextChange(boolean isMatch)
			{
				stateManager.setIdentifyMatch(isMatch);
			}
		});
	}

	/**
	 * 协议部分按钮
	 *
	 * @param spannableCallback
	 */
	public void initAgreementTextView(TextDecorateUtil.OnTextSpannableCallback spannableCallback)
	{
		TextView textView = viewHolder.get(R.id.tv_enter_register_phone);
		int length = textView.length();

		final int clickable_length = 8;
		if (length <= clickable_length)
		{
			LogFileUtil.e("checkBox text span", "too short");
			return;
		}

		TextDecorateUtil.decorateTextSpan(textView, false, length - clickable_length, length, Color.RED, spannableCallback);

		CheckBox checkBox = viewHolder.get(R.id.checkBox_enter_register_phone);
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				stateManager.setAgreementChecked(isChecked);
			}
		});
	}

	private class RegisterPhoneStateManager
	{
		// 手机号 是否满足 格式
		private boolean isMobileMatch;

		// 短信验证码 是否满足 格式
		private boolean isIdentifyMatch;

		// 协议是否勾选
		private boolean isAgreementChecked;

		// 验证码 按钮 是否在 倒计时
		private boolean isIdentifyCountDown;

		public RegisterPhoneStateManager()
		{
			this.isAgreementChecked = true;
		}

		public void setMobileMatch(boolean isMatch)
		{
			if (isMobileMatch == isMatch)
			{
				return;
			}

			if (isMatch)
			{
				// 如果没有在 倒计时; 就让获取验证码 可点击
				if (!isIdentifyCountDown && isAgreementChecked)
				{
					TextView textView = viewHolder.get(R.id.tv_register_phone_identify);
					textView.setTextColor(ContextCompat.getColor(sContext, R.color.hokolRed));
				}

				// 修改 nextButton状态
				if (isIdentifyMatch && isAgreementChecked)
				{
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_redhokol);
				}
			}
			else
			{
				// 如果没有在 倒计时; 就让获取验证码 不可点击
				if (!isIdentifyCountDown)
				{
					TextView textView = viewHolder.get(R.id.tv_register_phone_identify);
					textView.setTextColor(ContextCompat.getColor(sContext, R.color.hokolGray));
				}

				// 修改 nextButton状态
				if (isIdentifyMatch && isAgreementChecked)
				{
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_pinkhokol);
				}
			}

			this.isMobileMatch = isMatch;
		}

		public void setIdentifyMatch(boolean isMatch)
		{
			if (isIdentifyMatch == isMatch)
			{
				return;
			}

			if (isMatch)
			{
				// 修改 nextButton状态
				if (isMobileMatch && isAgreementChecked)
				{
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_redhokol);
				}
			}
			else
			{
				// 修改 nextButton状态
				if (isMobileMatch && isAgreementChecked)
				{
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_pinkhokol);
				}
			}
			this.isIdentifyMatch = isMatch;
		}

		public void setAgreementChecked(boolean isChecked)
		{
			if (isAgreementChecked == isChecked)
			{
				return;
			}

			if (isChecked)
			{
				// 修改发送验证码状态
				if (!isIdentifyCountDown && isMobileMatch)
				{
					TextView textView = viewHolder.get(R.id.tv_register_phone_identify);
					textView.setTextColor(ContextCompat.getColor(sContext, R.color.hokolRed));
				}

				// 修改 nextButton状态
				if (isMobileMatch && isIdentifyMatch)
				{
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_redhokol);
				}
			}
			else
			{
				// 修改发送验证码状态
				if (!isIdentifyCountDown && isMobileMatch)
				{
					TextView textView = viewHolder.get(R.id.tv_register_phone_identify);
					textView.setTextColor(ContextCompat.getColor(sContext, R.color.hokolGray));
				}

				// 修改 nextButton状态
				if (isMobileMatch && isIdentifyMatch)
				{
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_pinkhokol);
				}
			}

			this.isAgreementChecked = isChecked;
		}

		public void setIdentifyCountDown(boolean isCountDown)
		{
			if (isCountDown == isIdentifyCountDown)
			{
				return;
			}

			if (isCountDown)
			{
				// 修改成 倒计时状态
				if (isAgreementChecked)
				{
					TextView textView = viewHolder.get(R.id.tv_register_phone_identify);
					textView.setTextColor(ContextCompat.getColor(sContext, R.color.hokolGray));
				}
			}
			else
			{
				// 脱离倒计时 状态
				if (isAgreementChecked && isMobileMatch)
				{
					TextView textView = viewHolder.setText(R.id.tv_register_phone_identify, "重新发送");
					textView.setTextColor(ContextCompat.getColor(sContext, R.color.hokolRed));
				}
			}

			this.isIdentifyCountDown = isCountDown;
		}

		public boolean isNextBtnAble()
		{
			return isMobileMatch && isIdentifyMatch && isAgreementChecked;
		}

		public boolean isIdentifyClickable()
		{
			return !isIdentifyCountDown && isAgreementChecked && isMobileMatch;
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
			stateManager.setIdentifyCountDown(true);
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
			stateManager.setIdentifyCountDown(false);
		}
	}

	public interface OnNextButtonClickListener
	{
		/**
		 * 点击完成注册
		 *
		 * @param v
		 * @param mobile   手机号
		 * @param identify 验证码
		 */
		void onClick(View v, String mobile, String identify);
	}
}
