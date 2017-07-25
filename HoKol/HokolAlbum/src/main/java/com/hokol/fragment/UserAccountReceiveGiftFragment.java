package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserGiftReceiveBean;
import com.hokol.medium.http.bean.WUserGiftReceiveBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.base.BaseFragment;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.Calendar;
import java.util.List;

public class UserAccountReceiveGiftFragment extends BaseFragment
{
	private static final String KeyGiftReceiveUserId = "GiftReceiverUserId";

	private ReceiveGiftAdapter receiveGiftAdapter;

	public static UserAccountReceiveGiftFragment newInstance(String userId)
	{
		Bundle args = new Bundle();
		UserAccountReceiveGiftFragment fragment = new UserAccountReceiveGiftFragment();
		args.putString(KeyGiftReceiveUserId, userId);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_user_account_receive_gift, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
		initData();
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_receive_gift);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(getContext()));

		receiveGiftAdapter = new ReceiveGiftAdapter();
		recyclerView.setAdapter(receiveGiftAdapter);
	}

	private void initData()
	{
		String userId = getArguments().getString(KeyGiftReceiveUserId);

		receiveGiftAdapter.setShowEmpty(false);
		XHttpUtil.doUserGiftReceive(new WUserGiftReceiveBean(userId, 0, DeleteConstant.defaultNumberLarge), new HokolAdapter<VUserGiftReceiveBean>()
		{
			@Override
			public void onSuccess(VUserGiftReceiveBean vUserGiftReceiveBean)
			{
				receiveGiftAdapter.setShowEmpty(true);
				List<VUserGiftReceiveBean.VUserGiftReceiveOneBean> resultList = vUserGiftReceiveBean.getList();
				if (null != resultList)
				{
					receiveGiftAdapter.setDataList(resultList);
				}
			}
		});
	}

	private class ReceiveGiftAdapter extends WidgetRecyclerAdapter<VUserGiftReceiveBean.VUserGiftReceiveOneBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_user_account_receive_gift;
		}
		
		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			super.onBindViewHolder(holder, position);

			VUserGiftReceiveBean.VUserGiftReceiveOneBean receiveBean = sList.get(position);

			// 头像
			ImageView avatarImageView = holder.get(R.id.circle_item_user_account_receive_gift);
			Glide.with(getContext()).load(receiveBean.getUser_logo()).error(R.drawable.global_load_failed).into(avatarImageView);

			// 内容
			holder.setText(R.id.tv_item_user_account_receive_gift_title, String.format("收到%s %d红豆", receiveBean.getUser_nickname(), receiveBean.getCoin_num()));

			// 时间
			String dateStr = HokolTimeConvertUtil.stampToFormatDate(receiveBean.getAdd_time() * 1000, Calendar.SECOND);
			holder.setText(R.id.tv_item_user_account_receive_gift_sub, dateStr);
		}
	}
}
