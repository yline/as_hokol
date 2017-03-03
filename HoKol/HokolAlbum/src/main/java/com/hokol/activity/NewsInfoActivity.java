package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.hokol.application.IApplication;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.bean.RequestSingleNewsBean;
import com.hokol.medium.http.bean.ResponseSingleNewsBean;
import com.hokol.medium.http.xHttp;

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
		setTitle("新闻详情页面");

		initData();
	}

	private void initData()
	{
		RequestSingleNewsBean bean = getIntentData();

		if (null != bean && !TextUtils.isEmpty(bean.getNew_id()))
		{
			new xHttp<ResponseSingleNewsBean>()
			{
				@Override
				public void onSuccess(ResponseSingleNewsBean responseSingleNewsBean)
				{
					super.onSuccess(responseSingleNewsBean);
					IApplication.toast(responseSingleNewsBean.toString());
				}
			}.doPost(HttpConstant.HTTP_MAIN_SINGLE_NEWS_URL, bean, ResponseSingleNewsBean.class);
		}
		else
		{
			LogFileUtil.v("bean is null");
		}
	}

	public static void actionStart(Context context, RequestSingleNewsBean bean)
	{
		Intent intent = new Intent(context, NewsInfoActivity.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable("RequestSingleNewsBean", bean);
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

	private RequestSingleNewsBean getIntentData()
	{
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		RequestSingleNewsBean bean = bundle.getParcelable("RequestSingleNewsBean");
		return bean;
	}
}
