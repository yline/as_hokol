package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.widget.DialogIosWidget;
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
				if (mobile.equals("15958148487"))
				{
					new DialogIosWidget(EnterRegisterPhoneActivity.this)
					{
						@Override
						protected void initBuilder(Builder builder)
						{
							super.initBuilder(builder);
							builder.setTitle("您已经注册过红客了\n请直接登陆吧");
							builder.setPositiveText("立即登陆");
						}

						@Override
						protected void initMessageTextView(TextView textView, Builder builder)
						{
							super.initMessageTextView(textView, builder);
							textView.setVisibility(View.GONE);
						}
					}.show();
				}
				else if (identify.equals("123456"))
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
