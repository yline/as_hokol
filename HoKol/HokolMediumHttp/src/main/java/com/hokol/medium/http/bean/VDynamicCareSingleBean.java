package com.hokol.medium.http.bean;

import java.util.List;

public class VDynamicCareSingleBean
{
	public static final int Praised = 1;

	public static final int Cared = 1;

	/* 用户昵称 */
	private String user_nickname;

	/* 用户红豆 */
	private float user_coin;

	/* 用户头像 */
	private String user_logo;

	/* 动态用户ID */
	private String dt_user_id;

	/* 动态发布时间：(时间戳) */
	private long dt_pub_time;

	/* 动态点赞数 */
	private int dt_total_zan;

	/* 动态内容 */
	private String dt_content;

	/* 点赞人的昵称：[数组] */
	private List<String> dt_zan_people_nickname;

	/* 动态大图片：(链接) */
	private String dt_img;

	/* 是否关注该用户(0:未关注,1:已关注) */
	private int is_care;

	/* 是否点赞该评论(0:未点赞，1:已点赞) */
	private int is_zan;

	public String getUser_nickname()
	{
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname)
	{
		this.user_nickname = user_nickname;
	}

	public float getUser_coin()
	{
		return user_coin;
	}

	public void setUser_coin(float user_coin)
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

	public String getDt_user_id()
	{
		return dt_user_id;
	}

	public void setDt_user_id(String dt_user_id)
	{
		this.dt_user_id = dt_user_id;
	}

	public long getDt_pub_time()
	{
		return dt_pub_time;
	}

	public void setDt_pub_time(long dt_pub_time)
	{
		this.dt_pub_time = dt_pub_time;
	}

	public int getDt_total_zan()
	{
		return dt_total_zan;
	}

	public void setDt_total_zan(int dt_total_zan)
	{
		this.dt_total_zan = dt_total_zan;
	}

	public String getDt_content()
	{
		return dt_content;
	}

	public void setDt_content(String dt_content)
	{
		this.dt_content = dt_content;
	}

	public List<String> getDt_zan_people_nickname()
	{
		return dt_zan_people_nickname;
	}

	public void setDt_zan_people_nickname(List<String> dt_zan_people_nickname)
	{
		this.dt_zan_people_nickname = dt_zan_people_nickname;
	}

	public String getDt_img()
	{
		return dt_img;
	}

	public void setDt_img(String dt_img)
	{
		this.dt_img = dt_img;
	}

	public int getIs_care()
	{
		return is_care;
	}

	public void setIs_care(int is_care)
	{
		this.is_care = is_care;
	}

	public int getIs_zan()
	{
		return is_zan;
	}

	public void setIs_zan(int is_zan)
	{
		this.is_zan = is_zan;
	}
}
