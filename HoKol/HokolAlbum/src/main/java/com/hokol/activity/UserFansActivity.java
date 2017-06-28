package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserFansAllBean;
import com.hokol.medium.http.bean.WUserFansAllBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.utils.UIScreenUtil;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

public class UserFansActivity extends BaseAppCompatActivity
{
	private static final String KeyFansUserId = "KeyUserId";

	private RecyclerView recyclerView;

	private UserFansAdapter userFansAdapter;

	private SuperSwipeRefreshLayout swipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_fans);

		initView();
		initData();
	}

	private void initView()
	{
		recyclerView = (RecyclerView) findViewById(R.id.recycle_user_fans);
		userFansAdapter = new UserFansAdapter();

		recyclerView.setAdapter(userFansAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(this)
		{
			@Override
			public void drawVerticalDivider(Canvas c, Drawable divide, int currentPosition, int childLeft, int childTop, int childRight, int childBottom)
			{
				super.drawVerticalDivider(c, divide, currentPosition, childLeft + UIScreenUtil.dp2px(UserFansActivity.this, 10), childTop, childRight, childBottom);
			}
		});

		// view
		findViewById(R.id.iv_user_fans_finish).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		userFansAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VUserFansAllBean.VUserFansAllOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VUserFansAllBean.VUserFansAllOneBean fansBean, int position)
			{
				if (!TextUtils.isEmpty(fansBean.getUser_id()))
				{
					StarInfoActivity.actionStart(UserFansActivity.this, fansBean.getUser_id());
				}
			}
		});
		userFansAdapter.setOnUserFansCallback(new OnUserFansCallback()
		{
			@Override
			public void onAttentionClick(RecyclerViewHolder viewHolder, VUserFansAllBean.VUserFansAllOneBean fansBean)
			{
				SDKManager.toast("加关注");
				/*
				viewHolder.get(R.id.iv_user_fans_attention).setVisibility(View.GONE);
				viewHolder.get(R.id.iv_user_fans_arrow).setVisibility(View.VISIBLE);
				*/
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

	private void initData()
	{
		String userId = getIntent().getStringExtra(KeyFansUserId);

		userFansAdapter.setShowEmpty(false);
		XHttpUtil.doUserFansAll(new WUserFansAllBean(userId, 0, DeleteConstant.defaultNumberSuper), new XHttpAdapter<VUserFansAllBean>()
		{
			@Override
			public void onSuccess(VUserFansAllBean vUserFansAllBean)
			{
				userFansAdapter.setShowEmpty(true);
				List<VUserFansAllBean.VUserFansAllOneBean> resultList = vUserFansAllBean.getList();
				if (null != resultList)
				{
					userFansAdapter.setDataList(resultList);
				}
			}
		});
	}

	private class UserFansAdapter extends WidgetRecyclerAdapter<VUserFansAllBean.VUserFansAllOneBean>
	{
		private OnUserFansCallback onUserFansCallback;

		public void setOnUserFansCallback(OnUserFansCallback onUserFansCallback)
		{
			this.onUserFansCallback = onUserFansCallback;
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_user_fans;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
		{
			super.onBindViewHolder(viewHolder, position);

			VUserFansAllBean.VUserFansAllOneBean fansBean = sList.get(position);

			// 头像
			ImageView avatarImageView = viewHolder.get(R.id.circle_user_fans_avatar);
			Glide.with(UserFansActivity.this).load(fansBean.getUser_logo()).error(R.drawable.global_load_failed).into(avatarImageView);

			// 昵称
			viewHolder.setText(R.id.tv_user_fans_name, fansBean.getUser_nickname());

			// 性别
			if (HttpEnum.UserSex.Boy.getContent().equals(fansBean.getUser_sex()))
			{
				viewHolder.setImageResource(R.id.iv_user_info_sex, R.drawable.global_sex_boy).setVisibility(View.VISIBLE);
			}
			else if (HttpEnum.UserSex.Girl.getContent().equals(fansBean.getUser_sex()))
			{
				viewHolder.setImageResource(R.id.iv_user_info_sex, R.drawable.global_sex_girl).setVisibility(View.VISIBLE);
			}
			else
			{
				viewHolder.get(R.id.iv_user_info_sex).setVisibility(View.GONE);
			}

			// 等级
			ImageView vipImageView = viewHolder.get(R.id.iv_user_info_vip);
			if (!TextUtils.isEmpty(fansBean.getLevel_url()))
			{
				vipImageView.setVisibility(View.VISIBLE);
				Glide.with(UserFansActivity.this).load(fansBean.getLevel_url()).into(vipImageView);
			}
			else
			{
				vipImageView.setVisibility(View.GONE);
			}

			// 签名
			viewHolder.setText(R.id.tv_user_fans_brief, fansBean.getUser_sign());

			// 标签
			FlowLayout flowLayout = viewHolder.get(R.id.flow_layout_user_fans);
			FlowWidget labelWidget = new FlowWidget(UserFansActivity.this, flowLayout)
			{
				@Override
				protected int getItemResourceId()
				{
					return R.layout.widget_item_label_flow_padright_medium;
				}
			};
			labelWidget.setDataList(fansBean.getUser_tag());

			// 是否关注
			final ImageView attentionImageView = viewHolder.get(R.id.iv_user_fans_attention);
			if (VUserFansAllBean.Cared == fansBean.getIs_care())
			{
				attentionImageView.setVisibility(View.GONE);
				viewHolder.get(R.id.iv_user_fans_arrow).setVisibility(View.VISIBLE);
			}
			else
			{
				attentionImageView.setVisibility(View.VISIBLE);
				viewHolder.get(R.id.iv_user_fans_arrow).setVisibility(View.GONE);
				attentionImageView.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						if (null != onUserFansCallback)
						{
							onUserFansCallback.onAttentionClick(viewHolder, sList.get(position));
						}
					}
				});
			}
		}
	}

	public interface OnUserFansCallback
	{
		void onAttentionClick(RecyclerViewHolder viewHolder, VUserFansAllBean.VUserFansAllOneBean fansBean);
	}

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserFansActivity.class).putExtra(KeyFansUserId, userId));
	}
}
