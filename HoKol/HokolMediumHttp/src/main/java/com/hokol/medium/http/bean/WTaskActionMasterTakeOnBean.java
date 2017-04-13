package com.hokol.medium.http.bean;

public class WTaskActionMasterTakeOnBean
{
	/* 任务ID */
	private String task_id;

	/* 报名用户ID */
	private String user_id_join;

	/* 发布任务用户ID */
	private String user_id;

	public WTaskActionMasterTakeOnBean(String task_id, String user_id_join, String user_id)
	{
		this.task_id = task_id;
		this.user_id_join = user_id_join;
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

	public String getUser_id_join()
	{
		return user_id_join;
	}

	public void setUser_id_join(String user_id_join)
	{
		this.user_id_join = user_id_join;
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
