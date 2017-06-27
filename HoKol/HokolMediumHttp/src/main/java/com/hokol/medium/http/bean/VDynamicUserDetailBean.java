package com.hokol.medium.http.bean;

import java.util.ArrayList;
import java.util.List;

public class VDynamicUserDetailBean
{
	public static final int unCare = 0;

	public static final int cared = 1;

	/* 关注的人唯一标识 */
	private String user_id;

	/* 用户头像（链接） */
	private String user_logo;

	/* 用户背景 （链接) */
	private String user_big_logo;

	/* 昵称 */
	private String user_nickname;

	/* 标签：[数组] */
	private List<String> user_tag;

	/* 用户性别(男，女) */
	private String user_sex;

	/* 是否关注:0[未关注],1[已关注],2[自己的主页] */
	private int is_care;

	/* 用户收藏数 */
	private int user_collect_task_num;

	/* 用户所在城市 [石家庄市，130100] */
	private ArrayList<String> city;

	/* 用户手机号 */
	private String user_tel;

	/* 用户所在省份 [北京市，110000] */
	private ArrayList<String> province;

	/* 用户签名 */
	private String user_sign;

	/* 用户获奖经历 */
	private String user_prize;

	/* 用户星座 */
	private String user_constell;

	/* 用户点赞数 */
	private int user_zan;

	/* 用户红豆数 */
	private int user_coin;

	/* 用户关注数 */
	private int user_care_num;

	/* 用户粉丝数 */
	private int user_fans_num;

	/* 用户等级 */
	private int user_level;

	/* 用户等级图像 */
	private String level_url;

	public static int getUnCare()
	{
		return unCare;
	}

	public static int getCared()
	{
		return cared;
	}

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

	public String getUser_big_logo()
	{
		return user_big_logo;
	}

	public void setUser_big_logo(String user_big_logo)
	{
		this.user_big_logo = user_big_logo;
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

	public String getUser_sex()
	{
		return user_sex;
	}

	public void setUser_sex(String user_sex)
	{
		this.user_sex = user_sex;
	}

	public int getIs_care()
	{
		return is_care;
	}

	public void setIs_care(int is_care)
	{
		this.is_care = is_care;
	}

	public int getUser_collect_task_num()
	{
		return user_collect_task_num;
	}

	public void setUser_collect_task_num(int user_collect_task_num)
	{
		this.user_collect_task_num = user_collect_task_num;
	}

	public ArrayList<String> getCity()
	{
		return city;
	}

	public void setCity(ArrayList<String> city)
	{
		this.city = city;
	}

	public String getUser_tel()
	{
		return user_tel;
	}

	public void setUser_tel(String user_tel)
	{
		this.user_tel = user_tel;
	}

	public ArrayList<String> getProvince()
	{
		return province;
	}

	public void setProvince(ArrayList<String> province)
	{
		this.province = province;
	}

	public String getUser_sign()
	{
		return user_sign;
	}

	public void setUser_sign(String user_sign)
	{
		this.user_sign = user_sign;
	}

	public String getUser_prize()
	{
		return user_prize;
	}

	public void setUser_prize(String user_prize)
	{
		this.user_prize = user_prize;
	}

	public String getUser_constell()
	{
		return user_constell;
	}

	public void setUser_constell(String user_constell)
	{
		this.user_constell = user_constell;
	}

	public int getUser_zan()
	{
		return user_zan;
	}

	public void setUser_zan(int user_zan)
	{
		this.user_zan = user_zan;
	}

	public int getUser_coin()
	{
		return user_coin;
	}

	public void setUser_coin(int user_coin)
	{
		this.user_coin = user_coin;
	}

	public int getUser_care_num()
	{
		return user_care_num;
	}

	public void setUser_care_num(int user_care_num)
	{
		this.user_care_num = user_care_num;
	}

	public int getUser_fans_num()
	{
		return user_fans_num;
	}

	public void setUser_fans_num(int user_fans_num)
	{
		this.user_fans_num = user_fans_num;
	}

	public int getUser_level()
	{
		return user_level;
	}

	public void setUser_level(int user_level)
	{
		this.user_level = user_level;
	}

	public String getLevel_url()
	{
		return level_url;
	}

	public void setLevel_url(String level_url)
	{
		this.level_url = level_url;
	}
}
