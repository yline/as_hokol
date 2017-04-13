package com.hokol.medium.http.bean;

import java.util.List;

public class VTaskUserAcceptBean
{
	private List<VTaskUserAcceptOneBean> list;

	public List<VTaskUserAcceptOneBean> getList()
	{
		return list;
	}

	public void setList(List<VTaskUserAcceptOneBean> list)
	{
		this.list = list;
	}

	public static class VTaskUserAcceptOneBean
	{
		/* 用户id */
		private String user_id;

		/* 用户昵称 */
		private String user_nickname;

		/* 用户头像(链接) */
		private String user_logo;

		/* 用户签名 */
		private String user_sign;

		/* 用户标签 */
		private String user_tag;

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

		public String getUser_logo()
		{
			return user_logo;
		}

		public void setUser_logo(String user_logo)
		{
			this.user_logo = user_logo;
		}

		public String getUser_sign()
		{
			return user_sign;
		}

		public void setUser_sign(String user_sign)
		{
			this.user_sign = user_sign;
		}

		public String getUser_tag()
		{
			return user_tag;
		}

		public void setUser_tag(String user_tag)
		{
			this.user_tag = user_tag;
		}
	}
}
