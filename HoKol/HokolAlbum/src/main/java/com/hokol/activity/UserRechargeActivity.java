package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.Arrays;

public class UserRechargeActivity extends BaseAppCompatActivity
{
	private static final String KeyRechargeUserId = "UserId";

	private ViewHolder viewHolder;

	private UserRechargeAdapter rechargeAdapter;

	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_recharge);

		viewHolder = new ViewHolder(this);
		initView();
		initData();
	}

	private void initView()
	{
		RecyclerView recyclerView = viewHolder.get(R.id.recycler_user_recharge);
		recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(this)
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_null_size_medium;
			}
		});
		
		rechargeAdapter = new UserRechargeAdapter();
		recyclerView.setAdapter(rechargeAdapter);

		rechargeAdapter.setDataList(Arrays.asList(100, 680, 1280, 2680, 5180, 9980));

		viewHolder.setOnClickListener(R.id.iv_user_recharge_cancel, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
		viewHolder.setOnClickListener(R.id.tv_user_recharge_record, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserRechargeRecordActivity.actionStart(UserRechargeActivity.this, userId);
			}
		});

		viewHolder.setOnClickListener(R.id.iv_user_recharge_wechat, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SDKManager.toast("微信充值");
			}
		});
		viewHolder.setOnClickListener(R.id.iv_user_recharge_ali_pay, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SDKManager.toast("支付宝充值");
			}
		});

		rechargeAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<Integer>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, Integer integer, int position)
			{
				SDKManager.toast("coin = " + integer);
			}
		});
	}

	private void initData()
	{
		// id
		userId = getIntent().getStringExtra(KeyRechargeUserId);

		// 充值账号
		String userTel = AppStateManager.getInstance().getUserTel(this);
		viewHolder.setText(R.id.tv_user_recharge, String.format("充值账号：%s", userTel));

		// 硬币
		int userCoin = AppStateManager.getInstance().getUserCoinNum(this);
		viewHolder.setText(R.id.tv_user_coin_num, String.format("余额：%d红豆", userCoin));
	}

	private class UserRechargeAdapter extends WidgetRecyclerAdapter<Integer>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_user_recharge_value;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			super.onBindViewHolder(holder, position);

			holder.setText(R.id.tv_user_recharge_value_top, String.format("%d红豆", sList.get(position)));
			holder.setText(R.id.tv_user_recharge_value, String.format("￥%d", sList.get(position)));
		}
	}

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserRechargeActivity.class).putExtra(KeyRechargeUserId, userId));
	}
}
