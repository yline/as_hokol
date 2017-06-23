package com.hokol.medium.http.bean;

import java.util.List;

public class VTaskUserDeliveredBean
{
	private List<VTaskUserDeliveredOneBean> list;

	public List<VTaskUserDeliveredOneBean> getList()
	{
		return list;
	}

	public void setList(List<VTaskUserDeliveredOneBean> list)
	{
		this.list = list;
	}

	public static class VTaskUserDeliveredOneBean
	{
		/* 任务发布者 */
		private String task_user_id;

		/* 任务ID */
		private String task_id;

		/* 任务费用 */
		private int task_fee;

		/* 任务标题 */
		private String task_title;

		/* 任务结束时间(时间戳) */
		private long task_end_time;

		/* 任务发布者昵称 */
		private String user_nickname;

		/* 任务发布者头像(链接) */
		private String user_logo;

		/* 是否拒绝该任务（0-未拒绝,1-已拒绝） */
		private int is_refuse;

		/* 任务状态(1-待录用, 2-待接单, 3-已拒绝，4-待交易, 5-交易失败(主办方判定)，6-(自己判定),7-待评价,8-已完成,9-已结束（未被录用，但任务已结束)) */
		private int status;

		/* 任务报名人数 */
		private int join_num;

		public String getTask_user_id()
		{
			return task_user_id;
		}

		public void setTask_user_id(String task_user_id)
		{
			this.task_user_id = task_user_id;
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

		public String getTask_title()
		{
			return task_title;
		}

		public void setTask_title(String task_title)
		{
			this.task_title = task_title;
		}

		public long getTask_end_time()
		{
			return task_end_time;
		}

		public void setTask_end_time(long task_end_time)
		{
			this.task_end_time = task_end_time;
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

		public int getIs_refuse()
		{
			return is_refuse;
		}

		public void setIs_refuse(int is_refuse)
		{
			this.is_refuse = is_refuse;
		}

		public int getStatus()
		{
			return status;
		}

		public void setStatus(int status)
		{
			this.status = status;
		}

		public int getJoin_num()
		{
			return join_num;
		}

		public void setJoin_num(int join_num)
		{
			this.join_num = join_num;
		}
	}
}
