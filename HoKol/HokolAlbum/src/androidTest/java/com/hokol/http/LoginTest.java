package com.hokol.http;

import android.test.ActivityTestCase;

import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.bean.RequestPhoneLoginBean;
import com.hokol.medium.http.bean.ResponsePhoneLoginBean;
import com.hokol.medium.http.xHttp;

public class LoginTest extends ActivityTestCase
{
	public static final String TAG = "Login";

	public void testPhoneLogin() throws Exception
	{
		LogFileUtil.d(TAG, "testPhoneLogin");
		assertEquals("1", "1");

		phoneLogin("13656786656", "lj123456");
	}

	private void phoneLogin(String username, String password)
	{
		LogFileUtil.v(TAG, "phoneLogin start");

		String httpUrl = HttpConstant.HTTP_PHONE_LOGIN_URL;
		RequestPhoneLoginBean requestBean = new RequestPhoneLoginBean(username, password);
		// 这样的方法,并不会被执行
		new xHttp<ResponsePhoneLoginBean>()
		{

			@Override
			public void onSuccess(ResponsePhoneLoginBean responsePhoneLoginBean)
			{
				super.onSuccess(responsePhoneLoginBean);
				assertNotNull(responsePhoneLoginBean);

				LogFileUtil.v(TAG, responsePhoneLoginBean.toString());
			}

			@Override
			public void onFailureCode(int code)
			{
				super.onFailureCode(code);
				LogFileUtil.v(TAG, "onFailureCode");
			}

			@Override
			public void onFailure(Exception ex)
			{
				super.onFailure(ex);
				LogFileUtil.v(TAG, "onFailure");
			}
		}.doPost(httpUrl, requestBean, ResponsePhoneLoginBean.class);
	}
}
