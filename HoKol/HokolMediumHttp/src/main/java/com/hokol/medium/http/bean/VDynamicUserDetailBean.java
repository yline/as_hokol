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
}
