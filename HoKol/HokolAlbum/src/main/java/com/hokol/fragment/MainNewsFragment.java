package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonParseException;
import com.hokol.R;
import com.hokol.activity.NewsInfoActivity;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VNewsMultiplexBean;
import com.hokol.medium.http.bean.VNewsRecommendBean;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.http.bean.WNewsMultiplexBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.viewhelper.MainNewsHelper;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import org.json.JSONException;

import java.util.List;

public class MainNewsFragment extends BaseFragment
{
	private MainNewsHelper mainNewsHelper;

	private VNewsRecommendBean mRecommendBean;

	private SuperSwipeRefreshLayout superSwipeRefreshLayout;

	private int loadedNewsNumber;

	public static MainNewsFragment newInstance()
	{
		Bundle args = new Bundle();

		MainNewsFragment fragment = new MainNewsFragment();
		fragment.setArguments(args);
		return fragment;
	}

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
			public void onItemClick(RecyclerViewHolder viewHolder, VNewsSingleBean vNewsSingleBean, int position)
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
					NewsInfoActivity.actionStart(getContext(), mRecommendBean.getInfo());
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
				XHttpUtil.doNewsRecommend(new XHttpAdapter<VNewsRecommendBean>()
				{
					@Override
					public void onSuccess(VNewsRecommendBean vNewsRecommendBean)
					{
						IApplication.toast("刷新成功");
					}

					@Override
					public void onFailure(Exception ex, boolean isDebug)
					{
						super.onFailure(ex, isDebug);
						superSwipeRefreshLayout.setRefreshing(false);
					}

					@Override
					public void onSuccess(int code, String data) throws JSONException, JsonParseException
					{
						superSwipeRefreshLayout.setRefreshing(false);
						super.onSuccess(code, data);
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
					public void onSuccess(int code, String data) throws JSONException, JsonParseException
					{
						super.onSuccess(code, data);
						superSwipeRefreshLayout.setLoadMore(false);
					}

					@Override
					public void onSuccess(VNewsMultiplexBean vNewsMultiplexBean)
					{
						IApplication.toast("加载结束");
						loadedNewsNumber += vNewsMultiplexBean.getList().size();

						mainNewsHelper.addRecyclerData(vNewsMultiplexBean.getList());
					}

					@Override
					public void onFailure(Exception ex, boolean isDebug)
					{
						super.onFailure(ex, isDebug);
						superSwipeRefreshLayout.setLoadMore(false);
					}
				});
			}
		});
	}

	private void initData()
	{
		// 推荐
		XHttpUtil.doNewsRecommend(new XHttpAdapter<VNewsRecommendBean>()
		{
			@Override
			public void onSuccess(VNewsRecommendBean vNewsRecommendBean)
			{
				if (null == vNewsRecommendBean)
				{
					return;
				}
				mRecommendBean = vNewsRecommendBean;
				mainNewsHelper.updateRecommendData(vNewsRecommendBean);
			}
		});

		// 多条新闻
		XHttpUtil.doNewsMultiplex(new WNewsMultiplexBean(0, DeleteConstant.defaultNumberNormal), new XHttpAdapter<VNewsMultiplexBean>()
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
