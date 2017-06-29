package com.hokol.medium.http.bean;

public class VUserTaskCommentAssignedBean
{
	/* 用户ID */
	private String user_id;

	/* 评论发布时间 */
	private long comment_pub_time;

	/* 用户评论内容 */
	private String user_comment;

	/* 用户头像 */
	private String user_logo;

	/* 用户昵称 */
	private String user_nickname;

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public long getComment_pub_time()
	{
		return comment_pub_time;
	}

	public void setComment_pub_time(long comment_pub_time)
	{
		this.comment_pub_time = comment_pub_time;
	}

	public String getUser_comment()
	{
		return user_comment;
	}

	public void setUser_comment(String user_comment)
	{
		this.user_comment = user_comment;
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
