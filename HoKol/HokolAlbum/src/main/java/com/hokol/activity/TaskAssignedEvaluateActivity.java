package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hokol.R;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.recycler.adapter.CommonRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;

/**
 * 商家发布任务，评价
 *
 * @author yline 2017/6/1 -- 16:43
 * @version 1.0.0
 */
public class TaskAssignedEvaluateActivity extends BaseAppCompatActivity
{
	private AssignedEvaluateAdapter assignedEvaluateAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assigned_evaluate);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_task_assigned_evaluate);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(this)
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_graylight_size_medium;
			}

			@Override
			protected boolean isDivideLastLine()
			{
				return true;
			}
		});

		assignedEvaluateAdapter = new AssignedEvaluateAdapter();
		recyclerView.setAdapter(assignedEvaluateAdapter);

		assignedEvaluateAdapter.setDataList(HttpEnum.getUserSexListAll());

		findViewById(R.id.iv_task_assigned_evaluate_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	private class AssignedEvaluateAdapter extends CommonRecyclerAdapter<String>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_task_assigned_evaluate;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{

		}
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TaskAssignedEvaluateActivity.class));
	}
}
