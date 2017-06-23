package com.hokol.adapter;

import android.view.View;

import com.hokol.R;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.bean.VTaskUserDeliveredBean;
import com.yline.view.recycler.adapter.CommonRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;

/**
 * 已投递任务
 *
 * @author yline 2017/6/23 -- 10:06
 * @version 1.0.0
 */
public class TaskDeliveredAdapter extends CommonRecyclerAdapter<VTaskUserDeliveredBean.VTaskUserDeliveredOneBean>
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
		int status = sList.get(position).getStatus();
		HttpEnum.DeliveredStatus deliveredStatus = HttpEnum.getDeliveredStatus(status);
		onBindViewClick(viewHolder, deliveredStatus);
	}

	private void onBindViewClick(RecyclerViewHolder viewHolder, HttpEnum.DeliveredStatus deliveredStatus)
	{
		// 待报名
		if (deliveredStatus.equals(HttpEnum.DeliveredStatus.ToBeHired) || deliveredStatus.equals(HttpEnum.DeliveredStatus.ToBeOrder))
		{
			viewHolder.get(R.id.ll_task_delivered_start).setVisibility(View.VISIBLE);
			viewHolder.setOnClickListener(R.id.tv_item_task_delivered_sign_cancel, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != deliveredSignCallback)
					{
						deliveredSignCallback.onSignCancelClick(v);
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
						deliveredSignCallback.onSignConfirmClick(v);
					}
				}
			});
		}
		else
		{
			viewHolder.get(R.id.ll_task_delivered_start).setVisibility(View.INVISIBLE);
		}

		// 待交易
		if (deliveredStatus.equals(HttpEnum.DeliveredStatus.ToBeTrade))
		{
			viewHolder.get(R.id.ll_task_delivered_trade).setVisibility(View.VISIBLE);
			viewHolder.setOnClickListener(R.id.tv_item_task_delivered_trade_failed, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != deliveredTradeCallback)
					{
						deliveredTradeCallback.onTradeFailedClick(v);
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
						deliveredTradeCallback.onTradeFinishedClick(v);
					}
				}
			});
		}
		else
		{
			viewHolder.get(R.id.ll_task_delivered_trade).setVisibility(View.INVISIBLE);
		}

		// 待评论
		if (deliveredStatus.equals(HttpEnum.DeliveredStatus.ToBeEvaluate))
		{
			viewHolder.get(R.id.ll_task_delivered_finish).setVisibility(View.VISIBLE);
			viewHolder.setOnClickListener(R.id.tv_item_task_delivered_evaluate_delete, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != deliveredEvaluateCallback)
					{
						deliveredEvaluateCallback.onEvaluateDeleteClick(v);
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
						deliveredEvaluateCallback.onEvaluateAppealClick(v);
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
						deliveredEvaluateCallback.onEvaluateClick(v);
					}
				}
			});
		}
		else
		{
			viewHolder.get(R.id.ll_task_delivered_finish).setVisibility(View.INVISIBLE);
		}
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
		void onSignCancelClick(View view);

		/**
		 * 确认接单
		 *
		 * @param view
		 */
		void onSignConfirmClick(View view);
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
		void onTradeFailedClick(View view);

		/**
		 * 任务完成
		 *
		 * @param view
		 */
		void onTradeFinishedClick(View view);
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
		void onEvaluateDeleteClick(View view);

		/**
		 * 维权申诉
		 *
		 * @param view
		 */
		void onEvaluateAppealClick(View view);

		/**
		 * 用户评价
		 *
		 * @param view
		 */
		void onEvaluateClick(View view);
	}
}
