package com.hokol.medium.widget.labellayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hokol.medium.widget.R;

import java.util.Deque;

public class LabelFlowLayout extends FlowLayout implements LabelAdapter.OnDataSetChangedListener, LabelAdapter.OnSelectedChangeListener
{
	private static final String TAG = "TagFlowLayout";

	private LabelAdapter labelAdapter;

	private int mSelectedMax = Integer.MAX_VALUE; // -1为不限制数量

	private int mSelectedMin = -1; // -1为不限制下限

	public LabelFlowLayout(Context context)
	{
		this(context, null);
	}

	public LabelFlowLayout(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public LabelFlowLayout(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.WidgetLabelLayout);
		mSelectedMax = ta.getInt(R.styleable.WidgetLabelLayout_label_max_select, Integer.MAX_VALUE);
		mSelectedMin = ta.getInt(R.styleable.WidgetLabelLayout_label_min_select, -1);
		ta.recycle();

		setClickable(true);
	}

	/**
	 * 只支持,初始化的时候,设置
	 *
	 * @param maxCount
	 */
	public void setMaxSelectCount(int maxCount)
	{
		this.mSelectedMax = maxCount;
	}

	/**
	 * 只支持,初始化的时候,设置
	 *
	 * @param minCount
	 */
	public void setMinSelectCount(int minCount)
	{
		this.mSelectedMin = minCount;
	}

	public void setAdapter(LabelAdapter adapter)
	{
		this.labelAdapter = adapter;
		this.labelAdapter.setOnDataChangedListener(this);
		this.labelAdapter.setOnSelectedChangeListener(this);
		updateLabelFlowLayout();
		updateLabelState();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		// 将LabelView中的子控件，与父控件的可视化，绑定
		for (int i = 0; i < getChildCount(); i++)
		{
			LabelView labelView = (LabelView) getChildAt(i);
			if (labelView.getVisibility() == View.GONE)
			{
				continue;
			}
			if (labelView.getLabelView().getVisibility() == View.GONE)
			{
				labelView.setVisibility(View.GONE);
			}
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * 修改控件 自身状态(例如：增加、减少、可见、不可见)
	 */
	private void updateLabelFlowLayout()
	{
		removeAllViews();

		int sCount = labelAdapter.getDataSize();

		for (int i = 0; i < sCount; i++)
		{
			final View labelView = labelAdapter.getView(this, labelAdapter.getItem(i), i);
			final LabelView tagViewContainer = new LabelView(getContext());
			final int position = i;

			tagViewContainer.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (LabelFlowLayout.this.isClickable())
					{
						doSelect(tagViewContainer, position);
						labelAdapter.callLabelClick(LabelFlowLayout.this, labelView, position);
					}
				}
			});

			// 确定子控件,跟随父控件状态改变
			labelView.setDuplicateParentStateEnabled(true);
			tagViewContainer.addView(labelView);

			addView(tagViewContainer);
		}
	}

	/**
	 * 更新 LabelFlowLayout中Label的状态
	 */
	private void updateLabelState()
	{
		if (null != labelAdapter)
		{
			// 更新所有 Label的状态
			Deque<Integer> deque = labelAdapter.getSelectedList();
			int sCount = labelAdapter.getDataSize();

			for (int i = 0; i < sCount; i++)
			{
				if (deque.contains(i) && labelAdapter.getSelectedSize() <= mSelectedMax)
				{
					LabelView labelView = (LabelView) getChildAt(i);
					doSelectState(labelView, true);
				}
				else
				{
					LabelView labelView = (LabelView) getChildAt(i);
					doSelectState(labelView, false);
				}
			}

			labelAdapter.callLabelSelected();
		}
		else
		{
			Log.e(TAG, "updateLabelState labelAdapter is null");
		}
	}

	/**
	 * 移除超出边界的 Label的状态; 不更新UI
	 */
	private void removeOverLabelState()
	{
		if (null != labelAdapter)
		{
			Deque<Integer> deque = labelAdapter.getSelectedList();
			int sCount = labelAdapter.getDataSize();

			int length = deque.size();
			Integer[] selectArray = new Integer[length];
			selectArray = deque.toArray(selectArray);

			for (int i = 0; i < length; i++)
			{
				if (selectArray[i] >= sCount)
				{
					labelAdapter.removeInnerSelectedPosition(selectArray[i]);
				}
			}
		}
		else
		{
			Log.e(TAG, "removeOverLabelState labelAdapter is null");
		}
	}

	private void doSelect(LabelView child, int position)
	{
		if (!child.isChecked())
		{
			// 处理max_select=1的情况
			if (labelAdapter.getSelectedSize() >= mSelectedMax)
			{
				Integer preIndex = labelAdapter.getSelectedFirst();
				LabelView pre = (LabelView) getChildAt(preIndex);

				doSelectState(pre, false);
				doSelectState(child, true);

				labelAdapter.removeInnerSelectedPosition(preIndex);
				labelAdapter.addInnerSelectedPosition(position);
				labelAdapter.callLabelSelected();
			}
			else
			{
				doSelectState(child, true);
				labelAdapter.addInnerSelectedPosition(position);
				labelAdapter.callLabelSelected();
			}
		}
		else
		{
			// 小于最小值,则跳过
			if (mSelectedMin >= labelAdapter.getSelectedSize())
			{
				return;
			}
			doSelectState(child, false);
			labelAdapter.removeInnerSelectedPosition(position);
			labelAdapter.callLabelSelected();
		}
	}

	/**
	 * 修改选择的状态
	 *
	 * @param labelView
	 * @param state
	 */
	private void doSelectState(LabelView labelView, boolean state)
	{
		labelView.setChecked(state);
		labelView.setSelected(state);
		labelView.setPressed(state);
	}

	@Override
	public void onDataChanged()
	{
		updateLabelFlowLayout();
		removeOverLabelState();
		updateLabelState();
	}

	@Override
	public void onSelectedChanged()
	{
		updateLabelState();
	}

	/* &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 以下是别人的,咋就别读了吧 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& */

	private static final String KEY_CHOOSE_POS = "key_choose_pos";

	private static final String KEY_DEFAULT = "key_default";

	@Override
	protected Parcelable onSaveInstanceState()
	{
		Bundle bundle = new Bundle();
		bundle.putParcelable(KEY_DEFAULT, super.onSaveInstanceState());

		String selectPos = "";
		if (labelAdapter.getSelectedSize() > 0)
		{
			Deque<Integer> deque = labelAdapter.getSelectedList();
			for (int key : deque)
			{
				selectPos += key + "|";
			}
			selectPos = selectPos.substring(0, selectPos.length() - 1);
		}
		bundle.putString(KEY_CHOOSE_POS, selectPos);
		return bundle;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state)
	{
		if (state instanceof Bundle)
		{
			Bundle bundle = (Bundle) state;
			String mSelectPos = bundle.getString(KEY_CHOOSE_POS);
			if (!TextUtils.isEmpty(mSelectPos))
			{
				String[] split = mSelectPos.split("\\|");
				for (String pos : split)
				{
					int index = Integer.parseInt(pos);
					labelAdapter.addInnerSelectedPosition(index);
				}

				updateLabelState();
			}
			super.onRestoreInstanceState(bundle.getParcelable(KEY_DEFAULT));
			return;
		}
		super.onRestoreInstanceState(state);
	}


}
