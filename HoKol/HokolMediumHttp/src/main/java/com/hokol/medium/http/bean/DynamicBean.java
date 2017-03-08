package com.hokol.medium.http.bean;

/**
 * 用户信息
 *
 * @author yline 2017/3/8 --> 23:16
 * @version 1.0.0
 */
public class DynamicBean
{
	/**
	 * 关注的人唯一标识
	 */
	private String user_id;

	/**
	 * 头像
	 */
	private String user_logo;

	/**
	 * 昵称
	 */
	private String user_nickname;

	/**
	 * 标签
	 */
	private String user_tag;

	/**
	 * 关注的人所在城市
	 */
	private String user_city;

	/**
	 * 动态发布时间(时间戳)
	 */
	private String dt_pub_time;

	/**
	 * 动态点赞数
	 */
	private String dt_total_zan;

	/**
	 * 图片(链接)
	 */
	private String dt_img;

	/**
	 * 点赞人的昵称
	 */
	private String dt_zan_people_nickname;

	/**
	 * 动态内容
	 */
	private String dt_content;

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
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

	public String getUser_tag()
	{
		return user_tag;
	}

	public void setUser_tag(String user_tag)
	{
		this.user_tag = user_tag;
	}

	public String getUser_city()
	{
		return user_city;
	}

	public void setUser_city(String user_city)
	{
		this.user_city = user_city;
	}

	public String getDt_pub_time()
	{
		return dt_pub_time;
	}

	public void setDt_pub_time(String dt_pub_time)
	{
		this.dt_pub_time = dt_pub_time;
	}

	public String getDt_total_zan()
	{
		return dt_total_zan;
	}

	public void setDt_total_zan(String dt_total_zan)
	{
		this.dt_total_zan = dt_total_zan;
	}

	public String getDt_img()
	{
		return dt_img;
	}

	public void setDt_img(String dt_img)
	{
		this.dt_img = dt_img;
	}

	public String getDt_zan_people_nickname()
	{
		return dt_zan_people_nickname;
	}

	public void setDt_zan_people_nickname(String dt_zan_people_nickname)
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

	@Override
	public String toString()
	{
		return "DynamicBean{" +
				"user_id='" + user_id + '\'' +
				", user_logo='" + user_logo + '\'' +
				", user_nickname='" + user_nickname + '\'' +
				", user_tag='" + user_tag + '\'' +
				", user_city='" + user_city + '\'' +
				", dt_pub_time='" + dt_pub_time + '\'' +
				", dt_total_zan='" + dt_total_zan + '\'' +
				", dt_img='" + dt_img + '\'' +
				", dt_zan_people_nickname='" + dt_zan_people_nickname + '\'' +
				", dt_content='" + dt_content + '\'' +
				'}';
	}
}
