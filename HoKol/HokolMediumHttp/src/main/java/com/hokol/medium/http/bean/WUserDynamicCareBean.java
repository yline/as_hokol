package com.hokol.medium.http.bean;

/**
 * 用户关注的人,所有动态;请求参数
 *
 * @author yline 2017/3/8 --> 23:10
 * @version 1.0.0
 */
public class WUserDynamicCareBean
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
	 * 结束号
	 */
	private int num2;

	public WUserDynamicCareBean(String user_id, int num1, int num2)
	{
		this.user_id = user_id;
		this.num1 = num1;
		this.num2 = num2;
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
		return "WUserDynamicCareBean{" +
				"user_id='" + user_id + '\'' +
				", num1=" + num1 +
				", num2=" + num2 +
				'}';
	}
}
