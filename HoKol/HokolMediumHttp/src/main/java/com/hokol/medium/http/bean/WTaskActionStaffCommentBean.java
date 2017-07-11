package com.hokol.medium.http.bean;

public class WTaskActionStaffCommentBean
{
	/* 用户ID */private String user_id;

	/* 任务唯一标识 */private String task_id;

	/* 被评论用户ID */private String comment_user_id;

	/* 符合度评分 */private int conformity_score;

	/* 交流态度评分 */private int communion_score;

	/* 诚信经营 */private int credibility_score;

	/* 文字评价 */private String user_comment;

	public WTaskActionStaffCommentBean(String user_id, String task_id, String comment_user_id)
	{
		this.user_id = user_id;
		this.task_id = task_id;
		this.comment_user_id = comment_user_id;
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

	public int getCommunion_score()
	{
		return communion_score;
	}

	public void setCommunion_score(int communion_score)
	{
		this.communion_score = communion_score;
	}

	public int getCredibility_score()
	{
		return credibility_score;
	}

	public void setCredibility_score(int credibility_score)
	{
		this.credibility_score = credibility_score;
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
