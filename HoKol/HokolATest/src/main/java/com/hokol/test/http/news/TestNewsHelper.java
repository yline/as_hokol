package com.hokol.test.http.news;

import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.bean.RequestMultiplexNewsBean;
import com.hokol.medium.http.bean.RequestSingleNewsBean;
import com.hokol.medium.http.bean.ResponseMultiplexNewsBean;
import com.hokol.medium.http.bean.ResponseSingleNewsBean;
import com.hokol.medium.http.xHttp;

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
		new xHttp<ResponseMultiplexNewsBean>()
		{
			@Override
			public void onSuccess(ResponseMultiplexNewsBean multiplexNewsBeen)
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
		}.doPost(HttpConstant.HTTP_MAIN_MULTIPLEX_NEWS_URL, new RequestMultiplexNewsBean(start, end), ResponseMultiplexNewsBean.class);
	}

	public void doRecommendNews()
	{
		new xHttp<ResponseSingleNewsBean>()
		{
			@Override
			public void onSuccess(ResponseSingleNewsBean responseSingleNewsBean)
			{
				super.onSuccess(responseSingleNewsBean);
				LogFileUtil.e("doRecommendNews", "responseSingleNewsBean -> " + responseSingleNewsBean.toString());
			}
		}.doPost(HttpConstant.HTTP_MAIN_RECOMMEND_NEWS_URL, "", ResponseSingleNewsBean.class);
	}

	public void doSingleNews(String news_Id)
	{
		new xHttp<ResponseSingleNewsBean>()
		{
			@Override
			public void onSuccess(ResponseSingleNewsBean responseSingleNewsBean)
			{
				super.onSuccess(responseSingleNewsBean);
				LogFileUtil.e("doSingleNews", "responseSingleNewsBean -> " + responseSingleNewsBean.toString());
			}
		}.doPost(HttpConstant.HTTP_MAIN_SINGLE_NEWS_URL, new RequestSingleNewsBean(news_Id), ResponseSingleNewsBean.class);
	}
}
