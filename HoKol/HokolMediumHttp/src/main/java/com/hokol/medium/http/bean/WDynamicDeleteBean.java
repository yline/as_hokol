package com.hokol.medium.http.bean;

public class WDynamicDeleteBean
{
	/* 动态ID */
	private String dt_id;

	/* 用户ID */
	private String user_id;

	public WDynamicDeleteBean(String dt_id, String user_id)
	{
		this.dt_id = dt_id;
		this.user_id = user_id;
	}

	public String getDt_id()
	{
		return dt_id;
	}

	public void setDt_id(String dt_id)
	{
		this.dt_id = dt_id;
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
