package com.hokol.medium.http.bean;

import java.util.List;

public class VDynamicUserDetailBean
{
	/**
	 * 关注的人唯一标识
	 */
	private String user_id;

	/**
	 * 用户手机号
	 */
	private String user_tel;

	/**
	 * 用户头像（链接）
	 */
	private String user_logo;

	/**
	 * 用户背景 （链接)
	 */
	private String user_big_logo;

	/**
	 * 是否关注:0[未关注],1[已关注],2[自己的主页]
	 */
	private String is_care;

	/**
	 * 昵称
	 */
	private String user_nickname;

	/**
	 * 标签：[数组]
	 */
	private List<String> user_tag;

	/**
	 * 用户签名
	 */
	private String user_sign;

	/**
	 * 用户星座
	 */
	private String user_constell;

	/**
	 * 用户所在省份
	 */
	private String user_province;

	/**
	 * 用户所在城市
	 */
	private String user_city;

	/**
	 * 图片(链接)
	 */
	private String user_img;

	/**
	 * 用户点赞数
	 */
	private String user_zan;

	/**
	 * 用户红豆数
	 */
	private String user_coin;

	/**
	 * 用户关注数
	 */
	private String user_care_num;

	/**
	 * 用户粉丝数
	 */
	private String user_fans_num;

	/**
	 * 用户获奖经历：[数组]
	 */
	private String user_prize;

	/**
	 * 用户收藏数
	 */
	private String user_collect_task_num;

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getUser_tel()
	{
		return user_tel;
	}

	public void setUser_tel(String user_tel)
	{
		this.user_tel = user_tel;
	}

	public String getUser_logo()
	{
		return user_logo;
	}

	public void setUser_logo(String user_logo)
	{
		this.user_logo = user_logo;
	}

	public String getUser_big_logo()
	{
		return user_big_logo;
	}

	public void setUser_big_logo(String user_big_logo)
	{
		this.user_big_logo = user_big_logo;
	}

	public String getIs_care()
	{
		return is_care;
	}

	public void setIs_care(String is_care)
	{
		this.is_care = is_care;
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

	public String getUser_sign()
	{
		return user_sign;
	}

	public void setUser_sign(String user_sign)
	{
		this.user_sign = user_sign;
	}

	public String getUser_constell()
	{
		return user_constell;
	}

	public void setUser_constell(String user_constell)
	{
		this.user_constell = user_constell;
	}

	public String getUser_province()
	{
		return user_province;
	}

	public void setUser_province(String user_province)
	{
		this.user_province = user_province;
	}

	public String getUser_city()
	{
		return user_city;
	}

	public void setUser_city(String user_city)
	{
		this.user_city = user_city;
	}

	public String getUser_img()
	{
		return user_img;
	}

	public void setUser_img(String user_img)
	{
		this.user_img = user_img;
	}

	public String getUser_zan()
	{
		return user_zan;
	}

	public void setUser_zan(String user_zan)
	{
		this.user_zan = user_zan;
	}

	public String getUser_coin()
	{
		return user_coin;
	}

	public void setUser_coin(String user_coin)
	{
		this.user_coin = user_coin;
	}

	public String getUser_care_num()
	{
		return user_care_num;
	}

	public void setUser_care_num(String user_care_num)
	{
		this.user_care_num = user_care_num;
	}

	public String getUser_fans_num()
	{
		return user_fans_num;
	}

	public void setUser_fans_num(String user_fans_num)
	{
		this.user_fans_num = user_fans_num;
	}

	public String getUser_prize()
	{
		return user_prize;
	}

	public void setUser_prize(String user_prize)
	{
		this.user_prize = user_prize;
	}

	public String getUser_collect_task_num()
	{
		return user_collect_task_num;
	}

	public void setUser_collect_task_num(String user_collect_task_num)
	{
		this.user_collect_task_num = user_collect_task_num;
	}
}
