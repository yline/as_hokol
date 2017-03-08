package com.hokol.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttp;
import com.hokol.medium.http.bean.VLoginPhonePasswordBean;
import com.hokol.medium.http.bean.WLoginPhonePasswordBean;

/**
 * 首个欢迎界面
 *
 * @author yline 2017/2/8 --> 10:47
 * @version 1.0.0
 */
public class WelcomeActivity extends BaseAppCompatActivity
{
	private EditText etUserName;

	private EditText etPassWord;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		initView();
	}

	private void initView()
	{
		etUserName = (EditText) findViewById(R.id.et_main_username);
		etPassWord = (EditText) findViewById(R.id.et_main_password);
		findViewById(R.id.btn_action_main).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MainActivity.actionStart(WelcomeActivity.this);
			}
		});

		findViewById(R.id.btn_main_login).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String username = etUserName.getText().toString().trim();
				String password = etPassWord.getText().toString().trim();

				String httpUrl = HttpConstant.HTTP_PHONE_LOGIN_URL;

				doPost(httpUrl, new WLoginPhonePasswordBean(username, password));
			}
		});

		findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

			}
		});
	}
	
	private void doPost(String httpUrl, WLoginPhonePasswordBean requestBean)
	{
		new XHttp<VLoginPhonePasswordBean>()
		{

			@Override
			public void onSuccess(VLoginPhonePasswordBean responsePhoneLoginBean)
			{
				super.onSuccess(responsePhoneLoginBean);
				MainActivity.actionStart(WelcomeActivity.this);
				IApplication.finishActivity();
			}

			@Override
			public void onFailureCode(int code)
			{
				super.onFailureCode(code);
				Toast.makeText(WelcomeActivity.this, "code = " + code, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(Exception ex)
			{
				super.onFailure(ex);
				Toast.makeText(WelcomeActivity.this, "ex = " + Log.getStackTraceString(ex), Toast.LENGTH_SHORT).show();
			}
		}.doPost(httpUrl, requestBean, VLoginPhonePasswordBean.class);
	}
}
