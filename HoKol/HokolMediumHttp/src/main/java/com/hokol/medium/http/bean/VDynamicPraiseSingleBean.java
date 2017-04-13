package com.hokol.medium.http.bean;

import java.util.List;

/**
 * 给动态点赞；返回参数
 *
 * @author yline 2017/3/8 --> 22:58
 * @version 1.0.0
 */
public class VDynamicPraiseSingleBean
{
	/* 所有点赞的用户昵称 */
	private List<String> user_nickname;

	public List<String> getUser_nickname()
	{
		return user_nickname;
	}

	public void setUser_nickname(List<String> user_nickname)
	{
		this.user_nickname = user_nickname;
	}
}
