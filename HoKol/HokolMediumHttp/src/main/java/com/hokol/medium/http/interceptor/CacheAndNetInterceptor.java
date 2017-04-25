package com.hokol.medium.http.interceptor;

import com.hokol.medium.http.cache.CacheManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheAndNetInterceptor extends BaseInterceptor
{
	private final OnCacheResponseCallback onCacheResponseCallback;

	public CacheAndNetInterceptor(OnCacheResponseCallback callback)
	{
		this.onCacheResponseCallback = callback;
	}

	@Override
	public Response intercept(Interceptor.Chain chain) throws IOException
	{
		Request request = chain.request();
		preLog(chain, request);

		Response cacheResponse = CacheManager.getInstance().get(request);
		if (null != onCacheResponseCallback)
		{
			onCacheResponseCallback.onCacheResponse(cacheResponse);
		}

		long time1 = System.nanoTime();
		Response response = chain.proceed(request);

		postLog(response, System.nanoTime() - time1);
		CacheManager.getInstance().write(response);
		Response resultResponse = CacheManager.getInstance().get(request);

		return resultResponse;
	}
}
