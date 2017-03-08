package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.base.adapter.ViewHolder;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.http.bean.WNewsSingleBean;
import com.hokol.medium.http.xHttp;

/**
 * 新闻详情界面
 *
 * @author yline 2017/3/2 --> 16:05
 * @version 1.0.0
 */
public class NewsInfoActivity extends BaseAppCompatActivity
{
	private ViewHolder newsInfoViewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_info);
		setTitle("新闻详情页面");

		newsInfoViewHolder = new ViewHolder(getWindow().getDecorView());

		initData();
	}

	private void initData()
	{
		WNewsSingleBean bean = getIntentData();

		if (null != bean && !TextUtils.isEmpty(bean.getNews_id()))
		{
			new xHttp<VNewsSingleBean>()
			{
				@Override
				public void onSuccess(VNewsSingleBean responseSingleNewsBean)
				{
					super.onSuccess(responseSingleNewsBean);
					newsInfoViewHolder.setText(R.id.tv_news_info_title, responseSingleNewsBean.getNews_title());
					newsInfoViewHolder.setText(R.id.tv_news_info_sub, responseSingleNewsBean.getNews_source() + "  " + responseSingleNewsBean.getNews_time());
					newsInfoViewHolder.setText(R.id.tv_news_info_content, responseSingleNewsBean.getNews_content());

					ImageView imageView = newsInfoViewHolder.get(R.id.iv_news_info);
					Glide.with(NewsInfoActivity.this).load(responseSingleNewsBean.getNews_img()).placeholder(R.drawable.global_load_failed).into(imageView);
				}
			}.doPost(HttpConstant.HTTP_MAIN_SINGLE_NEWS_URL, bean, VNewsSingleBean.class);
		}
		else
		{
			LogFileUtil.v("bean is null");
		}
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
