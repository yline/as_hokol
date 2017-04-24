package com.hokol.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;
import com.yline.common.CommonRecyclerAdapter;
import com.yline.common.CommonRecyclerViewHolder;

public class UserInfoUpdateAreaActivity extends BaseAppCompatActivity
{
	private UserInfoAreaAdapter userInfoAreaAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_update_area);

		initView();
	}

	private void initView()
	{
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_user_info_update_area);
		userInfoAreaAdapter = new UserInfoAreaAdapter();
		recyclerView.setAdapter(userInfoAreaAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

	private class UserInfoAreaAdapter extends CommonRecyclerAdapter
	{

		@Override
		public int getItemRes()
		{
			return 0;
		}

		@Override
		public void onBindViewHolder(CommonRecyclerViewHolder viewHolder, int position)
		{

		}
	}
}
