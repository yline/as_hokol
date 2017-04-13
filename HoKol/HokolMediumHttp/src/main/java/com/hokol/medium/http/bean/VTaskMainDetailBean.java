package com.hokol.medium.http.bean;

import java.util.List;

public class VTaskMainDetailBean
{
	/* 任务唯一标识 */
	private String task_id;

	/* 任务类型：[数组] */
	private List<String> task_type;

	/* 任务预算 */
	private int task_fee;

	/* 任务标题 */
	private String task_title;

	/* 任务内容 */
	private String task_content;

	/* 任务剩余时间(时间戳) */
	private long task_rem_time;

	/* 任务发布时间（时间戳） */
	private long task_pub_time;

	/* 任务男生数量 */
	private int task_man_num;

	/* 任务女生数量 */
	private int task_woman_num;

	/* 任务数量 */
	private int task_num;


	public String getTask_id()
	{
		return task_id;
	}

	public void setTask_id(String task_id)
	{
		this.task_id = task_id;
	}

	public List<String> getTask_type()
	{
		return task_type;
	}

	public void setTask_type(List<String> task_type)
	{
		this.task_type = task_type;
	}

	public int getTask_fee()
	{
		return task_fee;
	}

	public void setTask_fee(int task_fee)
	{
		this.task_fee = task_fee;
	}

	public String getTask_title()
	{
		return task_title;
	}

	public void setTask_title(String task_title)
	{
		this.task_title = task_title;
	}

	public long getTask_rem_time()
	{
		return task_rem_time;
	}

	public void setTask_rem_time(long task_rem_time)
	{
		this.task_rem_time = task_rem_time;
	}

	public long getTask_pub_time()
	{
		return task_pub_time;
	}

	public void setTask_pub_time(long task_pub_time)
	{
		this.task_pub_time = task_pub_time;
	}

	public int getTask_man_num()
	{
		return task_man_num;
	}

	public void setTask_man_num(int task_man_num)
	{
		this.task_man_num = task_man_num;
	}

	public int getTask_woman_num()
	{
		return task_woman_num;
	}

	public void setTask_woman_num(int task_woman_num)
	{
		this.task_woman_num = task_woman_num;
	}

	public int getTask_num()
	{
		return task_num;
	}

	public void setTask_num(int task_num)
	{
		this.task_num = task_num;
	}

	public String getTask_content()
	{
		return task_content;
	}

	public void setTask_content(String task_content)
	{
		this.task_content = task_content;
	}
}
