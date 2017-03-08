package com.hokol.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hokol.R;
import com.hokol.adapter.HeadFootRecycleAdapter;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseFragment;
import com.hokol.base.utils.UIResizeUtil;
import com.hokol.bean.LivePersonInfo;
import com.hokol.medium.widget.ADWidget;
import com.hokol.viewhelper.MainHomeRedRefreshHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 热点
 *
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

	private HeadFootRecycleAdapter recycleAdapter;

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
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_news_hot_container);
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
		
		recycleAdapter = new MainNewsHotAdapter();

		// RecycleView
		initRecycleView(recycleAdapter);

		List<LivePersonInfo> dataList = new ArrayList<>();
		for (int i = 0; i < 40; i++)
		{
			dataList.add(new LivePersonInfo(ContextCompat.getDrawable(getContext(), res[i % res.length])));
		}
		recycleAdapter.addAll(dataList);

		recyclerView.setAdapter(recycleAdapter);

		// SwipeRefreshLayout
		MainHomeRedRefreshHelper mainNewsHotRefreshHelper = new MainHomeRedRefreshHelper();
		mainNewsHotRefreshHelper.init((SwipeRefreshLayout) view.findViewById(R.id.swipe_main_news_hot_container));
	}

	/**
	 * RecycleView 添加头部
	 */
	private void initRecycleView(HeadFootRecycleAdapter wrapperAdapter)
	{
		// AD
		final List<Integer> data = new ArrayList<>();
		data.add(R.drawable.delete_ad_img1);
		data.add(R.drawable.delete_ad_img2);
		data.add(R.drawable.delete_ad_img3);
		data.add(R.drawable.delete_ad_img4);
		data.add(R.drawable.delete_ad_img5);

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
		wrapperAdapter.addHeaderView(adWidget.getParentView());
	}

	private class MainNewsHotAdapter extends HeadFootRecycleAdapter<LivePersonInfo>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_main_home_hot;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder item, int position)
		{
			item.setText(R.id.tv_main_news_hot_name, sList.get(position).getPersonName());
			item.setText(R.id.tv_main_news_hot_number, sList.get(position).getWatchingNumber() + "人");

			String state = sList.get(position).isLiving() ? "直播中" : "稍后直播";
			item.setText(R.id.tv_main_news_hot_state, state);

			ImageView ivPic = item.get(R.id.iv_main_news_hot_pic);
			resize(ivPic, sList.get(position).getPersonImage().getMinimumWidth(), sList.get(position).getPersonImage().getMinimumHeight());
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
			{
				ivPic.setBackground(sList.get(position).getPersonImage());
			}
			else
			{
				ivPic.setBackgroundDrawable(sList.get(position).getPersonImage());
			}
		}
	}

	private void resize(View view, int beforeWidth, int beforeHeight)
	{
		UIResizeUtil.build().setIsHeightAdapter(true).setWidth(360).setHeight(360 * beforeHeight / beforeWidth).commit(view);
	}
}
