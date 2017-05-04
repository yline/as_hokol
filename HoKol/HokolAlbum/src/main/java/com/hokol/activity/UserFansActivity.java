package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.bean.VUserFansAllBean;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.medium.widget.labellayout.FlowLayout;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;
import com.yline.base.BaseAppCompatActivity;
import com.yline.common.CommonRecyclerAdapter;
import com.yline.common.CommonRecyclerViewHolder;

import java.util.Arrays;

public class UserFansActivity extends BaseAppCompatActivity
{
	private RecyclerView recyclerView;

	private UserFansAdapter userFansAdapter;

	private SuperSwipeRefreshLayout swipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_fans);

		recyclerView = (RecyclerView) findViewById(R.id.recycle_user_fans);
		userFansAdapter = new UserFansAdapter();

		recyclerView.setAdapter(userFansAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(this));

		// view
		findViewById(R.id.iv_user_fans_finish).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 刷新
		swipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.super_swipe_user_fans);
		swipeRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						swipeRefreshLayout.setRefreshing(false);
					}
				}, 2000);
			}
		});
		swipeRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						swipeRefreshLayout.setLoadMore(false);
					}
				}, 2000);
			}
		});
	}

	private class UserFansAdapter extends CommonRecyclerAdapter<VUserFansAllBean.VUserFansAllOneBean>
	{
		@Override
		public int getItemCount()
		{
			return 20;
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_user_fans;
		}

		@Override
		public void onBindViewHolder(final CommonRecyclerViewHolder viewHolder, int position)
		{
			FlowLayout flowLayout = viewHolder.get(R.id.flow_layout_user_fans);
			FlowWidget labelWidget = new FlowWidget(UserFansActivity.this, flowLayout);
			labelWidget.setDataList(Arrays.asList("网红", "模特"));

			ImageView avatarImageView = viewHolder.get(R.id.circle_user_fans_avatar);
			Glide.with(UserFansActivity.this).load(DeleteConstant.url_default_avatar).error(R.drawable.global_load_failed).into(avatarImageView);

			final ImageView attentionImageView = viewHolder.get(R.id.iv_user_fans_attention);
			attentionImageView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					attentionImageView.setVisibility(View.GONE);
					viewHolder.get(R.id.iv_user_fans_arrow).setVisibility(View.VISIBLE);
				}
			});
		}
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserFansActivity.class));
	}
}
