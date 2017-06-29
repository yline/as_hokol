package com.hokol.medium.http.bean;

public class WUserTaskScoreAssignedBean
{
	/* 用户ID */
	private String user_id;

	public WUserTaskScoreAssignedBean(String user_id)
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
