package com.hokol.medium.http.bean;

import java.util.List;

/**
 * 发布任务
 *
 * @author yline 2017/4/13 -- 10:41
 * @version 1.0.0
 */
public class WTaskMainPublishBean
{
	/* 任务结束时间 (时间戳) */
	private long task_end_time;

	/* 任务类型：[1:网红,2:主播,3:演员,4:模特,5:歌手,6:体育] */
	private List<Integer> task_type;

	/* 任务所需性别：[0:不限，1:男，2:女] */
	private int task_sex;

	/* 任务薪酬 */
	private float task_fee;

	/* 任务标题 */
	private String task_title;

	/* 任务内容 */
	private String task_content;

	/* 任务发布者唯一标识 */
	private String task_user_id;

	/* 任务所在省份：【例:浙江省，不限】 */
	private String task_province;

	/* 任务所在城市：【例:杭州市，不限】 */
	private String task_city;

	/* 任务所需男性数量 */
	private int task_man_num;

	/* 任务所需女性数量 */
	private int task_woman_num;

	public WTaskMainPublishBean(String task_user_id, List<Integer> task_type, float task_fee, int task_man_num, int task_woman_num)
	{
		this.task_user_id = task_user_id;
		this.task_type = task_type;
		this.task_fee = task_fee;
		this.task_man_num = task_man_num;
		this.task_woman_num = task_woman_num;

		long time = (System.currentTimeMillis() / 1000);
		this.task_end_time = time + 3600 * 24 * 5;
		this.task_sex = 0;
		this.task_title = "新发布的任务标题" + time;
		this.task_content = "新发布的任务内容" + time;
		this.task_province = "0";
		this.task_city = "0";
	}

	public long getTask_end_time()
	{
		return task_end_time;
	}

	public void setTask_end_time(long task_end_time)
	{
		this.task_end_time = task_end_time;
	}

	public List<Integer> getTask_type()
	{
		return task_type;
	}

	public void setTask_type(List<Integer> task_type)
	{
		this.task_type = task_type;
	}

	public int getTask_sex()
	{
		return task_sex;
	}

	public void setTask_sex(int task_sex)
	{
		this.task_sex = task_sex;
	}

	public float getTask_fee()
	{
		return task_fee;
	}

	public void setTask_fee(float task_fee)
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

	public String getTask_content()
	{
		return task_content;
	}

	public void setTask_content(String task_content)
	{
		this.task_content = task_content;
	}

	public String getTask_user_id()
	{
		return task_user_id;
	}

	public void setTask_user_id(String task_user_id)
	{
		this.task_user_id = task_user_id;
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
}
