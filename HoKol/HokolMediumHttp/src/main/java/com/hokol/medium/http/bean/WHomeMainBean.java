package com.hokol.medium.http.bean;

import com.hokol.medium.http.HttpEnum;

import java.util.ArrayList;
import java.util.List;

public class WHomeMainBean
{
	/**
	 * 用户标签
	 * 【必填 1:网红,2:主播,3:演员,4:模特,5:歌手,6:体育】
	 */
	private int user_tag;

	/**
	 * 用户性别：【0:不限,1:男，2:女】
	 */
	private int user_sex;

	/**
	 * 用户省份：【例：浙江省，不限】
	 */
	private String user_province;

	/**
	 * 用户城市：【例：杭州市，不限】
	 */
	private List<String> user_city;

	/**
	 * 用户筛选推荐：【不限：0,人气:1，最新:2】
	 */
	private int user_adv;

	/**
	 * 数据筛选上限：【必填】
	 */
	private int num1;

	/**
	 * 数据筛选长度：【必填】
	 */
	private int length;

	public WHomeMainBean(HttpEnum.UserTag userTag, int num1, int length)
	{
		this.user_tag = userTag.getIndex();
		this.user_sex = 0;
		this.user_province = "不限";
		this.user_city = new ArrayList<>();
		this.user_adv = 0;
		this.num1 = num1;
		this.length = length;
	}

	public int getUser_tag()
	{
		return user_tag;
	}

	public void setUser_tag(int user_tag)
	{
		this.user_tag = user_tag;
	}

	public int getUser_sex()
	{
		return user_sex;
	}

	public void setUser_sex(int user_sex)
	{
		this.user_sex = user_sex;
	}

	public String getUser_province()
	{
		return user_province;
	}

	public void setUser_province(String user_province)
	{
		this.user_province = user_province;
	}

	public List<String> getUser_city()
	{
		return user_city;
	}

	public void setUser_city(List<String> user_city)
	{
		this.user_city = user_city;
	}

	public int getUser_adv()
	{
		return user_adv;
	}

	public void setUser_adv(int user_adv)
	{
		this.user_adv = user_adv;
	}

	public int getNum1()
	{
		return num1;
	}

	public void setNum1(int num1)
	{
		this.num1 = num1;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}
}
