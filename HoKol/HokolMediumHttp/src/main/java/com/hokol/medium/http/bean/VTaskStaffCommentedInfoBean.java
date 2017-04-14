package com.hokol.medium.http.bean;

import java.util.List;

public class VTaskStaffCommentedInfoBean
{
	private List<VTaskStaffInfo> list;

	public List<VTaskStaffInfo> getList()
	{
		return list;
	}

	public void setList(List<VTaskStaffInfo> list)
	{
		this.list = list;
	}

	public static class VTaskStaffInfo
	{
		/* 用户ID */
		private String user_id;

		/* 用户昵称 */
		private String user_nickname;

		/* 用户头像（链接） */
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

		public String getUser_logo()
		{
			return user_logo;
		}

		public void setUser_logo(String user_logo)
		{
			this.user_logo = user_logo;
		}
	}
}
