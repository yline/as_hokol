package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.NewsInfoActivity;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VNewsMultiplexBean;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.http.bean.WNewsMultiplexBean;
import com.hokol.medium.widget.recycler.OnRecyclerItemClickListener;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;
import com.hokol.viewhelper.MainNewsHelper;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;

import java.util.List;

public class MainNewsFragment extends BaseFragment
{
	private MainNewsHelper mainNewsHelper;

	private VNewsSingleBean mRecommendBean;

	private SuperSwipeRefreshLayout superSwipeRefreshLayout;

	private int loadedNewsNumber;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_news, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		mainNewsHelper = new MainNewsHelper(getContext());

		initView(view);
		initData();
	}

	private void initView(View view)
	{
		// recycleView
		mainNewsHelper.initRecycleView(view);
		mainNewsHelper.setOnRecycleItemClickListener(new OnRecyclerItemClickListener<VNewsSingleBean>()
		{
			@Override
			public void onClick(RecyclerView.ViewHolder viewHolder, VNewsSingleBean vNewsSingleBean, int position)
			{
				NewsInfoActivity.actionStart(getContext(), vNewsSingleBean.getUrl());
			}
		});

		// 推荐
		View recommendView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_news_recommend, null);
		mainNewsHelper.initRecommendView(recommendView);
		mainNewsHelper.setOnRecommendClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != mRecommendBean)
				{
					NewsInfoActivity.actionStart(getContext(), mRecommendBean.getUrl());
				}
			}
		});

		// 刷新
		superSwipeRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_news);
		superSwipeRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
		{
			@Override
			public void onAnimate()
			{
				XHttpUtil.doNewsRecommend(new XHttpAdapter<VNewsSingleBean>()
				{
					@Override
					public void onSuccess(VNewsSingleBean vNewsSingleBean)
					{
						IApplication.toast("刷新成功");
						superSwipeRefreshLayout.setRefreshing(false);
					}

					@Override
					public void onFailure(Exception ex)
					{
						super.onFailure(ex);
						superSwipeRefreshLayout.setRefreshing(false);
					}

					@Override
					public void onFailureCode(int code)
					{
						super.onFailureCode(code);
						superSwipeRefreshLayout.setRefreshing(false);
					}
				});
			}
		});
		superSwipeRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
		{
			@Override
			public void onAnimate()
			{
				WNewsMultiplexBean loadBean = new WNewsMultiplexBean(loadedNewsNumber, DeleteConstant.defaultNumberNormal);
				XHttpUtil.doNewsMultiplex(loadBean, new XHttpAdapter<VNewsMultiplexBean>()
				{
					@Override
					public void onSuccess(VNewsMultiplexBean vNewsMultiplexBean)
					{
						IApplication.toast("加载结束");
						loadedNewsNumber += vNewsMultiplexBean.getList().size();
						superSwipeRefreshLayout.setLoadMore(false);

						mainNewsHelper.addRecyclerData(vNewsMultiplexBean.getList());
					}

					@Override
					public void onFailure(Exception ex)
					{
						super.onFailure(ex);
						superSwipeRefreshLayout.setLoadMore(false);
					}

					@Override
					public void onFailureCode(int code)
					{
						super.onFailureCode(code);
						superSwipeRefreshLayout.setLoadMore(false);
					}
				});
			}
		});
	}

	private void initData()
	{
		// 推荐
		XHttpUtil.doNewsRecommend(new XHttpAdapter<VNewsSingleBean>()
		{
			@Override
			public void onSuccess(VNewsSingleBean vNewsSingleBean)
			{
				if (null == vNewsSingleBean)
				{
					return;
				}
				mRecommendBean = vNewsSingleBean;
				mainNewsHelper.updateRecommendData(vNewsSingleBean);
			}
		});

		// 多条新闻
		XHttpUtil.doNewsMultiplex(new WNewsMultiplexBean(loadedNewsNumber, DeleteConstant.defaultNumberNormal), new XHttpAdapter<VNewsMultiplexBean>()
		{
			@Override
			public void onSuccess(VNewsMultiplexBean vNewsMultiplexBean)
			{
				List<VNewsSingleBean> result = vNewsMultiplexBean.getList();
				if (null != result)
				{
					loadedNewsNumber += result.size();
					mainNewsHelper.setRecyclerData(result);
				}
			}
		});
	}
}
