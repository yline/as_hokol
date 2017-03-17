package com.hokol.medium.widget.swiperefresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * SuperSwipeRefreshLayout
 * 1，下拉刷新
 * 2，上拉加载
 */
public abstract class BaseSwipeRefreshAdapter
{
	protected Context sContext;

	public BaseSwipeRefreshAdapter(Context context)
	{
		this.sContext = context;
	}

	/* ----------------- 提供四个方法可被重写 ------------------ */

	protected abstract void onCreate(float dragDistance, float targetDistance);

	protected abstract void onStart(boolean enable);

	protected void onAnimating()
	{
	}

	@NonNull
	protected abstract View getView(Context context);

	/**
	 * 子View是否跟随,手指的滑动,而移动
	 * 【目前只有头布局实现了】
	 *
	 * @return
	 */
	public boolean isTargetScroll()
	{
		return false;
	}

	/**
	 * 设置，背景布局颜色
	 * 【目前只有头布局实现了】
	 *
	 * @return
	 */
	public int getBackgroundResource()
	{
		return android.R.color.transparent;
	}

	/* ---------------------------------------- 被SuperSwipeRefreshLayout调用;一般不重写 ---------------------------------------------------- */
	private SuperSwipeRefreshLayout.OnSwipeListener refreshListener;

	void setSwipeAnimatingListener(SuperSwipeRefreshLayout.OnSwipeListener refreshListener)
	{
		this.refreshListener = refreshListener;
	}

	/**
	 * 拖出界面
	 *
	 * @param dragDistance   手指拖动距离
	 * @param targetDistance 目标距离
	 */
	void create(float dragDistance, float targetDistance)
	{
		onCreate(dragDistance, targetDistance);
	}

	/**
	 * 正式开始刷新
	 *
	 * @param enable
	 */
	void start(boolean enable)
	{
		onStart(enable);
	}

	void animating()
	{
		onAnimating();
		if (null != refreshListener)
		{
			refreshListener.onAnimating();
		}
	}

	/**
	 * 创建布局
	 *
	 * @return
	 */
	View getView()
	{
		return getView(sContext);
	}
}
