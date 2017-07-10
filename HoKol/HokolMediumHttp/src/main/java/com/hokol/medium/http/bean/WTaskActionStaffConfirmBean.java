package com.hokol.medium.http.bean;

public class WTaskActionStaffConfirmBean
{
	public static final int ActionAccept = 1;

	public static final int ActionRefuse = 0;

	/* 用户ID */
	private String user_id;

	/* 任务唯一标识 */
	private String task_id;

	/* 选择开关(0-拒绝接单,1-确定接单) */
	private int switchs;

	public WTaskActionStaffConfirmBean(String user_id, String task_id, int switchs)
	{
		this.user_id = user_id;
		this.task_id = task_id;
		this.switchs = switchs;
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

	public int getSwitchs()
	{
		return switchs;
	}

	public void setSwitchs(int switchs)
	{
		this.switchs = switchs;
	}
}
