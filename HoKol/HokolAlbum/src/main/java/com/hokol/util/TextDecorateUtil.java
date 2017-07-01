package com.hokol.util;

import android.support.annotation.ColorInt;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * EditText装饰模式
 *
 * @author yline 2017/5/15 -- 14:17
 * @version 1.0.0
 */
public class TextDecorateUtil
{
	/**
	 * 校验 手机号 是否正确
	 *
	 * @param editText
	 * @param matchCallback
	 */
	public static void isPhoneMatch(EditText editText, final OnEditMatchCallback matchCallback)
	{
		editText.addTextChangedListener(new TextWatcher()
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
						if (null != matchCallback)
						{
							matchCallback.onTextChange(true);
						}
						return;
					}
				}

				if (null != matchCallback)
				{
					matchCallback.onTextChange(false);
				}
			}

			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});
	}

	/**
	 * 检测 密码是否 符合规格
	 *
	 * @param editText
	 * @param matchCallback
	 */
	public static void isPhonePwdMatch(EditText editText, final OnEditMatchCallback matchCallback)
	{
		editText.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				String inputString = s.toString();
				if (inputString.length() > 5 && inputString.length() < 21)
				{
					if (null != matchCallback)
					{
						matchCallback.onTextChange(true);
					}
					return;
				}

				if (null != matchCallback)
				{
					matchCallback.onTextChange(false);
				}
			}

			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});
	}

	/**
	 * 检测 昵称 是否 符合规格
	 *
	 * @param editText
	 * @param matchCallback
	 */
	public static void isNicknameMatch(EditText editText, final OnEditMatchCallback matchCallback)
	{
		editText.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				String inputString = s.toString();
				if (inputString.length() > 0 && inputString.length() < 17)
				{
					if (null != matchCallback)
					{
						matchCallback.onTextChange(true);
					}
					return;
				}

				if (null != matchCallback)
				{
					matchCallback.onTextChange(false);
				}
			}

			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});
	}

	/**
	 * 校验手机 校验码 是否 符合规格
	 *
	 * @param editText
	 * @param matchCallback
	 */
	public static void isIdentifyMatch(EditText editText, final OnEditMatchCallback matchCallback)
	{
		editText.addTextChangedListener(new TextWatcher()
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
					if (null != matchCallback)
					{
						matchCallback.onTextChange(true);
					}
					return;
				}

				if (null != matchCallback)
				{
					matchCallback.onTextChange(false);
				}
			}

			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});
	}

	/**
	 * @param textView             长度
	 * @param isUnderLine          是否有下划线
	 * @param clickableStartLength 可点击的开始数
	 * @param clickableEndLength   可点击的结束数
	 * @param clickableColor       可点击的颜色
	 */
	public static void decorateTextSpan(TextView textView, final boolean isUnderLine, int clickableStartLength, int clickableEndLength, @ColorInt int clickableColor, final OnTextSpannableCallback callback)
	{
		String agreementText = textView.getText().toString().trim();
		final SpannableString spannableString = new SpannableString(agreementText);
		spannableString.setSpan(new ClickableSpan()
		{
			@Override
			public void updateDrawState(TextPaint ds)
			{
				if (!isUnderLine)
				{
					ds.setUnderlineText(false); // 出去下划线
				}
				else
				{
					super.updateDrawState(ds);
				}
			}

			@Override
			public void onClick(View widget)
			{
				if (null != callback)
				{
					callback.onClick(widget);
				}
			}
		}, clickableStartLength, clickableEndLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		spannableString.setSpan(new ForegroundColorSpan(clickableColor), clickableStartLength, clickableEndLength, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		textView.setText(spannableString);
		textView.setMovementMethod(LinkMovementMethod.getInstance());
	}

	/**
	 * 检测手机号；11 位
	 *
	 * @param mobile
	 * @return
	 */
	public static boolean isMobileNumber(String mobile)
	{
		if (TextUtils.isEmpty(mobile))
		{
			return false;
		}

		return (mobile.length() == 11);
		/*Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();*/
	}

	public interface OnEditMatchCallback
	{
		/**
		 * 校验 结果
		 *
		 * @param isMatch
		 */
		void onTextChange(boolean isMatch);
	}

	public interface OnTextSpannableCallback
	{
		void onClick(View widget);
	}
}
