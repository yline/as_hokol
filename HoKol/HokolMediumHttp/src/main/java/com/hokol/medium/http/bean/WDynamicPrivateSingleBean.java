package com.hokol.medium.http.bean;

public class WDynamicPrivateSingleBean
{
	/* 用户id */
	private String user_id;

	/* 动态唯一标识 */
	private String pri_id;

	public WDynamicPrivateSingleBean(String user_id, String pri_id)
	{
		this.user_id = user_id;
		this.pri_id = pri_id;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getPri_id()
	{
		return pri_id;
	}

	public void setPri_id(String pri_id)
	{
		this.pri_id = pri_id;
	}
}
