package com.hokol.medium.http.bean;

import java.util.List;

/**
 * 多条新闻请求时,使用
 *
 * @author yline 2017/3/2 --> 14:10
 * @version 1.0.0
 */
public class ResponseMultiplexNewsBean
{
	/**
	 * 多条新闻
	 */
	private List<ResponseSingleNewsBean> list;

	public List<ResponseSingleNewsBean> getList()
	{
		return list;
	}
	
	public void setList(List<ResponseSingleNewsBean> list)
	{
		this.list = list;
	}
}
