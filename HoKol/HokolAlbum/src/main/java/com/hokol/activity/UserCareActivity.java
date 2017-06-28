package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.bean.VUserCareAllBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.recycler.holder.RecyclerViewHolder;

public class UserCareActivity extends BaseAppCompatActivity
{
	private static final String KeyCareUserId = "CareUserId";

	private RecyclerView recyclerView;

	private UserCareAdapter userCareAdapter;

	private SuperSwipeRefreshLayout swipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_care);

		initView();
	}

	private void initView()
	{
		recyclerView = (RecyclerView) findViewById(R.id.recycle_user_care);
		userCareAdapter = new UserCareAdapter();

		recyclerView.setAdapter(userCareAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(this));

		// 结束
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

	private class UserCareAdapter extends WidgetRecyclerAdapter<VUserCareAllBean.UserCareAllOneBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_user_care;
		}
		
		@Override
		public void onBindViewHolder(RecyclerViewHolder viewHolder, int position)
		{
			VUserCareAllBean.UserCareAllOneBean careBean = sList.get(position);

			// 头像
			ImageView avatarImageView = viewHolder.get(R.id.circle_user_care_avatar);
			Glide.with(UserCareActivity.this).load(careBean.getUser_logo()).error(R.drawable.global_load_failed).into(avatarImageView);

			// 昵称
			viewHolder.setText(R.id.tv_user_care_name, careBean.getUser_nickname());

			// 性别
			if (HttpEnum.UserSex.Boy.getContent().equals(careBean.getUser_sex()))
			{
				viewHolder.setImageResource(R.id.iv_user_care_sex, R.drawable.global_sex_boy).setVisibility(View.VISIBLE);
			}
			else if (HttpEnum.UserSex.Girl.getContent().equals(careBean.getUser_sex()))
			{
				viewHolder.setImageResource(R.id.iv_user_care_sex, R.drawable.global_sex_girl).setVisibility(View.VISIBLE);
			}
			else
			{
				viewHolder.get(R.id.iv_user_care_sex).setVisibility(View.GONE);
			}

			// 等级
			ImageView vipImageView = viewHolder.get(R.id.iv_user_care_vip);
			if (!TextUtils.isEmpty(careBean.getLevel_url()))
			{
				vipImageView.setVisibility(View.VISIBLE);
				Glide.with(UserCareActivity.this).load(careBean.getLevel_url()).into(vipImageView);
			}
			else
			{
				vipImageView.setVisibility(View.GONE);
			}

			// 签名
			viewHolder.setText(R.id.tv_user_care_brief, careBean.getUser_sign());

			// 标签
			FlowLayout flowLayout = viewHolder.get(R.id.flow_layout_user_care);
			FlowWidget labelWidget = new FlowWidget(UserCareActivity.this, flowLayout);
			labelWidget.setDataList(careBean.getUser_tag());
		}
	}

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserCareActivity.class).putExtra(KeyCareUserId, userId));
	}
}
