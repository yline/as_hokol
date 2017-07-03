package com.hokol.medium.http.bean;

import java.util.List;

public class VRecommendHomeBean
{
	public static final int TypeUser = 0;

	public static final int TypeUrl = 1;

	private List<VRecommendHomeOneBean> list;

	public List<VRecommendHomeOneBean> getList()
	{
		return list;
	}

	public void setList(List<VRecommendHomeOneBean> list)
	{
		this.list = list;
	}

	public static class VRecommendHomeOneBean
	{
		/* banner图片(链接) */
		private String banner_img;

		/* 推荐类型(0:推荐主播,1:广告) */
		private int type;

		/* 推荐内容【type为0=>用户ID，type为1=>url链接】 */
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
