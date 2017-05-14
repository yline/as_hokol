package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.viewhelper.EnterRegisterPhoneHelper;
import com.yline.base.BaseAppCompatActivity;

public class EnterRegisterPhoneActivity extends BaseAppCompatActivity
{
	private EnterRegisterPhoneHelper registerPhoneHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_register_phone);

		registerPhoneHelper = new EnterRegisterPhoneHelper(this);
		registerPhoneHelper.setOnFinishListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
		registerPhoneHelper.setOnNextListener(new EnterRegisterPhoneHelper.OnNextButtonClickListener()
		{
			@Override
			public void onClick(View v, String mobile, String identify)
			{
				IApplication.toast("mobile = " + mobile + ",identify = " + identify);
				if (identify.equals("123456"))
				{
					EnterRegisterCompleteInfoActivity.actionStart(EnterRegisterPhoneActivity.this);
				}
			}
		});

		registerPhoneHelper.initAgreementTextView(new ClickableSpan()
		{
			@Override
			public void updateDrawState(TextPaint ds)
			{
				ds.setUnderlineText(false); // 出去下划线
			}

			@Override
			public void onClick(View widget)
			{

			}
		});
		registerPhoneHelper.setOnIdentifyCodeListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("已发送验证码《123456》");
			}
		});
		registerPhoneHelper.initInputView();
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, EnterRegisterPhoneActivity.class));
	}
}
