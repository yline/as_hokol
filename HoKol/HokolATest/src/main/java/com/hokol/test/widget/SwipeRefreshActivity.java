package com.hokol.test.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.medium.widget.recycler.HeadFootRecycleAdapter;
import com.hokol.medium.widget.swiperefresh.BaseSwipeRefreshAdapter;
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
		// swipeRefreshLayout.setHeaderViewBackgroundColor(0xff888888);
		// swipeRefreshLayout.setTargetScrollWithLayout(false);
		swipeRefreshLayout.setRefreshAdapter(new BaseSwipeRefreshAdapter()
		{
			// Header View
			private ProgressBar progressBar;

			private TextView textView;

			private ImageView imageView;

			@Override
			public void onAnimating()
			{
				textView.setText("正在刷新");
				imageView.setVisibility(View.GONE);
				progressBar.setVisibility(View.VISIBLE);
				new Handler().postDelayed(new Runnable()
				{

					@Override
					public void run()
					{
						swipeRefreshLayout.setRefreshing(false);
						progressBar.setVisibility(View.GONE);
					}
				}, 2000);
			}

			@Override
			public void onDistance(int distance)
			{
				// pull distance
			}

			@Override
			public void onStart(boolean enable)
			{
				textView.setText(enable ? "松开刷新" : "下拉刷新");
				imageView.setVisibility(View.VISIBLE);
				imageView.setRotation(enable ? 180 : 0);
			}

			@NonNull
			@Override
			protected View getView()
			{
				return createHeaderView();
			}

			private View createHeaderView()
			{
				View headerView = LayoutInflater.from(swipeRefreshLayout.getContext()).inflate(R.layout.layout_head, null);
				progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
				textView = (TextView) headerView.findViewById(R.id.text_view);
				textView.setText("下拉刷新");
				imageView = (ImageView) headerView.findViewById(R.id.image_view);
				imageView.setVisibility(View.VISIBLE);
				imageView.setImageResource(R.drawable.down_arrow);
				progressBar.setVisibility(View.GONE);
				return headerView;
			}
		});

		swipeRefreshLayout.setLoadAdapter(new BaseSwipeRefreshAdapter()
		{
			// Footer View
			private ProgressBar footerProgressBar;

			private TextView footerTextView;

			private ImageView footerImageView;

			@Override
			public void onStart(boolean enable)
			{
				footerTextView.setText(enable ? "松开加载" : "上拉加载");
				footerImageView.setVisibility(View.VISIBLE);
				footerImageView.setRotation(enable ? 0 : 180);
			}

			@Override
			public void onAnimating()
			{
				footerTextView.setText("正在加载...");
				footerImageView.setVisibility(View.GONE);
				footerProgressBar.setVisibility(View.VISIBLE);
				new Handler().postDelayed(new Runnable()
				{

					@Override
					public void run()
					{
						footerImageView.setVisibility(View.VISIBLE);
						footerProgressBar.setVisibility(View.GONE);
						swipeRefreshLayout.setLoadMore(false);
					}
				}, 5000);
			}

			@Override
			public void onDistance(int distance)
			{
				// TODO Auto-generated method stub
			}

			@NonNull
			@Override
			protected View getView()
			{
				return createFooterView();
			}

			private View createFooterView()
			{
				View footerView = LayoutInflater.from(swipeRefreshLayout.getContext()).inflate(R.layout.layout_footer, null);
				footerProgressBar = (ProgressBar) footerView.findViewById(R.id.footer_pb_view);
				footerImageView = (ImageView) footerView.findViewById(R.id.footer_image_view);
				footerTextView = (TextView) footerView.findViewById(R.id.footer_text_view);
				footerProgressBar.setVisibility(View.GONE);
				footerImageView.setVisibility(View.VISIBLE);
				footerImageView.setImageResource(R.drawable.down_arrow);
				footerTextView.setText("上拉加载更多...");
				return footerView;
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
