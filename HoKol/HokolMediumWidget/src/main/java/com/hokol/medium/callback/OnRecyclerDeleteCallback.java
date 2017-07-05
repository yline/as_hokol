package com.hokol.medium.callback;

import com.yline.view.recycler.holder.RecyclerViewHolder;

public interface OnRecyclerDeleteCallback<T>
{
	/**
	 * @param viewHolder
	 * @param t
	 * @param position
	 */
	void onDelete(RecyclerViewHolder viewHolder, T t, int position);
}
