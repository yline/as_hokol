package com.hokol.medium.http.bean;

/**
 * 这是所有Http请求的父类
 *
 * @author yline 2017/2/28 --> 17:08
 * @version 1.0.0
 */
public class ResponseXBean
{
	private int code;

	private String data;

	public ResponseXBean(int code, String data)
	{
		this.code = code;
		this.data = data;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	@Override
	public String toString()
	{
		return "ResponseXBean{" +
				"code=" + code +
				", data='" + data + '\'' +
				'}';
	}
}
