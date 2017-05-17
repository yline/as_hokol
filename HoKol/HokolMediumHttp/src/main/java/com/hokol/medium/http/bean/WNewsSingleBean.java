package com.hokol.medium.http.bean;

public class WNewsSingleBean
{
	private String news_id;

	public WNewsSingleBean()
	{
	}

	public WNewsSingleBean(String news_id)
	{
		this.news_id = news_id;
	}

	public String getNews_id()
	{
		return news_id;
	}

	public void setNews_id(String news_id)
	{
		this.news_id = news_id;
	}
}
