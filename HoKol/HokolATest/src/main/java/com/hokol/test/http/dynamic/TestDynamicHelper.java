package com.hokol.test.http.dynamic;

import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttp;
import com.hokol.medium.http.bean.VDynamicCareAllBean;
import com.hokol.medium.http.bean.VDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.WDynamicCareAllBean;
import com.hokol.medium.http.bean.WDynamicPraiseSingleBean;

public class TestDynamicHelper
{
	/**
	 * 请求动态（该用户关注的人,的所有动态）
	 *
	 * @param userId
	 * @param start
	 * @param end
	 */
	public void doDynamicCareAll(final String userId, final int start, final int end)
	{
		String httpUrl = HttpConstant.url_dynamic_care_all;

		new XHttp<VDynamicCareAllBean>()
		{
			@Override
			protected Object getRequestPostParam()
			{
				return new WDynamicCareAllBean(userId, start, end);
			}

			@Override
			public void onSuccess(VDynamicCareAllBean vUserDynamicCareBean)
			{

			}
		}.doRequest(httpUrl, VDynamicCareAllBean.class);
	}

	public void doDynamicUserDetail()
	{
		String httpUrl = HttpConstant.url_dynamic_user_detail;

		new XHttp<String>()
		{


			@Override
			public void onSuccess(String s)
			{

			}
		}.doRequest(httpUrl, String.class);
	}

	/**
	 * 给用户点赞
	 *
	 * @param userId
	 * @param dynamicId
	 * @param action
	 */
	public void doDynamicPraiseSingle(final String userId, final String dynamicId, boolean action)
	{
		String httpUrl = HttpConstant.url_dynamic_praise_single;

		final int dynamicPraise = action ? 1 : 0;

		new XHttp<VDynamicPraiseSingleBean>()
		{
			@Override
			protected Object getRequestPostParam()
			{
				return new WDynamicPraiseSingleBean(userId, dynamicId, dynamicPraise);
			}

			@Override
			public void onSuccess(VDynamicPraiseSingleBean vUserDynamicPraiseBean)
			{

			}
		}.doRequest(httpUrl, VDynamicPraiseSingleBean.class);
	}
}
