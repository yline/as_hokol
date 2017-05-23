package com.hokol.medium.widget.recycler;

import android.view.View;

import com.yline.view.callback.OnRecyclerItemClickListener;
import com.yline.view.common.HeadFootRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

public abstract class WidgetRecyclerAdapter<Data> extends HeadFootRecyclerAdapter<Data>
{
	private boolean isLoadError;

	private OnRecyclerItemClickListener listener;

	public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener)
	{
		this.listener = listener;
	}

	@Override
	public int getItemRes()
	{
		return 0;
	}

	@Override
	public int getEmptyItemRes()
	{
		return super.getEmptyItemRes();
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

	@Override
	public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
	{
		super.onBindEmptyViewHolder(viewHolder, position);

		if (isLoadError)
		{

		}
	}
}
