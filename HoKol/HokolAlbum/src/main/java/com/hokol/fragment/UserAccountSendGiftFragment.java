package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.yline.base.BaseFragment;
import com.yline.view.recycler.adapter.CommonRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;

public class UserAccountSendGiftFragment extends BaseFragment
{
	private SendGiftAdapter sendGiftAdapter;

	public static UserAccountSendGiftFragment newInstance()
	{
		
		Bundle args = new Bundle();
		
		UserAccountSendGiftFragment fragment = new UserAccountSendGiftFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_user_account_send_gift, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_send_gift);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(getContext()));

		sendGiftAdapter = new SendGiftAdapter();
		recyclerView.setAdapter(sendGiftAdapter);

		// 临时数据
		sendGiftAdapter.setDataList(HttpEnum.getUserSexListAll());
	}

	private class SendGiftAdapter extends CommonRecyclerAdapter<String>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_user_account_send_gift;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{

		}
	}
}
