package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseFragment;
import com.hokol.base.utils.UIResizeUtil;
import com.hokol.base.utils.UIScreenUtil;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.HeadFootRecycleAdapter;

import java.util.ArrayList;
import java.util.List;

public class StarInfoDynamicFragment extends BaseFragment
{
	private HeadFootRecycleAdapter starInfoDynamicAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_star_info_dynamic, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_star_info_dynamic);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(getContext())
		{
			@Override
			protected int getDividerResourceId()
			{
				return R.drawable.widget_recycler_divider_white_small;
			}
		});

		starInfoDynamicAdapter = new StarInfoDynamicAdapter();
		recyclerView.setAdapter(starInfoDynamicAdapter);

		starInfoDynamicAdapter.setOnClickListener(new CommonRecyclerAdapter.OnClickListener<String>()
		{
			@Override
			public void onClick(View view, String string, int position)
			{
				IApplication.toast("url = " + string);
			}
		});

		List<String> data = new ArrayList<>();
		for (int i = 0; i < 35; i++)
		{
			data.add(DeleteConstant.getUrlSquare());
		}
		starInfoDynamicAdapter.addAll(data);
	}

	private class StarInfoDynamicAdapter extends HeadFootRecycleAdapter<String>
	{
		private final int border_square;

		public StarInfoDynamicAdapter()
		{
			border_square = UIScreenUtil.getScreenWidth(getContext()) / 3 - 10;
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_star_info_dynamic;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
		{
			ImageView imageView = viewHolder.get(R.id.iv_item_star_info_dynamic);

			UIResizeUtil.build().setWidth(border_square).setHeight(border_square).commit(imageView);

			Glide.with(getContext()).load(sList.get(position)).into(imageView);
		}
	}
}






















