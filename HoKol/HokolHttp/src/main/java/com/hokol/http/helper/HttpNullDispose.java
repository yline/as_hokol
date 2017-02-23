package com.hokol.http.helper;

import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hokol.base.log.LogFileUtil;
import com.hokol.http.bean.ResponseXBean;
import com.hokol.http.xHttp;

import okhttp3.Call;

public class HttpNullDispose<Result> implements IHttpDispose<Result>
{
	private Handler handler;

	private IHttpResponse iHttpResponse;

	public HttpNullDispose(Handler handler, IHttpResponse iHttpResponse)
	{
		this.handler = handler;
		this.iHttpResponse = iHttpResponse;
	}

	@Override
	public void onNetSuccess(Call call, String jsonResult, Class<Result> clazz)
	{
		if (null != iHttpResponse && null != handler)
		{
			Gson gson = new Gson();
			ResponseXBean<Result> responseXBean = gson.fromJson(jsonResult, new TypeToken<ResponseXBean<Result>>()
			{
			}.getType());

			final int code = responseXBean.getCode();
			preResponse("onNetSuccess code -> " + code);
			final Result result = gson.fromJson(responseXBean.getData().toString(), clazz);

			handler.post(new Runnable()
			{
				@Override
				public void run()
				{
					if (xHttp.REQUEST_SUCCESS_CODE == code)
					{
						iHttpResponse.onSuccess(result);
					}
					else
					{
						iHttpResponse.onFailureCode(code);
					}
				}
			});
		}
	}

	@Override
	public void onFailure(Call call, final Exception ex, boolean isResponse)
	{
		if (null != iHttpResponse && null != handler)
		{
			handler.post(new Runnable()
			{
				@Override
				public void run()
				{
					// 网络错误,网络本身错误
					iHttpResponse.onFailure(ex);
				}
			});
		}
	}

	private void preResponse(String msg)
	{
		if (xHttp.isDebug)
		{
			LogFileUtil.v(msg);
		}
	}
}
