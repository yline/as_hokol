package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.activity.DynamicInfoActivity;
import com.hokol.adapter.HeadFootRecycleAdapter;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseFragment;
import com.hokol.base.utils.UIScreenUtil;
import com.hokol.custom.DefaultGridItemDecoration;
import com.hokol.medium.widget.ADWidget;
import com.hokol.viewhelper.MainHomeRedRefreshHelper;

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
			protected int getDividerResourceId()
			{
				return R.drawable.recycle_divider_white_little;
			}

			@Override
			protected int getHeadNumber()
			{
				return 2;
			}
		});

		recycleAdapter = new MainNewsHotAdapter();
		recyclerView.setAdapter(recycleAdapter);
		recycleAdapter.setOnClickListener(new MainNewsHotAdapter.OnClickListener<String>()
		{
			@Override
			public void onClick(View view, String string, int position)
			{
				IApplication.toast("string = " + string);
				DynamicInfoActivity.actionStart(getContext());
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

		// SwipeRefreshLayout
		MainHomeRedRefreshHelper mainNewsHotRefreshHelper = new MainHomeRedRefreshHelper();
		mainNewsHotRefreshHelper.init((SwipeRefreshLayout) view.findViewById(R.id.swipe_main_home_red));
	}

	/**
	 * RecycleView 添加头部
	 */
	private void initRecycleViewHead(HeadFootRecycleAdapter wrapperAdapter)
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
		adWidget.start(getContext(), 5);
		adWidget.setListener(new ADWidget.OnPageListener()
		{
			@Override
			public void onPageClick(View v, int position)
			{
				IApplication.toast("AD position = " + position);
			}

			@Override
			public void onPageInstance(ImageView imageView, int position)
			{
				Glide.with(getContext()).load(DeleteConstant.getUrlRec()).centerCrop().placeholder(R.drawable.global_load_failed).into(imageView);
			}
		});
		wrapperAdapter.addHeaderView(adWidget.getParentView());

		// 分割线
		View divideView = new View(getContext());
		divideView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 10)));
		divideView.setBackgroundResource(android.R.color.darker_gray);
		wrapperAdapter.addHeaderView(divideView);
	}

	private class MainNewsHotAdapter extends HeadFootRecycleAdapter<String>
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
