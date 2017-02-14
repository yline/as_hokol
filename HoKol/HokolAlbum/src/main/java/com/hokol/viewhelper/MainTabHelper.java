package com.hokol.viewhelper;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hokol.R;

/**
 * 初始化 MainActivity 的 底部栏
 * @author yline 2017/2/14 --> 14:25
 * @version 1.0.0
 */
public class MainTabHelper
{
	private static final int COLOR_BEFORE = Color.BLACK;

	private static final int COLOR_AFTER = Color.GREEN;

	private static final int NUMBER_OF_TAB = 5;

	private ViewHolder viewHolder = new ViewHolder();

	private OnTabClickListener listener;

	public MainTabHelper()
	{
	}

	public void initTabView(View parentView)
	{
		initView(parentView);
		initData();

		setTabsClick();
	}

	public void setListener(OnTabClickListener listener)
	{
		this.listener = listener;
	}

	private void initView(View view)
	{
		viewHolder.tvOne = (TextView) view.findViewById(R.id.tv_tab_one);
		viewHolder.tvTwo = (TextView) view.findViewById(R.id.tv_tab_two);
		viewHolder.tvThree = (TextView) view.findViewById(R.id.tv_tab_three);
		viewHolder.tvFour = (TextView) view.findViewById(R.id.tv_tab_four);
		viewHolder.tvFive = (TextView) view.findViewById(R.id.tv_tab_five);

		viewHolder.llOne = (LinearLayout) view.findViewById(R.id.ll_tab_one);
		viewHolder.llTwo = (LinearLayout) view.findViewById(R.id.ll_tab_two);
		viewHolder.llThree = (LinearLayout) view.findViewById(R.id.ll_tab_three);
		viewHolder.llFour = (LinearLayout) view.findViewById(R.id.ll_tab_four);
		viewHolder.llFive = (LinearLayout) view.findViewById(R.id.ll_tab_five);
	}

	private void initData()
	{
		setTextColor(0);
	}

	/** line 点击事件 */
	private void setTabsClick()
	{
		TabClickListener tabListener = new TabClickListener();

		viewHolder.llOne.setOnClickListener(tabListener);
		viewHolder.llOne.setTag(0);
		viewHolder.llTwo.setOnClickListener(tabListener);
		viewHolder.llTwo.setTag(1);
		viewHolder.llThree.setOnClickListener(tabListener);
		viewHolder.llThree.setTag(2);
		viewHolder.llFour.setOnClickListener(tabListener);
		viewHolder.llFour.setTag(3);
		viewHolder.llFive.setOnClickListener(tabListener);
		viewHolder.llFive.setTag(4);
	}

	/**
	 * 设置标签字体颜色
	 * @param position 滑动结束,位置
	 */
	private void setTextColor(int position)
	{
		if (position < 0 || position >= NUMBER_OF_TAB)
		{
			throw new IllegalArgumentException("setTextColor in an error position");
		}

		viewHolder.tvOne.setTextColor(COLOR_BEFORE);
		viewHolder.tvTwo.setTextColor(COLOR_BEFORE);
		viewHolder.tvThree.setTextColor(COLOR_BEFORE);
		viewHolder.tvFour.setTextColor(COLOR_BEFORE);
		viewHolder.tvFive.setTextColor(COLOR_BEFORE);

		switch (position)
		{
			case 0:
				viewHolder.tvOne.setTextColor(COLOR_AFTER);
				break;
			case 1:
				viewHolder.tvTwo.setTextColor(COLOR_AFTER);
				break;
			case 2:
				viewHolder.tvThree.setTextColor(COLOR_AFTER);
				break;
			case 3:
				viewHolder.tvFour.setTextColor(COLOR_AFTER);
				break;
			case 4:
				viewHolder.tvFive.setTextColor(COLOR_AFTER);
				break;
			default:
				break;
		}
	}

	private class TabClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			int position = (Integer) v.getTag();
			setTextColor(position); // 改变字体颜色

			if (null != listener)
			{
				listener.onTabClick(position);
			}
		}
	}

	private static class ViewHolder
	{
		private TextView tvOne, tvTwo, tvThree, tvFour, tvFive;

		private LinearLayout llOne, llTwo, llThree, llFour, llFive;
	}

	public interface OnTabClickListener
	{
		/**
		 * tab点击回调
		 * @param position 点击的位置
		 */
		void onTabClick(int position);
	}
}
