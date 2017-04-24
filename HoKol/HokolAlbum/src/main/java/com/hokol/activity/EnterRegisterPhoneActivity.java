package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.yline.base.BaseAppCompatActivity;
import com.yline.log.LogFileUtil;

public class EnterRegisterPhoneActivity extends BaseAppCompatActivity
{
	private CheckBox checkBox;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_register_phone);

		findViewById(R.id.btn_enter_register_phone_action_next).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EnterRegisterCompleteInfoActivity.actionStart(EnterRegisterPhoneActivity.this);
			}
		});

		checkBox = (CheckBox) findViewById(R.id.checkBox_enter_register_phone);

		TextView textView = (TextView) findViewById(R.id.tv_enter_register_phone);
		initAgreementText(textView);
	}

	/**
	 * 给文字添加点击事件
	 *
	 * @param textView
	 */
	private void initAgreementText(TextView textView)
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
		spannableString.setSpan(new ClickableSpan()
		{
			@Override
			public void onClick(View widget)
			{
				IApplication.toast("");
			}
		}, length - clickable_length, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		spannableString.setSpan(new ForegroundColorSpan(Color.RED), length - clickable_length, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView.setText(spannableString);
		textView.setMovementMethod(LinkMovementMethod.getInstance());
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, EnterRegisterPhoneActivity.class));
	}
}
