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

	/**
	 * 手势操作时，一直调用
	 *
	 * @param dragDistance   实时改变的内容
	 * @param targetDistance 目标内容
	 */
	protected abstract void onCreating(float dragDistance, float targetDistance);

	/**
	 * 开始动画时,被调用一次
	 */
	protected void onAnimate()
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
	void creating(float dragDistance, float targetDistance)
	{
		onCreating(dragDistance, targetDistance);
	}

	void animate()
	{
		onAnimate();
		if (null != refreshListener)
		{
			refreshListener.onAnimate();
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
