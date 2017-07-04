package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.yline.base.BaseFragment;
import com.yline.view.recycler.holder.ViewHolder;

public class UserAccountProfitFragment extends BaseFragment
{
	private ViewHolder viewHolder;

	public static UserAccountProfitFragment newInstance()
	{
		Bundle args = new Bundle();
		
		UserAccountProfitFragment fragment = new UserAccountProfitFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_user_account_profit, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		viewHolder = new ViewHolder(view);
		initView();
	}

	private void initView()
	{
		viewHolder.setText(R.id.btn_user_account_profit_withdraw, "功能尚未开通");
		viewHolder.setText(R.id.tv_user_account_profit_money_num, "");
	}
}
