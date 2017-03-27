package com.hokol.medium.http.bean;

import java.util.List;

public class VDynamicUserAllBean
{
	private List<VDynamicUserAllOneBean> list;

	public List<VDynamicUserAllOneBean> getList()
	{
		return list;
	}

	public void setList(List<VDynamicUserAllOneBean> list)
	{
		this.list = list;
	}

	public static class VDynamicUserAllOneBean
	{
		/**
		 * 用户唯一标识
		 */
		private String user_id;

		/**
		 * 用户头像
		 */
		private String user_logo;

		/**
		 * 用户昵称
		 */
		private String user_nickname;

		/**
		 * 用户城市
		 */
		private String user_city;

		/**
		 * 用户红豆数
		 */
		private String user_coin;

		/**
		 * 动态发布时间
		 */
		private long dt_pub_time;

		/**
		 * 动态唯一标识
		 */
		private String dt_id;

		/**
		 * 动态小图(链接)
		 */
		private String dt_small_img;

		/**
		 * 动态点赞数
		 */
		private int dt_total_zan;

		/**
		 * 点赞人的昵称：[数组]
		 */
		private List<String> dt_zan_people_nickname;

		/**
		 * 动态内容
		 */
		private String dt_content;

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

		public String getUser_city()
		{
			return user_city;
		}

		public void setUser_city(String user_city)
		{
			this.user_city = user_city;
		}

		public String getUser_coin()
		{
			return user_coin;
		}

		public void setUser_coin(String user_coin)
		{
			this.user_coin = user_coin;
		}

		public long getDt_pub_time()
		{
			return dt_pub_time;
		}

		public void setDt_pub_time(long dt_pub_time)
		{
			this.dt_pub_time = dt_pub_time;
		}

		public String getDt_id()
		{
			return dt_id;
		}

		public void setDt_id(String dt_id)
		{
			this.dt_id = dt_id;
		}

		public String getDt_small_img()
		{
			return dt_small_img;
		}

		public void setDt_small_img(String dt_small_img)
		{
			this.dt_small_img = dt_small_img;
		}

		public int getDt_total_zan()
		{
			return dt_total_zan;
		}

		public void setDt_total_zan(int dt_total_zan)
		{
			this.dt_total_zan = dt_total_zan;
		}

		public List<String> getDt_zan_people_nickname()
		{
			return dt_zan_people_nickname;
		}

		public void setDt_zan_people_nickname(List<String> dt_zan_people_nickname)
		{
			this.dt_zan_people_nickname = dt_zan_people_nickname;
		}

		public String getDt_content()
		{
			return dt_content;
		}

		public void setDt_content(String dt_content)
		{
			this.dt_content = dt_content;
		}
	}
}
