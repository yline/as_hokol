package com.hokol.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * T -> 数据类型
 *
 * @author YLine
 *         2016年8月1日 下午11:16:12
 */
public abstract class CommonListAdapter<T> extends BaseAdapter implements ICommonAdapterCallback<T>
{
	protected Context sContext;

	protected List<T> sList;

	public CommonListAdapter(Context context)
	{
		this.sContext = context;
		this.sList = new ArrayList<>();
	}

	@Override
	public int getCount()
	{
		return sList.size();
	}

	@Override
	public T getItem(int position)
	{
		if (position >= sList.size())
		{
			throw new IllegalArgumentException("invalid position");
		}
		return sList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;
		if (convertView == null)
		{
			convertView = LayoutInflater.from(sContext).inflate(getItemRes(position), parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		setViewContent(position, parent, holder);

		return convertView;
	}

	/**
	 * @param position 当前的位置
	 * @return item 资源文件
	 */
	protected abstract int getItemRes(int position);

	/**
	 * 对内容设置
	 *
	 * @param position 当前item位置
	 * @param parent   副控件(一般不用)
	 * @param item     ViewHolder
	 */
	protected abstract void setViewContent(int position, ViewGroup parent, ViewHolder item);

	public void set(List<T> tList)
	{
		this.sList = tList;
		this.notifyDataSetChanged();
	}

	@Override
	public boolean add(T object)
	{
		boolean result = sList.add(object);
		this.notifyDataSetChanged();
		return result;
	}

	@Override
	public void add(int index, T element)
	{
		sList.add(index, element);
		this.notifyDataSetChanged();
	}

	@Override
	public boolean addAll(Collection collection)
	{
		boolean result = sList.addAll(collection);
		this.notifyDataSetChanged();
		return result;
	}

	@Override
	public void clear()
	{
		sList.clear();
		this.notifyDataSetChanged();
	}

	@Override
	public boolean contains(Object object)
	{
		boolean result = sList.contains(object);
		return result;
	}

	@Override
	public T remove(int index)
	{
		T t = sList.remove(index);
		this.notifyDataSetChanged();
		return t;
	}

	@Override
	public boolean remove(Object object)
	{
		boolean result = sList.remove(object);
		this.notifyDataSetChanged();
		return result;
	}

	@Override
	public int size()
	{
		int size = sList.size();
		return size;
	}

	@Override
	public boolean removeAll(Collection collection)
	{
		boolean result = sList.removeAll(collection);
		this.notifyDataSetChanged();
		return result;
	}

	@Override
	public boolean containsAll(Collection collection)
	{
		boolean result = sList.containsAll(collection);
		return result;
	}
}
