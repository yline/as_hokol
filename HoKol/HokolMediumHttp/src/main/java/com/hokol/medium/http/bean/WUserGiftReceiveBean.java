package com.hokol.medium.http.bean;

public class WUserGiftReceiveBean
{
	/* 用户ID */
	private String user_id;

	/* 请求初始值 */
	private int num1;

	/* 请求数据长度 */
	private int length;

	public WUserGiftReceiveBean(String user_id, int num1, int length)
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
