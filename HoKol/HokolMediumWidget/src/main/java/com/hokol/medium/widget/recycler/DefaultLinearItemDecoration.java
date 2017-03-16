package com.hokol.medium.widget.recycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 1,自定义 线性分割线
 * 2,自定义最后一个是否绘制
 *
 * @author yline 2017/3/3 --> 18:30
 * @version 1.0.0
 */
public class DefaultLinearItemDecoration extends RecyclerView.ItemDecoration
{
	private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

	private Drawable mDivider;

	public DefaultLinearItemDecoration(Context context)
	{
		if (-1 != getDividerResourceId())
		{
			mDivider = ContextCompat.getDrawable(context, getDividerResourceId());
		}
		else
		{
			final TypedArray a = context.obtainStyledAttributes(ATTRS);
			mDivider = a.getDrawable(0);
			a.recycle();
		}
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state)
	{
		super.onDraw(c, parent, state);

		if (getOrientation() == LinearLayoutManager.VERTICAL)
		{
			drawVertical(c, parent);
		}
		else if (getOrientation() == LinearLayoutManager.HORIZONTAL)
		{
			drawHorizontal(c, parent);
		}
	}

	private void drawVertical(Canvas c, RecyclerView parent)
	{
		final int left = parent.getPaddingLeft();
		final int right = parent.getWidth() - parent.getPaddingRight();
		final int childTotalCount = parent.getAdapter().getItemCount();
		final int childCount = parent.getChildCount();

		int currentPosition;
		for (int i = 0; i < childCount; i++)
		{
			final View child = parent.getChildAt(i);
			currentPosition = parent.getChildAdapterPosition(child);

			if (isDrawDivider(childTotalCount, currentPosition))
			{
				final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
				final int top = child.getBottom() + params.bottomMargin;
				final int bottom = top + mDivider.getIntrinsicHeight();
				mDivider.setBounds(left, top, right, bottom);
				mDivider.draw(c);
			}
		}
	}

	private void drawHorizontal(Canvas c, RecyclerView parent)
	{
		final int top = parent.getPaddingTop();
		final int bottom = parent.getHeight() - parent.getPaddingBottom();
		final int childCount = parent.getChildCount();
		final int childTotalCount = parent.getAdapter().getItemCount();

		int currentPosition;
		for (int i = 0; i < childCount; i++)
		{
			final View child = parent.getChildAt(i);
			currentPosition = parent.getChildAdapterPosition(child);

			if (isDrawDivider(childTotalCount, currentPosition))
			{
				final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
				final int left = child.getRight() + params.rightMargin;
				final int right = left + mDivider.getIntrinsicHeight();
				mDivider.setBounds(left, top, right, bottom);
				mDivider.draw(c);
			}
		}
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
	{
		super.getItemOffsets(outRect, view, parent, state);

		final int currentPosition = parent.getChildAdapterPosition(view);
		final int totalCount = parent.getAdapter().getItemCount();
		if (isDrawDivider(totalCount, currentPosition))
		{
			if (getOrientation() == LinearLayoutManager.VERTICAL)
			{
				outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
			}
			else if (getOrientation() == LinearLayoutManager.HORIZONTAL)
			{
				outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
			}
		}
	}

	/**
	 * 判断是否绘制分割线
	 *
	 * @param totalCount
	 * @param currentPosition
	 * @return
	 */
	private boolean isDrawDivider(int totalCount, int currentPosition)
	{
		if (getHeadNumber() > currentPosition)
		{
			return false;
		}

		if (isLastDivideDisappear() && (currentPosition == totalCount - 1))
		{
			return false;
		}

		return true;
	}

	/* ------------------------------------ 提供重写的参数 ---------------------------------------- */

	/**
	 * 方向
	 *
	 * @return
	 */
	protected int getOrientation()
	{
		return LinearLayoutManager.VERTICAL;
	}

	/**
	 * 最后一个分割线是否绘制
	 *
	 * @return
	 */
	protected boolean isLastDivideDisappear()
	{
		return true;
	}

	/**
	 * 分割线资源
	 *
	 * @return
	 */
	protected int getDividerResourceId()
	{
		return -1;
	}

	/**
	 * 添加的头部的数量
	 *
	 * @return
	 */
	protected int getHeadNumber()
	{
		return 0;
	}


}
