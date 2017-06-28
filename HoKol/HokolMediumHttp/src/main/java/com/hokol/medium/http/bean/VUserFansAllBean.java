package com.hokol.medium.http.bean;

import java.util.List;

public class VUserFansAllBean
{
	public static final int Cared = 1;

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
		/* 关注的人唯一标识 */
		private String user_id;

		/* 头像(链接) */
		private String user_logo;

		/* 签名 */
		private String user_sign;

		/* 用户性别 */
		private String user_sex;

		/* 昵称 */
		private String user_nickname;

		/* 标签：[数组] */
		private List<String> user_tag;

		/* 用户的赞 */
		private int user_zan;

		/* 是否关注（0 未关注，1 已关注） */
		private int is_care;

		/* 链接地址 */
		private String level_url;

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

		public String getUser_sign()
		{
			return user_sign;
		}

		public void setUser_sign(String user_sign)
		{
			this.user_sign = user_sign;
		}

		public String getUser_sex()
		{
			return user_sex;
		}

		public void setUser_sex(String user_sex)
		{
			this.user_sex = user_sex;
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

		public int getUser_zan()
		{
			return user_zan;
		}

		public void setUser_zan(int user_zan)
		{
			this.user_zan = user_zan;
		}

		public int getIs_care()
		{
			return is_care;
		}

		public void setIs_care(int is_care)
		{
			this.is_care = is_care;
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
