package com.hokol.medium.http.bean;

/**
 * 用户关注的人,所有动态;请求参数
 *
 * @author yline 2017/3/8 --> 23:10
 * @version 1.0.0
 */
public class WDynamicCareAllBean
{
	/**
	 * 用户唯一标识
	 */
	private String user_id;

	/**
	 * 开始号
	 */
	private int num1;

	/**
	 * 请求长度
	 */
	private int length;

	public WDynamicCareAllBean(String user_id, int num1, int length)
	{
		this.user_id = user_id;
		this.num1 = num1;
		this.length = length;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public int getNum1()
	{
		return num1;
	}

	public void setNum1(int num1)
	{
		this.num1 = num1;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}
}
