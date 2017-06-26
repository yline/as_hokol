package com.hokol.medium.http.bean;

public class WUserCareOrCancelBean
{
	public static final int actionCare = 1;

	public static final int actionCancelCare = 0;

	/**
	 * 用户唯一标识
	 */
	private String user_id;

	/**
	 * 被关注/取消关注用户唯一标识
	 */
	private String user_id_other;

	/**
	 * 用户关注开关：[0:取消关注，1：关注]
	 */
	private int care;

	public WUserCareOrCancelBean(String user_id, String user_id_other, int care)
	{
		this.user_id = user_id;
		this.user_id_other = user_id_other;
		this.care = care;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getUser_id_other()
	{
		return user_id_other;
	}

	public void setUser_id_other(String user_id_other)
	{
		this.user_id_other = user_id_other;
	}

	public int getCare()
	{
		return care;
	}

	public void setCare(int care)
	{
		this.care = care;
	}
}
