package com.hokol.medium.http.bean;

import java.util.List;

public class VTaskMainDetailBean
{
	public static final int Guaranteed = 1; // 已担保

	public static final int Collected = 1; // 已收藏

	public static final int StatusAssigning = 1; // 任务报名中

	public static final int StatusAssignPass = 2; // 已终止报名

	public static final int StatusAssignFinish = 3; // 已结束

	public static final int UserAssignNull = 0; // 未报名

	public static final int UserAssignEd = 1; // 已报名

	/* 任务唯一标识 */
	private String task_id;

	/* 任务类型：[数组] */
	private List<String> task_tag;

	/* 任务付费方式[0:全额担保，1:面议] */
	private int fee_type;

	/* 每个雇员薪水 */
	private int task_fee;

	/* 任务总额 */
	private int task_fee_total;

	/* 任务标题 */
	private String task_title;

	/* 任务内容 */
	private String task_content;

	/* 是否担保(0:未担保,1:已担保) */
	private int is_guarantee;

	/* 是否已收藏(0:未收藏，1:已收藏) */
	private int is_collect;

	/* 任务剩余时间(时间戳) */
	private long task_rem_time;

	/* 任务发布时间（时间戳） */
	private long task_pub_time;

	/* 任务所需性别 */
	private String task_sex;

	/* 任务状态(1:报名中,2:已终止报名,3:已结束) */
	private int status;

	/* 用户是否已报名(0:未报名，1:已报名) */
	private int user_is_join;

	/* 任务数量 */
	private int task_peo_num;

	/* 任务女生数量 */
	private int task_woman_num;

	/* 任务男生数量 */
	private int task_man_num;

	/* 用户所在城市=>[石家庄市,130100] */
	private List<String> city;

	/* 用户所在省份=>[北京市,110000] */
	private List<String> province;

	public static int getGuaranteed()
	{
		return Guaranteed;
	}

	public String getTask_id()
	{
		return task_id;
	}

	public void setTask_id(String task_id)
	{
		this.task_id = task_id;
	}

	public List<String> getTask_tag()
	{
		return task_tag;
	}

	public void setTask_tag(List<String> task_tag)
	{
		this.task_tag = task_tag;
	}

	public int getFee_type()
	{
		return fee_type;
	}

	public void setFee_type(int fee_type)
	{
		this.fee_type = fee_type;
	}

	public int getTask_fee()
	{
		return task_fee;
	}

	public void setTask_fee(int task_fee)
	{
		this.task_fee = task_fee;
	}

	public int getTask_fee_total()
	{
		return task_fee_total;
	}

	public void setTask_fee_total(int task_fee_total)
	{
		this.task_fee_total = task_fee_total;
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

	public int getIs_guarantee()
	{
		return is_guarantee;
	}

	public void setIs_guarantee(int is_guarantee)
	{
		this.is_guarantee = is_guarantee;
	}

	public int getIs_collect()
	{
		return is_collect;
	}

	public void setIs_collect(int is_collect)
	{
		this.is_collect = is_collect;
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

	public String getTask_sex()
	{
		return task_sex;
	}

	public void setTask_sex(String task_sex)
	{
		this.task_sex = task_sex;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public int getUser_is_join()
	{
		return user_is_join;
	}

	public void setUser_is_join(int user_is_join)
	{
		this.user_is_join = user_is_join;
	}

	public int getTask_peo_num()
	{
		return task_peo_num;
	}

	public void setTask_peo_num(int task_peo_num)
	{
		this.task_peo_num = task_peo_num;
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

	public List<String> getCity()
	{
		return city;
	}

	public void setCity(List<String> city)
	{
		this.city = city;
	}

	public List<String> getProvince()
	{
		return province;
	}

	public void setProvince(List<String> province)
	{
		this.province = province;
	}
}
