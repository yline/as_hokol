package com.hokol.medium.http.helper;

import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.bean.ResponseXBean;

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
			preResponse("onNetSuccess code -> " + code + ",data -> " + responseXBean.getData());

			// 为了放到子线程进行Gson解析
			Result result = null;
			if (HttpConstant.REQUEST_SUCCESS_CODE == code)
			{
				result = gson.fromJson(responseXBean.getData().toString(), clazz);
			}

			final Result finalResult = result;
			handler.post(new Runnable()
			{
				@Override
				public void run()
				{
					if (HttpConstant.REQUEST_SUCCESS_CODE == code)
					{
						iHttpResponse.onSuccess(finalResult);
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
		if (HttpConstant.isDebug)
		{
			LogFileUtil.v(msg);
		}
	}
}
