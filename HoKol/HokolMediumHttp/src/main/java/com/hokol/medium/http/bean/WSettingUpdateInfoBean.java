package com.hokol.medium.http.bean;

import java.util.ArrayList;

public class WSettingUpdateInfoBean
{
	/* 用户唯一标识*/
	private String user_id;

	/* 用户昵称*/
	private String user_nickname;

	/* 用户性别：[1:男，2:女]*/
	private int user_sex;

	/* 用户城市*/
	private String c_code;

	/* 用户省份*/
	private String p_code;

	/* 用户签名*/
	private String user_sign;

	/* 用户标签：[1:网红,2:主播,3:演员,4:模特,5:歌手,6:体育]*/
	private ArrayList<Integer> user_tag;

	/* 用户获奖*/
	private String user_prize;

	/* 用户星座：[水瓶座：1，双鱼座：2，白羊座：3，金牛座：4，双子座：5，巨蟹座：6，狮子座：7，处女座：8，天枰座：9，天蝎座：10，射手座：11，摩羯座：12]*/
	private int user_constell;

	public WSettingUpdateInfoBean(String user_id)
	{
		this.user_id = user_id;
	}

	public WSettingUpdateInfoBean(String user_id, String user_nickname, int user_sex, String c_code, String p_code, String user_sign, ArrayList<Integer> user_tag, String user_prize, int user_constell)
	{
		this.user_id = user_id;
		this.user_nickname = user_nickname;
		this.user_sex = user_sex;
		this.c_code = c_code;
		this.p_code = p_code;
		this.user_sign = user_sign;
		this.user_tag = user_tag;
		this.user_prize = user_prize;
		this.user_constell = user_constell;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getUser_nickname()
	{
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname)
	{
		this.user_nickname = user_nickname;
	}

	public int getUser_sex()
	{
		return user_sex;
	}

	public void setUser_sex(int user_sex)
	{
		this.user_sex = user_sex;
	}

	public String getC_code()
	{
		return c_code;
	}

	public void setC_code(String c_code)
	{
		this.c_code = c_code;
	}

	public String getP_code()
	{
		return p_code;
	}

	public void setP_code(String p_code)
	{
		this.p_code = p_code;
	}

	public String getUser_sign()
	{
		return user_sign;
	}

	public void setUser_sign(String user_sign)
	{
		this.user_sign = user_sign;
	}

	public ArrayList<Integer> getUser_tag()
	{
		return user_tag;
	}

	public void setUser_tag(ArrayList<Integer> user_tag)
	{
		this.user_tag = user_tag;
	}

	public String getUser_prize()
	{
		return user_prize;
	}

	public void setUser_prize(String user_prize)
	{
		this.user_prize = user_prize;
	}

	public int getUser_constell()
	{
		return user_constell;
	}

	public void setUser_constell(int user_constell)
	{
		this.user_constell = user_constell;
	}
}
