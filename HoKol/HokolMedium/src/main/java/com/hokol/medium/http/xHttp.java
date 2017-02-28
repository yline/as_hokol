package com.hokol.medium.http;

import com.google.gson.Gson;
import com.hokol.medium.http.helper.HttpJsonDispose;
import com.hokol.medium.http.helper.HttpJsonRequest;
import com.hokol.medium.http.helper.HttpNullDispose;
import com.hokol.medium.http.helper.HttpNullRequest;
import com.hokol.medium.http.helper.IHttpResponse;

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
	 * 这个就是做 Get请求,不带参数
	 *
	 * @param httpUrl
	 * @param resultClass
	 */
	public void doRequest(String httpUrl, Class<Result> resultClass)
	{
		HttpNullRequest httpNullRequest = new HttpNullRequest(new HttpNullDispose(httpHandler, this));
		httpNullRequest.doRequest(httpUrl, resultClass);
	}

	/**
	 * 这个就是做Post请求,带参数(Json)
	 *
	 * @param httpUrl
	 * @param requestParam 可转换成Json的数据类型
	 * @param resultClass
	 */
	public void doRequest(String httpUrl, Object requestParam, Class<Result> resultClass)
	{
		HttpJsonRequest httpJsonRequest = new HttpJsonRequest(new HttpJsonDispose(httpHandler, this));
		httpJsonRequest.doRequest(httpUrl, new Gson().toJson(requestParam), resultClass);
	}

	@Override
	public abstract void onSuccess(Result result);

	@Override
	public void onFailureCode(int code)
	{

	}

	@Override
	public void onFailure(Exception ex)
	{

	}
}
