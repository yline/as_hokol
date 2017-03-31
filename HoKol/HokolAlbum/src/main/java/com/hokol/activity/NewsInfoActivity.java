package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.medium.http.bean.WNewsSingleBean;

/**
 * 新闻详情界面
 *
 * @author yline 2017/3/2 --> 16:05
 * @version 1.0.0
 */
public class NewsInfoActivity extends BaseAppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_info);

		findViewById(R.id.tv_news_info_back).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		initData();
	}

	private void initData()
	{
		final WNewsSingleBean bean = getIntentData();
	}

	public static void actionStart(Context context, WNewsSingleBean bean)
	{
		Intent intent = new Intent(context, NewsInfoActivity.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable("WNewsSingleBean", bean);
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

	private WNewsSingleBean getIntentData()
	{
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		WNewsSingleBean bean = bundle.getParcelable("WNewsSingleBean");
		return bean;
	}
}
