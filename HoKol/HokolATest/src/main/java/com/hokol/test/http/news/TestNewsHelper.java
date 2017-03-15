package com.hokol.test.http.news;

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
	public void doMultiplexNews(final int start, final int end)
	{
		new XHttp<VNewsMultiplexBean>()
		{
			@Override
			protected Object getRequestPostParam()
			{
				return new WNewsMultiplexBean(start, end);
			}

			@Override
			public void onSuccess(VNewsMultiplexBean multiplexNewsBeen)
			{

			}
		}.doRequest(HttpConstant.url_news_multiplex, VNewsMultiplexBean.class);
	}

	public void doRecommendNews()
	{
		new XHttp<VNewsSingleBean>()
		{
			@Override
			public void onSuccess(VNewsSingleBean vNewsSingleBean)
			{

			}
		}.doRequest(HttpConstant.url_news_recommend, VNewsSingleBean.class);
	}

	public void doSingleNews(final String news_Id)
	{
		new XHttp<VNewsSingleBean>()
		{
			@Override
			protected Object getRequestPostParam()
			{
				return new WNewsSingleBean(news_Id);
			}

			@Override
			public void onSuccess(VNewsSingleBean vNewsSingleBean)
			{
			}
		}.doRequest(HttpConstant.url_news_single, VNewsSingleBean.class);
	}
}
