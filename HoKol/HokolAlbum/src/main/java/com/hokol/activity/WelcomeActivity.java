package com.hokol.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.bean.RequestPhoneLoginBean;
import com.hokol.medium.http.bean.ResponsePhoneLoginBean;
import com.hokol.medium.http.xHttp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首个欢迎界面
 *
 * @author yline 2017/2/8 --> 10:47
 * @version 1.0.0
 */
public class WelcomeActivity extends BaseAppCompatActivity
{
	// 跳转
	@OnClick(R.id.btn_action_main)
	public void btnActionMain()
	{
		MainActivity.actionStart(WelcomeActivity.this);
	}

	@BindView(R.id.et_main_username)
	public EditText etUserName;

	@BindView(R.id.et_main_password)
	public EditText etPassWord;

	// 网络登陆请求
	@OnClick(R.id.btn_main_login)
	public void btnActionLogin()
	{
		String username = etUserName.getText().toString().trim();
		String password = etPassWord.getText().toString().trim();

		String httpUrl = HttpConstant.HTTP_PHONE_LOGIN_URL;

		doPost(httpUrl, new RequestPhoneLoginBean(username, password));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		ButterKnife.bind(this);
	}
	
	private void doPost(String httpUrl, RequestPhoneLoginBean requestBean)
	{
		new xHttp<ResponsePhoneLoginBean>()
		{

			@Override
			public void onSuccess(ResponsePhoneLoginBean responsePhoneLoginBean)
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
		}.doPost(httpUrl, requestBean, ResponsePhoneLoginBean.class);
	}

	@OnClick(R.id.btn_test)
	public void btnTest()
	{

	}
}
