package com.hokol.medium.http.bean;

public class WDynamicCareSingleBean
{
	/**
	 * 动态唯一标识
	 */
	private String dt_id;

	public WDynamicCareSingleBean(String dt_id)
	{
		this.dt_id = dt_id;
	}

	public String getDt_id()
	{
		return dt_id;
	}

	public void setDt_id(String dt_id)
	{
		this.dt_id = dt_id;
	}
}
