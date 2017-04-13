package com.hokol.medium.http.bean;

import java.util.List;

public class VHomeMainBean
{
	private List<VHomeMainOneBean> list;

	public List<VHomeMainOneBean> getList()
	{
		return list;
	}

	public void setList(List<VHomeMainOneBean> list)
	{
		this.list = list;
	}

	public static class VHomeMainOneBean
	{
		/* 用户唯一标识 */
		private String user_id;

		/* 用户昵称 */
		private String user_nickname;

		/* 用户性别 */
		private String user_sex;

		/* 用户省份 */
		private String user_province;

		/* 用户城市 */
		private String user_city;

		/* 动态唯一标识 */
		private String dt_id;

		/* 动态图片 */
		private String dt_img;

		/* 动态发布时间(时间戳) */
		private long dt_pub_time;

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

		public String getUser_province()
		{
			return user_province;
		}

		public void setUser_province(String user_province)
		{
			this.user_province = user_province;
		}

		public String getUser_city()
		{
			return user_city;
		}

		public void setUser_city(String user_city)
		{
			this.user_city = user_city;
		}

		public String getDt_id()
		{
			return dt_id;
		}

		public void setDt_id(String dt_id)
		{
			this.dt_id = dt_id;
		}

		public String getDt_img()
		{
			return dt_img;
		}

		public void setDt_img(String dt_img)
		{
			this.dt_img = dt_img;
		}

		public long getDt_pub_time()
		{
			return dt_pub_time;
		}

		public void setDt_pub_time(long dt_pub_time)
		{
			this.dt_pub_time = dt_pub_time;
		}
	}
}
