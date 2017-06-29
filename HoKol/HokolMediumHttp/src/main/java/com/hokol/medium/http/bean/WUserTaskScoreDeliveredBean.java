package com.hokol.medium.http.bean;

public class WUserTaskScoreDeliveredBean
{
	/* 用户ID */
	private String user_id;

	public WUserTaskScoreDeliveredBean(String user_id)
	{
		this.user_id = user_id;
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
