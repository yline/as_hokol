package com.hokol.test.http.news;

import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttp;
import com.hokol.medium.http.bean.VNewsMultiplexBean;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.http.bean.WNewsMultiplexBean;
import com.hokol.medium.http.bean.WNewsSingleBean;

/**
 * 新闻接口测试
 *
 * @author yline 2017/3/3 --> 15:33
 * @version 1.0.0
 */
public class TestNewsHelper
{
	public void doMultiplexNews(int start, int end)
	{
		new XHttp<VNewsMultiplexBean>()
		{
			@Override
			public void onSuccess(VNewsMultiplexBean multiplexNewsBeen)
			{
				super.onSuccess(multiplexNewsBeen);
				LogFileUtil.e("doMultiplexNews", "multiplexNewsBeen size -> " + multiplexNewsBeen.getList().size());
			}

			@Override
			public void onFailureCode(int code)
			{
				super.onFailureCode(code);
			}

			@Override
			public void onFailure(Exception ex)
			{
				super.onFailure(ex);
			}
		}.doPost(HttpConstant.HTTP_MAIN_MULTIPLEX_NEWS_URL, new WNewsMultiplexBean(start, end), VNewsMultiplexBean.class);
	}

	public void doRecommendNews()
	{
		new XHttp<VNewsSingleBean>()
		{
			@Override
			public void onSuccess(VNewsSingleBean responseSingleNewsBean)
			{
				super.onSuccess(responseSingleNewsBean);
				LogFileUtil.e("doRecommendNews", "responseSingleNewsBean -> " + responseSingleNewsBean.toString());
			}
		}.doPost(HttpConstant.HTTP_MAIN_RECOMMEND_NEWS_URL, "", VNewsSingleBean.class);
	}

	public void doSingleNews(String news_Id)
	{
		new XHttp<VNewsSingleBean>()
		{
			@Override
			public void onSuccess(VNewsSingleBean responseSingleNewsBean)
			{
				super.onSuccess(responseSingleNewsBean);
				LogFileUtil.e("doSingleNews", "responseSingleNewsBean -> " + responseSingleNewsBean.toString());
			}
		}.doPost(HttpConstant.HTTP_MAIN_SINGLE_NEWS_URL, new WNewsSingleBean(news_Id), VNewsSingleBean.class);
	}
}
