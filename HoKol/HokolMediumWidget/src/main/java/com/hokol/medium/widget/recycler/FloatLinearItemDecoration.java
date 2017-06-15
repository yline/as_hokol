package com.hokol.medium.widget.recycler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.simple.SimpleLinearItemDecoration;

import java.util.HashMap;
import java.util.Map;

public class FloatLinearItemDecoration extends SimpleLinearItemDecoration
{
	private Map<Integer, String> keys = new HashMap<>();

	private Context sContext;

	private Paint mTextPaint;

	private Paint mBackgroundPaint;

	public FloatLinearItemDecoration(Context context)
	{
		super(context);

		this.sContext = context;

		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		initTextPaint(mTextPaint);

		mBackgroundPaint = new Paint();
		mBackgroundPaint.setAntiAlias(true);
		initBackgroundPaint(mBackgroundPaint);
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state)
	{
		super.onDrawOver(c, parent, state);
		if (!isShowHeadFloating())
		{
			return;
		}

		int firstVisiblePos = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
		int realPosition = firstVisiblePos - getHeadNumber();
		if (realPosition == RecyclerView.NO_POSITION)
		{
			return;
		}

		String title = getTitle(realPosition);
		if (TextUtils.isEmpty(title))
		{
			return;
		}

		boolean flag = false;
		String nextTitle = getTitle(realPosition + 1);
		if (!TextUtils.isEmpty(nextTitle) && !title.equals(nextTitle))
		{
			// 说明是当前组最后一个元素，但不一定碰撞了
			View child = parent.findViewHolderForAdapterPosition(firstVisiblePos).itemView;

			if (child.getTop() + child.getMeasuredHeight() + sDivider.getIntrinsicHeight() < getTitleHeight())
			{
				// 进一步检测碰撞
				c.save();// 保存画布当前的状态
				flag = true;
				c.translate(0, child.getTop() + child.getMeasuredHeight() - getTitleHeight() + sDivider.getIntrinsicHeight());//负的代表向上
			}
		}

		int parentLeft = parent.getPaddingLeft();
		int parentRight = parent.getWidth() - parent.getPaddingRight();
		int parentTop = parent.getPaddingTop();
		int parentBottom = parent.getBottom() - parent.getPaddingBottom();
		c.drawRect(parentLeft, parentTop, parentRight, parentTop + getTitleHeight(), mBackgroundPaint);
		Rect bgRect = new Rect(parentLeft, parentTop, parentRight, parentTop + getTitleHeight());
		c.drawText(title, bgRect.left + getTextMarginLeft(),
				bgRect.centerY() + ((int) mTextPaint.getTextSize() >> 1), mTextPaint);

		if (flag) // 还原画布为初始状态
		{
			c.restore();
		}
	}

	@Override
	public void setVerticalOffsets(Rect outRect, Drawable divider, int currentPosition)
	{
		if (keys.containsKey(currentPosition - getHeadNumber()))
		{
			outRect.set(0, getTitleHeight() + divider.getIntrinsicHeight(), 0, divider.getIntrinsicHeight());
		}
		else
		{
			super.setVerticalOffsets(outRect, divider, currentPosition);
		}
	}

	@Override
	public void drawVerticalDivider(Canvas c, Drawable divide, int currentPosition, int childLeft, int childTop, int childRight, int childBottom)
	{
		if (keys.containsKey(currentPosition - getHeadNumber()))
		{
			Rect bgRect = new Rect(childLeft, childTop - getTitleHeight() - divide.getIntrinsicHeight(), childRight, childTop - divide.getIntrinsicHeight());

			int textLeft = bgRect.left + getTextMarginLeft();
			int textBottom = bgRect.centerY() + ((int) mTextPaint.getTextSize() >> 1);
			c.drawRect(bgRect, mBackgroundPaint);
			c.drawText(keys.get(currentPosition), textLeft, textBottom, mTextPaint);

			divide.setBounds(childLeft, childTop - divide.getIntrinsicHeight(), childRight, childTop);
			divide.draw(c);
		}
		super.drawVerticalDivider(c, divide, currentPosition, childLeft, childTop, childRight, childBottom);
	}


	/**
	 * *如果该位置没有，则往前循环去查找标题，找到说明该位置属于该分组
	 *
	 * @param position
	 * @return
	 */
	private String getTitle(int position)
	{
		while (position >= 0)
		{
			if (keys.containsKey(position))
			{
				return keys.get(position);
			}
			position--;
		}
		return null;
	}

	public void initTextPaint(Paint textPaint)
	{
		textPaint.setTextSize(UIScreenUtil.dp2px(sContext, 18));
		textPaint.setColor(Color.BLACK);
	}

	public void initBackgroundPaint(Paint backgroundPaint)
	{
		backgroundPaint.setColor(0xFFF2F2F2);
	}

	public int getTitleHeight()
	{
		return UIScreenUtil.dp2px(sContext, 42);
	}

	public int getTextMarginLeft()
	{
		return UIScreenUtil.dp2px(sContext, 14);
	}

	public void setKeys(Map<Integer, String> keys)
	{
		this.keys.clear();
		this.keys.putAll(keys);
	}

	public boolean isShowHeadFloating()
	{
		return true;
	}
}
