package com.hokol.medium.http.cache;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpCache
{
	public void testSampleCache()
	{
		final CacheControl.Builder builder = new CacheControl.Builder();
		builder.noCache();//不使用缓存，全部走网络
		builder.noStore();//不使用缓存，也不存储缓存
		builder.onlyIfCached();//只使用缓存
		builder.noTransform();//禁止转码
		builder.maxAge(10, TimeUnit.MILLISECONDS);//指示客户机可以接收生存期不大于指定时间的响应。
		builder.maxStale(10, TimeUnit.SECONDS);//指示客户机可以接收超出超时期间的响应消息
		builder.minFresh(10, TimeUnit.SECONDS);//指示客户机可以接收响应时间小于当前时间加上指定时间的响应。
		CacheControl cache = builder.build();//cacheControl
	}

	public void test10SecondCache()
	{
		final CacheControl.Builder builder = new CacheControl.Builder();
		builder.maxAge(10, TimeUnit.MILLISECONDS);
		CacheControl cache = builder.build();
	}
	
	private static final String TAG = "Fucker";

	/**
	 * 如果cache没有过去会直接返回cache而不会发起网络请求，若过期会自动发起网络请求
	 */
	public void testUseCache(OkHttpClient mOkHttpClient, String requestUrl)
	{
		final CacheControl.Builder builder = new CacheControl.Builder();
		builder.maxAge(10, TimeUnit.MILLISECONDS);
		CacheControl cache = builder.build();
		final Request request = new Request.Builder().cacheControl(cache).url(requestUrl).build();
		final Call call = mOkHttpClient.newCall(request);//
		call.enqueue(new Callback()
		{
			@Override
			public void onFailure(Call call, IOException e)
			{
				//failedCallBack("访问失败", callBack);
				Log.e(TAG, e.toString());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException
			{
				if (response.isSuccessful())
				{
					String string = response.body().string();
					Log.e(TAG, "response ----->" + string);
					//successCallBack((T) string, callBack);
				}
				else
				{
					//failedCallBack("服务器错误", callBack);
				}
			}
		});
		return;
	}
}
