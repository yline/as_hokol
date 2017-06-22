package com.hokol.adapter;

import android.view.View;

import com.hokol.R;
import com.hokol.medium.http.bean.VTaskUserPublishedBean;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
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

	@Override
	public int getItemRes()
	{
		return R.layout.item_task_assigned;
	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder viewHolder, int position)
	{
		viewHolder.get(R.id.ll_task_assigned_start).setVisibility(View.INVISIBLE);
		viewHolder.get(R.id.ll_task_assigned_trade).setVisibility(View.INVISIBLE);
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
