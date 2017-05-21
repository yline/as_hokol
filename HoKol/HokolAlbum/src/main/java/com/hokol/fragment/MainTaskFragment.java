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

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.activity.TaskAssignedActivity;
import com.hokol.activity.TaskDetailActivity;
import com.hokol.activity.TaskPublishActivity;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VAreaAllBean;
import com.hokol.medium.widget.ADWidget;
import com.hokol.medium.widget.recycler.OnRecyclerItemClickListener;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;
import com.hokol.viewhelper.MainTaskHelper;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.common.RecyclerViewHolder;

import java.util.List;
import java.util.Map;

public class MainTaskFragment extends BaseFragment
{
	private MainTaskHelper mainTaskHelper;

	private SuperSwipeRefreshLayout superRefreshLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_task, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		mainTaskHelper = new MainTaskHelper(getContext());
		initView(view);

		initData();
	}

	private void initView(View parentView)
	{
		// 广告
		LinearLayout linearLayout = (LinearLayout) parentView.findViewById(R.id.ll_main_task_ad);
		ADWidget adWidget = new ADWidget()
		{
			@Override
			protected int getViewPagerHeight()
			{
				return UIScreenUtil.dp2px(getContext(), 150);
			}
		};
		View adView = adWidget.start(getContext(), 3);
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
				Glide.with(getContext()).load(DeleteConstant.getUrlRec()).placeholder(R.drawable.global_load_failed).error(R.drawable.global_load_failed).into(imageView);
			}
		});
		linearLayout.addView(adView);

		// 下拉菜单
		TabLayout menuTabLayout = (TabLayout) parentView.findViewById(R.id.tab_main_task_menu);
		mainTaskHelper.initTabDownMenuView(menuTabLayout);

		// 刷新
		superRefreshLayout = (SuperSwipeRefreshLayout) parentView.findViewById(R.id.swipe_main_task);
		mainTaskHelper.initRefreshLayout(superRefreshLayout);

		// 内容
		RecyclerView recycleView = (RecyclerView) parentView.findViewById(R.id.recycle_main_task);
		mainTaskHelper.initRecycleView(recycleView);
		mainTaskHelper.setOnRecyclerClickListener(new OnRecyclerItemClickListener()
		{
			@Override
			public void onClick(RecyclerViewHolder viewHolder, Object o, int position)
			{
				TaskDetailActivity.actionStart(getContext());
			}
		});

		mainTaskHelper.setOnTaskFilterCallback(new MainTaskHelper.OnTaskFilterCallback()
		{
			@Override
			public void onFilterClassify(HttpEnum.UserTag userTag)
			{
				LogFileUtil.v("userTag = " + userTag);
			}

			@Override
			public void onFilterSex(HttpEnum.UserSex userSex)
			{
				LogFileUtil.v("userSex = " + userSex);
			}

			@Override
			public void onFilterArea(String first, List<String> second)
			{
				LogFileUtil.v("first = " + first + ", second = " + second.toString());
			}
		});

		// 头部
		parentView.findViewById(R.id.iv_main_task_action_task).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TaskPublishActivity.actionStart(getContext());
			}
		});
		parentView.findViewById(R.id.iv_main_task_action_history).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TaskAssignedActivity.actionStart(getContext());
			}
		});
	}

	private void initData()
	{
		XHttpUtil.doAreaAll(new XHttpAdapter<VAreaAllBean>()
		{
			@Override
			public void onSuccess(VAreaAllBean vAreaAllBean)
			{
				Map provinceMap = vAreaAllBean.getList();

				mainTaskHelper.setAreaData(provinceMap);
			}
		});
	}
}
