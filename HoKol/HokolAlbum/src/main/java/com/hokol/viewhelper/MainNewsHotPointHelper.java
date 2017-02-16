package com.hokol.viewhelper;

import android.view.View;
import android.widget.LinearLayout;

import com.hokol.R;
import com.hokol.base.utils.UIResizeUtil;

/**
 * Hot页面的Item
 * @author yline 2017/2/15 --> 10:49
 * @version 1.0.0
 */
public class MainNewsHotPointHelper
{
	private ViewHolder viewHolder = new ViewHolder();

	private OnItemClickListener listener;

	public void init(View parentView)
	{
		viewHolder.llOne = (LinearLayout) parentView.findViewById(R.id.ll_hot_point_one);
		viewHolder.llTwo = (LinearLayout) parentView.findViewById(R.id.ll_hot_point_two);
		viewHolder.llThree = (LinearLayout) parentView.findViewById(R.id.ll_hot_point_three);
		viewHolder.llFour = (LinearLayout) parentView.findViewById(R.id.ll_hot_point_four);
		viewHolder.llFive = (LinearLayout) parentView.findViewById(R.id.ll_hot_point_five);
		viewHolder.llSix = (LinearLayout) parentView.findViewById(R.id.ll_hot_point_six);
		viewHolder.llSeven = (LinearLayout) parentView.findViewById(R.id.ll_hot_point_seven);

		UIResizeUtil.build().setWidth(160).commit(viewHolder.llOne);
		UIResizeUtil.build().setWidth(160).commit(viewHolder.llTwo);
		UIResizeUtil.build().setWidth(160).commit(viewHolder.llThree);
		UIResizeUtil.build().setWidth(160).commit(viewHolder.llFour);
		UIResizeUtil.build().setWidth(160).commit(viewHolder.llFive);
		UIResizeUtil.build().setWidth(160).commit(viewHolder.llSix);
		UIResizeUtil.build().setWidth(160).commit(viewHolder.llSeven);

		ItemClickListener listener = new ItemClickListener();

		viewHolder.llOne.setOnClickListener(listener);
		viewHolder.llOne.setTag(0);
		viewHolder.llTwo.setOnClickListener(listener);
		viewHolder.llTwo.setTag(1);
		viewHolder.llThree.setOnClickListener(listener);
		viewHolder.llThree.setTag(2);
		viewHolder.llFour.setOnClickListener(listener);
		viewHolder.llFour.setTag(3);
		viewHolder.llFive.setOnClickListener(listener);
		viewHolder.llFive.setTag(4);
		viewHolder.llSix.setOnClickListener(listener);
		viewHolder.llSix.setTag(5);
		viewHolder.llSeven.setOnClickListener(listener);
		viewHolder.llSeven.setTag(6);
	}

	public void setListener(OnItemClickListener listener)
	{
		this.listener = listener;
	}

	private class ItemClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			int position = (int) v.getTag();

			if (null != listener)
			{
				listener.onItemClick(position);
			}
		}
	}

	private class ViewHolder
	{
		private LinearLayout llOne, llTwo, llThree, llFour, llFive, llSix, llSeven;
	}

	public interface OnItemClickListener
	{
		void onItemClick(int position);
	}
}
