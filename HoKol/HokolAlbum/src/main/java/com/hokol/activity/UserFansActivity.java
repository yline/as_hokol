package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hokol.R;
import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.medium.http.bean.VUserFansAllBean;
import com.hokol.medium.widget.LabelWidget;
import com.hokol.medium.widget.labellayout.FlowLayout;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;

import java.util.Arrays;

public class UserFansActivity extends BaseAppCompatActivity
{
	private RecyclerView recyclerView;

	private UserFansAdapter userFansAdapter;

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
		public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
		{
			final FlowLayout flowLayout = viewHolder.get(R.id.label_flow_user_fans);
			LabelWidget labelWidget = new LabelWidget(UserFansActivity.this, flowLayout);
			labelWidget.setDataList(Arrays.asList("网红", "模特"));
		}
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserFansActivity.class));
	}
}
