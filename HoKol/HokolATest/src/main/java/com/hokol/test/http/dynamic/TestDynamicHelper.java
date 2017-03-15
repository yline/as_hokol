package com.hokol.test.http.dynamic;

import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttp;
import com.hokol.medium.http.bean.VUserDynamicCareBean;
import com.hokol.medium.http.bean.VUserDynamicPraiseBean;
import com.hokol.medium.http.bean.WUserDynamicCareBean;
import com.hokol.medium.http.bean.WUserDynamicPraiseBean;

public class TestDynamicHelper
{
	/**
	 * 请求动态（该用户关注的人,的所有动态）
	 *
	 * @param userId
	 * @param start
	 * @param end
	 */
	public void doDynamicCare(final String userId, final int start, final int end)
	{
		String httpUrl = HttpConstant.url_care_dynamic_multiplex;

		new XHttp<VUserDynamicCareBean>()
		{
			@Override
			protected Object getRequestPostParam()
			{
				return new WUserDynamicCareBean(userId, start, end);
			}

			@Override
			public void onSuccess(VUserDynamicCareBean vUserDynamicCareBean)
			{

			}
		}.doRequest(httpUrl, VUserDynamicCareBean.class);
	}

	/**
	 * 给用户点赞
	 *
	 * @param userId
	 * @param dynamicId
	 * @param action
	 */
	public void doDynamicPraise(final String userId, final String dynamicId, boolean action)
	{
		String httpUrl = HttpConstant.url_care_dynamic_praise;

		final int dynamicPraise = action ? 1 : 0;

		new XHttp<VUserDynamicPraiseBean>()
		{
			@Override
			protected Object getRequestPostParam()
			{
				return new WUserDynamicPraiseBean(userId, dynamicId, dynamicPraise);
			}

			@Override
			public void onSuccess(VUserDynamicPraiseBean vUserDynamicPraiseBean)
			{

			}
		}.doRequest(httpUrl, VUserDynamicPraiseBean.class);
	}
}
