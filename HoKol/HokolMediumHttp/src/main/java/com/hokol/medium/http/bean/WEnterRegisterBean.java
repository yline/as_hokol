package com.hokol.medium.http.bean;

public class WEnterRegisterBean
{
	/* 用户手机 */
	private String user_tel;

	/* 手机验证码 */
	private String check_code;

	public WEnterRegisterBean(String user_tel, String check_code)
	{
		this.user_tel = user_tel;
		this.check_code = check_code;
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
