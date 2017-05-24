package com.hokol.medium.widget.recycler;

import android.view.View;

import com.hokol.medium.widget.R;
import com.yline.view.callback.OnRecyclerItemClickListener;
import com.yline.view.common.HeadFootRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

public abstract class WidgetRecyclerAdapter<Data> extends HeadFootRecyclerAdapter<Data>
{
	// 是否 显示空白页 (需要手动使用)
	protected boolean isShowEmpty;

	private OnRecyclerItemClickListener listener;

	public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener)
	{
		this.listener = listener;
	}

	/**
	 * @param isShowEmpty 是否显示 空白页内容
	 */
	public void setShowEmpty(boolean isShowEmpty)
	{
		this.isShowEmpty = isShowEmpty;

		if (sList.size() == 0)
		{
			notifyItemChanged(getHeadersCount());
		}
	}

	@Override
	public int getEmptyItemRes()
	{
		return R.layout.widget_recycler_load_error_nomal;
	}

	@Override
	public void onBindViewHolder(final RecyclerViewHolder holder, final int position)
	{
		holder.getItemView().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != listener)
				{
					listener.onItemClick(holder, sList.get(position), position);
				}
			}
		});
	}
}
