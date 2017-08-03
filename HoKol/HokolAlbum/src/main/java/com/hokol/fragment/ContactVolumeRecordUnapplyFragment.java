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
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.base.BaseFragment;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.Calendar;
import java.util.List;

public class ContactVolumeRecordUnapplyFragment extends BaseFragment
{
	private static final String KeyContactUnapplyUserId = "UnapplyUserId";

	private VolumeRecordUnapplyAdapter recordUnapplyAdapter;

	public static ContactVolumeRecordUnapplyFragment newInstance(String userId)
	{
		Bundle args = new Bundle();
		args.putString(KeyContactUnapplyUserId, userId);
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
		initData();
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
	}

	private void initData()
	{
		recordUnapplyAdapter.setShowEmpty(false);

		String userId = getArguments().getString(KeyContactUnapplyUserId);
		XHttpUtil.doUserContactVolumeUnapply(new WUserContactVolumeBean(userId), new HokolAdapter<VUserContactVolumeBean>()
		{
			@Override
			public void onSuccess(VUserContactVolumeBean vUserContactVolumeBean)
			{
				List<VUserContactVolumeBean.VUserContactVolumeOneBean> result = vUserContactVolumeBean.getList();
				if (null != result)
				{
					if (getActivity() instanceof OnLoadRecordFinishCallback)
					{
						((OnLoadRecordFinishCallback) getActivity()).onLoadFinish(result.size(), 0);
					}
					recordUnapplyAdapter.setDataList(result);
				}
				else
				{
					if (getActivity() instanceof OnLoadRecordFinishCallback)
					{
						((OnLoadRecordFinishCallback) getActivity()).onLoadFinish(0, 0);
					}
				}
			}
		});
	}

	public interface OnLoadRecordFinishCallback
	{
		/**
		 * @param number   加载的个数
		 * @param position 对应TabLayout的位置
		 */
		void onLoadFinish(int number, int position);
	}

	private class VolumeRecordUnapplyAdapter extends WidgetRecyclerAdapter<VUserContactVolumeBean.VUserContactVolumeOneBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_contact_volume_record_unapply;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			long expireTime = sList.get(position).getExpire_time();

			// 到期时间
			Calendar instance = Calendar.getInstance();
			instance.setTimeInMillis(expireTime * 1000);
			holder.setText(R.id.tv_contact_volume_record_unpply_time, String.format("%d-%d-%d到期", instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH)));

			// 剩余时间
			long diffTime = expireTime - System.currentTimeMillis() / 1000;
			holder.setText(R.id.tv_contact_volume_record_unpply_remainder, String.format("(还有%d天到期)", diffTime / 86400));
		}
	}
}
