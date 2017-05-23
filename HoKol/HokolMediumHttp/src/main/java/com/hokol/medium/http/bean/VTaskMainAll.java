package com.hokol.medium.http.bean;

import java.util.List;

public class VTaskMainAll
{
	private List<TaskMainAllOne> list;

	public List<TaskMainAllOne> getList()
	{
		return list;
	}

	public void setList(List<TaskMainAllOne> list)
	{
		this.list = list;
	}

	public static class TaskMainAllOne
	{
		/* 任务唯一标识 */
		private String task_id;

		/* 报名人数 */
		private int employee_num;

		/* 任务预算 */
		private int task_fee;

		/* 任务标题 */
		private String task_title;

		/* 是否担保交易(0:未担保,1:已担保) */
		private int is_guarantee;

		/* 任务发布者昵称 */
		private String user_nickname;

		/* 任务发布者头像 */
		private String user_logo;

		/* 任务剩余时间(时间戳) */
		private long task_rem_time;

		public String getTask_id()
		{
			return task_id;
		}

		public void setTask_id(String task_id)
		{
			this.task_id = task_id;
		}

		public int getEmployee_num()
		{
			return employee_num;
		}

		public void setEmployee_num(int employee_num)
		{
			this.employee_num = employee_num;
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

		public int getIs_guarantee()
		{
			return is_guarantee;
		}

		public void setIs_guarantee(int is_guarantee)
		{
			this.is_guarantee = is_guarantee;
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

		public long getTask_rem_time()
		{
			return task_rem_time;
		}

		public void setTask_rem_time(long task_rem_time)
		{
			this.task_rem_time = task_rem_time;
		}
	}
}
