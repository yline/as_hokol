package com.hokol.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * RecyclerView 公共适配器
 *
 * @author yline 2017/3/2 --> 14:15
 * @version 1.0.0
 */
public class CommonRecyclerViewHolder extends RecyclerView.ViewHolder
{
	private SparseArray<View> sArray;
	
	public CommonRecyclerViewHolder(View itemView)
	{
		super(itemView);
		sArray = new SparseArray<View>();
	}
	
	public CommonRecyclerViewHolder setText(int viewId, String content)
	{
		TextView textView = this.get(viewId);
		textView.setText(content);
		return this;
	}
	
	public CommonRecyclerViewHolder setImageBackground(int viewId, int resId)
	{
		ImageView imageView = this.get(viewId);
		imageView.setBackgroundResource(resId);
		return this;
	}
	
	/**
	 * 获取到相应的资源
	 *
	 * @param viewId
	 * @return
	 */
	public <T extends View> T get(int viewId)
	{
		if (sArray.get(viewId) == null)
		{
			View view = itemView.findViewById(viewId);
			sArray.put(viewId, view);
		}
		return (T) sArray.get(viewId);
	}
}
