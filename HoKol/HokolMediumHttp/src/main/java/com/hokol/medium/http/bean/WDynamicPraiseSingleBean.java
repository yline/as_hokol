package com.hokol.medium.http.bean;

/**
 * 给动态 点赞；请求参数
 *
 * @author yline 2017/3/8 --> 22:56
 * @version 1.0.0
 */
public class WDynamicPraiseSingleBean
{
	public static final int actionPraise = 1;

	public static final int actionPraiseCancel = 0;

	public WDynamicPraiseSingleBean(String user_id, String dt_id, int zan)
	{
		this.user_id = user_id;
		this.dt_id = dt_id;
		this.zan = zan;
	}

	/**
	 * 用户唯一标识
	 */
	private String user_id;

	/**
	 * 动态唯一标识
	 */
	private String dt_id;

	/**
	 * 点赞:zan=1
	 * 取消点赞:zan=0
	 */
	private int zan;

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getDt_id()
	{
		return dt_id;
	}

	public void setDt_id(String dt_id)
	{
		this.dt_id = dt_id;
	}

	public int getZan()
	{
		return zan;
	}

	public void setZan(int zan)
	{
		this.zan = zan;
	}

	@Override
	public String toString()
	{
		return "WDynamicPraiseSingleBean{" +
				"user_id='" + user_id + '\'' +
				", dt_id='" + dt_id + '\'' +
				", zan=" + zan +
				'}';
	}
}
