package com.hokol.medium.http.bean;

public class WWeChatRegisterICodeBean
{
	/* 用户手机号 */
	private String user_tel;

	public WWeChatRegisterICodeBean(String user_tel)
	{
		this.user_tel = user_tel;
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
