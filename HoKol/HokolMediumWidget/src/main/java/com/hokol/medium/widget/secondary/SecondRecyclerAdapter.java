package com.hokol.medium.widget.secondary;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hokol.medium.widget.R;
import com.yline.view.common.CommonRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondRecyclerAdapter extends CommonRecyclerAdapter<String>
{
	private boolean[] isSelected;

	private String secondInsertStr;

	public SecondRecyclerAdapter(String insertStr)
	{
		this.secondInsertStr = insertStr;
	}

	/**
	 * 初始化数据，并设置默认选择项
	 *
	 * @param selectedList
	 */
	public void initSelect(List<String> selectedList)
	{
		isSelected = new boolean[getItemCount()];
		Arrays.fill(isSelected, false);

		if (null != selectedList)
		{
			for (int i = 0; i < sList.size(); i++)
			{
				if (selectedList.contains(sList.get(i)))
				{
					isSelected[i] = true;
				}
			}
		}
	}

	/**
	 * 获取已选择的数据
	 *
	 * @return
	 */
	public List<String> getSelectedList()
	{
		List<String> secondSelectedList = new ArrayList<>();
		if (null != isSelected)
		{
			for (int i = 0; i < isSelected.length; i++)
			{
				if (isSelected[i])
				{
					secondSelectedList.add(sList.get(i));
				}
			}
		}
		return secondSelectedList;
	}

	@Override
	public int getItemRes()
	{
		return getItemResource();
	}

	@Override
	public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
	{
		viewHolder.setText(R.id.tv_item_second, sList.get(position));
		viewHolder.getItemView().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!TextUtils.isEmpty(secondInsertStr))
				{
					if (position == 0)
					{
						if (isSelected[0] == false)
						{
							Arrays.fill(isSelected, false);
							isSelected[0] = true;
							updateItemState(viewHolder, true);
							notifyDataSetChanged();
						}
					}
					else
					{
						if (isSelected[0] == true)
						{
							isSelected[0] = false;
							notifyItemChanged(0);
						}
						isSelected[position] = !isSelected[position];
						updateItemState(viewHolder, isSelected[position]);
					}
				}
				else
				{
					isSelected[position] = !isSelected[position];
					updateItemState(viewHolder, isSelected[position]);
				}
			}
		});

		updateItemState(viewHolder, isSelected[position]);
	}

	/**
	 * 更新条目状态
	 *
	 * @param viewHolder
	 * @param select
	 */

	private void updateItemState(RecyclerViewHolder viewHolder, boolean select)
	{
		if (select)
		{
			viewHolder.getItemView().setBackgroundColor(getColorBgSelected());
			TextView textView = viewHolder.get(R.id.tv_item_second);
			textView.setTextColor(getColorTextSelected());
			viewHolder.get(R.id.checkbox_item_second).setBackgroundResource(getItemDrawableSelected());
		}
		else
		{
			viewHolder.getItemView().setBackgroundColor(getColorBgUnselected());
			TextView textView = viewHolder.get(R.id.tv_item_second);
			textView.setTextColor(getColorTextUnselected());
			viewHolder.get(R.id.checkbox_item_second).setBackgroundResource(getItemDrawableUnselected());
		}
	}

	@LayoutRes
	protected int getItemResource()
	{
		return R.layout.widget_secondary_item_second;
	}

	@ColorInt
	protected int getColorBgSelected()
	{
		return 0x00000000;
	}

	@ColorInt
	protected int getColorBgUnselected()
	{
		return 0x00000000;
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

	@DrawableRes
	protected int getItemDrawableSelected()
	{
		return R.drawable.widget_item_secondary_second_selected;
	}

	@DrawableRes
	protected int getItemDrawableUnselected()
	{
		return R.drawable.widget_item_secondary_second_unselected;
	}
}
