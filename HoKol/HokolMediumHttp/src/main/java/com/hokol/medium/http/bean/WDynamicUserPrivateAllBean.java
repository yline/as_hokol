package com.hokol.medium.http.bean;

public class WDynamicUserPrivateAllBean
{
	/* 用户唯一标识  备注:如果查看自己的私密动态，user_id与user_id_fnd相同 */
	private String user_id;

	/* 被查看用户ID */
	private String user_id_find;

	/* 开始号 */
	private int num1;

	/* 请求长度 */
	private int length;

	public WDynamicUserPrivateAllBean(String user_id, String user_id_find, int num1, int length)
	{
		this.user_id = user_id;
		this.user_id_find = user_id_find;
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

	public String getUser_id_find()
	{
		return user_id_find;
	}

	public void setUser_id_find(String user_id_find)
	{
		this.user_id_find = user_id_find;
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
