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

public class ContactVolumeRecordUnapplyFragment extends BaseFragment
{
	private VolumeRecordUnapplyAdapter recordUnapplyAdapter;

	public static ContactVolumeRecordUnapplyFragment newInstance()
	{
		Bundle args = new Bundle();

		ContactVolumeRecordUnapplyFragment fragment = new ContactVolumeRecordUnapplyFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_contact_volume_record_unapply, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_contact_volume_record_unapply);
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

		recordUnapplyAdapter = new VolumeRecordUnapplyAdapter();
		recyclerView.setAdapter(recordUnapplyAdapter);
		
		// 头部
		TextView textView = new TextView(getContext());
		textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		textView.setPadding(0, UIScreenUtil.dp2px(getContext(), 6), 0, UIScreenUtil.dp2px(getContext(), 6));
		textView.setText("交流卷使用说明>");
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		textView.setTextColor(ContextCompat.getColor(getContext(), R.color.hokolBlackSmall));
		recordUnapplyAdapter.addHeadView(textView);

		// 底部
		TextView textViewFoot = new TextView(getContext());
		textViewFoot.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		textViewFoot.setPadding(0, UIScreenUtil.dp2px(getContext(), 4), 0, UIScreenUtil.dp2px(getContext(), 10));
		textViewFoot.setText("以上为所有未使用且未过期的交流卷");
		textViewFoot.setGravity(Gravity.CENTER_HORIZONTAL);
		textViewFoot.setTextColor(ContextCompat.getColor(getContext(), R.color.hokolGray));
		recordUnapplyAdapter.addFootView(textViewFoot);

		recordUnapplyAdapter.setDataList(HttpEnum.getUserTagListAll());
	}

	private class VolumeRecordUnapplyAdapter extends HeadFootRecyclerAdapter<String>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_contact_volume_record_unapply;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{

		}
	}
}
