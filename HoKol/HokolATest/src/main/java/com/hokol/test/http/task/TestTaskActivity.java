package com.hokol.test.http.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttp;
import com.hokol.medium.http.bean.WTaskMainAll;
import com.hokol.test.common.BaseTestActivity;

/**
 * 登录接口
 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
 * 获取任务列表(多条)--> task_index --> url_task_main_all --> WTaskMainAll - ？？？ --> 没有测试数据
 */
public class TestTaskActivity extends BaseTestActivity
{
	private void testtask_index()
	{
		/*final EditText editTextOne = addEditNumber("task_type");
		final EditText editTextTwo = addEditNumber("task_province");
		final EditText editTextThree = addEditNumber("task_city");*/
		final EditText editTextFour = addEditNumber("num1");
		final EditText editTextFive = addEditNumber("length");
		/*final EditText editTextSix = addEditNumber("task_sex");*/

		addButton("获取任务列表(多条)", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String httpUrl = HttpConstant.url_task_main_all;

				/*int task_type = Integer.parseInt(editTextOne.getText().toString().trim());
				String task_province = editTextTwo.getText().toString().trim();
				String task_city = editTextThree.getText().toString().trim();*/
				final int num1 = Integer.parseInt(editTextFour.getText().toString().trim());
				final int length = Integer.parseInt(editTextFive.getText().toString().trim());

				new XHttp<String>()
				{
					@Override
					protected Object getRequestPostParam()
					{
						return new WTaskMainAll(num1, length);
					}

					@Override
					public void onSuccess(String s)
					{

					}
				}.doRequest(httpUrl, String.class);
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// 暂时不测
		testtask_index();
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestTaskActivity.class));
	}
}
