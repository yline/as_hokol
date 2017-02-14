package com.hokol.viewhelper;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.base.utils.UIResizeUtil;

/**
 * Created by yline on 2017/2/14.
 */
public class MainNewsTitleHelper
{
	private static final int COLOR_BEFORE = Color.LTGRAY;

	private static final int COLOR_AFTER = Color.WHITE;

	private static final int NUMBER_OF_TAB = 5;

	private ViewHolder viewHolder = new ViewHolder();

	private final static int LINE_WIDTH = UIResizeUtil.getDesignWidth() / NUMBER_OF_TAB;

	private OnTabClickListener listener;

	private ViewPager viewPager;

	public MainNewsTitleHelper()
	{
	}

	public void initViewPagerView(ViewPager viewPager)
	{
		this.viewPager = viewPager;

		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
				moveTabLine(position, positionOffset);
			}

			@Override
			public void onPageSelected(int position)
			{
				setTextColor(position);

				if (null != listener)
				{
					listener.onTabClick(position);
				}
			}

			@Override
			public void onPageScrollStateChanged(int state)
			{

			}
		});
	}

	public void initTabView(View parentView)
	{
		initView(parentView);
		setTabsClick();

		initData();
	}

	public void setListener(OnTabClickListener listener)
	{
		this.listener = listener;
	}

	private void initView(View view)
	{
		viewHolder.tvOne = (TextView) view.findViewById(R.id.tv_news_one);
		viewHolder.tvTwo = (TextView) view.findViewById(R.id.tv_news_two);
		viewHolder.tvThree = (TextView) view.findViewById(R.id.tv_news_three);
		viewHolder.tvFour = (TextView) view.findViewById(R.id.tv_news_four);
		viewHolder.tvFive = (TextView) view.findViewById(R.id.tv_news_five);

		viewHolder.llOne = (LinearLayout) view.findViewById(R.id.ll_news_one);
		viewHolder.llTwo = (LinearLayout) view.findViewById(R.id.ll_news_two);
		viewHolder.llThree = (LinearLayout) view.findViewById(R.id.ll_news_three);
		viewHolder.llFour = (LinearLayout) view.findViewById(R.id.ll_news_four);
		viewHolder.llFive = (LinearLayout) view.findViewById(R.id.ll_news_five);

		viewHolder.ivLine = (ImageView) view.findViewById(R.id.iv_news_line);
	}

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

	private void initData()
	{
		UIResizeUtil.build().setWidth(LINE_WIDTH).setLeftMargin(0 * LINE_WIDTH).commit(viewHolder.ivLine);
		setTextColor(0);
	}

	private class TabClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			int position = (int) v.getTag();

			viewPager.setCurrentItem(position);
		}
	}

	/**
	 * @param position       滑动时,位置(采取的是退一法)
	 * @param positionOffset 滑动时,偏移量
	 */
	private void moveTabLine(int position, float positionOffset)
	{
		UIResizeUtil.build().setLeftMargin((int) (LINE_WIDTH * (position + positionOffset))).commit(viewHolder.ivLine);
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

	private static class ViewHolder
	{
		private TextView tvOne, tvTwo, tvThree, tvFour, tvFive;

		private LinearLayout llOne, llTwo, llThree, llFour, llFive;

		private ImageView ivLine;
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
