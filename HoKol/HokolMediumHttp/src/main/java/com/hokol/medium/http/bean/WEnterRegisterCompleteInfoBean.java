package com.hokol.medium.http.bean;

import java.util.List;

public class WEnterRegisterCompleteInfoBean
{
	/* 用户ID */
	private String user_id;

	/* 用户密码 */
	private String user_pwd;

	/* 用户性别[1:男，2:女] */
	private int user_sex;

	/* 用户标签[数组形式] */
	private List<String> user_tag;

	public WEnterRegisterCompleteInfoBean(String user_id, String user_pwd, int user_sex, List<String> user_tag)
	{
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_sex = user_sex;
		this.user_tag = user_tag;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getUser_pwd()
	{
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd)
	{
		this.user_pwd = user_pwd;
	}

	public int getUser_sex()
	{
		return user_sex;
	}

	public void setUser_sex(int user_sex)
	{
		this.user_sex = user_sex;
	}

	public List<String> getUser_tag()
	{
		return user_tag;
	}

	public void setUser_tag(List<String> user_tag)
	{
		this.user_tag = user_tag;
	}
}
