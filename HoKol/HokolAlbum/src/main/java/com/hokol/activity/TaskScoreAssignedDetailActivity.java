package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.medium.http.HttpEnum;
import com.yline.base.BaseAppCompatActivity;
import com.yline.utils.UIScreenUtil;
import com.yline.view.common.HeadFootRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

public class TaskScoreAssignedDetailActivity extends BaseAppCompatActivity
{
	private ScoreAssignedDetailAdapter assignedDetailAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_score_assigned_detail);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_task_score_assigned_detail);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		assignedDetailAdapter = new ScoreAssignedDetailAdapter();
		recyclerView.setAdapter(assignedDetailAdapter);

		View headView = new View(this);
		headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(this, 4)));
		headView.setBackgroundColor(ContextCompat.getColor(this, R.color.hokolGrayLight));
		assignedDetailAdapter.addHeadView(headView);

		assignedDetailAdapter.setDataList(HttpEnum.getUserTagListAll());

		findViewById(R.id.iv_task_score_assigned_detail_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	private class ScoreAssignedDetailAdapter extends HeadFootRecyclerAdapter<String>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_task_score_assigned_detail;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{

		}
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TaskScoreAssignedDetailActivity.class));
	}
}
