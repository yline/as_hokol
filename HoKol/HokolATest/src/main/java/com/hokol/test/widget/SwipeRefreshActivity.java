package com.hokol.test.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.medium.widget.recycler.HeadFootRecycleAdapter;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;
import com.hokol.test.R;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshActivity extends BaseAppCompatActivity
{
	private SuperSwipeRefreshLayout swipeRefreshLayout;

	private RecyclerView recycleView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe_refresh);

		swipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.swipe_refresh_super);
		recycleView = (RecyclerView) findViewById(R.id.recycle_swipe_super);
		recycleView.setLayoutManager(new LinearLayoutManager(this));

		// init SuperSwipeRefreshLayout
		swipeRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
		{
			@Override
			public void onAnimating()
			{
				new Handler().postDelayed(new Runnable()
				{

					@Override
					public void run()
					{
						swipeRefreshLayout.setRefreshing(false);
					}
				}, 4000);
			}
		});
		swipeRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
		{
			@Override
			public void onAnimating()
			{
				new Handler().postDelayed(new Runnable()
				{

					@Override
					public void run()
					{
						/*swipeRefreshLayout.setRefreshing(false);*/
						swipeRefreshLayout.setLoadMore(false);
					}
				}, 4000);
			}
		});
		
		HeadFootRecycleAdapter recycleAdapter = new HeadFootRecycleAdapter<String>()
		{
			@Override
			public int getItemRes()
			{
				return android.R.layout.simple_list_item_1;
			}

			@Override
			public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
			{
				viewHolder.setText(android.R.id.text1, sList.get(position));
			}
		};
		recycleView.setAdapter(recycleAdapter);

		List<String> dataList = new ArrayList<>();
		for (int i = 0; i < 35; i++)
		{
			dataList.add("item -> " + i);
		}
		recycleAdapter.setDataList(dataList);
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, SwipeRefreshActivity.class));
	}
}
