package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseFragment;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.HeadFootRecycleAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainHomeSingerFragment extends BaseFragment
{
	private static final String ARG_PARAM1 = "param1";

	private String mParam1;

	private HeadFootRecycleAdapter mainHomeSingerAdapter;

	public static MainHomeSingerFragment newInstance()
	{
		MainHomeSingerFragment fragment = new MainHomeSingerFragment();
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
		return inflater.inflate(R.layout.fragment_main_home_singer, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_home_singer);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(getContext())
		{

			@Override
			protected int getDividerResourceId()
			{
				return R.drawable.widget_recycler_divider_white_small;
			}
		});

		mainHomeSingerAdapter = new MainHomeSingerFragment.MainHomeSingerAdapter();
		recyclerView.setAdapter(mainHomeSingerAdapter);

		List<String> dataList = new ArrayList<>();
		for (int i = 0; i < 35; i++)
		{
			dataList.add(DeleteConstant.getUrlSquare());
		}
		mainHomeSingerAdapter.setDataList(dataList);

		final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_main_home_singer);
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
						IApplication.toast("刷新结束");
						swipeRefreshLayout.setRefreshing(false);
					}
				}, 3000);
			}
		});
	}

	private class MainHomeSingerAdapter extends HeadFootRecycleAdapter<String>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_home_singer;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
		{
			ImageView imageView = viewHolder.get(R.id.iv_item_main_home_singer);
			Glide.with(getContext()).load(sList.get(position)).centerCrop()
					.placeholder(R.mipmap.ic_launcher)
					.into(imageView);
		}
	}
}
