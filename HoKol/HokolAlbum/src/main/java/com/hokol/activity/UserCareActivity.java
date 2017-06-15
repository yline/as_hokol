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
import com.hokol.medium.http.bean.VUserCareAllBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.recycler.adapter.CommonRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.Arrays;

public class UserCareActivity extends BaseAppCompatActivity
{
	private RecyclerView recyclerView;

	private UserCareAdapter userCareAdapter;

	private SuperSwipeRefreshLayout swipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_care);

		recyclerView = (RecyclerView) findViewById(R.id.recycle_user_care);
		userCareAdapter = new UserCareAdapter();

		recyclerView.setAdapter(userCareAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(this));

		findViewById(R.id.iv_user_care_finish).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 刷新
		swipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.super_swipe_user_care);
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

	private class UserCareAdapter extends CommonRecyclerAdapter<VUserCareAllBean.UserCareAllOneBean>
	{
		@Override
		public int getItemCount()
		{
			return 20;
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_user_care;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder viewHolder, int position)
		{
			FlowLayout flowLayout = viewHolder.get(R.id.flow_layout_user_care);
			FlowWidget labelWidget = new FlowWidget(UserCareActivity.this, flowLayout);
			labelWidget.setDataList(Arrays.asList("网红", "模特"));

			ImageView avatarImageView = viewHolder.get(R.id.circle_user_care_avatar);
			Glide.with(UserCareActivity.this).load(DeleteConstant.url_default_avatar).error(R.drawable.global_load_failed).into(avatarImageView);
		}
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserCareActivity.class));
	}
}
