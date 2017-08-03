package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.activity.StarDynamicActivity;
import com.hokol.activity.StarInfoActivity;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VHomeMainBean;
import com.hokol.medium.http.bean.VRecommendHomeBean;
import com.hokol.medium.http.bean.WHomeMainBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.ADWidget;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.hokol.util.IntentUtil;
import com.hokol.viewhelper.MainHomeHelper;
import com.yline.base.BaseFragment;
import com.yline.log.LogFileUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.adapter.HeadFootRecyclerAdapter;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yline 2017/2/13 --> 17:36
 * @version 1.0.0
 */
public class MainHomeRedFragment extends BaseFragment implements MainHomeFragment.OnHomeFilterCallback
{
	private MainHomeRedAdapter recycleAdapter;

	private SuperSwipeRefreshLayout superRefreshLayout;

	private int refreshedNumber;

	private ViewHolder viewHolder;

	private GridLayoutManager gridLayoutManager;

	private WHomeMainBean homeRedBean;

	private ViewGroup adViewGroup;

	public static MainHomeRedFragment newInstance()
	{
		Bundle args = new Bundle();

		MainHomeRedFragment fragment = new MainHomeRedFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_home_red, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		viewHolder = new ViewHolder(view);

		initView(view);
		initData();
	}
	
	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_home_red);
		gridLayoutManager = new GridLayoutManager(getContext(), 2);
		recyclerView.setLayoutManager(gridLayoutManager);
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(getContext())
		{

			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_null_size_little;
			}
		});

		recycleAdapter = new MainHomeRedAdapter();
		recycleAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VHomeMainBean.VHomeMainOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VHomeMainBean.VHomeMainOneBean vHomeMainOneBean, int position)
			{
				StarDynamicActivity.actionStart(getContext(), vHomeMainOneBean.getDt_id());
			}
		});
		recyclerView.setAdapter(recycleAdapter);

		// RecycleView
		initRecycleViewHead(recycleAdapter);

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
	
	private void initData()
	{
		homeRedBean = new WHomeMainBean(HttpEnum.UserTag.Red, 0, DeleteConstant.defaultNumberSmall);
		doRequest();

		// 请求 头部数据
		XHttpUtil.doRecommendHome(new HokolAdapter<VRecommendHomeBean>()
		{
			@Override
			public void onSuccess(final VRecommendHomeBean vRecommendHomeBean)
			{
				final List<VRecommendHomeBean.VRecommendHomeOneBean> resultList = vRecommendHomeBean.getList();
				if (null != resultList && 0 != resultList.size())
				{
					adViewGroup.removeAllViews(); // 清除所有数据

					ADWidget adWidget = new ADWidget()
					{
						@Override
						protected int getViewPagerHeight()
						{
							return UIScreenUtil.dp2px(getContext(), 120);
						}
					};
					View adView = adWidget.start(getContext(), resultList.size());
					adViewGroup.addView(adView);

					adWidget.setListener(new ADWidget.OnPageListener()
					{
						@Override
						public void onPageClick(View v, int position)
						{
							VRecommendHomeBean.VRecommendHomeOneBean recommendBean = resultList.get(position);

							if (recommendBean.getType() == VRecommendHomeBean.TypeUser)
							{
								StarInfoActivity.actionStart(getContext(), recommendBean.getInfo());
							}
							else if (recommendBean.getType() == VRecommendHomeBean.TypeUrl)
							{
								IntentUtil.openBrower(getContext(), recommendBean.getInfo());
							}
							else
							{
								LogFileUtil.v("Home Recommend type error");
							}
						}

						@Override
						public void onPageInstance(ImageView imageView, int position)
						{
							Glide.with(getContext()).load(resultList.get(position).getBanner_img()).centerCrop().error(R.drawable.global_load_failed).into(imageView);
						}
					});
				}
				else
				{
					LogFileUtil.v("Home Recommend list is null or size is zero");
				}
			}
		});
	}

	private void doRequest()
	{
		recycleAdapter.setShowEmpty(false);
		XHttpUtil.doHomeMain(homeRedBean, new HokolAdapter<VHomeMainBean>()
		{
			@Override
			public void onSuccess(VHomeMainBean vHomeMainBean)
			{
				recycleAdapter.setShowEmpty(true);

				List<VHomeMainBean.VHomeMainOneBean> resultList = vHomeMainBean.getList();
				if (null == resultList)
				{
					resultList = new ArrayList<>();
				}

				if (resultList.size() == 0)
				{
					gridLayoutManager.setSpanCount(1);
				}
				else
				{
					gridLayoutManager.setSpanCount(2);
				}

				recycleAdapter.setDataList(resultList);
				refreshedNumber = recycleAdapter.getDataSize();
				LogFileUtil.v("vHomeMainBean size = " + refreshedNumber);
			}
		});
	}

	@Override
	public void onAreaUpdate(String firstCode, List<String> secondCodeList)
	{
		LogFileUtil.v("onAreaUpdate first = " + firstCode + ",second = " + secondCodeList.toString());

		refreshedNumber = 0;
		homeRedBean.setNum1(refreshedNumber);
		homeRedBean.setLength(DeleteConstant.defaultNumberSmall);

		homeRedBean.setP_code(firstCode);
		homeRedBean.setC_code(secondCodeList);

		doRequest();
	}

	@Override
	public void onFilterUpdate(MainHomeHelper.FilterSex typeSex, MainHomeHelper.FilterRecommend typeRecommend)
	{
		LogFileUtil.v("onAreaUpdate typeSex = " + typeSex + ",typeRecommend = " + typeRecommend);

		refreshedNumber = 0;
		homeRedBean.setNum1(refreshedNumber);
		homeRedBean.setLength(DeleteConstant.defaultNumberSmall);

		homeRedBean.setUser_sex(typeSex.getIndex());
		homeRedBean.setUser_adv(typeRecommend.getIndex());

		doRequest();
	}

	/**
	 * RecycleView 添加头部
	 */
	private void initRecycleViewHead(HeadFootRecyclerAdapter wrapperAdapter)
	{
		// AD
		adViewGroup = new LinearLayout(getContext());
		wrapperAdapter.addHeadView(adViewGroup);

		// 分割线
		View divideView = new View(getContext());
		divideView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 6)));
		divideView.setBackgroundResource(R.color.hokolGrayLight);
		wrapperAdapter.addHeadView(divideView);
	}

	private class MainHomeRedAdapter extends WidgetRecyclerAdapter<VHomeMainBean.VHomeMainOneBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_main_home_red;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
		{
			super.onBindViewHolder(viewHolder, position);

			ImageView ivPic = viewHolder.get(R.id.iv_main_news_hot_pic);
			Glide.with(getContext()).load(sList.get(position).getDt_img()).centerCrop().placeholder(R.drawable.global_load_failed).error(R.drawable.global_load_failed).into(ivPic);
		}

		@Override
		public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
		{
			if (isShowEmpty)
			{
				viewHolder.get(R.id.rl_loading_cover).setVisibility(View.VISIBLE);
				viewHolder.setText(R.id.tv_loading_cover, "没有找到相关信息，减少筛选条件试一试^_^");
			}
			else
			{
				viewHolder.get(R.id.rl_loading_cover).setVisibility(View.INVISIBLE);
			}
		}
	}

}
