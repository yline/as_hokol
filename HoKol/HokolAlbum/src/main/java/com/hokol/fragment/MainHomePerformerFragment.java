package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.HeadFootRecycleAdapter;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;


public class MainHomePerformerFragment extends BaseFragment
{
	private static final String ARG_PARAM1 = "param1";

	private String mParam1;

	private HeadFootRecycleAdapter mainHomePerformerAdapter;

	private SuperSwipeRefreshLayout superRefreshLayout;

	public static MainHomePerformerFragment newInstance()
	{
		MainHomePerformerFragment fragment = new MainHomePerformerFragment();
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
		return inflater.inflate(R.layout.fragment_main_home_performer, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	private void initView(View view)
	{
		RecyclerView recycleView = (RecyclerView) view.findViewById(R.id.recycle_main_home_performer);
		recycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));
		recycleView.addItemDecoration(new DefaultGridItemDecoration(getContext())
		{
			@Override
			protected int getDividerResourceId()
			{
				return R.drawable.widget_recycler_divider_white_small;
			}
		});

		mainHomePerformerAdapter = new MainHomePerformerFragment.MainHomePerformerAdapter();
		recycleView.setAdapter(mainHomePerformerAdapter);

		List<String> dataList = new ArrayList<>();
		for (int i = 0; i < 35; i++)
		{
			dataList.add(DeleteConstant.getUrlSquare());
		}
		mainHomePerformerAdapter.setDataList(dataList);

		mainHomePerformerAdapter.setOnClickListener(new HeadFootRecycleAdapter.OnClickListener<String>()
		{
			@Override
			public void onClick(View view, String string, int position)
			{
				StarDynamicActivity.actionStart(getContext());
			}
		});

		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_home_performer);
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

	private class MainHomePerformerAdapter extends HeadFootRecycleAdapter<String>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_home_performer;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
		{
			ImageView imageView = viewHolder.get(R.id.iv_item_main_home_performer);
			Glide.with(getContext()).load(sList.get(position)).centerCrop()
					.placeholder(R.mipmap.ic_launcher)
					.into(imageView);
		}
	}
}
