package com.hokol.medium.http.bean;

public class WSettingSubmitProposalBean
{
	/**
	 * 用户唯一标识
	 */
	private String user_id;

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

	public WSettingSubmitProposalBean(String user_id, int advice_type, String advice_content, String user_connection)
	{
		this.user_id = user_id;
		this.advice_type = advice_type;
		this.advice_content = advice_content;
		this.user_connection = user_connection;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
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
