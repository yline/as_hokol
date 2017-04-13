package com.hokol.medium.http.bean;

public class WTaskMainAll
{
	/* 任务类型：【0:不限,1:网红,2:主播,3:演员,4:模特,5:歌手,6:体育】 */
	private int task_type = 0;

	/* 任务所在省份：【例：浙江省，不限:0】 */
	private String task_province = "0";

	/* 任务所在城市:【例：杭州市，不限:0】 */
	private String task_city = "0";

	/* 获取任务数量(上限)： */
	private int num1;

	/* 获取任务数量 */
	private int length;

	/* 任务所需性别：【0:不限,1:男，2:女】 */
	private int task_sex = 0;

	public WTaskMainAll(int num1, int length)
	{
		this.num1 = num1;
		this.length = length;
	}

	public WTaskMainAll(int task_type, String task_province, String task_city, int num1, int length, int task_sex)
	{
		this.task_type = task_type;
		this.task_province = task_province;
		this.task_city = task_city;
		this.num1 = num1;
		this.length = length;
		this.task_sex = task_sex;
	}

	public int getTask_type()
	{
		return task_type;
	}

	public void setTask_type(int task_type)
	{
		this.task_type = task_type;
	}

	public String getTask_province()
	{
		return task_province;
	}

	public void setTask_province(String task_province)
	{
		this.task_province = task_province;
	}

	public String getTask_city()
	{
		return task_city;
	}

	public void setTask_city(String task_city)
	{
		this.task_city = task_city;
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

	public int getTask_sex()
	{
		return task_sex;
	}

	public void setTask_sex(int task_sex)
	{
		this.task_sex = task_sex;
	}
}
