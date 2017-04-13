package com.hokol.medium.http.bean;

public class WTaskUserAcceptBean
{
	/* 任务id */
	private String task_id;

	/* 请求数据上限 */
	private int num1;

	/* 请求数据量 */
	private int length;

	public WTaskUserAcceptBean(String task_id, int num1, int length)
	{
		this.task_id = task_id;
		this.num1 = num1;
		this.length = length;
	}

	public String getTask_id()
	{
		return task_id;
	}

	public void setTask_id(String task_id)
	{
		this.task_id = task_id;
	}

	public int getNum1()
	{
		return num1;
	}

	public void setNum1(int num1)
	{
		this.num1 = num1;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}
}
