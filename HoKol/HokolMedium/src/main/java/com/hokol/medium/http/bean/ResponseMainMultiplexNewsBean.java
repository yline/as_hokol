package com.hokol.medium.http.bean;

/**
 * 多条新闻请求时,使用
 *
 * @author yline 2017/3/2 --> 14:10
 * @version 1.0.0
 */
public class ResponseMainMultiplexNewsBean
{
	/**
	 * 新闻ID标识
	 */
	private String news_id;

	/**
	 * 新闻图片;缩略图
	 */
	private String news_small_img;

	/**
	 * 新闻来源
	 */
	private String news_source;

	/**
	 * 新闻发布时间
	 */
	private String news_time;

	/**
	 * 新闻标题
	 */
	private String news_title;

	public ResponseMainMultiplexNewsBean(String news_id, String news_small_img, String news_source, String news_time, String news_title, String news_content)
	{
		this.news_id = news_id;
		this.news_small_img = news_small_img;
		this.news_source = news_source;
		this.news_time = news_time;
		this.news_title = news_title;
		this.news_content = news_content;
	}

	/**
	 * 新闻内容
	 */
	private String news_content;

	public String getNews_id()
	{
		return news_id;
	}

	public void setNews_id(String news_id)
	{
		this.news_id = news_id;
	}

	public String getNews_small_img()
	{
		return news_small_img;
	}

	public void setNews_small_img(String news_small_img)
	{
		this.news_small_img = news_small_img;
	}

	public String getNews_source()
	{
		return news_source;
	}

	public void setNews_source(String news_source)
	{
		this.news_source = news_source;
	}

	public String getNews_time()
	{
		return news_time;
	}

	public void setNews_time(String news_time)
	{
		this.news_time = news_time;
	}

	public String getNews_title()
	{
		return news_title;
	}

	public void setNews_title(String news_title)
	{
		this.news_title = news_title;
	}

	public String getNews_content()
	{
		return news_content;
	}

	public void setNews_content(String news_content)
	{
		this.news_content = news_content;
	}

	@Override
	public String toString()
	{
		return "ResponseMainMultiplexNewsBean{" +
				"news_id='" + news_id + '\'' +
				", news_small_img='" + news_small_img + '\'' +
				", news_source='" + news_source + '\'' +
				", news_time='" + news_time + '\'' +
				", news_title='" + news_title + '\'' +
				", news_content='" + news_content + '\'' +
				'}';
	}
}
