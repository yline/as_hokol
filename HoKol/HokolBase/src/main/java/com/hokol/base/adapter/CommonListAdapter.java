package com.hokol.base.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * T -> 数据类型
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
	 * @param position 当前item位置
	 * @param parent   副控件(一般不用)
	 * @param item     ViewHolder
	 */
	protected abstract void setViewContent(int position, ViewGroup parent, ViewHolder item);


	protected class ViewHolder
	{
		private SparseArray<View> sArray;

		private View sView;

		public ViewHolder(View view)
		{
			this.sView = view;
			sArray = new SparseArray<View>();
		}

		/**
		 * 获取到相应的资源
		 * @param viewId
		 * @return
		 */
		public <T extends View> T get(int viewId)
		{
			if (sArray.get(viewId) == null)
			{
				View view = sView.findViewById(viewId);
				sArray.put(viewId, view);
			}
			return (T) sArray.get(viewId);
		}

		/**
		 * 要求是TextView;   这样的方法就可以多写几个,然后就可以作死的连缀了
		 * @param viewId  资源
		 * @param content 内容
		 * @return 为了连缀写法, 返回自身
		 */
		public ViewHolder setText(int viewId, String content)
		{
			TextView textView = this.get(viewId);
			textView.setText(content);
			return this;
		}

		/**
		 * 要求是ImageView;
		 * @param viewId 资源id
		 * @param resId  图片背景id
		 * @return
		 */
		public ViewHolder setImage(int viewId, int resId)
		{
			ImageView imageView = this.get(viewId);
			imageView.setBackgroundResource(resId);
			return this;
		}
	}

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
