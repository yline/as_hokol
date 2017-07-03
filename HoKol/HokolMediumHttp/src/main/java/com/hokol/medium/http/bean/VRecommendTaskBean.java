package com.hokol.medium.http.bean;

import java.util.List;

public class VRecommendTaskBean
{
	public static final int TypeTask = 0;

	public static final int TypeUrl = 1;

	private List<VRecommendTaskOneBean> list;

	public List<VRecommendTaskOneBean> getList()
	{
		return list;
	}

	public void setList(List<VRecommendTaskOneBean> list)
	{
		this.list = list;
	}

	public static class VRecommendTaskOneBean
	{
		/* banner图片(链接) */
		private String banner_img;

		/* 推荐类型(0:推荐任务,1:广告) */
		private int type;

		/* 推荐内容【type为0=>任务ID，type为1=>url链接】 */
		private String info;

		public String getBanner_img()
		{
			return banner_img;
		}

		public void setBanner_img(String banner_img)
		{
			this.banner_img = banner_img;
		}

		public int getType()
		{
			return type;
		}

		public void setType(int type)
		{
			this.type = type;
		}

		public String getInfo()
		{
			return info;
		}

		public void setInfo(String info)
		{
			this.info = info;
		}
	}
}
