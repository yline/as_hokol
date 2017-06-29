package com.hokol.medium.http.bean;

public class WUserTaskCommentDeliveredBean
{
	/* 用户ID */
	private String user_id;

	/* 任务ID */
	private String task_id;

	public WUserTaskCommentDeliveredBean(String user_id, String task_id)
	{
		this.user_id = user_id;
		this.task_id = task_id;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getTask_id()
	{
		return task_id;
	}

	public void setTask_id(String task_id)
	{
		this.task_id = task_id;
	}
}
