package com.hokol.http.helper;

/**
 * Http请求回调
 *
 * @author yline 2017/2/22 --> 17:07
 * @version 1.0.0
 */
public interface IHttpResponse<T>
{
	/**
	 * 请求成功
	 *
	 * @param t 返回类型
	 */
	void onSuccess(T t);

	/**
	 * 返回错误码
	 *
	 * @param code 错误类型
	 */
	void onFailureCode(int code);

	/**
	 * 网络错误
	 *
	 * @param ex 错误具体
	 */
	void onFailure(Exception ex);
}