package com.hokol.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hokol.R;
import com.hokol.adapter.HeadFootWrapperAdapter;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseFragment;
import com.hokol.bean.LivePersonInfo;
import com.hokol.viewhelper.MainNewsHotADHelper;
import com.hokol.viewhelper.MainNewsHotPointHelper;
import com.hokol.viewhelper.MainNewsHotRefreshHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 热点
 * @author yline 2017/2/13 --> 17:36
 * @version 1.0.0
 */
public class MainNewsHotFragment extends BaseFragment implements MainNewsHotADHelper.OnPageClickListener, MainNewsHotPointHelper.OnItemClickListener
{
	private HeadFootWrapperAdapter recycleAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_news_hot, container, false);
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

		CommonRecyclerAdapter tempAdapter = new MainNewsHotAdapter();
		recycleAdapter = new HeadFootWrapperAdapter(tempAdapter);

		// RecycleView
		initRecycleView(recycleAdapter);
		recyclerView.setAdapter(recycleAdapter);
		
		// SwipeRefreshLayout
		MainNewsHotRefreshHelper mainNewsHotRefreshHelper = new MainNewsHotRefreshHelper();
		mainNewsHotRefreshHelper.init((SwipeRefreshLayout) view.findViewById(R.id.swipe_main_news_hot_container));
	}

	/** RecycleView 添加头部 */
	private void initRecycleView(HeadFootWrapperAdapter wrapperAdapter)
	{
		// AD
		List<Integer> data = new ArrayList<>();
		data.add(R.drawable.delete_ad_img1);
		data.add(R.drawable.delete_ad_img2);
		data.add(R.drawable.delete_ad_img3);
		data.add(R.drawable.delete_ad_img4);
		data.add(R.drawable.delete_ad_img5);

		View adHeadParentView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_news_hot_ad, null);
		MainNewsHotADHelper mainNewsHotADHelper = new MainNewsHotADHelper();
		mainNewsHotADHelper.build().setResource(data).commit(getContext());
		mainNewsHotADHelper.initPoint((LinearLayout) adHeadParentView.findViewById(R.id.ll_main_hot_news_ad));
		mainNewsHotADHelper.initViewPagerView((ViewPager) adHeadParentView.findViewById(R.id.viewpager_main_hot_news_ad));
		mainNewsHotADHelper.startAutoRecycle();
		mainNewsHotADHelper.setListener(this);

		// 横向滑动
		View pointHeadParentView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_news_hot_point, null);
		MainNewsHotPointHelper mainNewsHotPointHelper = new MainNewsHotPointHelper();
		mainNewsHotPointHelper.init(pointHeadParentView);
		mainNewsHotPointHelper.setListener(this);

		wrapperAdapter.addHeaderView(adHeadParentView);
		wrapperAdapter.addHeaderView(pointHeadParentView);
	}

	private class MainNewsHotAdapter extends CommonRecyclerAdapter<LivePersonInfo>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_main_news_hot;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder item, int position)
		{
			item.setText(R.id.tv_main_news_hot_name, sList.get(position).getPersonName());
			item.setText(R.id.tv_main_news_hot_number, sList.get(position).getWatchingNumber() + "人");

			String state = sList.get(position).isLiving() ? "直播中" : "稍后直播";
			item.setText(R.id.tv_main_news_hot_state, state);

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
			{
				item.get(R.id.ll_main_news_hot_recycle).setBackground(sList.get(position).getPersonImage());
			}
			else
			{

				item.get(R.id.ll_main_news_hot_recycle).setBackgroundDrawable(sList.get(position).getPersonImage());
			}
		}
	}

	@Override
	public void onPagerClick(View v, int position)
	{
		IApplication.toast("ad position = " + position);
	}

	@Override
	public void onItemClick(int position)
	{
		IApplication.toast("point position = " + position);
	}
}
