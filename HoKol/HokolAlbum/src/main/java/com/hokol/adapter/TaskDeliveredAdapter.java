package com.hokol.adapter;

import android.view.View;

import com.hokol.R;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.bean.VTaskUserDeliveredBean;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;

/**
 * 已投递任务
 *
 * @author yline 2017/6/23 -- 10:06
 * @version 1.0.0
 */
public class TaskDeliveredAdapter extends WidgetRecyclerAdapter<VTaskUserDeliveredBean.VTaskUserDeliveredOneBean>
{
	private OnTaskDeliveredSignCallback deliveredSignCallback;

	private OnTaskDeliveredTradeCallback deliveredTradeCallback;

	private OnTaskDeliveredEvaluateCallback deliveredEvaluateCallback;

	@Override
	public int getItemRes()
	{
		return R.layout.item_task_delivered;
	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder viewHolder, int position)
	{
		super.onBindViewHolder(viewHolder, position);

		int status = sList.get(position).getStatus();
		HttpEnum.DeliveredStatus deliveredStatus = HttpEnum.getDeliveredStatus(status);
		onBindSignViewClick(viewHolder, sList.get(position), deliveredStatus);
		onBindWorkViewClick(viewHolder, sList.get(position), deliveredStatus);
		onBindPassViewClick(viewHolder, sList.get(position), deliveredStatus);
	}
	
