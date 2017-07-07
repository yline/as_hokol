package com.hokol.medium.http.bean;

import java.util.List;

public class VWDynamicPrivateSingleBean
{
	/* 用户昵称 */
	private String user_nickname;

	/* 用户红豆 */
	private int user_coin;

	/* 用户头像 */
	private String user_logo;

	/* 动态用户ID */
	private String pri_user_id;

	/* 动态发布时间(时间戳) */
	private long pri_pub_time;

	/* 动态点赞数 */
	private int pri_total_zan;

	/* 动态内容 */
	private String pri_content;

	/* [点赞人的昵称] */
	private List<String> pri_zan_people_nickname;

	/* 动态大图片(链接) */
	private String pri_img;

	public String getUser_nickname()
	{
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname)
	{
		this.user_nickname = user_nickname;
	}

	public int getUser_coin()
	{
		return user_coin;
	}

	public void setUser_coin(int user_coin)
	{
		this.user_coin = user_coin;
	}

	public String getUser_logo()
	{
		return user_logo;
	}

	public void setUser_logo(String user_logo)
	{
		this.user_logo = user_logo;
	}

	public String getPri_user_id()
	{
		return pri_user_id;
	}

	public void setPri_user_id(String pri_user_id)
	{
		this.pri_user_id = pri_user_id;
	}

	public long getPri_pub_time()
	{
		return pri_pub_time;
	}

	public void setPri_pub_time(long pri_pub_time)
	{
		this.pri_pub_time = pri_pub_time;
	}

	public int getPri_total_zan()
	{
		return pri_total_zan;
	}

	public void setPri_total_zan(int pri_total_zan)
	{
		this.pri_total_zan = pri_total_zan;
	}

	public String getPri_content()
	{
		return pri_content;
	}

	public void setPri_content(String pri_content)
	{
		this.pri_content = pri_content;
	}

	public List<String> getPri_zan_people_nickname()
	{
		return pri_zan_people_nickname;
	}

	public void setPri_zan_people_nickname(List<String> pri_zan_people_nickname)
	{
		this.pri_zan_people_nickname = pri_zan_people_nickname;
	}

	public String getPri_img()
	{
		return pri_img;
	}

	public void setPri_img(String pri_img)
	{
		this.pri_img = pri_img;
	}
}
