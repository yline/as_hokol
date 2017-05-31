package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hokol.R;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.common.CommonRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;
import com.yline.view.common.ViewHolder;

import java.util.Arrays;

public class UserRechargeActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	private UserRechargeAdapter rechargeAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_recharge);

		viewHolder = new ViewHolder(this);
		initView();
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
				UserRechargeRecordActivity.actionStart(UserRechargeActivity.this);
			}
		});
	}

	private class UserRechargeAdapter extends CommonRecyclerAdapter<Integer>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_user_recharge_value;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			holder.setText(R.id.tv_user_recharge_value_top, String.format("%d红豆", sList.get(position)));
			holder.setText(R.id.tv_user_recharge_value, String.format("￥%d", sList.get(position)));
		}
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserRechargeActivity.class));
	}
}
