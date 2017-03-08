package com.hokol.test.http.dynamic;

import com.hokol.medium.http.HttpConstant;

public class TestDynamicHelper
{
	/**
	 * 请求动态（该用户关注的人,的所有动态）
	 *
	 * @param userId
	 * @param start
	 * @param end
	 */
	public void doCareStartInfo(String userId, int start, int end)
	{
		String httpUrl = HttpConstant.HTTP_MAIN_CARE_STAR_URL;

		/*
		new xHttp()
		{

		}.doPost(httpUrl, );*/
	}

	public void doActionPraise(String userId, String dynamicId, boolean action)
	{
		String httpUrl = HttpConstant.HTTP_MAIN_DYNAMIC_PRAISE_URL;

		int actionPraise = action ? 1 : 0;

	}
}
