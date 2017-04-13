package com.hokol.medium.http.bean;

import java.util.List;

public class VTaskUserSignUpDetailBean
{
	private List<VTaskUserSignUpDetailOneBean> list;

	public List<VTaskUserSignUpDetailOneBean> getList()
	{
		return list;
	}

	public void setList(List<VTaskUserSignUpDetailOneBean> list)
	{
		this.list = list;
	}

	public static class VTaskUserSignUpDetailOneBean
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

		/* 是否录用 */
		private int is_employe;

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

		public int getIs_employe()
		{
			return is_employe;
		}

		public void setIs_employe(int is_employe)
		{
			this.is_employe = is_employe;
		}
	}
}
