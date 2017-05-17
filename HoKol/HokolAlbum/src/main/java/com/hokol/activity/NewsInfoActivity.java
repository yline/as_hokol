package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;
import com.yline.log.LogFileUtil;

/**
 * 新闻详情界面
 *
 * @author yline 2017/3/2 --> 16:05
 * @version 1.0.0
 */
public class NewsInfoActivity extends BaseAppCompatActivity
{
	private static final String SingleInfoBeanUrl = "NewsInfoBeanUrl";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_info);

		findViewById(R.id.iv_news_info_back).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		String newsUrl = getIntent().getStringExtra(SingleInfoBeanUrl);
		LogFileUtil.v("newsUrl = " + newsUrl);

		WebView webView = (WebView) findViewById(R.id.web_view_news);

		WebSettings webSettings = webView.getSettings();
		webSettings.setDomStorageEnabled(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

		webView.loadUrl(newsUrl);
	}

	public static void actionStart(Context context, String url)
	{
		Intent intent = new Intent(context, NewsInfoActivity.class);
		intent.putExtra(SingleInfoBeanUrl, url);
		context.startActivity(intent);
	}
}
