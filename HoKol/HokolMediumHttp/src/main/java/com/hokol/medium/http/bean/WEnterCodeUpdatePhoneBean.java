package com.hokol.medium.http.bean;

public class WEnterCodeUpdatePhoneBean
{
	/* 用户手机号码 */
	private String user_tel;

	public WEnterCodeUpdatePhoneBean(String user_tel)
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
