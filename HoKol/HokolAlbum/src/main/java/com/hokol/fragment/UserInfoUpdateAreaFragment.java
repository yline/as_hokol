package com.hokol.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hokol.R;
import com.yline.base.BaseListFragment;
import com.yline.log.LogFileUtil;
import com.yline.view.common.CommonListAdapter;
import com.yline.view.common.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class UserInfoUpdateAreaFragment extends BaseListFragment
{
	private AreaListAdapter areaListAdapter;

	private List<String> dataList;

	private AdapterView.OnItemClickListener onItemClickListener;

	public static UserInfoUpdateAreaFragment newInstance()
	{
		return new UserInfoUpdateAreaFragment();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		if (null == dataList)
		{
			dataList = new ArrayList<>();
		}

		areaListAdapter = new AreaListAdapter(getContext());
		setListAdapter(areaListAdapter);

		refresh(dataList);
		updateListShown();
	}

	public void refresh(List<String> dataList)
	{
		this.dataList = dataList;
		if (null != areaListAdapter)
		{
			areaListAdapter.setDataList(dataList);
			updateListShown();
		}
	}

	private void updateListShown()
	{
		if (areaListAdapter.getCount() > 0)
		{
			setListShown(true);
		}
		else
		{
			setEmptyText("正在加载");
			setListShown(false);
		}
	}

	public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener)
	{
		this.onItemClickListener = onItemClickListener;
	}

	public String getSelectedItem(int position)
	{
		if (null != areaListAdapter)
		{
			return areaListAdapter.getItem(position);
		}
		else
		{
			LogFileUtil.e("listView", "areaListAdapter is null");
			return null;
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		if (null != onItemClickListener)
		{
			onItemClickListener.onItemClick(l, v, position, id);
		}
	}

	private class AreaListAdapter extends CommonListAdapter<String>
	{

		public AreaListAdapter(Context context)
		{
			super(context);
		}

		@Override
		protected int getItemRes(int position)
		{
			return R.layout.item_user_info_update_area;
		}

		@Override
		protected void onBindViewHolder(ViewGroup parent, ViewHolder viewHolder, int position)
		{
			viewHolder.setText(R.id.tv_name, sList.get(position));
		}
	}
}
