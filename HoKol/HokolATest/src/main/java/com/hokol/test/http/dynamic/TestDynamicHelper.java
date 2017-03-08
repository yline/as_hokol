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
	public void doDynamicCare(String userId, int start, int end)
	{
		String httpUrl = HttpConstant.HTTP_MAIN_CARE_STAR_URL;

		new XHttp<VUserDynamicCareBean>()
		{
			@Override
			public void onSuccess(VUserDynamicCareBean vUserDynamicCareBean)
			{
				super.onSuccess(vUserDynamicCareBean);
			}
		}.doPost(httpUrl, new WUserDynamicCareBean(userId, start, end), VUserDynamicCareBean.class);
	}

	/**
	 * 给用户点赞
	 *
	 * @param userId
	 * @param dynamicId
	 * @param action
	 */
	public void doDynamicPraise(String userId, String dynamicId, boolean action)
	{
		String httpUrl = HttpConstant.HTTP_MAIN_DYNAMIC_PRAISE_URL;

		int dynamicPraise = action ? 1 : 0;

		new XHttp<VUserDynamicPraiseBean>()
		{
			@Override
			public void onSuccess(VUserDynamicPraiseBean vUserDynamicPraiseBean)
			{
				super.onSuccess(vUserDynamicPraiseBean);
			}
		}.doPost(httpUrl, new WUserDynamicPraiseBean(userId, dynamicId, dynamicPraise), VUserDynamicPraiseBean.class);
	}
}
