package com.hokol.viewhelper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.utils.UIResizeUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.common.HeadFootRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

import java.util.List;

public class MainCareHelper
{
	private HeadFootRecyclerAdapter recyclerAdapter;

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
			protected int getDivideResourceId()
			{
				return R.drawable.widget_recycler_divider_gray_light_normal;
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

	private class RecycleAdapter extends HeadFootRecyclerAdapter
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_main_care;
		}

		@Override
		public void setViewContent(RecyclerViewHolder viewHolder, int position)
		{
			ImageView avatarView = viewHolder.get(R.id.circle_item_main_care_avatar);
			Glide.with(sContext).load(DeleteConstant.url_default_avatar).placeholder(R.drawable.global_load_failed).error(R.drawable.global_load_failed).centerCrop().into(avatarView);

			ImageView contentView = viewHolder.get(R.id.iv_item_main_care_content);
			int width = UIScreenUtil.getScreenWidth(sContext) - UIScreenUtil.dp2px(sContext, 20);
			UIResizeUtil.build().setHeight(width).commit(contentView);
			Glide.with(sContext).load(DeleteConstant.getUrlRec()).placeholder(R.drawable.global_load_failed).error(R.drawable.global_load_failed).into(contentView);
			
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
