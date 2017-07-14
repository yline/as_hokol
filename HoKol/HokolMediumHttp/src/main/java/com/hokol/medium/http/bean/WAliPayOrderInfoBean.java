package com.hokol.medium.http.bean;

public class WAliPayOrderInfoBean
{
	/* 用户ID */
	private String user_id;

	/* 充值数额(人民币) */
	private float total_amount;

	public WAliPayOrderInfoBean(String user_id, float total_amount)
	{
		this.user_id = user_id;
		this.total_amount = total_amount;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public float getTotal_amount()
	{
		return total_amount;
	}

	public void setTotal_amount(float total_amount)
	{
		this.total_amount = total_amount;
	}
}
