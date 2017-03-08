package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.base.common.BaseFragment;
import com.hokol.medium.widget.ADWidget;
import com.hokol.viewhelper.MainTaskHelper;

import java.util.ArrayList;
import java.util.List;

public class MainTaskFragment extends BaseFragment
{
	private MainTaskHelper mainTaskHelper;

	private List<Integer> data;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_task, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
	}

	private void initView(View parentView)
	{
		data = new ArrayList<>();
		data.add(R.drawable.delete_ad_img1);
		data.add(R.drawable.delete_ad_img2);
		data.add(R.drawable.delete_ad_img3);
		data.add(R.drawable.delete_ad_img4);
		data.add(R.drawable.delete_ad_img5);

		LinearLayout linearLayout = (LinearLayout) parentView.findViewById(R.id.ll_main_task_ad);

		ADWidget adWidget = new ADWidget()
		{
			@Override
			protected int getViewPagerHeight()
			{
				return 300;
			}
		};
		adWidget.start(getContext(), 5);
		adWidget.setListener(new ADWidget.OnPageListener()
		{
			@Override
			public void onPageClick(View v, int position)
			{
				IApplication.toast("position = " + position);
			}

			@Override
			public void onPageInstance(ImageView imageView, int position)
			{
				imageView.setImageResource(data.get(position));
			}
		});
		adWidget.attach(linearLayout);

		TabLayout tabLayout = (TabLayout) parentView.findViewById(R.id.tab_layout_main_task_menu);
		mainTaskHelper = new MainTaskHelper();
		mainTaskHelper.initTabDownMenuView(getContext(), tabLayout);

		RecyclerView recycleView = (RecyclerView) parentView.findViewById(R.id.recycle_main_task);
		mainTaskHelper.initRecycleView(getContext(), recycleView);
		mainTaskHelper.setRecycleData();
	}
}
