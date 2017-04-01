package com.hokol.viewhelper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.HeadFootRecycleAdapter;

import java.util.List;

public class MainCareHelper
{
	private HeadFootRecycleAdapter recyclerAdapter;

	private OnCareRecycleClickListener careRecycleClickListener;

	private Context sContext;

	public MainCareHelper(Context context)
	{
		this.sContext = context;
	}

	/**
	 * 初始化Recycle控件
	 *
	 * @param recyclerView
	 */
	public void initRecycleView(RecyclerView recyclerView)
	{
		recyclerView.setLayoutManager(new LinearLayoutManager(sContext));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(sContext)
		{
			@Override
			protected int getDividerResourceId()
			{
				return R.drawable.widget_recycler_divider_gray_normal;
			}
		});

		recyclerAdapter = new RecycleAdapter();
		recyclerView.setAdapter(recyclerAdapter);
	}

	public void setOnRecycleItemClickListener(OnCareRecycleClickListener listener)
	{
		this.careRecycleClickListener = listener;
	}

	public void setRecycleData(List<Object> dataList)
	{
		recyclerAdapter.setDataList(dataList);
	}

	private class RecycleAdapter extends HeadFootRecycleAdapter<VNewsSingleBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_main_care;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
		{
			ImageView avatarView = viewHolder.get(R.id.iv_item_main_care_avatar);
			Glide.with(sContext).load(DeleteConstant.url_default_avatar).centerCrop().into(avatarView);
			
			if (null != careRecycleClickListener)
			{
				viewHolder.get(R.id.rl_item_main_care).setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						careRecycleClickListener.onAvatarClick();
					}
				});

				viewHolder.get(R.id.iv_item_main_care_content).setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						careRecycleClickListener.onPictureClick();
					}
				});
			}
		}
	}

	public interface OnCareRecycleClickListener
	{
		void onAvatarClick();

		void onPictureClick();
	}
}
