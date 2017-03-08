package com.hokol.test.http.login;

import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.bean.VLoginPhonePasswordBean;
import com.hokol.medium.http.bean.WLoginPhonePasswordBean;
import com.hokol.medium.http.xHttp;

public class TestLoginHelper
{
	public void doPhoneLogin(String username, String password)
	{
		String httpUrl = HttpConstant.HTTP_PHONE_LOGIN_URL;
		final WLoginPhonePasswordBean requestBean = new WLoginPhonePasswordBean(username, password);
		// 这样的方法,并不会被执行
		new xHttp<VLoginPhonePasswordBean>()
		{

			@Override
			public void onSuccess(VLoginPhonePasswordBean responsePhoneLoginBean)
			{
				super.onSuccess(responsePhoneLoginBean);
				if (null != responsePhoneLoginBean)
				{
					LogFileUtil.e("doPhoneLogin", "success");
				}
			}

			@Override
			public void onFailureCode(int code)
			{
				super.onFailureCode(code);
			}

			@Override
			public void onFailure(Exception ex)
			{
				super.onFailure(ex);
			}
		}.doPost(httpUrl, requestBean, VLoginPhonePasswordBean.class);
	}
}
