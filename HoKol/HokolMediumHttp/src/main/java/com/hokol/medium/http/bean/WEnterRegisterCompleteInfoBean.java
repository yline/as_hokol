package com.hokol.medium.http.bean;

import java.util.List;

public class WEnterRegisterCompleteInfoBean
{
	/* 用户手机号码 */
	private String user_tel;

	/* 用户密码 */
	private String user_pwd;

	/* 用户性别[1:男，2:女] */
	private int user_sex;

	/* 用户昵称 */
	private String user_nickname;

	/* 用户标签[数组形式] */
	private List<Integer> user_tag;

	public WEnterRegisterCompleteInfoBean(String user_tel)
	{
		this.user_tel = user_tel;
	}

	public WEnterRegisterCompleteInfoBean(String user_tel, String user_pwd, int user_sex)
	{
		this.user_tel = user_tel;
		this.user_pwd = user_pwd;
		this.user_sex = user_sex;
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

	public int getUser_sex()
	{
		return user_sex;
	}

	public void setUser_sex(int user_sex)
	{
		this.user_sex = user_sex;
	}

	public List<Integer> getUser_tag()
	{
		return user_tag;
	}

	public void setUser_tag(List<Integer> user_tag)
	{
		this.user_tag = user_tag;
	}

	public String getUser_nickname()
	{
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname)
	{
		this.user_nickname = user_nickname;
	}

	@Override
	public String toString()
	{
		return "WEnterRegisterCompleteInfoBean{" +
				"user_tel='" + user_tel + '\'' +
				", user_pwd='" + user_pwd + '\'' +
				", user_sex=" + user_sex +
				", user_nickname='" + user_nickname + '\'' +
				", user_tag=" + user_tag +
				'}';
	}
}
