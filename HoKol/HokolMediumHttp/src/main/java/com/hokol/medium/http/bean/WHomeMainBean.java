package com.hokol.medium.http.bean;

import com.hokol.medium.http.HttpEnum;

import java.util.List;

public class WHomeMainBean
{
	/* 用户标签 * 【必填 1:网红,2:主播,3:演员,4:模特,5:歌手,6:体育】 */
	private int user_tag;

	/* 用户性别：【0:不限,1:男，2:女】 */
	private int user_sex;

	/* 用户省份编码 */
	private String p_code;

	/* 用户城市编码(数组) */
	private List<String> c_code;

	/* 用户筛选推荐：【0-不限,1-人气，2-最新】 */
	private int user_adv;

	/* 数据筛选上限：【必填】 */
	private int num1;

	/* 数据筛选长度：【必填】 */
	private int length;

	public WHomeMainBean(HttpEnum.UserTag tag, int num1, int length)
	{
		this.user_tag = tag.getIndex();
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

	public String getP_code()
	{
		return p_code;
	}

	public void setP_code(String p_code)
	{
		this.p_code = p_code;
	}

	public List<String> getC_code()
	{
		return c_code;
	}

	public void setC_code(List<String> c_code)
	{
		this.c_code = c_code;
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
