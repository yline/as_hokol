package com.hokol.http.helper;

import okhttp3.Call;

/**
 * 获取到Http请求结果,进行一次Handler处理,提交回去
 *
 * @author yline 2017/2/23 --> 16:39
 * @version 1.0.0
 */
public interface IHttpDispose<Result>
{
	/**
	 * 请求成功
	 *
	 * @param call       请求参数
	 * @param jsonResult 收到结果
	 */
	void onNetSuccess(Call call, String jsonResult, Class<Result> clazz);

	/**
	 * 请求失败
	 *
	 * @param call       请求参数
	 * @param ex         返回异常
	 * @param isResponse 服务器是否有相应(若有,则为服务器异常)
	 */
	void onFailure(Call call, Exception ex, boolean isResponse);
}
