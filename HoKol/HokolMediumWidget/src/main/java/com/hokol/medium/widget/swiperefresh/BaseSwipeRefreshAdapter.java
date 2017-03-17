package com.hokol.medium.widget.swiperefresh;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * SuperSwipeRefreshLayout
 * 1，下拉刷新
 * 2，上拉加载
 *
 * @param <TargetView> 目标数据类型
 */
public abstract class BaseSwipeRefreshAdapter
{
	/**
	 * 操作开始
	 *
	 * @param enable
	 */
	protected void onStart(boolean enable)
	{

	}

	/**
	 * 人为操作过程
	 *
	 * @param distance
	 */
	protected void onDistance(int distance)
	{

	}

	/**
	 * 正在进行动画
	 */
	protected void onAnimating()
	{

	}

	/**
	 * 返回 被加载的控件
	 *
	 * @return
	 */
	@NonNull
	protected abstract View getView();

	/**
	 * 子View是否跟随,手指的滑动,而移动
	 * 【目前只有头布局实现了】
	 *
	 * @return
	 */
	protected boolean isTargetScroll()
	{
		return false;
	}

	/**
	 * 设置，背景布局颜色
	 * 【目前只有头布局实现了】
	 *
	 * @return
	 */
	protected int getBackgroundResource()
	{
		return android.R.color.holo_red_light;
	}
}
