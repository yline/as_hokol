package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.application.DeleteConstant;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskUserAcceptBean;
import com.hokol.medium.http.bean.WTaskActionMasterTradeBean;
import com.hokol.medium.http.bean.WTaskUserAcceptBean;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.utils.UIScreenUtil;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 确认交易 界面
 *
 * @author yline 2017/6/2 -- 16:42
 * @version 1.0.0
 */
public class TaskAssignedTradeSureDetailActivity extends BaseAppCompatActivity
{
	private static final String KeyTaskId = "taskId";

	private TradeSureDetailAdapter tradeSureDetailAdapter;

	private String taskId;

	public static void actionStart(Context context, String taskId)
	{
		context.startActivity(new Intent(context, TaskAssignedTradeSureDetailActivity.class).putExtra(KeyTaskId, taskId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assigned_trade_sure_detail);

		initView();
		initData();

		findViewById(R.id.iv_task_assigned_trade_sure_detail_cancel).setOnClickListener(new View.OnClickListener()
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
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_task_assigned_trade_sure_detail);
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

		tradeSureDetailAdapter = new TradeSureDetailAdapter();
		recyclerView.setAdapter(tradeSureDetailAdapter);

		// 头部
		View headView = new View(this);
		headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(this, 4)));
		headView.setBackgroundColor(ContextCompat.getColor(this, R.color.hokolGrayLight));
		tradeSureDetailAdapter.addHeadView(headView);

		// 提交按钮
		findViewById(R.id.tv_task_assigned_trade_sure_detail_commit).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = AppStateManager.getInstance().getUserLoginId(TaskAssignedTradeSureDetailActivity.this);
				XHttpUtil.doTaskActionMasterTrade(new WTaskActionMasterTradeBean(taskId, userId, tradeSureDetailAdapter.getRequestList()), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						SDKManager.toast("确认成功");
						finish();
					}
				});
			}
		});
	}

	private void initData()
	{
		taskId = getIntent().getStringExtra(KeyTaskId);
		XHttpUtil.doTaskUserAcceptDetail(new WTaskUserAcceptBean(taskId, 0, DeleteConstant.defaultNumberSuper), new XHttpAdapter<VTaskUserAcceptBean>()
		{
			@Override
			public void onSuccess(VTaskUserAcceptBean vTaskUserAcceptBean)
			{
				List<VTaskUserAcceptBean.VTaskUserAcceptOneBean> resultList = vTaskUserAcceptBean.getList();
				if (null != resultList)
				{
					tradeSureDetailAdapter.setDataList(resultList);
					tradeSureDetailAdapter.initRequestList(resultList.size());
				}
			}
		});
	}

	/**
	 * 显示信息 + 修改的信息
	 */
	private class TradeSureDetailAdapter extends WidgetRecyclerAdapter<VTaskUserAcceptBean.VTaskUserAcceptOneBean>
	{
		private WTaskActionMasterTradeBean.WTaskActionMasterTradeInfoBean[] requestArrays;

		public void initRequestList(int count)
		{
			requestArrays = new WTaskActionMasterTradeBean.WTaskActionMasterTradeInfoBean[count];
			Arrays.fill(requestArrays, new WTaskActionMasterTradeBean.WTaskActionMasterTradeInfoBean());
			for (int i = 0; i < count; i++)
			{
				// 初始化请求数据
				requestArrays[i].setConfirm_user_id(sList.get(i).getUser_id());
				requestArrays[i].setConfirm_status(WTaskActionMasterTradeBean.ActionFinished);
			}
		}

		public List<WTaskActionMasterTradeBean.WTaskActionMasterTradeInfoBean> getRequestList()
		{
			if (null != requestArrays)
			{
				return Arrays.asList(requestArrays);
			}
			else
			{
				return new ArrayList<>();
			}
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_task_assigned_trade_sure_detail;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, final int position)
		{
			super.onBindViewHolder(holder, position);

			VTaskUserAcceptBean.VTaskUserAcceptOneBean taskBean = sList.get(position);

			// 头像
			ImageView avatarImageView = holder.get(R.id.circle_sure_detail_avatar);
			Glide.with(TaskAssignedTradeSureDetailActivity.this).load(taskBean.getUser_logo()).error(R.drawable.global_load_avatar).into(avatarImageView);

			// 昵称
			holder.setText(R.id.tv_trade_sure_detail_name, taskBean.getUser_nickname());

			// 性别
			if (taskBean.getUser_sex().equals(HttpEnum.UserSex.Boy))
			{
				holder.setImageResource(R.id.iv_trade_sure_detail_sex, R.drawable.global_sex_boy).setVisibility(View.VISIBLE);
			}
			else if (taskBean.getUser_sex().equals(HttpEnum.UserSex.Girl))
			{
				holder.setImageResource(R.id.iv_trade_sure_detail_sex, R.drawable.global_sex_girl).setVisibility(View.VISIBLE);
			}
			else
			{
				holder.get(R.id.iv_trade_sure_detail_sex).setVisibility(View.GONE);
			}

			// 等级图标
			ImageView levelImageView = holder.get(R.id.iv_trade_sure_detail_level);
			Glide.with(TaskAssignedTradeSureDetailActivity.this).load(taskBean.getLevel_url()).into(levelImageView);

			// 简介
			holder.setText(R.id.tv_trade_sure_detail_brief, taskBean.getUser_sign());

			// 标签
			FlowLayout flowLayout = holder.get(R.id.flow_layout_trade_sure_detail);
			FlowWidget flowWidget = new FlowWidget(TaskAssignedTradeSureDetailActivity.this, flowLayout);
			flowWidget.setDataList(taskBean.getUser_tag());

			CheckBox checkBox = holder.get(R.id.checkBox_trade_sure_detail_phone);
			if (requestArrays[position].getConfirm_status() == WTaskActionMasterTradeBean.ActionFinished)
			{
				checkBox.setChecked(true);
				holder.setText(R.id.tv_trade_sure_detail_phone, "成功").setTextColor(ContextCompat.getColor(TaskAssignedTradeSureDetailActivity.this, R.color.hokolRed));
			}
			else
			{
				checkBox.setChecked(false);
				holder.setText(R.id.tv_trade_sure_detail_phone, "失败").setTextColor(ContextCompat.getColor(TaskAssignedTradeSureDetailActivity.this, R.color.hokolGraySmall));
			}
			checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
			{
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
				{
					int newStatus = isChecked ? WTaskActionMasterTradeBean.ActionFinished : WTaskActionMasterTradeBean.ActionFailed;
					requestArrays[position].setConfirm_status(newStatus);

					// notifyItemChanged(position);
					notifyDataSetChanged();
				}
			});
		}
	}
}
