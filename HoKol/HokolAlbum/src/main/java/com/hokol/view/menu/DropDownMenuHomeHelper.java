package com.hokol.view.menu;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.base.adapter.CommonListAdapter;
import com.hokol.base.adapter.ViewHolder;

import java.util.List;

/**
 * 这个的可定制性，很强
 *
 * @author yline 2017/3/6 --> 14:57
 * @version 1.0.0
 */
public class DropDownMenuHomeHelper
{
	private ProvinceListAdapter provinceListAdapter;

	private OnDropMenuClickListener listener;

	public View initProvinceView(Context context)
	{
		final ListView provinceView = new ListView(context)
		{
			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
			{
				heightMeasureSpec = MeasureSpec.makeMeasureSpec(500, MeasureSpec.AT_MOST);
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			}
		};
		provinceView.setDividerHeight(1);
		provinceListAdapter = new ProvinceListAdapter(context);
		provinceView.setAdapter(provinceListAdapter);
		provinceView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				provinceListAdapter.setCheckItem(position);
				if (null != listener)
				{
					listener.onProvinceClick(position);
				}
			}
		});

		return provinceView;
	}

	public void setOnDropMenuClickListener(OnDropMenuClickListener listener)
	{
		this.listener = listener;
	}

	public void setProvinceListData(List<String> data)
	{
		if (null != provinceListAdapter)
		{
			provinceListAdapter.set(data);
		}
	}

	/**
	 * 省份的 listView
	 */
	private class ProvinceListAdapter extends CommonListAdapter<String>
	{
		private int checkItemPosition = 0;

		public ProvinceListAdapter(Context context)
		{
			super(context);
		}

		public void setCheckItem(int position)
		{
			checkItemPosition = position;
			notifyDataSetChanged();
		}

		@Override
		protected int getItemRes(int i)
		{
			return R.layout.item_main_home_menu;
		}

		@Override
		protected void setViewContent(int position, ViewGroup viewGroup, ViewHolder viewHolder)
		{
			TextView textView = viewHolder.get(R.id.tv_item_home_menu);
			textView.setText(sList.get(position));
			if (checkItemPosition != -1)
			{
				if (checkItemPosition == position)
				{
					textView.setTextColor(ContextCompat.getColor(sContext, android.R.color.holo_red_light));
					// textView.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(sContext, R.drawable.drop_down_checked), null);
				}
				else
				{
					textView.setTextColor(ContextCompat.getColor(sContext, android.R.color.black));
					// textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
				}
			}
		}
	}

	public interface OnDropMenuClickListener
	{
		void onProvinceClick(int position);
	}
}
