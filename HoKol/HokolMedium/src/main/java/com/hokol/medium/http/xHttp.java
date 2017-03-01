package com.hokol.medium.http;

import com.google.gson.Gson;
import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.helper.HttpJsonDispose;
import com.hokol.medium.http.helper.HttpJsonRequest;
import com.hokol.medium.http.helper.HttpNullDispose;
import com.hokol.medium.http.helper.HttpNullRequest;
import com.hokol.medium.http.helper.IHttpResponse;

import okhttp3.Request;

/**
 * Http使用类,不断地复写doRequest方法即可统一使用
 *
 * @author yline 2017/2/22 --> 23:23
 * @version 1.0.0
 */
public abstract class xHttp<Result> implements IHttpResponse<Result>
{
	private final HttpHandler httpHandler;

	public xHttp()
	{
		httpHandler = HttpHandler.build();
	}

	/**
	 * @param httpUrl
	 * @param resultClass
	 */
	public void doGet(String httpUrl, Class<Result> resultClass)
	{
		doGet(httpUrl, null, null, resultClass);
	}

	/**
	 * 这个就是做 Get请求
	 *
	 * @param httpUrl     请求链接
	 * @param key         请求的key
	 * @param value       请求的value
	 * @param resultClass 返回数据类型
	 */
	public void doGet(String httpUrl, String[] key, String[] value, Class<Result> resultClass)
	{
		HttpNullRequest httpNullRequest = new HttpNullRequest(new HttpNullDispose(httpHandler, this));

		Request.Builder builder = new Request.Builder().url(httpUrl);
		if (null != key)
		{
			for (int i = 0; i < key.length; i++)
			{
				builder.addHeader(key[i], value[i]);
			}
		}
		Request request = builder.build();

		httpNullRequest.doRequest(request, resultClass);
	}

	/**
	 * 这个就是做Post请求,带参数(Json)
	 *
	 * @param httpUrl
	 * @param requestParam 可转换成Json的数据类型
	 * @param resultClass
	 */
	public void doPost(String httpUrl, Object requestParam, Class<Result> resultClass)
	{
		HttpJsonRequest httpJsonRequest = new HttpJsonRequest(new HttpJsonDispose(httpHandler, this));
		httpJsonRequest.doRequest(httpUrl, new Gson().toJson(requestParam), resultClass);
	}

	@Override
	public void onSuccess(Result result)
	{
		LogFileUtil.v("xHttp onSuccess result -> " + result.toString());
	}

	@Override
	public void onFailureCode(int code)
	{
		LogFileUtil.v("xHttp onFailureCode code -> " + code);
	}

	@Override
	public void onFailure(Exception ex)
	{
		LogFileUtil.e("", "xHttp onFailure ex ", ex);
	}
}
