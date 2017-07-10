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
		private List<String> user_tag;

		/* 用户性别 */
		private String user_sex;

		/* 用户等级 */
		private int user_level;

		/* 用户等级图片 */
		private String level_url;

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

		public List<String> getUser_tag()
		{
			return user_tag;
		}

		public void setUser_tag(List<String> user_tag)
		{
			this.user_tag = user_tag;
		}

		public String getUser_sex()
		{
			return user_sex;
		}

		public void setUser_sex(String user_sex)
		{
			this.user_sex = user_sex;
		}

		public int getUser_level()
		{
			return user_level;
		}

		public void setUser_level(int user_level)
		{
			this.user_level = user_level;
		}

		public String getLevel_url()
		{
			return level_url;
		}

		public void setLevel_url(String level_url)
		{
			this.level_url = level_url;
		}
	}
}
