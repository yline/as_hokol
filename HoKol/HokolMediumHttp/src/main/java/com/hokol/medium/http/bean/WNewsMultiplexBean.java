package com.hokol.medium.http.bean;

public class WNewsMultiplexBean
{
	/**
	 * 请求新闻开始号
	 */
	private int num1;

	/**
	 * 请求新闻结束号
	 */
	private int num2;

	public WNewsMultiplexBean(int num1, int num2)
	{
		this.num1 = num1;
		this.num2 = num2;
	}

	public int getNum1()
	{
		return num1;
	}

	public void setNum1(int num1)
	{
		this.num1 = num1;
	}

	public int getNum2()
	{
		return num2;
	}

	public void setNum2(int num2)
	{
		this.num2 = num2;
	}

	@Override
	public String toString()
	{
		return "WNewsMultiplexBean{" +
				"num1=" + num1 +
				", num2=" + num2 +
				'}';
	}
}
