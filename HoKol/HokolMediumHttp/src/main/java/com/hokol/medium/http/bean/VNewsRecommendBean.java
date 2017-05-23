package com.hokol.medium.http.bean;

public class VNewsRecommendBean
{
	/**
	 * banner图片(链接)
	 */
	private String banner_img;

	/**
	 * 推荐内容(url链接)
	 */
	private String info;

	/**
	 * 推荐类型(0:推荐新闻,1:广告)
	 */
	private int type;

	public String getBanner_img()
	{
		return banner_img;
	}

	public void setBanner_img(String banner_img)
	{
		this.banner_img = banner_img;
	}

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}
}
