package com.hokol.medium.http.helper;

import com.hokol.base.log.LogFileUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.hokol.medium.http.helper.HttpDefaultInterceptor.Interceptor_debug;

public class HttpDefaultNetworkInterceptor implements Interceptor
{
	@Override
	public Response intercept(Chain chain) throws IOException
	{
		Request request = chain.request();
		long time1 = System.nanoTime();
		if (Interceptor_debug)
		{
			LogFileUtil.v(String.format("Network request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
		}

		Response response = chain.proceed(request);
		// 打印日志
		if (Interceptor_debug)
		{
			long time2 = System.nanoTime();

			LogFileUtil.v(String.format("Network response %s in %.1fms%n%s", response.request().url(), (time2 - time1) / 1e6d, response.headers()));
		}

		// 设置缓存
		return response;
	}
}
