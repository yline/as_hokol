package com.hokol.medium.http.bean;

import java.util.List;

public class WTaskActionMasterTradeBean
{
	public static final int ActionFinished = 1;

	public static final int ActionFailed = 0;

	/* 任务唯一标识 */
	private String task_id;

	/* 用户ID */
	private String user_id;

	/* 确认信息 */
	private List<WTaskActionMasterTradeInfoBean> info;

	public WTaskActionMasterTradeBean(String task_id, String user_id, List<WTaskActionMasterTradeInfoBean> info)
	{
		this.task_id = task_id;
		this.user_id = user_id;
		this.info = info;
	}

	public String getTask_id()
	{
		return task_id;
	}

	public void setTask_id(String task_id)
	{
		this.task_id = task_id;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public List<WTaskActionMasterTradeInfoBean> getInfo()
	{
		return info;
	}

	public void setInfo(List<WTaskActionMasterTradeInfoBean> info)
	{
		this.info = info;
	}

	public static class WTaskActionMasterTradeInfoBean
	{
		/* 被确认用户ID */
		private String confirm_user_id;

		/* 完成状态(0-未完成任务, 1-完成任务) */
		private int confirm_status;

		public WTaskActionMasterTradeInfoBean(String confirm_user_id, int confirm_status)
		{
			this.confirm_user_id = confirm_user_id;
			this.confirm_status = confirm_status;
		}

		public String getConfirm_user_id()
		{
			return confirm_user_id;
		}

		public void setConfirm_user_id(String confirm_user_id)
		{
			this.confirm_user_id = confirm_user_id;
		}

		public int getConfirm_status()
		{
			return confirm_status;
		}

		public void setConfirm_status(int confirm_status)
		{
			this.confirm_status = confirm_status;
		}
	}
}
