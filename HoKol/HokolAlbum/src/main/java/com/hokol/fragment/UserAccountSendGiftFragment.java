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
import com.hokol.medium.http.bean.VUserGiftSendBean;
import com.hokol.medium.http.bean.WUserGiftSendBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.base.BaseFragment;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.Calendar;
import java.util.List;

public class UserAccountSendGiftFragment extends BaseFragment
{
	private static final String KeyGiftSendUserId = "GiftSendUserId";

	private SendGiftAdapter sendGiftAdapter;

	public static UserAccountSendGiftFragment newInstance(String userId)
	{
		Bundle args = new Bundle();
		UserAccountSendGiftFragment fragment = new UserAccountSendGiftFragment();
		args.putString(KeyGiftSendUserId, userId);
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
		initData();
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_send_gift);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(getContext()));

		sendGiftAdapter = new SendGiftAdapter();
		recyclerView.setAdapter(sendGiftAdapter);
	}

	private void initData()
	{
		String userId = getArguments().getString(KeyGiftSendUserId);

		sendGiftAdapter.setShowEmpty(false);
		XHttpUtil.doUserGiftSend(new WUserGiftSendBean(userId, 0, DeleteConstant.defaultNumberLarge), new HokolAdapter<VUserGiftSendBean>()
		{
			@Override
			public void onSuccess(VUserGiftSendBean vUserGiftSend)
			{
				sendGiftAdapter.setShowEmpty(true);
				List<VUserGiftSendBean.VUserGiftOneSendBean> resultList = vUserGiftSend.getList();
				if (null != resultList)
				{
					sendGiftAdapter.setDataList(resultList);
				}
			}
		});
	}

	private class SendGiftAdapter extends WidgetRecyclerAdapter<VUserGiftSendBean.VUserGiftOneSendBean>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_user_account_send_gift;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			super.onBindViewHolder(holder, position);

			VUserGiftSendBean.VUserGiftOneSendBean sendBean = sList.get(position);

			// 头像
			ImageView avatarImageView = holder.get(R.id.circle_item_user_account_send_gift);
			Glide.with(getContext()).load(sendBean.getUser_logo()).error(R.drawable.global_load_failed).into(avatarImageView);

			// 内容
			holder.setText(R.id.tv_item_user_account_send_gift_title, String.format("送给%s %d红豆", sendBean.getUser_nickname(), sendBean.getCoin_num()));

			// 时间
			String dateStr = HokolTimeConvertUtil.stampToFormatDate(sendBean.getAdd_time() * 1000, Calendar.SECOND);
			holder.setText(R.id.tv_item_user_account_send_gift_sub, dateStr);
		}
	}
}
