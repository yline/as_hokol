package com.hokol.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.hokol.medium.widget.DialogFootWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.HeadFootRecyclerAdapter;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMineDynamicFragment extends BaseFragment
{
	private HeadFootRecyclerAdapter recyclerAdapter;

	private SuperSwipeRefreshLayout superSwipeRefreshLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_mine_dynamic, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
		initData();
	}

	private void initView(View view)
	{
		// RecyclerView 内容
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_mine_dynamic);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected int getNonDivideHeadNumber()
			{
				return 1;
			}

			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_recycler_divider_gray_light_normal;
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

		// 头部
		initRecyclerHeadView(recyclerAdapter);

		// 刷新
		superSwipeRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_mine_dynamic);
		superSwipeRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
		{
			@Override
			public void onAnimate()
			{
				IApplication.toast("正在加载");
				IApplication.getHandler().postDelayed(new Runnable()
				{
					@Override
					public void run()
					{
						IApplication.toast("加载结束");
						superSwipeRefreshLayout.setRefreshing(false);
					}
				}, 3000);
			}
		});
		superSwipeRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
		{
			@Override
			public void onAnimate()
			{
				IApplication.toast("正在加载");
				IApplication.getHandler().postDelayed(new Runnable()
				{
					@Override
					public void run()
					{
						IApplication.toast("加载结束");
						superSwipeRefreshLayout.setLoadMore(false);
					}
				}, 3000);
			}
		});
	}

	private void initData()
	{
		List dataList = new ArrayList();
		for (int i = 0; i < 10; i++)
		{
			dataList.add("i" + i);
		}
		recyclerAdapter.setDataList(dataList);
	}

	/**
	 * 初始化 Recycler 头部
	 *
	 * @param wrapperAdapter
	 */
	private void initRecyclerHeadView(HeadFootRecyclerAdapter wrapperAdapter)
	{
		View cameraView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_mine_dynamic_camera, null);
		cameraView.findViewById(R.id.rl_main_mine_dynamic_camera).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DialogFootWidget dialogFootWidget = new DialogFootWidget(getContext(), Arrays.asList("从手机相册选择", "拍照", "小视频"));
				dialogFootWidget.show(new DialogFootWidget.OnSelectedListener()
				{
					@Override
					public void onCancelSelected(DialogInterface dialog)
					{
						dialog.dismiss();
					}

					@Override
					public void onOptionSelected(DialogInterface dialog, int position, String content)
					{
						dialog.dismiss();
					}
				});
			}
		});
		wrapperAdapter.addHeadView(cameraView);
	}

	private class DynamicRecycleAdapter extends HeadFootRecyclerAdapter
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_mine_dynamic;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, final int position)
		{
			ImageView avatarImageView = viewHolder.get(R.id.circle_item_main_mine_dynamic_avatar);
			Glide.with(getContext()).load(DeleteConstant.url_default_avatar).into(avatarImageView);

			ImageView contentImageView = viewHolder.get(R.id.iv_item_main_mine_dynamic_content);
			int width = contentImageView.getWidth();
			UIResizeUtil.build().setIsHeightAdapter(false).setHeight(width).commit(contentImageView);
			Glide.with(getContext()).load(DeleteConstant.getUrlRec()).error(R.mipmap.global_load_failed).into(contentImageView);
		}
	}
}
