package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserContactVolumeBean;
import com.hokol.medium.http.bean.WUserContactVolumeBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.Calendar;
import java.util.List;

public class ContactVolumeRecordPassedFragment extends BaseFragment
{
	private static final String KeyContactPassedUserId = "PassedUserId";

	private VolumeRecordPassedAdapter recordPassedAdapter;

	public static ContactVolumeRecordPassedFragment newInstance(String userId)
	{
		Bundle args = new Bundle();
		args.putString(KeyContactPassedUserId, userId);
		ContactVolumeRecordPassedFragment fragment = new ContactVolumeRecordPassedFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_contact_volume_record_passed, container, false);
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
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_contact_volume_record_passed);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_null_size_medium;
			}

			@Override
			protected int getHeadNumber()
			{
				return 1;
			}

			@Override
			protected int getFootNumber()
			{
				return 1;
			}
		});

		recordPassedAdapter = new VolumeRecordPassedAdapter();
		recyclerView.setAdapter(recordPassedAdapter);

		// 头部
		View headView = new TextView(getContext());
		headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 10)));
		recordPassedAdapter.addHeadView(headView);
		
		// 底部
		TextView textViewFoot = new TextView(getContext());
		textViewFoot.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		textViewFoot.setPadding(0, UIScreenUtil.dp2px(getContext(), 4), 0, UIScreenUtil.dp2px(getContext(), 10));
		textViewFoot.setText("仅展示最近一个月已过期的交流卷");
		textViewFoot.setGravity(Gravity.CENTER_HORIZONTAL);
		textViewFoot.setTextColor(ContextCompat.getColor(getContext(), R.color.hokolGray));
		recordPassedAdapter.addFootView(textViewFoot);
	}

	private void initData()
	{
		String userId = getArguments().getString(KeyContactPassedUserId);
		XHttpUtil.doUserContactVolumeUnapply(new WUserContactVolumeBean(userId), new XHttpAdapter<VUserContactVolumeBean>()
		{
			@Override
			public void onSuccess(VUserContactVolumeBean vUserContactVolumeBean)
			{
				List<VUserContactVolumeBean.VUserContactVolumeOneBean> result = vUserContactVolumeBean.getList();
				if (null != result)
				{
					if (getActivity() instanceof ContactVolumeRecordUnapplyFragment.OnLoadRecordFinishCallback)
					{
						((ContactVolumeRecordUnapplyFragment.OnLoadRecordFinishCallback) getActivity()).onLoadFinish(result.size(), 2);
					}
					recordPassedAdapter.setDataList(result);
				}
			}
		});
	}


	private class VolumeRecordPassedAdapter extends WidgetRecyclerAdapter<VUserContactVolumeBean.VUserContactVolumeOneBean>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_contact_volume_record_passed;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			super.onBindViewHolder(holder, position);

			long expireTime = sList.get(position).getExpire_time();

			// 到期时间
			Calendar instance = Calendar.getInstance();
			instance.setTimeInMillis(expireTime * 1000);
			holder.setText(R.id.tv_contact_volume_record_passed_time, String.format("%d-%d-%d到期", instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH)));
		}

		@Override
		public int getEmptyItemRes()
		{
			return R.layout.fragment_contact_volume_record_passed_empty;
		}

		@Override
		public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
		{
			super.onBindEmptyViewHolder(viewHolder, position);
		}
	}
}
