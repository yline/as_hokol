package com.hokol.medium.http.bean;

import java.util.List;

/**
 * 用户信息
 *
 * @author yline 2017/3/8 --> 23:16
 * @version 1.0.0
 */
public class VDynamicCareBean
{
	/**
	 * 关注的人唯一标识
	 */
	private String user_care_id;

	/**
	 * 关注的人头像
	 */
	private String user_logo;

	/**
	 * 关注的人昵称
	 */
	private String user_nickname;

	/**
	 * 关注的人标签
	 */
	private List<String> user_tag;

	/**
	 * 关注的人红豆数
	 */
	private String user_coin;

	/**
	 * 关注的人所在城市
	 */
	private String user_city;

	/**
	 * 动态发布时间(时间戳)
	 */
	private long dt_pub_time;

	/**
	 * 动态唯一标识
	 */
	private String dt_id;

	/**
	 * 动态图片(链接)
	 */
	private String dt_mg;

	/**
	 * 动态点赞数
	 */
	private int dt_total_zan;

	/**
	 * 点赞人的昵称[数组]
	 */
	private List<String> dt_zan_people_nickname;

	/**
	 * 动态内容
	 */
	private String dt_content;

	public String getUser_care_id()
	{
		return user_care_id;
	}

	public void setUser_care_id(String user_care_id)
	{
		this.user_care_id = user_care_id;
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

	public List<String> getUser_tag()
	{
		return user_tag;
	}

	public void setUser_tag(List<String> user_tag)
	{
		this.user_tag = user_tag;
	}

	public String getUser_coin()
	{
		return user_coin;
	}

	public void setUser_coin(String user_coin)
	{
		this.user_coin = user_coin;
	}

	public String getUser_city()
	{
		return user_city;
	}

	public void setUser_city(String user_city)
	{
		this.user_city = user_city;
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

	public String getDt_mg()
	{
		return dt_mg;
	}

	public void setDt_mg(String dt_mg)
	{
		this.dt_mg = dt_mg;
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
