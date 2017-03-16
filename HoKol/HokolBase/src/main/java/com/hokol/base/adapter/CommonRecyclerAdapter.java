package com.hokol.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author yline 2017/2/17 --> 11:15
 * @version 1.0.0
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonRecyclerViewHolder> implements ICommonAdapterCallback<T>
{
	protected List<T> sList = new ArrayList();
	
	private CommonRecyclerAdapter.OnClickListener onClickListener;
	
	private CommonRecyclerAdapter.OnTouchListener onTouchListener;
	
	private CommonRecyclerAdapter.OnLongClickListener onLongClickListener;
	
	public CommonRecyclerAdapter()
	{
	}
	
	public void setOnClickListener(CommonRecyclerAdapter.OnClickListener listener)
	{
		this.onClickListener = listener;
	}
	
	public void setOnTouchListener(CommonRecyclerAdapter.OnTouchListener listener)
	{
		this.onTouchListener = listener;
	}
	
	public void setOnLongClickListener(CommonRecyclerAdapter.OnLongClickListener listener)
	{
		this.onLongClickListener = listener;
	}

	public OnClickListener getOnClickListener()
	{
		return onClickListener;
	}

	public OnTouchListener getOnTouchListener()
	{
		return onTouchListener;
	}

	public OnLongClickListener getOnLongClickListener()
	{
		return onLongClickListener;
	}

	public CommonRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		return new CommonRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(this.getItemRes(), parent, false));
	}
	
	public abstract int getItemRes();
	
	public abstract void setViewContent(CommonRecyclerViewHolder viewHolder, int position);
	
	public void onBindViewHolder(CommonRecyclerViewHolder holder, final int position)
	{
		if (null != this.onClickListener)
		{
			holder.itemView.setOnClickListener(new android.view.View.OnClickListener()
			{
				public void onClick(View v)
				{
					CommonRecyclerAdapter.this.onClickListener.onClick(v, sList.get(position), position);
				}
			});
		}
		
		if (null != this.onLongClickListener)
		{
			holder.itemView.setOnLongClickListener(new android.view.View.OnLongClickListener()
			{
				public boolean onLongClick(View v)
				{
					return CommonRecyclerAdapter.this.onLongClickListener.onClick(v, sList.get(position), position);
				}
			});
		}
		
		if (null != this.onTouchListener)
		{
			holder.itemView.setOnTouchListener(new android.view.View.OnTouchListener()
			{
				public boolean onTouch(View v, MotionEvent event)
				{
					return CommonRecyclerAdapter.this.onTouchListener.onClick(v, sList.get(position), position);
				}
			});
		}
		
		this.setViewContent(holder, position);
	}
	
	public int getItemCount()
	{
		return this.sList.size();
	}
	
	public T getItem(int position)
	{
		if (position >= this.sList.size())
		{
			throw new IllegalArgumentException("invalid position");
		}
		else
		{
			return this.sList.get(position);
		}
	}
	
	public List<T> getDataList()
	{
		return this.sList;
	}
	
	public void setDataList(List<T> list)
	{
		this.sList = list;
		this.notifyDataSetChanged();
	}
	
	public boolean add(T object)
	{
		boolean result = this.sList.add(object);
		this.notifyDataSetChanged();
		return result;
	}
	
	public void add(int index, T element)
	{
		this.sList.add(index, element);
		this.notifyItemInserted(index);
	}
	
	public boolean addAll(Collection collection)
	{
		boolean result = this.sList.addAll(collection);
		this.notifyDataSetChanged();
		return result;
	}
	
	public void clear()
	{
		this.sList.clear();
		this.notifyDataSetChanged();
	}
	
	public boolean contains(Object object)
	{
		boolean result = this.sList.contains(object);
		return result;
	}
	
	public boolean isEmpty()
	{
		return false;
	}
	
	public boolean remove(Object object)
	{
		boolean result = this.sList.remove(object);
		this.notifyDataSetChanged();
		return result;
	}
	
	public T remove(int index)
	{
		T t = this.sList.remove(index);
		this.notifyItemRemoved(index);
		return t;
	}
	
	public int size()
	{
		int size = this.sList.size();
		return size;
	}
	
	public boolean removeAll(Collection collection)
	{
		boolean result = this.sList.removeAll(collection);
		this.notifyDataSetChanged();
		return result;
	}
	
	public boolean containsAll(Collection collection)
	{
		boolean result = this.sList.containsAll(collection);
		return result;
	}
	
	public interface OnTouchListener<T>
	{
		boolean onClick(View view, T t, int position);
	}
	
	public interface OnLongClickListener<T>
	{
		boolean onClick(View view, T t, int position);
	}
	
	public interface OnClickListener<T>
	{
		void onClick(View view, T t, int position);
	}
}
