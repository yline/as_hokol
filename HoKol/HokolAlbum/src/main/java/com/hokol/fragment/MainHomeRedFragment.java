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
import com.hokol.activity.StarDynamicActivity;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseFragment;
import com.hokol.base.utils.UIScreenUtil;
import com.hokol.medium.widget.ADWidget;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.HeadFootRecyclerAdapter;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yline 2017/2/13 --> 17:36
 * @version 1.0.0
 */
public class MainHomeRedFragment extends BaseFragment
{
	private final static int[] res = new int[]{
			R.drawable.delete_ad_img1,
			R.drawable.delete_ad_img2,
			R.drawable.delete_ad_img3,
			R.drawable.delete_ad_img4,
			R.drawable.delete_ad_img5,
	};

	private MainNewsHotAdapter recycleAdapter;

	private SuperSwipeRefreshLayout superRefreshLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_home_red, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}
	
	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_home_red);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(getContext())
		{

			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_recycler_divider_white_small;
			}

			@Override
			protected int getHeadNumber()
			{
				return 2;
			}
		});

		recycleAdapter = new MainNewsHotAdapter();
		recyclerView.setAdapter(recycleAdapter);
		recycleAdapter.setOnClickListener(new HeadFootRecyclerAdapter.OnClickListener<String>()
		{
			@Override
			public void onClick(View view, String string, int position)
			{
				StarDynamicActivity.actionStart(getContext());
			}
		});

		// RecycleView
		initRecycleViewHead(recycleAdapter);

		List<String> dataList = new ArrayList<>();
		for (int i = 0; i < 35; i++)
		{
			dataList.add(DeleteConstant.getUrlSquare());
		}
		recycleAdapter.setDataList(dataList);

		// 下拉刷新
		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_home_red);
		superRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
		{
			@Override
			public void onAnimate()
			{
				IApplication.toast("正在加载");
				IApplication.getHandler().postDelayed(new Runnable()
				{
					@Override
					public void run()
					{
						IApplication.toast("刷新结束");
						superRefreshLayout.setRefreshing(false);
					}
				}, 2000);
			}
		});
		superRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
		{
			@Override
			public void onAnimate()
			{
				IApplication.toast("正在加载");
				IApplication.getHandler().postDelayed(new Runnable()
				{
					@Override
					public void run()
					{
						IApplication.toast("刷新结束");
						superRefreshLayout.setLoadMore(false);
					}
				}, 2000);
			}
		});
	}

	/**
	 * RecycleView 添加头部
	 */
	private void initRecycleViewHead(HeadFootRecyclerAdapter wrapperAdapter)
	{
		// AD
		ADWidget adWidget = new ADWidget()
		{
			@Override
			protected int getViewPagerHeight()
			{
				return UIScreenUtil.dp2px(getContext(), 150);
			}
		};
		View adView = adWidget.start(getContext(), 5);
		adWidget.setListener(new ADWidget.OnPageListener()
		{
			@Override
			public void onPageClick(View v, int position)
			{
				IApplication.toast("广告位置 = " + position);
			}

			@Override
			public void onPageInstance(ImageView imageView, int position)
			{
				Glide.with(getContext()).load(DeleteConstant.getUrlRec()).centerCrop().error(R.mipmap.global_load_failed).into(imageView);
			}
		});
		wrapperAdapter.addHeadView(adView);

		// 分割线
		View divideView = new View(getContext());
		divideView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 10)));
		divideView.setBackgroundResource(android.R.color.darker_gray);
		wrapperAdapter.addHeadView(divideView);
	}

	private class MainNewsHotAdapter extends HeadFootRecyclerAdapter<String>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_main_home_red;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder item, int position)
		{
			ImageView ivPic = item.get(R.id.iv_main_news_hot_pic);
			Glide.with(getContext()).load(sList.get(position)).centerCrop()
					.placeholder(R.mipmap.ic_launcher)
					.into(ivPic);
		}
	}

}
