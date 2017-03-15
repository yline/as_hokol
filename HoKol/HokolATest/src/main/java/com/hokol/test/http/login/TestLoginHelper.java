package com.hokol.test.http.login;

import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttp;
import com.hokol.medium.http.bean.VLoginPhonePasswordBean;
import com.hokol.medium.http.bean.WLoginPhonePasswordBean;

public class TestLoginHelper
{
	public void doPhoneLogin(String username, String password)
	{
		String httpUrl = HttpConstant.url_login_pwd;
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
			public void onSuccess(VLoginPhonePasswordBean responsePhoneLoginBean)
			{

			}
		}.doRequest(httpUrl, VLoginPhonePasswordBean.class);
	}
}
