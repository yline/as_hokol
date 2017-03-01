package com.hokol.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.base.log.LogFileUtil;
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

	@OnClick(R.id.btn_main_login)
	public void btnActionLogin()
	{
		String username = etUserName.getText().toString().trim();
		String password = etPassWord.getText().toString().trim();

		String httpUrl = HttpConstant.HTTP_PHONE_LOGIN_URL;

		LogFileUtil.v("username = " + username + ",password = " + password);
		LogFileUtil.v("httpUrl = " + httpUrl);

		// doGet(httpUrl, new String[]{"user_tel", "user_pwd"}, new String[]{username, password});
		doPost(httpUrl, new RequestPhoneLoginBean(username, password));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		ButterKnife.bind(this);
	}
	
	private void doGet(String httpUrl, String[] key, String[] value)
	{
		new xHttp<ResponsePhoneLoginBean>()
		{
			@Override
			public void onSuccess(ResponsePhoneLoginBean bean)
			{
				LogFileUtil.v(bean.toString());
				Toast.makeText(WelcomeActivity.this, bean.toString(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailureCode(int code)
			{
				Toast.makeText(WelcomeActivity.this, "code = " + code, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(Exception ex)
			{
				Toast.makeText(WelcomeActivity.this, "ex = " + Log.getStackTraceString(ex), Toast.LENGTH_SHORT).show();
			}
		}.doGet(httpUrl, key, value, ResponsePhoneLoginBean.class);
	}

	private void doPost(String httpUrl, RequestPhoneLoginBean requestBean)
	{
		new xHttp<ResponsePhoneLoginBean>()
		{

			@Override
			public void onSuccess(ResponsePhoneLoginBean responsePhoneLoginBean)
			{
				LogFileUtil.v(responsePhoneLoginBean.toString());
				Toast.makeText(WelcomeActivity.this, responsePhoneLoginBean.toString(), Toast.LENGTH_SHORT).show();
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
