package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.WEnterCodeRegisterBean;
import com.hokol.medium.http.bean.WEnterRegisterBean;
import com.hokol.medium.widget.DialogIosWidget;
import com.hokol.util.TextDecorateUtil;
import com.hokol.viewhelper.EnterRegisterPhoneHelper;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.common.ViewHolder;

public class EnterRegisterPhoneActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	private EnterRegisterPhoneHelper registerPhoneHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_register_phone);

		viewHolder = new ViewHolder(this);
		registerPhoneHelper = new EnterRegisterPhoneHelper(this, viewHolder);
		registerPhoneHelper.initInputView();

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
				final String phoneNumber = viewHolder.getText(R.id.et_enter_register_phone_username);
				String identifyCode = viewHolder.getText(R.id.et_register_phone_password);

				XHttpUtil.doEnterRegister(new WEnterRegisterBean(phoneNumber, identifyCode), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						EnterRegisterCompleteInfoActivity.actionStart(EnterRegisterPhoneActivity.this, phoneNumber);
					}

					@Override
					public void onFailureCode(int code)
					{
						super.onFailureCode(code);
						IApplication.toast("填写信息错误");
					}
				});
			}
		});

		registerPhoneHelper.initAgreementTextView(new TextDecorateUtil.OnTextSpannableCallback()
		{
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
				String phoneNumber = viewHolder.getText(R.id.et_enter_register_phone_username);
				XHttpUtil.doEnterCodeRegister(new WEnterCodeRegisterBean(phoneNumber), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						IApplication.toast("请查看手机短信");
					}

					@Override
					public void onFailureCode(int code)
					{
						super.onFailureCode(code);
						if (3001 == code)
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
							}.show();
						}
					}
				});
			}
		});
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, EnterRegisterPhoneActivity.class));
	}
}
