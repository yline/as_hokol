package com.hokol.medium.http.bean;

public class WDynamicPrivateDeleteBean
{
	/* 动态ID */
	private String pri_id;

	/* 用户ID */
	private String user_id;

	public WDynamicPrivateDeleteBean(String pri_id, String user_id)
	{
		this.pri_id = pri_id;
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

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
}
