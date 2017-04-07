package com.hokol.fragment;

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
import com.hokol.activity.StarDynamicActivity;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseFragment;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.HeadFootRecyclerAdapter;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainHomeSportFragment extends BaseFragment
{
	private static final String ARG_PARAM1 = "param1";

	private String mParam1;

	private HeadFootRecyclerAdapter mainHomeSportAdapter;

	private SuperSwipeRefreshLayout superRefreshLayout;

	public static MainHomeSportFragment newInstance()
	{
		MainHomeSportFragment fragment = new MainHomeSportFragment();
		/*Bundle args = new Bundle();
		fragment.setArguments(args);*/
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (getArguments() != null)
		{
			mParam1 = getArguments().getString(ARG_PARAM1);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_home_sport, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_home_sport);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{

			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_recycler_divider_white_small;
			}
		});

		mainHomeSportAdapter = new MainHomeSportFragment.MainHomeSportAdapter();
		recyclerView.setAdapter(mainHomeSportAdapter);

		List<String> dataList = new ArrayList<>();
		for (int i = 0; i < 35; i++)
		{
			dataList.add(DeleteConstant.getUrlSquare());
		}
		mainHomeSportAdapter.setDataList(dataList);

		mainHomeSportAdapter.setOnClickListener(new HeadFootRecyclerAdapter.OnClickListener<String>()
		{
			@Override
			public void onClick(View view, String string, int position)
			{
				StarDynamicActivity.actionStart(getContext());
			}
		});

		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_home_sport);
		superRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						IApplication.toast("刷新结束");
						superRefreshLayout.setRefreshing(false);
					}
				}, 2000);
			}
		});
		superRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						IApplication.toast("刷新结束");
						superRefreshLayout.setLoadMore(false);
					}
				}, 2000);
			}
		});
	}

	private class MainHomeSportAdapter extends HeadFootRecyclerAdapter<String>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_home_sport;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
		{
			ImageView imageView = viewHolder.get(R.id.iv_item_main_home_sport);
			Glide.with(getContext()).load(sList.get(position)).centerCrop()
					.placeholder(R.mipmap.ic_launcher)
					.into(imageView);
		}
	}
}
