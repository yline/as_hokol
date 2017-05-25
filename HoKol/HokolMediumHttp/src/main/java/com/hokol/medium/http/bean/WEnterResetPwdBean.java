package com.hokol.medium.http.bean;

public class WEnterResetPwdBean
{
	/* 用户手机 */
	private String user_tel;

	/* 用户密码 */
	private String user_pwd;

	/* 手机验证码 */
	private String check_code;

	public WEnterResetPwdBean(String user_tel, String user_pwd, String check_code)
	{
		this.user_tel = user_tel;
		this.user_pwd = user_pwd;
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

	public String getUser_pwd()
	{
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd)
	{
		this.user_pwd = user_pwd;
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
