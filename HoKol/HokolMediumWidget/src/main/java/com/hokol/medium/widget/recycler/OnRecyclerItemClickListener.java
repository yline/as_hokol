package com.hokol.medium.widget.recycler;

import com.yline.view.common.RecyclerViewHolder;

public interface OnRecyclerItemClickListener<T>
{
	void onClick(RecyclerViewHolder viewHolder, T t, int position);
}