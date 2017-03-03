package com.hokol.medium.http.bean;

public class RequestMultiplexNewsBean
{
	/**
	 * 请求新闻开始号
	 */
	private String num1;

	/**
	 * 请求新闻结束号
	 */
	private String num2;

	public RequestMultiplexNewsBean(String num1, String num2)
	{
		this.num1 = num1;
		this.num2 = num2;
	}

	public String getNum1()
	{
		return num1;
	}

	public void setNum1(String num1)
	{
		this.num1 = num1;
	}

	public String getNum2()
	{
		return num2;
	}

	public void setNum2(String num2)
	{
		this.num2 = num2;
	}

	@Override
	public String toString()
	{
		return "RequestMultiplexNewsBean{" +
				"num1=" + num1 +
				", num2=" + num2 +
				'}';
	}
}
