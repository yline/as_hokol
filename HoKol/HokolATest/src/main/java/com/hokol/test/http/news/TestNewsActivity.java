package com.hokol.test.http.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VNewsMultiplexBean;
import com.hokol.medium.http.bean.VNewsRecommendBean;
import com.hokol.medium.http.bean.WNewsMultiplexBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.test.common.BaseTestActivity;

public class TestNewsActivity extends BaseTestActivity
{
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestNewsActivity.class));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		addButton("推荐新闻获取", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				XHttpUtil.doNewsRecommend(new HokolAdapter<VNewsRecommendBean>()
				{
					@Override
					public void onSuccess(VNewsRecommendBean vNewsRecommendBean)
					{

					}
				});
			}
		});

		final EditText editTextMuch1 = addEditNumber("输入开始号", "0");
		final EditText editTextMuch2 = addEditNumber("长度", "2");
		addButton("多条新闻测试", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final int start = Integer.parseInt(editTextMuch1.getText().toString().trim());
				final int length = Integer.parseInt(editTextMuch2.getText().toString().trim());

				XHttpUtil.doNewsMultiplex(new WNewsMultiplexBean(start, length), new HokolAdapter<VNewsMultiplexBean>()
				{
					@Override
					public void onSuccess(VNewsMultiplexBean vNewsMultiplexBean)
					{

					}
				});
			}
		});
	}
}
