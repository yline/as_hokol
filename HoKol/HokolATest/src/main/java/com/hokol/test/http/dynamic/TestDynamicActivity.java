package com.hokol.test.http.dynamic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.test.common.BaseTestActivity;


public class TestDynamicActivity extends BaseTestActivity
{
	private TestDynamicHelper dynamicHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		dynamicHelper = new TestDynamicHelper();

		final EditText editPraise1 = addEditText("user_id");
		final EditText editPraise2 = addEditText("dynamic_id");
		final EditText editPraise3 = addEditNumber("0 --> 点赞; 其它 --> 取消点赞");
		addButton("点赞", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = editPraise1.getText().toString().trim();
				String dynamicId = editPraise2.getText().toString().trim();
				boolean praiseAction = editPraise3.getText().toString().trim().equals("0") ? true : false;
				dynamicHelper.doDynamicPraiseSingle(userId, dynamicId, praiseAction);
			}
		});
		
		final EditText editCare1 = addEditText("user_id");
		final EditText editCare2 = addEditNumber("start");
		final EditText editCare3 = addEditNumber("end");
		addButton("关注的所有人的动态总和", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = editCare1.getText().toString().trim();
				int start = Integer.parseInt(editCare2.getText().toString().trim());
				int end = Integer.parseInt(editCare3.getText().toString().trim());
				dynamicHelper.doDynamicCareAll(userId, start, end);
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestDynamicActivity.class));
	}
}
