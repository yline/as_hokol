package com.hokol.test.http.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.medium.http.XHttpAdapter;
import com.hokol.medium.http.bean.VTaskMainAll;
import com.hokol.medium.http.bean.VTaskMainDetailBean;
import com.hokol.medium.http.bean.WTaskMainAll;
import com.hokol.medium.http.bean.WTaskMainCollectionBean;
import com.hokol.medium.http.bean.WTaskMainDetailBean;
import com.hokol.medium.http.helper.XHttpUtil;
import com.hokol.test.common.BaseTestActivity;

/**
 * 任务接口
 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
 * 获取任务列表(多条)--> task_index --> url_task_main_all --> WTaskMainAll - VTaskMainAll --> ok
 * 获取任务详情(单条)--> task_detail --> url_task_main_detail --> WTaskMainDetailBean - VTaskMainDetailBean --> ok
 * 任务发布--> 暂时不测
 * 任务收藏/取消收藏--> task_collect_switch --> url_task_main_collection --> WTaskMainCollectionBean - null --> ok
 */
public class TestTaskActivity extends BaseTestActivity
{
	private void testtask_collect_switch()
	{
		final EditText editTextOne = addEditNumber("user_id", "2");
		final EditText editTextTwp = addEditNumber("task_id", "1");
		final EditText editTextThree = addEditNumber("collect", "1");

		addButton("任务收藏/取消收藏", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String user_id = editTextOne.getText().toString().trim();
				final String task_id = editTextTwp.getText().toString().trim();

				final int collect = parseInt(editTextThree, 0);

				XHttpUtil.doTaskMainCollection(new WTaskMainCollectionBean(user_id, task_id, collect), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}

	private void testtask_detail()
	{
		final EditText editTextOne = addEditNumber("task_id", "2");
		addButton("获取任务详情(单条)", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String task_id = editTextOne.getText().toString().trim();

				XHttpUtil.doTaskMainDetail(new WTaskMainDetailBean(task_id), new XHttpAdapter<VTaskMainDetailBean>()
				{
					@Override
					public void onSuccess(VTaskMainDetailBean vTaskMainDetailBean)
					{

					}
				});
			}
		});
	}

	private void testtask_index()
	{
		/*final EditText editTextOne = addEditNumber("task_type");
		final EditText editTextTwo = addEditNumber("task_province");
		final EditText editTextThree = addEditNumber("task_city");*/
		final EditText editTextFour = addEditNumber("num1", "0");
		final EditText editTextFive = addEditNumber("length", "8");
		/*final EditText editTextSix = addEditNumber("task_sex");*/

		addButton("获取任务列表(多条)", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				/*int task_type = Integer.parseInt(editTextOne.getText().toString().trim());
				String task_province = editTextTwo.getText().toString().trim();
				String task_city = editTextThree.getText().toString().trim();*/
				final int num1 = Integer.parseInt(editTextFour.getText().toString().trim());
				final int length = Integer.parseInt(editTextFive.getText().toString().trim());

				XHttpUtil.doTaskMainAll(new WTaskMainAll(num1, length), new XHttpAdapter<VTaskMainAll>()
				{
					@Override
					public void onSuccess(VTaskMainAll vTaskMainAll)
					{

					}
				});
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// 暂时不测 任务发布
		testtask_index();
		testtask_detail();
		testtask_collect_switch();
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestTaskActivity.class));
	}
}
