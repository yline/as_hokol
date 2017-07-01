package com.hokol.medium.http.bean;

public class WSettingSubmitProposalBean
{
	public static final int TypeNull = 0; // 应用崩溃

	public static final int TypeCrash = 1; // 应用崩溃

	public static final int TypeFunction = 2; // 功能问题

	public static final int TypeEnter = 3; // 注册登录

	public static final int TypeImprove = 4; // 改善建议

	public static final int TypePay = 5; // 订单支付

	/**
	 * 用户唯一标识
	 */
	private String advice_user_id;

	/**
	 * 意见类型：[1:应用崩溃,2:功能问题,3:注册登录,4:改善建议,5:订单支付]
	 */
	private int advice_type;
	
	/**
	 * 意见内容
	 */
	private String advice_content;

	/**
	 * 用户联系方式
	 */
	private String user_connection;

	public WSettingSubmitProposalBean(String advice_user_id, int advice_type, String advice_content, String user_connection)
	{
		this.advice_user_id = advice_user_id;
		this.advice_type = advice_type;
		this.advice_content = advice_content;
		this.user_connection = user_connection;
	}

	public String getAdvice_user_id()
	{
		return advice_user_id;
	}

	public void setAdvice_user_id(String advice_user_id)
	{
		this.advice_user_id = advice_user_id;
	}

	public int getAdvice_type()
	{
		return advice_type;
	}

	public void setAdvice_type(int advice_type)
	{
		this.advice_type = advice_type;
	}

	public String getAdvice_content()
	{
		return advice_content;
	}

	public void setAdvice_content(String advice_content)
	{
		this.advice_content = advice_content;
	}

	public String getUser_connection()
	{
		return user_connection;
	}

	public void setUser_connection(String user_connection)
	{
		this.user_connection = user_connection;
	}
}
