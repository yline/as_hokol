package com.hokol.medium.http.bean;

public class WSettingResetPhoneBean
{
	/**
	 * 用户唯一标识
	 */
	private String user_id;

	/**
	 * 用户手机
	 */
	private String user_tel;

	public WSettingResetPhoneBean(String user_id, String user_tel)
	{
		this.user_id = user_id;
		this.user_tel = user_tel;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getUser_tel()
	{
		return user_tel;
	}

	public void setUser_tel(String user_tel)
	{
		this.user_tel = user_tel;
	}
}
