package com.hokol.medium.http.bean;

import java.util.List;

public class VDynamicUserPrivateAllBean
{
	private List<VDynamicUserPrivateSingleBean> list;

	public List<VDynamicUserPrivateSingleBean> getList()
	{
		return list;
	}

	public void setList(List<VDynamicUserPrivateSingleBean> list)
	{
		this.list = list;
	}

	public static class VDynamicUserPrivateSingleBean
	{
		/* 动态唯一标识 */
		private String pri_id;

		/* 动态小图(链接) */
		private String pri_small_img;

		/* 动态点赞数 */
		private int pri_total_zan;

		/* 用户唯一标识 */
		private String user_id;

		/* 用户昵称 */
		private String user_nickname;

		/* 用户头像 */
		private String user_logo;

		/* 用户红豆数 */
		private int user_coin;

		/* 动态内容 */
		private String pri_content;

		/* 动态发布时间 */
		private long pri_pub_time;

		/* 点赞人的昵称：[数组] */
		private List<String> pri_zan_people_nickname;

		/* 用户城市 石家庄市，130100 */
		private List<String> city;

		/* 用户省份 北京市，110000 */
		private List<String> province;

		public String getPri_id()
		{
			return pri_id;
		}

		public void setPri_id(String pri_id)
		{
			this.pri_id = pri_id;
		}

		public String getPri_small_img()
		{
			return pri_small_img;
		}

		public void setPri_small_img(String pri_small_img)
		{
			this.pri_small_img = pri_small_img;
		}

		public int getPri_total_zan()
		{
			return pri_total_zan;
		}

		public void setPri_total_zan(int pri_total_zan)
		{
			this.pri_total_zan = pri_total_zan;
		}

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

		public int getUser_coin()
		{
			return user_coin;
		}

		public void setUser_coin(int user_coin)
		{
			this.user_coin = user_coin;
		}

		public String getPri_content()
		{
			return pri_content;
		}

		public void setPri_content(String pri_content)
		{
			this.pri_content = pri_content;
		}

		public long getPri_pub_time()
		{
			return pri_pub_time;
		}

		public void setPri_pub_time(long pri_pub_time)
		{
			this.pri_pub_time = pri_pub_time;
		}

		public List<String> getPri_zan_people_nickname()
		{
			return pri_zan_people_nickname;
		}

		public void setPri_zan_people_nickname(List<String> pri_zan_people_nickname)
		{
			this.pri_zan_people_nickname = pri_zan_people_nickname;
		}

		public List<String> getCity()
		{
			return city;
		}

		public void setCity(List<String> city)
		{
			this.city = city;
		}

		public List<String> getProvince()
		{
			return province;
		}

		public void setProvince(List<String> province)
		{
			this.province = province;
		}
	}
}
