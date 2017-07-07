package com.hokol.medium.http.bean;

public class WTaskDeleteBean
{
	// 雇员
	public static final int TypeStaff = 0;

	// 雇主
	public static final int TypeEmployer = 1;

	/* 用户id */
	private String user_id;

	/* 要删除的任务id */
	private String task_id;

	/* 删除类型(0雇主删除发布的任务,1:雇员删除投递的任务) */
	private int type;

	public WTaskDeleteBean(String user_id, String task_id, int type)
	{
		this.user_id = user_id;
		this.task_id = task_id;
		this.type = type;
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

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}
}
