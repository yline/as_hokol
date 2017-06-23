package com.hokol.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.bean.VTaskUserPublishedBean;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.utils.TimeConvertUtil;
import com.yline.view.recycler.holder.RecyclerViewHolder;

/**
 * 已发任务
 *
 * @author yline 2017/6/22 -- 16:47
 * @version 1.0.0
 */
public class TaskAssignedAdapter extends WidgetRecyclerAdapter<VTaskUserPublishedBean.VTaskUserPublishedOneBean>
{
	private OnTaskAssignedSignCallback assignedSignCallback;

	private OnTaskAssignedTradeCallback assignedTradeCallback;

	private OnTaskAssignedEvaluateCallback assignedEvaluateCallback;

	private Context sContext;

	public TaskAssignedAdapter(Context context)
	{
		this.sContext = context;
	}

	@Override
	public int getItemRes()
	{
		return R.layout.item_task_assigned;
	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder viewHolder, int position)
	{
		VTaskUserPublishedBean.VTaskUserPublishedOneBean taskBean = sList.get(position);
		// 初始化数据
		viewHolder.setText(R.id.tv_item_main_task_price, String.format("￥%f × %d", taskBean.getTask_fee(), taskBean.getTask_peo_num()));
		viewHolder.setText(R.id.tv_item_main_task_title, taskBean.getTask_title());

		// 右侧状态
		String rightState = taskBean.getIs_guarantee() == VTaskUserPublishedBean.Guaranteed ? "已担保交易" : "未担保交易";
		viewHolder.setText(R.id.tv_item_main_task_state, rightState);
		
		// 头像
		ImageView avatarImageView = viewHolder.get(R.id.iv_item_main_task_avatar);
		Glide.with(sContext).load(taskBean.getUser_logo()).into(avatarImageView);

		// 用户名
		viewHolder.setText(R.id.tv_item_main_task_user, taskBean.getUser_nickname());

		// 报名 状态
		viewHolder.setText(R.id.tv_item_main_task_user_state, String.format("5人报名，1人录用", taskBean.getJoin_num(), taskBean.getEmployee_num()));

		// 截止时间
		String showTime = TimeConvertUtil.stamp2FormatTime(taskBean.getTask_end_time() * 1000);
		viewHolder.setText(R.id.tv_item_main_task_time, showTime);

		// 初始状态
		int status = taskBean.getStatus();
		HttpEnum.AssignedStatus assignedStatus = HttpEnum.getAssignedStatus(status);
		onBindViewClick(viewHolder, assignedStatus);
	}

	@Override
	public int getEmptyItemRes()
	{
		return super.getEmptyItemRes();
	}

	@Override
	public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
	{
		viewHolder.setText(R.id.tv_loading_cover, "您还没有发布任务哦");
		// viewHolder.setText(R.id.btn_loading_cover, "发布任务");
		viewHolder.getItemView().setVisibility(View.GONE);
	}

	private void onBindViewClick(RecyclerViewHolder viewHolder, HttpEnum.AssignedStatus assignedStatus)
	{
		// 待报名
		if (assignedStatus.equals(HttpEnum.AssignedStatus.ToBeSign))
		{
			viewHolder.get(R.id.ll_task_assigned_start).setVisibility(View.VISIBLE);
			viewHolder.setOnClickListener(R.id.tv_task_assigned_start_cancel, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != assignedSignCallback)
					{
						assignedSignCallback.onSignCancelClick(v);
					}
				}
			});
			viewHolder.setOnClickListener(R.id.tv_task_assigned_start_over, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != assignedSignCallback)
					{
						assignedSignCallback.onSignFinishClick(v);
					}
				}
			});
			viewHolder.setOnClickListener(R.id.tv_task_assigned_start_detail, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != assignedSignCallback)
					{
						assignedSignCallback.onSignDetailClick(v);
					}
				}
			});
		}
		else
		{
			viewHolder.get(R.id.ll_task_assigned_start).setVisibility(View.INVISIBLE);
		}

		// 待交易
		if (assignedStatus.equals(HttpEnum.AssignedStatus.ToBeTrade))
		{
			viewHolder.get(R.id.ll_task_assigned_trade).setVisibility(View.VISIBLE);
			viewHolder.setOnClickListener(R.id.tv_task_assigned_trade_cancel, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != assignedTradeCallback)
					{
						assignedTradeCallback.onTradeCancelClick(v);
					}
				}
			});
			viewHolder.setOnClickListener(R.id.tv_task_assigned_trade_detail, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != assignedTradeCallback)
					{
						assignedTradeCallback.onTradeDetailClick(v);
					}
				}
			});
			viewHolder.setOnClickListener(R.id.tv_task_assigned_trade_sure_detail, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != assignedTradeCallback)
					{
						assignedTradeCallback.onTradeConfirmClick(v);
					}
				}
			});
		}
		else
		{
			viewHolder.get(R.id.ll_task_assigned_trade).setVisibility(View.INVISIBLE);
		}

		// 评论
		if (assignedStatus.equals(HttpEnum.AssignedStatus.ToBeEvaluate))
		{
			viewHolder.get(R.id.ll_task_assigned_finish).setVisibility(View.VISIBLE);
			viewHolder.setOnClickListener(R.id.tv_item_task_assigned_evaluate, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != assignedEvaluateCallback)
					{
						assignedEvaluateCallback.onEvaluateClick(v);
					}
				}
			});
		}
		else
		{
			viewHolder.get(R.id.ll_task_assigned_finish).setVisibility(View.INVISIBLE);
		}
	}

	public void setOnAssignedSignCallback(OnTaskAssignedSignCallback assignedSignCallback)
	{
		this.assignedSignCallback = assignedSignCallback;
	}

	public void setOnAssignedTradeCallback(OnTaskAssignedTradeCallback assignedTradeCallback)
	{
		this.assignedTradeCallback = assignedTradeCallback;
	}

	public void setOnAssignedEvaluateCallback(OnTaskAssignedEvaluateCallback assignedEvaluateCallback)
	{
		this.assignedEvaluateCallback = assignedEvaluateCallback;
	}

	/**
	 * 待报名
	 */
	public interface OnTaskAssignedSignCallback
	{
		/**
		 * 取消任务
		 *
		 * @param view
		 */
		void onSignCancelClick(View view);

		/**
		 * 结束报名
		 *
		 * @param view
		 */
		void onSignFinishClick(View view);

		/**
		 * 报名详情
		 *
		 * @param view
		 */
		void onSignDetailClick(View view);
	}

	/**
	 * 待交易
	 */
	public interface OnTaskAssignedTradeCallback
	{
		/**
		 * 取消交易
		 *
		 * @param view
		 */
		void onTradeCancelClick(View view);

		/**
		 * 交易详情
		 *
		 * @param view
		 */
		void onTradeDetailClick(View view);

		/**
		 * 确认交易
		 *
		 * @param view
		 */
		void onTradeConfirmClick(View view);
	}

	/**
	 * 待评价
	 */
	public interface OnTaskAssignedEvaluateCallback
	{
		/**
		 * 评价
		 *
		 * @param view
		 */
		void onEvaluateClick(View view);
	}
}
