package com.hokol.medium.http.bean;

public class WDynamicCareSingleBean
{
	/* 用户ID */
	private String user_id;

	/* 动态唯一标识 */
	private String dt_id;

	public WDynamicCareSingleBean(String user_id, String dt_id)
	{
		this.user_id = user_id;
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

	public String getDt_id()
	{
		return dt_id;
	}

	public void setDt_id(String dt_id)
	{
		this.dt_id = dt_id;
	}
}
