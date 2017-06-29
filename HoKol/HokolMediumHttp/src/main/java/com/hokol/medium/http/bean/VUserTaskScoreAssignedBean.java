package com.hokol.medium.http.bean;

import java.util.List;

public class VUserTaskScoreAssignedBean
{
	private List<VUserTaskScoreAssignedOneBean> list;

	public List<VUserTaskScoreAssignedOneBean> getList()
	{
		return list;
	}

	public void setList(List<VUserTaskScoreAssignedOneBean> list)
	{
		this.list = list;
	}

	public static class VUserTaskScoreAssignedOneBean
	{
		/* 任务ID */
		private String task_id;

		/* 任务标题 */
		private String task_title;

		/* 任务类型(0:全额担保, 1:面议) */
		private int fee_type;

		/* 任务费用(单人) */
		private int task_fee;

		/* 任务人数 */
		private int task_peo_num;

		/* 任务报名人数 */
		private int task_join_peo;

		/* 用户头像 */
		private String user_logo;

		/* 用户昵称 */
		private String user_nickname;

		public String getTask_id()
		{
			return task_id;
		}

		public void setTask_id(String task_id)
		{
			this.task_id = task_id;
		}

		public String getTask_title()
		{
			return task_title;
		}

		public void setTask_title(String task_title)
		{
			this.task_title = task_title;
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

		public int getTask_peo_num()
		{
			return task_peo_num;
		}

		public void setTask_peo_num(int task_peo_num)
		{
			this.task_peo_num = task_peo_num;
		}

		public int getTask_join_peo()
		{
			return task_join_peo;
		}

		public void setTask_join_peo(int task_join_peo)
		{
			this.task_join_peo = task_join_peo;
		}

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
	}
}
