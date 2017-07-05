package com.hokol.medium.http.bean;

import java.util.List;

public class WTaskMainAllBean
{
	/* 任务类型：【0:不限,1:网红,2:主播,3:演员,4:模特,5:歌手,6:体育】 */
	private int task_tag = 0;

	/* 用户省份编码 */
	private String p_code;

	/* 用户城市编码(数组) */
	private List<String> c_code;

	/* 获取任务数量(上限)： */
	private int num1;

	/* 获取任务数量 */
	private int length;

	/* 任务所需性别：【0:不限,1:男，2:女】 */
	private int task_sex = 0;

	public WTaskMainAllBean(int num1, int length)
	{
		this.num1 = num1;
		this.length = length;
	}
	
	public int getTask_tag()
	{
		return task_tag;
	}

	public void setTask_tag(int task_tag)
	{
		this.task_tag = task_tag;
	}

	public String getP_code()
	{
		return p_code;
	}

	public void setP_code(String p_code)
	{
		this.p_code = p_code;
	}

	public List<String> getC_code()
	{
		return c_code;
	}

	public void setC_code(List<String> c_code)
	{
		this.c_code = c_code;
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
