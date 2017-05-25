package com.hokol.medium.http.bean;

/**
 * 获取 注册验证码
 *
 * @author yline 2017/5/24 -- 16:47
 * @version 1.0.0
 */
public class WEnterCodeRegisterBean
{
	/**
	 * 用户手机
	 */
	private String user_tel;

	public WEnterCodeRegisterBean(String user_tel)
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
