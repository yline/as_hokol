package com.hokol.medium.http.bean;

import java.util.List;

public class WTaskActionMasterCommentBean
{
	/* 用户ID */
	private String user_id;

	/* 任务ID */
	private String task_id;

	/* 评论内容 */
	private List<MasterCommentContentBean> comment;

	public WTaskActionMasterCommentBean(String user_id, String task_id, List<MasterCommentContentBean> comment)
	{
		this.user_id = user_id;
		this.task_id = task_id;
		this.comment = comment;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getTask_id()
	{
		return task_id;
	}

	public void setTask_id(String task_id)
	{
		this.task_id = task_id;
	}

	public List<MasterCommentContentBean> getComment()
	{
		return comment;
	}

	public void setComment(List<MasterCommentContentBean> comment)
	{
		this.comment = comment;
	}

	public static class MasterCommentContentBean
	{
		/* 被评论用户ID */
		private String comment_user_id;

		/* 符合度评分 */
		private int conformity_score;

		/* 活动能力评分 */
		private int action_capacity_score;

		/* 工作态度 */
		private int attitude_score;

		/* 文字评价 */
		private String user_comment;

		public MasterCommentContentBean(String comment_user_id, int conformity_score, int action_capacity_score, int attitude_score)
		{
			this.comment_user_id = comment_user_id;
			this.conformity_score = conformity_score;
			this.action_capacity_score = action_capacity_score;
			this.attitude_score = attitude_score;
		}

		public String getComment_user_id()
		{
			return comment_user_id;
		}

		public void setComment_user_id(String comment_user_id)
		{
			this.comment_user_id = comment_user_id;
		}

		public int getConformity_score()
		{
			return conformity_score;
		}

		public void setConformity_score(int conformity_score)
		{
			this.conformity_score = conformity_score;
		}

		public int getAction_capacity_score()
		{
			return action_capacity_score;
		}

		public void setAction_capacity_score(int action_capacity_score)
		{
			this.action_capacity_score = action_capacity_score;
		}

		public int getAttitude_score()
		{
			return attitude_score;
		}

		public void setAttitude_score(int attitude_score)
		{
			this.attitude_score = attitude_score;
		}

		public String getUser_comment()
		{
			return user_comment;
		}

		public void setUser_comment(String user_comment)
		{
			this.user_comment = user_comment;
		}
	}
}
