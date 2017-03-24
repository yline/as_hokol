package com.hokol.medium.widget.labellayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.base.common.BaseViewGroup;
import com.hokol.medium.widget.R;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends BaseViewGroup
{
	public enum LabelGravity
	{
		LEFT(-1), CENTER(0), RIGHT(1);

		private final int value;

		LabelGravity(int value)
		{
			this.value = value;
		}
	}

	private List<List<View>> mAllViews = new ArrayList<>();

	private List<Integer> mLineHeight = new ArrayList<>();

	private List<Integer> mLineWidth = new ArrayList<>();

	private int mGravity;

	private List<View> lineViews = new ArrayList<>();

	private int maxCountEachLine = -1;

	private int tempCountEachLine = 0;

	public FlowLayout(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.WidgetLabelLayout);
		mGravity = ta.getInt(R.styleable.WidgetLabelLayout_label_gravity, LabelGravity.LEFT.value);
		maxCountEachLine = ta.getInt(R.styleable.WidgetLabelLayout_label_max_count_each_line, -1);
		ta.recycle();
	}

	public FlowLayout(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public FlowLayout(Context context)
	{
		this(context, null);
	}

	public void setLabelGravity(LabelGravity gravity)
	{
		this.mGravity = gravity.value;
	}

	public void setMaxCountEachLine(int maxCountEachLine)
	{
		this.maxCountEachLine = maxCountEachLine;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int sizeWidth = View.MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = View.MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = View.MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = View.MeasureSpec.getMode(heightMeasureSpec);

		// wrap_content, 总高度
		int width = 0;
		int height = 0;

		// 每一行的高度，考虑对齐的问题
		int lineWidth = 0;
		int lineHeight = 0;

		int cCount = getChildCount();

		for (int i = 0; i < cCount; i++)
		{
			View child = getChildAt(i);
			// 如果，view不可见, 则跳过
			if (child.getVisibility() == View.GONE)
			{
				if (i == cCount - 1)
				{
					width = Math.max(lineWidth, width);
					height += lineHeight;
				}
				continue;
			}
			// 计算大小，获取子控件的宽高
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();

			int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
			int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

			// 如果线宽+子控件宽度，大于剩余宽度，则换行； 否则，依序排列
			if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight())
			{
				width = Math.max(width, lineWidth);
				lineWidth = childWidth;

				height += lineHeight;
				lineHeight = childHeight;

				tempCountEachLine = 1;
			}
			else if (tempCountEachLine == maxCountEachLine) // 如果超过个数，换行
			{
				width = Math.max(width, lineWidth);
				lineWidth = childWidth;

				height += lineHeight;
				lineHeight = childHeight;

				tempCountEachLine = 1;
			}
			else // 正常计算
			{
				lineWidth += childWidth;
				lineHeight = Math.max(lineHeight, childHeight);

				tempCountEachLine += 1;
			}

			// 最后一个，特殊处理
			if (i == cCount - 1)
			{
				width = Math.max(lineWidth, width);
				height += lineHeight;
			}
		}

		tempCountEachLine = 0; // 结束之后，清零，给onLayout使用
		int setWidth = (modeWidth == View.MeasureSpec.EXACTLY) ? sizeWidth : width + getPaddingLeft() + getPaddingRight();
		int setHeight = (modeHeight == View.MeasureSpec.EXACTLY) ? sizeHeight : height + getPaddingTop() + getPaddingBottom();

		setMeasuredDimension(setWidth, setHeight);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		mAllViews.clear();
		mLineHeight.clear();
		mLineWidth.clear();
		lineViews.clear();

		int width = getWidth();

		int lineWidth = 0;
		int lineHeight = 0;

		int cCount = getChildCount();

		for (int i = 0; i < cCount; i++)
		{
			View child = getChildAt(i);
			// 如果视图不可见，则跳过
			if (child.getVisibility() == View.GONE)
			{
				continue;
			}
			ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();

			// 获取大小
			int childWidth = child.getMeasuredWidth();
			int childHeight = child.getMeasuredHeight();

			// 满足缓茫条件，则换行
			if ((childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - getPaddingLeft() - getPaddingRight()) || tempCountEachLine == maxCountEachLine)
			{
				mLineHeight.add(lineHeight);
				mAllViews.add(lineViews);
				mLineWidth.add(lineWidth);

				lineWidth = 0;
				lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
				lineViews = new ArrayList<>();

				tempCountEachLine = 0;
			}

			tempCountEachLine += 1;
			lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
			lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
			lineViews.add(child);
		}
		mLineHeight.add(lineHeight);
		mLineWidth.add(lineWidth);
		mAllViews.add(lineViews);

		int left = getPaddingLeft();
		int top = getPaddingTop();

		int lineNum = mAllViews.size();

		for (int i = 0; i < lineNum; i++)
		{
			lineViews = mAllViews.get(i);
			lineHeight = mLineHeight.get(i);

			// set gravity
			int currentLineWidth = this.mLineWidth.get(i);
			if (this.mGravity == LabelGravity.LEFT.value)
			{
				left = getPaddingLeft();
			}
			else if (this.mGravity == LabelGravity.CENTER.value)
			{
				left = (width - currentLineWidth) / 2 + getPaddingLeft();
			}
			else
			{
				left = width - currentLineWidth + getPaddingLeft();
			}

			for (int j = 0; j < lineViews.size(); j++)
			{
				View child = lineViews.get(j);
				if (child.getVisibility() == View.GONE)
				{
					continue;
				}

				ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();

				int lc = left + lp.leftMargin;
				int tc = top + lp.topMargin;
				int rc = lc + child.getMeasuredWidth();
				int bc = tc + child.getMeasuredHeight();

				child.layout(lc, tc, rc, bc);

				left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
			}
			top += lineHeight;
		}

	}

	@Override
	public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs)
	{
		return new ViewGroup.MarginLayoutParams(getContext(), attrs);
	}

	@Override
	protected ViewGroup.LayoutParams generateDefaultLayoutParams()
	{
		return new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	}

	@Override
	protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p)
	{
		return new ViewGroup.MarginLayoutParams(p);
	}
}
