package com.hokol.medium.http.bean;

public class WEnterPhoneUpdateBean
{
	/* 用户id */
	private String user_id;

	/* 用户号码 */
	private String user_tel;

	/* 验证码 */
	private String check_code;

	public WEnterPhoneUpdateBean(String user_id, String user_tel, String check_code)
	{
		this.user_id = user_id;
		this.user_tel = user_tel;
		this.check_code = check_code;
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

	public String getCheck_code()
	{
		return check_code;
	}

	public void setCheck_code(String check_code)
	{
		this.check_code = check_code;
	}
}
