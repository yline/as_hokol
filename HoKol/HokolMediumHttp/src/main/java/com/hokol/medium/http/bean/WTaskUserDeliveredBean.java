package com.hokol.medium.http.bean;

public class WTaskUserDeliveredBean
{
	public static final int TypeAll = 0; // 全部

	public static final int TypeSigned = 1; // 已报名

	public static final int TypeTraded = 2; // 已接单

	public static final int TypeEvaluate = 3; // 待评论

	/* 用户ID */
	private String user_id;

	/* 列表选择开关(0-全部,1-已报名,2-已接单,3-待评论) */
	private int switchs;

	/* 请求数据上限 */
	private int num1;

	/* 请求数据长度 */
	private int length;

	public WTaskUserDeliveredBean(String user_id, int switchs, int num1, int length)
	{
		this.user_id = user_id;
		this.switchs = switchs;
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

	public int getSwitchs()
	{
		return switchs;
	}

	public void setSwitchs(int switchs)
	{
		this.switchs = switchs;
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
