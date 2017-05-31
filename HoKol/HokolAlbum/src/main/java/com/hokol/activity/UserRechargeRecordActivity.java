package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hokol.R;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.common.CommonRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

import java.util.Arrays;

public class UserRechargeRecordActivity extends BaseAppCompatActivity
{
	private UserRechargeRecordAdapter rechargeRecordAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_recharge_record);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_user_recharge_record);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(this));

		rechargeRecordAdapter = new UserRechargeRecordAdapter();
		recyclerView.setAdapter(rechargeRecordAdapter);

		rechargeRecordAdapter.setDataList(Arrays.asList("1", "2", "3"));

		// 返回
		findViewById(R.id.iv_user_recharge_record_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	private class UserRechargeRecordAdapter extends CommonRecyclerAdapter<String>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_user_recharge_record;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{

		}
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserRechargeRecordActivity.class));
	}
}
