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
		/**
		 * 用户唯一标识
		 */
		private String user_id;

		/**
		 * 用户昵称
		 */
		private String user_nickname;

		/**
		 * 用户性别
		 */
		private String user_sex;

		/**
		 * 用户省份
		 */
		private String user_province;

		/**
		 * 用户城市
		 */
		private String user_city;

		/**
		 * 动态唯一标识
		 */
		private String dt_id;

		/**
		 * 动态图片
		 */
		private String dt_img;

		/**
		 * 动态发布时间(时间戳)
		 */
		private long dt_pub_time;
	}
}
