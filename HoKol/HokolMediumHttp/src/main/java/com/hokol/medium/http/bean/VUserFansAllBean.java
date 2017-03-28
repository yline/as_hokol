package com.hokol.medium.http.bean;

import java.util.List;

public class VUserFansAllBean
{
	private List<VUserFansAllOneBean> list;

	public List<VUserFansAllOneBean> getList()
	{
		return list;
	}

	public void setList(List<VUserFansAllOneBean> list)
	{
		this.list = list;
	}

	public static class VUserFansAllOneBean
	{
		/**
		 * 关注的人唯一标识
		 */
		private String user_id;

		/**
		 * 头像(链接)
		 */
		private String user_logo;

		/**
		 * 昵称
		 */
		private String user_nickname;

		/**
		 * 标签：[数组]
		 */
		private List<String> user_tag;

		/**
		 * 签名
		 */
		private String user_sign;

		public String getUser_id()
		{
			return user_id;
		}

		public void setUser_id(String user_id)
		{
			this.user_id = user_id;
		}

		public String getUser_logo()
		{
			return user_logo;
		}

		public void setUser_logo(String user_logo)
		{
			this.user_logo = user_logo;
		}

		public String getUser_nickname()
		{
			return user_nickname;
		}

		public void setUser_nickname(String user_nickname)
		{
			this.user_nickname = user_nickname;
		}

		public List<String> getUser_tag()
		{
			return user_tag;
		}

		public void setUser_tag(List<String> user_tag)
		{
			this.user_tag = user_tag;
		}

		public String getUser_sign()
		{
			return user_sign;
		}

		public void setUser_sign(String user_sign)
		{
			this.user_sign = user_sign;
		}
	}
}
