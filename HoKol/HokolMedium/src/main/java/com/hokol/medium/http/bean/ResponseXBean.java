package com.hokol.medium.http.bean;

/**
 * 这是所有Http请求的父类
 *
 * @author yline 2017/2/28 --> 17:08
 * @version 1.0.0
 */
public class ResponseXBean<T>
{
	private int code;

	private T data;

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}
}
