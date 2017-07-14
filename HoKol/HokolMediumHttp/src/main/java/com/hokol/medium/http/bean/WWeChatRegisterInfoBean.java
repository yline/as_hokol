package com.hokol.medium.http.bean;

public class WWeChatRegisterInfoBean
{
	/* 用户ID */
	private String user_id;

	/* 用户手机号 */
	private String user_tel;

	/* 用户密码 */
	private String user_pwd;

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

	public String getUser_pwd()
	{
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd)
	{
		this.user_pwd = user_pwd;
	}
}
