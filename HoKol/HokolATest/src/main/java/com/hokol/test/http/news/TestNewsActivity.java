package com.hokol.test.http.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttp;
import com.hokol.medium.http.bean.VNewsMultiplexBean;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.http.bean.WNewsMultiplexBean;
import com.hokol.test.common.BaseTestActivity;

/**
 * 新闻接口
 * Button名称 --> API后缀 --> HttpConstant --> Bean名称/Bean名称 --> 情况
 * 推荐新闻获取 --> new_tui --> url_news_recommend --> null - VNewsSingleBean --> 还差 Html返回值
 * 多条新闻获取 --> news --> url_news_multiplex --> WNewsMultiplexBean - VNewsMultiplexBean --> 还差 Html返回值 + 待处理长度<=0的情况
 * 单条新闻获取 --> 等待Html5,未提供接口
 */
public class TestNewsActivity extends BaseTestActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		addButton("推荐新闻获取", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new XHttp<VNewsSingleBean>()
				{
					@Override
					public void onSuccess(VNewsSingleBean vNewsSingleBean)
					{

					}
				}.doRequest(HttpConstant.url_news_recommend, VNewsSingleBean.class);
			}
		});

		final EditText editTextMuch1 = addEditNumber("输入开始号");
		final EditText editTextMuch2 = addEditNumber("长度");
		addButton("多条新闻测试", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final int start = Integer.parseInt(editTextMuch1.getText().toString().trim());
				final int length = Integer.parseInt(editTextMuch2.getText().toString().trim());

				new XHttp<VNewsMultiplexBean>()
				{
					@Override
					protected Object getRequestPostParam()
					{
						return new WNewsMultiplexBean(start, length);
					}

					@Override
					public void onSuccess(VNewsMultiplexBean multiplexNewsBeen)
					{

					}
				}.doRequest(HttpConstant.url_news_multiplex, VNewsMultiplexBean.class);
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestNewsActivity.class));
	}
}
