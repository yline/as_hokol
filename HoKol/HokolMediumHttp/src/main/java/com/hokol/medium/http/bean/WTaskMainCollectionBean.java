package com.hokol.medium.http.bean;

public class WTaskMainCollectionBean
{
	public static final int actionCollect = 1;

	public static final int actionCollectCancel = 0;

	/* 用户唯一标识 */
	private String user_id;

	/* 任务唯一标识 */
	private String task_id;

	/* 任务收藏开关：[0:取消收藏，1：收藏] */
	private int collect;

	public WTaskMainCollectionBean(String user_id, String task_id, int collect)
	{
		this.user_id = user_id;
		this.task_id = task_id;
		this.collect = collect;
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

	public int getCollect()
	{
		return collect;
	}

	public void setCollect(int collect)
	{
		this.collect = collect;
	}
}
