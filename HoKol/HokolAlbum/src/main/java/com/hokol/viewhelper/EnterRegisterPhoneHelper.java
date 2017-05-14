package com.hokol.viewhelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.hokol.R;
import com.yline.log.LogFileUtil;
import com.yline.view.common.ViewHolder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnterRegisterPhoneHelper
{
	private ViewHolder viewHolder;

	private Context sContext;

	private RegisterPhoneStateManager stateManager;

	public EnterRegisterPhoneHelper(Activity activity)
	{
		this.sContext = activity.getBaseContext();
		viewHolder = new ViewHolder(activity);
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
		editUsername.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				String inputString = s.toString();
				if (inputString.length() == 11)
				{
					if (isMobileNumber(s.toString()))
					{
						stateManager.setMobileMatch(true);
						return;
					}
				}

				stateManager.setMobileMatch(false);
			}

			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});

		EditText editIdentify = viewHolder.get(R.id.et_register_phone_password);
		editIdentify.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				String inputString = s.toString();
				if (inputString.length() == 6)
				{
					stateManager.setIdentifyMatch(true);
					return;
				}

				stateManager.setIdentifyMatch(false);
			}

			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});
	}

	/**
	 * 协议部分按钮
	 *
	 * @param clickableSpan
	 */
	public void initAgreementTextView(ClickableSpan clickableSpan)
	{
		TextView textView = viewHolder.get(R.id.tv_enter_register_phone);
		initAgreementText(textView, clickableSpan);

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

	/**
	 * 给文字添加点击事件
	 *
	 * @param textView
	 */
	private void initAgreementText(TextView textView, ClickableSpan clickableSpan)
	{
		String agreementText = textView.getText().toString().trim();
		int length = agreementText.length();

		final int clickable_length = 8;
		if (length <= clickable_length)
		{
			LogFileUtil.e("checkBox text span", "too short");
			return;
		}

		final SpannableString spannableString = new SpannableString(agreementText);
		spannableString.setSpan(clickableSpan, length - clickable_length, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		spannableString.setSpan(new ForegroundColorSpan(Color.RED), length - clickable_length, length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		textView.setText(spannableString);
		textView.setMovementMethod(LinkMovementMethod.getInstance());
	}

	private boolean isMobileNumber(String mobile)
	{
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
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
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.enter_register_phone_finish_able);
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
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.enter_register_phone_finish);
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
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.enter_register_phone_finish_able);
				}
			}
			else
			{
				// 修改 nextButton状态
				if (isMobileMatch && isAgreementChecked)
				{
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.enter_register_phone_finish);
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
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.enter_register_phone_finish_able);
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
					viewHolder.get(R.id.btn_register_phone_action_next).setBackgroundResource(R.drawable.enter_register_phone_finish);
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
