package com.hokol.medium.http.bean;

import java.util.List;

public class VUserTaskCollectionBean
{
	/* 已报名任务 */
	public static final int Signed = 1;

	private List<VUserCollectionOneBean> list;

	public List<VUserCollectionOneBean> getList()
	{
		return list;
	}

	public void setList(List<VUserCollectionOneBean> list)
	{
		this.list = list;
	}

	public static class VUserCollectionOneBean
	{
		/* 任务唯一标识 */
		private String task_id;

		/* 任务费用 */
		private int task_fee;

		/* 任务标题 */
		private String task_title;

		/* 用户昵称 */
		private String user_nickname;

		/* 用户头像：(链接) */
		private String user_logo;

		/* 任务结束时间 */
		private long task_end_time;

		/* 任务剩余时间 */
		private long task_rem_time;

		/* 任务所需人数 */
		private int task_peo_num;

		/* 任务报名人数 */
		private int task_join_num;

		/* 任务录取人数 */
		private int task_employee_num;

		/* 是否报名(0:未报名，1:已报名) */
		private int is_join;

		public String getTask_id()
		{
			return task_id;
		}

		public void setTask_id(String task_id)
		{
			this.task_id = task_id;
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

		public String getUser_nickname()
		{
			return user_nickname;
		}

		public void setUser_nickname(String user_nickname)
		{
			this.user_nickname = user_nickname;
		}

		public String getUser_logo()
		{
			return user_logo;
		}

		public void setUser_logo(String user_logo)
		{
			this.user_logo = user_logo;
		}

		public long getTask_end_time()
		{
			return task_end_time;
		}

		public void setTask_end_time(long task_end_time)
		{
			this.task_end_time = task_end_time;
		}

		public long getTask_rem_time()
		{
			return task_rem_time;
		}

		public void setTask_rem_time(long task_rem_time)
		{
			this.task_rem_time = task_rem_time;
		}

		public int getTask_peo_num()
		{
			return task_peo_num;
		}

		public void setTask_peo_num(int task_peo_num)
		{
			this.task_peo_num = task_peo_num;
		}

		public int getTask_join_num()
		{
			return task_join_num;
		}

		public void setTask_join_num(int task_join_num)
		{
			this.task_join_num = task_join_num;
		}

		public int getTask_employee_num()
		{
			return task_employee_num;
		}

		public void setTask_employee_num(int task_employee_num)
		{
			this.task_employee_num = task_employee_num;
		}

		public int getIs_join()
		{
			return is_join;
		}

		public void setIs_join(int is_join)
		{
			this.is_join = is_join;
		}
	}
}
