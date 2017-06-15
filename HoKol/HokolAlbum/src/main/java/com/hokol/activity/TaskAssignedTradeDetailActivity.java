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
import com.hokol.medium.widget.FlowWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.base.BaseAppCompatActivity;
import com.yline.utils.UIScreenUtil;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.recycler.adapter.HeadFootRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.Arrays;

public class TaskAssignedTradeDetailActivity extends BaseAppCompatActivity
{
	private TradeDetailAdapter tradeDetailAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assigned_trade_detail);

		initView();
		findViewById(R.id.iv_task_assigned_trade_detail_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	private void initView()
	{
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_task_assigned_trade_detail);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(this)
		{
			@Override
			protected boolean isDivideLastLine()
			{
				return true;
			}

			@Override
			protected int getHeadNumber()
			{
				return 1;
			}
		});

		tradeDetailAdapter = new TradeDetailAdapter();
		recyclerView.setAdapter(tradeDetailAdapter);

		// 头部
		View headView = new View(this);
		headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(this, 4)));
		headView.setBackgroundColor(ContextCompat.getColor(this, R.color.hokolGrayLight));
		tradeDetailAdapter.addHeadView(headView);

		tradeDetailAdapter.setDataList(HttpEnum.getUserTagListAll());
	}

	private class TradeDetailAdapter extends HeadFootRecyclerAdapter<String>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_task_assigned_trade_detail;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			FlowLayout flowLayout = holder.get(R.id.flow_layout_trade_detail);
			FlowWidget flowWidget = new FlowWidget(TaskAssignedTradeDetailActivity.this, flowLayout);
			flowWidget.setDataList(Arrays.asList("演员", "模特"));

			holder.setOnClickListener(R.id.iv_trade_detail_forward, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					StarInfoActivity.actionStart(TaskAssignedTradeDetailActivity.this);
				}
			});
		}
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TaskAssignedTradeDetailActivity.class));
	}
}
