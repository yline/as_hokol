package com.hokol.medium.http.bean;

public class WUserCoinGiftBean
{
	/**
	 * 用户唯一标识
	 */
	private String user_id;

	/**
	 * 接收红豆用户唯一标识
	 */
	private String recive_user_id;

	/**
	 * 动态唯一标识:[非动态送红豆dt_id为0]
	 */
	private int dt_id;

	/**
	 * 赠送红豆数目
	 */
	private int coin_num;

	public WUserCoinGiftBean(String user_id, String recive_user_id, int dt_id, int coin_num)
	{
		this.user_id = user_id;
		this.recive_user_id = recive_user_id;
		this.dt_id = dt_id;
		this.coin_num = coin_num;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getRecive_user_id()
	{
		return recive_user_id;
	}

	public void setRecive_user_id(String recive_user_id)
	{
		this.recive_user_id = recive_user_id;
	}

	public int getDt_id()
	{
		return dt_id;
	}

	public void setDt_id(int dt_id)
	{
		this.dt_id = dt_id;
	}

	public int getCoin_num()
	{
		return coin_num;
	}

	public void setCoin_num(int coin_num)
	{
		this.coin_num = coin_num;
	}
}
