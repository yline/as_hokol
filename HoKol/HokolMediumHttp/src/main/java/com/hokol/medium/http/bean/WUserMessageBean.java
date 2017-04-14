package com.hokol.medium.http.bean;

public class WUserMessageBean
{
	/* 用户唯一标识 */
	private String user_id;

	/* 开始号 */
	private String num1;

	/* 数据长度 */
	private String length;

	public WUserMessageBean(String user_id, String num1, String length)
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

	public String getNum1()
	{
		return num1;
	}

	public void setNum1(String num1)
	{
		this.num1 = num1;
	}

	public String getLength()
	{
		return length;
	}

	public void setLength(String length)
	{
		this.length = length;
	}
}
