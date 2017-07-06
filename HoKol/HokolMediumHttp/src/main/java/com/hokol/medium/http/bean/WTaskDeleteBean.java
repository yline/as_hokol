package com.hokol.medium.http.bean;

import java.util.Arrays;
import java.util.List;

public class WTaskDeleteBean
{
	/* 用户id */
	private String user_id;

	/* 要删除的任务(数组形式) */
	private List<String> task_ids;

	public WTaskDeleteBean(String user_id, String taskId)
	{
		this.user_id = user_id;
		this.task_ids = Arrays.asList(taskId);
	}

	public WTaskDeleteBean(String user_id, List<String> task_ids)
	{
		this.user_id = user_id;
		this.task_ids = task_ids;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public List<String> getTask_ids()
	{
		return task_ids;
	}

	public void setTask_ids(List<String> task_ids)
	{
		this.task_ids = task_ids;
	}
}
