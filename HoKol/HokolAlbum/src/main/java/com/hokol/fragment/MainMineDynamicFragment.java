package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseFragment;
import com.hokol.base.utils.UIScreenUtil;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.HeadFootRecyclerAdapter;
import com.hokol.medium.widget.transform.CircleTransform;

import java.util.ArrayList;
import java.util.List;

public class MainMineDynamicFragment extends BaseFragment
{
	private HeadFootRecyclerAdapter recyclerAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_mine_dynamic, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_mine_dynamic);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected int getNonDivideHeadNumber()
			{
				return 3;
			}

			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_recycler_divider_white_small;
			}
		});

		recyclerAdapter = new DynamicRecycleAdapter();
		recyclerView.setAdapter(recyclerAdapter);
		recyclerAdapter.setOnClickListener(new CommonRecyclerAdapter.OnClickListener()
		{
			@Override
			public void onClick(View view, Object o, int position)
			{
				IApplication.toast("position = " + position);
			}
		});

		initRecyclerHeadView(recyclerAdapter);

		List dataList = new ArrayList();
		for (int i = 0; i < 10; i++)
		{
			dataList.add("i" + i);
		}
		recyclerAdapter.setDataList(dataList);

		final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_main_mine_dynamic);
		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
		{
			@Override
			public void onRefresh()
			{
				IApplication.toast("正在加载");
				IApplication.getHandler().postDelayed(new Runnable()
				{
					@Override
					public void run()
					{
						IApplication.toast("加载结束");
						swipeRefreshLayout.setRefreshing(false);
					}
				}, 3000);
			}
		});
	}

	/**
	 * 初始化 Recycler 头部
	 *
	 * @param wrapperAdapter
	 */
	private void initRecyclerHeadView(HeadFootRecyclerAdapter wrapperAdapter)
	{
		View grayView = new View(getContext());
		grayView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 10)));
		grayView.setBackgroundResource(android.R.color.darker_gray);
		wrapperAdapter.addHeadView(grayView);

		// 照相栏目
		View cameraView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_mine_dynamic_camera, null);
		ImageView cameraImageView = (ImageView) cameraView.findViewById(R.id.iv_main_mine_dynamic_camera);
		Glide.with(getContext()).load(DeleteConstant.url_icon_camera).into(cameraImageView);
		wrapperAdapter.addHeadView(cameraView);

		View grayView2 = new View(getContext());
		grayView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 10)));
		grayView2.setBackgroundResource(android.R.color.darker_gray);
		wrapperAdapter.addHeadView(grayView2);
	}

	private class DynamicRecycleAdapter extends HeadFootRecyclerAdapter
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_care;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, final int position)
		{
			ImageView imageView = viewHolder.get(R.id.iv_item_main_care_avatar);

			Glide.with(getContext()).load(DeleteConstant.url_default_avatar).centerCrop()
					.bitmapTransform(new CircleTransform(getContext())).placeholder(R.mipmap.ic_launcher)
					.into(imageView);
		}
	}
}
