package com.hokol.medium.widget.recycler;

import android.support.v7.widget.RecyclerView;

public interface OnRecyclerItemClickListener<T>
{
	void onClick(RecyclerView.ViewHolder viewHolder, T t, int position);
}
