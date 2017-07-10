package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskUserAcceptBean;
import com.hokol.medium.http.bean.WTaskUserAcceptBean;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.utils.UIScreenUtil;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.Arrays;
import java.util.List;

public class TaskAssignedTradeDetailActivity extends BaseAppCompatActivity
{
	private static final String KeyTaskId = "taskId";

	private TradeDetailAdapter tradeDetailAdapter;

	public static void actionStart(Context context, String taskId)
	{
		context.startActivity(new Intent(context, TaskAssignedTradeDetailActivity.class).putExtra(KeyTaskId, taskId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assigned_trade_detail);

		initView();
		initData();
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

		tradeDetailAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VTaskUserAcceptBean.VTaskUserAcceptOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VTaskUserAcceptBean.VTaskUserAcceptOneBean vTaskUserAcceptOneBean, int position)
			{
				StarInfoActivity.actionStart(TaskAssignedTradeDetailActivity.this, vTaskUserAcceptOneBean.getUser_id());
			}
		});

		// 头部
		View headView = new View(this);
		headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(this, 4)));
		headView.setBackgroundColor(ContextCompat.getColor(this, R.color.hokolGrayLight));
		tradeDetailAdapter.addHeadView(headView);
	}

	private void initData()
	{
		String taskId = getIntent().getStringExtra(KeyTaskId);

		XHttpUtil.doTaskUserAcceptDetail(new WTaskUserAcceptBean(taskId, 0, DeleteConstant.defaultNumberLarge), new XHttpAdapter<VTaskUserAcceptBean>()
		{
			@Override
			public void onSuccess(VTaskUserAcceptBean vTaskUserAcceptBean)
			{
				List<VTaskUserAcceptBean.VTaskUserAcceptOneBean> resultList = vTaskUserAcceptBean.getList();
				if (null != resultList)
				{
					tradeDetailAdapter.setDataList(resultList);
				}
			}
		});
	}

	private class TradeDetailAdapter extends WidgetRecyclerAdapter<VTaskUserAcceptBean.VTaskUserAcceptOneBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_task_assigned_trade_detail;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, final int position)
		{
			VTaskUserAcceptBean.VTaskUserAcceptOneBean taskBean = sList.get(position);

			// 头像
			ImageView avatarImageView = holder.get(R.id.circle_detail_avatar);
			Glide.with(TaskAssignedTradeDetailActivity.this).load(taskBean.getUser_logo()).error(R.drawable.global_load_avatar).into(avatarImageView);

			// 昵称
			holder.setText(R.id.tv_trade_detail_name, taskBean.getUser_nickname());

			// 性别
			if (taskBean.getUser_sex().equals(HttpEnum.UserSex.Boy))
			{
				holder.setImageResource(R.id.iv_trade_detail_sex, R.drawable.global_sex_boy).setVisibility(View.VISIBLE);
			}
			else if (taskBean.getUser_sex().equals(HttpEnum.UserSex.Girl))
			{
				holder.setImageResource(R.id.iv_trade_detail_sex, R.drawable.global_sex_girl).setVisibility(View.VISIBLE);
			}
			else
			{
				holder.get(R.id.iv_trade_detail_sex).setVisibility(View.GONE);
			}

			// 等级
			ImageView levelImageView = holder.get(R.id.iv_trade_detail_info);
			Glide.with(TaskAssignedTradeDetailActivity.this).load(taskBean.getLevel_url()).into(levelImageView);

			// 简介

			// 标签
			FlowLayout flowLayout = holder.get(R.id.flow_layout_trade_detail);
			FlowWidget flowWidget = new FlowWidget(TaskAssignedTradeDetailActivity.this, flowLayout);
			flowWidget.setDataList(Arrays.asList("演员", "模特"));
		}
	}
}
