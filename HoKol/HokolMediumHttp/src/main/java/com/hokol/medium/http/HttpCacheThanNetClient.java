package com.hokol.medium.http;

import com.yline.http.interceptor.CacheThanNetInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class HttpCacheThanNetClient
{
	private HttpCacheThanNetClient()
	{
	}

	public static OkHttpClient getInstance()
	{
		return HttpCacheThanNetHolder.getHttpClient();
	}

	private static class HttpCacheThanNetHolder
	{
		private static OkHttpClient getHttpClient()
		{
			OkHttpClient.Builder builder = new OkHttpClient.Builder();

			// 设置超时
			builder.connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS);

			// 添加拦截器；默认走网络，如果没有网，则走缓存
			builder.addInterceptor(new CacheThanNetInterceptor());

			return builder.build();
		}
	}
}