	/**
	 * 待报名
	 */
	private void onBindSignViewClick(RecyclerViewHolder viewHolder, final VTaskUserDeliveredBean.VTaskUserDeliveredOneBean bean, HttpEnum.DeliveredStatus deliveredStatus)
	{
		if (deliveredStatus.equals(HttpEnum.DeliveredStatus.ToBeHired)) // 待录用
		{
			viewHolder.setText(R.id.tv_item_main_task_state, "已报名");
			viewHolder.get(R.id.ll_task_delivered_start).setVisibility(View.GONE);
		}
		else if (deliveredStatus.equals(HttpEnum.DeliveredStatus.ToBeOrder)) // 已录用，待接单
		{
			viewHolder.setText(R.id.tv_item_main_task_state, "待接单");
			viewHolder.get(R.id.ll_task_delivered_start).setVisibility(View.VISIBLE);
			viewHolder.setOnClickListener(R.id.tv_item_task_delivered_sign_cancel, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != deliveredSignCallback)
					{
						deliveredSignCallback.onSignCancelClick(v, bean.getTask_id());
					}
				}
			});
			viewHolder.setOnClickListener(R.id.tv_item_task_delivered_sign_confirm, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != deliveredSignCallback)
					{
						deliveredSignCallback.onSignConfirmClick(v, bean.getTask_id());
					}
				}
			});
		}
		else if (deliveredStatus.equals(HttpEnum.DeliveredStatus.Refused))
		{
			viewHolder.setText(R.id.tv_item_main_task_state, "已取消接单");
			viewHolder.get(R.id.ll_task_delivered_start).setVisibility(View.GONE);
		}
		else
		{
			viewHolder.get(R.id.ll_task_delivered_start).setVisibility(View.GONE);
		}
	}

	/**
	 * 待交易
	 */
	private void onBindWorkViewClick(RecyclerViewHolder viewHolder, final VTaskUserDeliveredBean.VTaskUserDeliveredOneBean bean, HttpEnum.DeliveredStatus deliveredStatus)
	{
		if (deliveredStatus.equals(HttpEnum.DeliveredStatus.ToBeTrade))
		{
			viewHolder.setText(R.id.tv_item_main_task_state, "待交易");
			viewHolder.get(R.id.ll_task_delivered_trade).setVisibility(View.VISIBLE);
			viewHolder.setOnClickListener(R.id.tv_item_task_delivered_trade_failed, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != deliveredTradeCallback)
					{
						deliveredTradeCallback.onTradeFailedClick(v, bean.getTask_id());
					}
				}
			});
			viewHolder.setOnClickListener(R.id.tv_item_task_delivered_trade_failed, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != deliveredTradeCallback)
					{
						deliveredTradeCallback.onTradeFinishedClick(v, bean.getTask_id());
					}
				}
			});
		}
		else
		{
			viewHolder.get(R.id.ll_task_delivered_trade).setVisibility(View.GONE);
		}
	}
	
	/**
	 * 待评论
	 */
	private void onBindPassViewClick(RecyclerViewHolder viewHolder, final VTaskUserDeliveredBean.VTaskUserDeliveredOneBean bean, HttpEnum.DeliveredStatus deliveredStatus)
	{
		if (deliveredStatus.equals(HttpEnum.DeliveredStatus.ToBeEvaluate))
		{
			viewHolder.setText(R.id.tv_item_main_task_state, "待评价");
			viewHolder.get(R.id.ll_task_delivered_finish).setVisibility(View.VISIBLE);
			viewHolder.setOnClickListener(R.id.tv_item_task_delivered_evaluate_delete, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != deliveredEvaluateCallback)
					{
						deliveredEvaluateCallback.onEvaluateDeleteClick(v, bean.getTask_id());
					}
				}
			});
			viewHolder.setOnClickListener(R.id.tv_item_task_delivered_evaluate_appeal, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != deliveredEvaluateCallback)
					{
						deliveredEvaluateCallback.onEvaluateAppealClick(v, bean.getTask_id());
					}
				}
			});
			viewHolder.setOnClickListener(R.id.tv_item_task_delivered_evaluate, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != deliveredEvaluateCallback)
					{
						deliveredEvaluateCallback.onEvaluateClick(v, bean.getTask_id());
					}
				}
			});
		}
		else
		{
			viewHolder.get(R.id.ll_task_delivered_finish).setVisibility(View.GONE);
		}
	}

	@Override
	public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
	{
		// super.onBindEmptyViewHolder(viewHolder, position);
		viewHolder.getItemView().setVisibility(View.GONE);
	}

	public void setOnDeliveredSignCallback(OnTaskDeliveredSignCallback deliveredSignCallback)
	{
		this.deliveredSignCallback = deliveredSignCallback;
	}

	public void setOnDeliveredTradeCallback(OnTaskDeliveredTradeCallback deliveredTradeCallback)
	{
		this.deliveredTradeCallback = deliveredTradeCallback;
	}

	public void setOnDeliveredEvaluateCallback(OnTaskDeliveredEvaluateCallback deliveredEvaluateCallback)
	{
		this.deliveredEvaluateCallback = deliveredEvaluateCallback;
	}

	/**
	 * 已报名
	 */
	public interface OnTaskDeliveredSignCallback
	{
		/**
		 * 取消接单
		 *
		 * @param view
		 */
		void onSignCancelClick(View view, String taskId);

		/**
		 * 确认接单
		 *
		 * @param view
		 */
		void onSignConfirmClick(View view, String taskId);
	}

	/**
	 * 已接单
	 */
	public interface OnTaskDeliveredTradeCallback
	{
		/**
		 * 任务未完成
		 *
		 * @param view
		 */
		void onTradeFailedClick(View view, String taskId);

		/**
		 * 任务完成
		 *
		 * @param view
		 */
		void onTradeFinishedClick(View view, String taskId);
	}

	/**
	 * 待评价
	 */
	public interface OnTaskDeliveredEvaluateCallback
	{
		/**
		 * 删除任务
		 *
		 * @param view
		 */
		void onEvaluateDeleteClick(View view, String taskId);

		/**
		 * 维权申诉
		 *
		 * @param view
		 */
		void onEvaluateAppealClick(View view, String taskId);

		/**
		 * 用户评价
		 *
		 * @param view
		 */
		void onEvaluateClick(View view, String taskId);
	}
}
