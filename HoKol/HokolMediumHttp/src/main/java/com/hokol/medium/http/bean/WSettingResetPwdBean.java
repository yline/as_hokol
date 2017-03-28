package com.hokol.medium.http.bean;

public class WSettingResetPwdBean
{
	/**
	 * 用户唯一标识
	 */
	private String user_id;

	/**
	 * 原密码
	 */
	private String old_pwd;

	/**
	 * 新密码
	 */
	private String new_pwd;

	public WSettingResetPwdBean(String user_id, String old_pwd, String new_pwd)
	{
		this.user_id = user_id;
		this.old_pwd = old_pwd;
		this.new_pwd = new_pwd;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getOld_pwd()
	{
		return old_pwd;
	}

	public void setOld_pwd(String old_pwd)
	{
		this.old_pwd = old_pwd;
	}

	public String getNew_pwd()
	{
		return new_pwd;
	}

	public void setNew_pwd(String new_pwd)
	{
		this.new_pwd = new_pwd;
	}
}
