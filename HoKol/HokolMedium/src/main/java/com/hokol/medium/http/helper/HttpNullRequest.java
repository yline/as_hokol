package com.hokol.medium.http.helper;

import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpClient;
import com.hokol.medium.http.HttpConstant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Http请求类,负责Http请求,然后,然后交给Dispose处理
 *
 * @author yline 2017/2/23 --> 9:57
 * @version 1.0.0
 */
public class HttpNullRequest
{
	private OkHttpClient okHttpClient;

	private IHttpDispose iHttpDispose;

	public HttpNullRequest(IHttpDispose iHttpDispose)
	{
		this.iHttpDispose = iHttpDispose;
	}

	/**
	 * 发起Http请求之前的 准备
	 */
	private void preRequest()
	{
		okHttpClient = HttpClient.getInstance();

		okHttpClient.newBuilder().connectTimeout(HttpConstant.CONNECT_TIME_OUT, TimeUnit.SECONDS).build();

		if (HttpConstant.isDebug)
		{
			LogFileUtil.v("OkHttpClient preRequest over");
		}
	}

	public void doRequest(String httpUrl, final Class clazz)
	{
		preRequest();

		final Request request = new Request.Builder().url(httpUrl).build();

		okHttpClient.newCall(request).enqueue(new okhttp3.Callback()
		{
			@Override
			public void onFailure(final Call call, final IOException e)
			{
				preResponse("onFailure ->", e);
				if (null != iHttpDispose)
				{
					iHttpDispose.onFailure(call, e, false);
				}
			}

			@Override
			public void onResponse(final Call call, final Response response) throws IOException
			{
				if (response.isSuccessful())
				{
					String msg = response.body().string();
					preResponse("onResponse success -> " + msg);
					if (null != iHttpDispose)
					{
						iHttpDispose.onNetSuccess(call, msg, clazz);
					}
				}
				else
				{
					preResponse("onResponse failed -> " + response.message());
					if (null != iHttpDispose)
					{
						iHttpDispose.onFailure(call, new UnsupportedOperationException(response.message()), true);
					}
				}
			}
		});
	}

	private void preResponse(String msg)
	{
		if (HttpConstant.isDebug)
		{
			LogFileUtil.v(msg);
		}
	}

	private void preResponse(String msg, IOException e)
	{
		if (HttpConstant.isDebug)
		{
			LogFileUtil.e("", msg, e);
		}
	}
}
