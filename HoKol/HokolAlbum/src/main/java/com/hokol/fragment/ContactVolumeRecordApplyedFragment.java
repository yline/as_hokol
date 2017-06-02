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
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.base.BaseFragment;
import com.yline.utils.UIScreenUtil;
import com.yline.view.common.HeadFootRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

public class ContactVolumeRecordApplyedFragment extends BaseFragment
{
	private VolumeRecordApplyedAdapter recordApplyedAdapter;

	public static ContactVolumeRecordApplyedFragment newInstance()
	{
		Bundle args = new Bundle();

		ContactVolumeRecordApplyedFragment fragment = new ContactVolumeRecordApplyedFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_contact_volume_record_applyed, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_contact_volume_record_applyed);
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

		recordApplyedAdapter = new VolumeRecordApplyedAdapter();
		recyclerView.setAdapter(recordApplyedAdapter);

		// 头部
		View headView = new TextView(getContext());
		headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 10)));
		recordApplyedAdapter.addHeadView(headView);

		// 底部
		TextView textViewFoot = new TextView(getContext());
		textViewFoot.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		textViewFoot.setPadding(0, UIScreenUtil.dp2px(getContext(), 4), 0, UIScreenUtil.dp2px(getContext(), 10));
		textViewFoot.setText("仅展示最近一个月已使用的交流卷");
		textViewFoot.setGravity(Gravity.CENTER_HORIZONTAL);
		textViewFoot.setTextColor(ContextCompat.getColor(getContext(), R.color.hokolGray));
		recordApplyedAdapter.addFootView(textViewFoot);

		recordApplyedAdapter.setDataList(HttpEnum.getUserTagListAll());
	}

	private class VolumeRecordApplyedAdapter extends HeadFootRecyclerAdapter<String>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_contact_volume_record_applyed;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{

		}
	}
}
