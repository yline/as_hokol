package com.hokol.medium.widget.secondary;

import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hokol.medium.widget.R;
import com.yline.view.common.CommonRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstRecyclerAdapter extends CommonRecyclerAdapter<String>
{
	private int oldPosition = -1;

	private boolean[] isSelected;

	private SecondRecyclerAdapter secondAdapter;

	public FirstRecyclerAdapter(SecondRecyclerAdapter secondAdapter)
	{
		this.secondAdapter = secondAdapter;
	}

	@Override
	public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		return super.onCreateViewHolder(parent, viewType);
	}

	/**
	 * 初始化选择项
	 *
	 * @param firstSelectedStr   第一列已选择项
	 * @param secondTotalList    第二列所有项
	 * @param secondSelectedList 第二列已选择项
	 */
	public void initSelect(String firstSelectedStr, List<String> secondTotalList, List<String> secondSelectedList)
	{
		isSelected = new boolean[getItemCount()];
		Arrays.fill(isSelected, false);
		if (!TextUtils.isEmpty(firstSelectedStr))
		{
			for (int i = 0; i < sList.size(); i++)
			{
				if (firstSelectedStr.equals(sList.get(i)))
				{
					oldPosition = i;
					isSelected[i] = true;
					break;
				}
			}

			if (null != secondTotalList)
			{
				List<String> tempSecondList = new ArrayList<>(secondTotalList);
				secondAdapter.setDataList(tempSecondList);
				secondAdapter.initSelect(secondSelectedList);
			}
		}
	}

	public String getSelectedString()
	{
		if (-1 == oldPosition)
		{
			return null;
		}
		else
		{
			return sList.get(oldPosition);
		}
	}

	@Override
	public int getItemRes()
	{
		return getItemResource();
	}

	@Override
	public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
	{
		final TextView textView = viewHolder.get(R.id.tv_item_first);
		textView.setText(sList.get(position));
		if (isSelected[position])
		{
			textView.setBackgroundColor(getColorBgSelected());
			textView.setTextColor(getColorTextSelected());
		}
		else
		{
			textView.setBackgroundColor(getColorBgUnselected());
			textView.setTextColor(getColorTextUnselected());
		}

		textView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (oldPosition != position)
				{
					// 点击事件
					if (null != onFirstItemClickListener)
					{
						List<String> tempSelectList = onFirstItemClickListener.onFirstItemClick(sList.get(position), position);
						if (null != tempSelectList)
						{
							List<String> selectList = new ArrayList<>(tempSelectList);
							secondAdapter.setDataList(selectList);
							secondAdapter.initSelect(null);
						}
						else
						{
							secondAdapter.clear();
						}
					}

					// 更新状态
					isSelected[position] = true;
					if (-1 != oldPosition)
					{
						isSelected[oldPosition] = false;
						notifyItemChanged(oldPosition);
					}
					textView.setBackgroundColor(getColorBgSelected());
					textView.setTextColor(getColorTextSelected());
					oldPosition = position;
				}
			}
		});
	}

	private OnFirstItemClickListener onFirstItemClickListener;

	public void setOnFirstItemClickListener(OnFirstItemClickListener onFirstItemClickListener)
	{
		this.onFirstItemClickListener = onFirstItemClickListener;
	}

	public interface OnFirstItemClickListener
	{
		/**
		 * @param content  选择的内容
		 * @param position 选择的位置
		 * @return 第二列展现的值
		 */
		List<String> onFirstItemClick(String content, int position);
	}

	@LayoutRes
	protected int getItemResource()
	{
		return R.layout.widget_secondary_item_first;
	}

	@ColorInt
	protected int getColorBgSelected()
	{
		return 0xfff2f2f2;
	}

	@ColorInt
	protected int getColorBgUnselected()
	{
		return 0xffffffff;
	}

	@ColorInt
	protected int getColorTextSelected()
	{
		return 0xffff2742;
	}

	@ColorInt
	protected int getColorTextUnselected()
	{
		return 0xff666666;
	}
}
