package cn.test.login;

import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.bean.RequestPhoneLoginBean;
import com.hokol.medium.http.bean.ResponsePhoneLoginBean;
import com.hokol.medium.http.xHttp;

public class TestLoginHelper
{
	public void doPhoneLogin(String username, String password)
	{
		String httpUrl = HttpConstant.HTTP_PHONE_LOGIN_URL;
		final RequestPhoneLoginBean requestBean = new RequestPhoneLoginBean(username, password);
		// 这样的方法,并不会被执行
		new xHttp<ResponsePhoneLoginBean>()
		{

			@Override
			public void onSuccess(ResponsePhoneLoginBean responsePhoneLoginBean)
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
		}.doPost(httpUrl, requestBean, ResponsePhoneLoginBean.class);
	}
}
