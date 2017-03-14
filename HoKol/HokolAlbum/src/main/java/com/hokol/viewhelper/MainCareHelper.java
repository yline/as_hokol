package com.hokol.viewhelper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.hokol.R;
import com.hokol.adapter.HeadFootRecycleAdapter;
import com.hokol.application.DeleteConstant;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.custom.DefaultLinearItemDecoration;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.widget.transform.CircleTransform;

import java.util.List;

public class MainCareHelper
{
	private HeadFootRecycleAdapter recyclerAdapter;

	private OnCareRecycleClickListener careRecycleClickListener;

	private RequestManager glideManager;

	private Context context;

	/**
	 * 初始化Recycle控件
	 *
	 * @param context
	 * @param parentView
	 */
	public void initRecycleView(Context context, View parentView)
	{
		RecyclerView recyclerView = (RecyclerView) parentView.findViewById(R.id.recycle_main_care);
		recyclerView.setLayoutManager(new LinearLayoutManager(context));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(context, DefaultLinearItemDecoration.VERTICAL_LIST, R.drawable.main_care_divider));

		this.glideManager = Glide.with(context);
		this.context = context;

		recyclerAdapter = new MainCareHelper.RecycleAdapter();

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

			glideManager.load(DeleteConstant.url_default_avatar).centerCrop()
					.transform(new CircleTransform(context)).placeholder(R.mipmap.ic_launcher)
					.into(avatarView);
			
			if (null != careRecycleClickListener)
			{
				viewHolder.get(R.id.iv_item_main_care_avatar).setOnClickListener(new View.OnClickListener()
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
