package com.hokol.medium.http.bean;

public class WWeChatLoginBean
{
	public static final int TypeRegister = -1;

	/* code码 */
	private String code;

	public WWeChatLoginBean(String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
}
