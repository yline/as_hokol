package com.hokol.medium.http.bean;

import java.util.List;

public class VUserTaskCollectionBean
{
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
		/**
		 * 用户头像：(链接)
		 */
		private String user_logo;

		/**
		 * 用户昵称
		 */
		private String user_nickname;

		/**
		 * 任务唯一标识
		 */
		private String task_id;

		/**
		 * 任务费用
		 */
		private int task_fee;

		/**
		 * 任务剩余时间
		 */
		private long task_rem_time;

		public String getUser_logo()
		{
			return user_logo;
		}

		public void setUser_logo(String user_logo)
		{
			this.user_logo = user_logo;
		}

		public String getUser_nickname()
		{
			return user_nickname;
		}

		public void setUser_nickname(String user_nickname)
		{
			this.user_nickname = user_nickname;
		}

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
