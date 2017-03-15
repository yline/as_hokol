package com.hokol.http;

import android.test.ActivityTestCase;

import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttp;
import com.hokol.medium.http.bean.VLoginPhonePasswordBean;
import com.hokol.medium.http.bean.WLoginPhonePasswordBean;

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
		final WLoginPhonePasswordBean requestBean = new WLoginPhonePasswordBean(username, password);
		// 这样的方法,并不会被执行
		new XHttp<VLoginPhonePasswordBean>()
		{
			@Override
			protected Object getRequestPostParam()
			{
				return requestBean;
			}

			@Override
			public void onSuccess(VLoginPhonePasswordBean vLoginPhonePasswordBean)
			{
				assertNotNull(vLoginPhonePasswordBean);
			}
		}.doRequest(httpUrl, VLoginPhonePasswordBean.class);
	}
}
