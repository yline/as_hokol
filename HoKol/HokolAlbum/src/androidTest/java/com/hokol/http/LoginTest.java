package com.hokol.http;

import android.test.ActivityTestCase;

import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.XHttpAdapter;
import com.hokol.medium.http.bean.VLoginPhonePwdBean;
import com.hokol.medium.http.bean.WLoginPhonePwdBean;
import com.hokol.medium.http.helper.XHttpUtil;

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
		
		final WLoginPhonePwdBean requestBean = new WLoginPhonePwdBean(username, password);
		// 这样的方法,并不会被执行
		XHttpUtil.doLoginPhonePwd(requestBean, new XHttpAdapter<VLoginPhonePwdBean>()
		{
			@Override
			public void onSuccess(VLoginPhonePwdBean vLoginPhonePwdBean)
			{
				assertNotNull(vLoginPhonePwdBean);
			}
		});
	}
}
