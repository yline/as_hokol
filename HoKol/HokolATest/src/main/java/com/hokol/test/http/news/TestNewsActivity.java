package com.hokol.test.http.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.test.BaseTestActivity;

public class TestNewsActivity extends BaseTestActivity
{
	private TestNewsHelper testNewsHelper = new TestNewsHelper();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		final EditText editTextMuch1 = addEditNumber("输入开始号");
		final EditText editTextMuch2 = addEditNumber("输入结束号");
		addButton("多条新闻测试", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				int start = Integer.parseInt(editTextMuch1.getText().toString().trim());
				int end = Integer.parseInt(editTextMuch2.getText().toString().trim());

				testNewsHelper.doMultiplexNews(start, end);
			}
		});
		
		addButton("推荐新闻测试", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				testNewsHelper.doRecommendNews();
			}
		});
		
		final EditText editText = addEditText("输入新闻唯一标识");
		addButton("单条新闻测试", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String newsId = editText.getText().toString().trim();
				testNewsHelper.doSingleNews(newsId);
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestNewsActivity.class));
	}
}
