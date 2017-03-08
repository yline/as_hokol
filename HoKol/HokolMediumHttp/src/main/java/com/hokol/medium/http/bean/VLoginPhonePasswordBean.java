package com.hokol.medium.http.bean;

public class VLoginPhonePasswordBean
{
	/**
	 * 用户手机
	 */
	private String user_tel;

	/**
	 * 用户密码
	 */
	private String user_pwd;

	/**
	 * 用户昵称
	 */
	private String user_nickname;

	/**
	 * 用户省份
	 */
	private String user_province;

	/**
	 * 用户ID，唯一标识
	 */
	private String user_id;

	/**
	 * 用户城市
	 */
	private String user_city;

	/**
	 * 用户微信
	 */
	private String user_weixin;

	/**
	 * 微信ID标识
	 */
	private String user_weixin_id;

	/**
	 * 用户性别
	 */
	private String user_sex;

	/**
	 * 用户头像
	 */
	private String user_logo;

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

	public String getUser_nickname()
	{
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname)
	{
		this.user_nickname = user_nickname;
	}

	public String getUser_province()
	{
		return user_province;
	}

	public void setUser_province(String user_province)
	{
		this.user_province = user_province;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getUser_city()
	{
		return user_city;
	}

	public void setUser_city(String user_city)
	{
		this.user_city = user_city;
	}

	public String getUser_weixin()
	{
		return user_weixin;
	}

	public void setUser_weixin(String user_weixin)
	{
		this.user_weixin = user_weixin;
	}

	public String getUser_weixin_id()
	{
		return user_weixin_id;
	}

	public void setUser_weixin_id(String user_weixin_id)
	{
		this.user_weixin_id = user_weixin_id;
	}

	public String getUser_sex()
	{
		return user_sex;
	}

	public void setUser_sex(String user_sex)
	{
		this.user_sex = user_sex;
	}

	public String getUser_logo()
	{
		return user_logo;
	}

	public void setUser_logo(String user_logo)
	{
		this.user_logo = user_logo;
	}

	@Override
	public String toString()
	{
		return "VLoginPhonePasswordBean{" +
				"user_tel='" + user_tel + '\'' +
				", user_pwd='" + user_pwd + '\'' +
				", user_nickname='" + user_nickname + '\'' +
				", user_province='" + user_province + '\'' +
				", user_id='" + user_id + '\'' +
				", user_city='" + user_city + '\'' +
				", user_weixin='" + user_weixin + '\'' +
				", user_weixin_id='" + user_weixin_id + '\'' +
				", user_sex='" + user_sex + '\'' +
				", user_logo='" + user_logo + '\'' +
				'}';
	}
}
