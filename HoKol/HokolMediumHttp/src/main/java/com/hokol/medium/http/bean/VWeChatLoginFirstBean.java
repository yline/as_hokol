package com.hokol.medium.http.bean;

public class VWeChatLoginFirstBean
{
	/* 用户ID */
	private String user_id;

	/* 用户昵称 */
	private String user_nickname;

	/* 用户性别 */
	private String user_sex;

	/* 用户头像 */
	private String user_logo;

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getUser_nickname()
	{
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname)
	{
		this.user_nickname = user_nickname;
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
}
