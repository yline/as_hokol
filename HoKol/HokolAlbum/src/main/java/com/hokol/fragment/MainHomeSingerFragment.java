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
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VHomeMainBean;
import com.hokol.medium.http.bean.WHomeMainBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.hokol.viewhelper.MainHomeHelper;
import com.yline.base.BaseFragment;
import com.yline.log.LogFileUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainHomeSingerFragment extends BaseFragment implements MainHomeFragment.OnHomeFilterCallback
{
	private MainHomeSingerFragment.MainHomeSingerAdapter mainHomeSingerAdapter;

	private SuperSwipeRefreshLayout superRefreshLayout;

	private int refreshedNumber;

	private GridLayoutManager gridLayoutManager;

	private WHomeMainBean wHomeMainBean;

	public static MainHomeSingerFragment newInstance()
	{
		MainHomeSingerFragment fragment = new MainHomeSingerFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_home_singer, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		initView(view);

		initData();
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_home_singer);

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

		mainHomeSingerAdapter = new MainHomeSingerFragment.MainHomeSingerAdapter();
		mainHomeSingerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VHomeMainBean.VHomeMainOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VHomeMainBean.VHomeMainOneBean bean, int position)
			{
				StarDynamicActivity.actionStart(getContext(), bean.getDt_id());
			}
		});
		recyclerView.setAdapter(mainHomeSingerAdapter);

		// 分割线
		View divideView = new View(getContext());
		divideView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 6)));
		divideView.setBackgroundResource(R.color.hokolGrayLight);
		mainHomeSingerAdapter.addHeadView(divideView);

		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_home_singer);
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
		wHomeMainBean = new WHomeMainBean(HttpEnum.UserTag.Singer, 0, DeleteConstant.defaultNumberNormal);
		doRequest();
	}

	private void doRequest()
	{
		mainHomeSingerAdapter.setShowEmpty(false);
		XHttpUtil.doHomeMain(wHomeMainBean, new HokolAdapter<VHomeMainBean>()
		{
			@Override
			public void onSuccess(VHomeMainBean vHomeMainBean)
			{
				mainHomeSingerAdapter.setShowEmpty(true);

				if (null == vHomeMainBean.getList())
				{
					vHomeMainBean.setList(new ArrayList<VHomeMainBean.VHomeMainOneBean>());
				}

				if (vHomeMainBean.getList().size() == 0)
				{
					gridLayoutManager.setSpanCount(1);
				}
				else
				{
					gridLayoutManager.setSpanCount(2);
				}

				mainHomeSingerAdapter.setDataList(vHomeMainBean.getList());

				refreshedNumber = mainHomeSingerAdapter.getDataSize();
				LogFileUtil.v("vHomeMainBean size = " + refreshedNumber);
			}
		});
	}

	@Override
	public void onAreaUpdate(String firstCode, List<String> secondCodeList)
	{
		LogFileUtil.v("onAreaUpdate first = " + firstCode + ",second = " + secondCodeList.toString());

		refreshedNumber = 0;
		wHomeMainBean.setNum1(refreshedNumber);
		wHomeMainBean.setLength(DeleteConstant.defaultNumberLarge);

		wHomeMainBean.setP_code(firstCode);
		wHomeMainBean.setC_code(secondCodeList);

		doRequest();
	}

	@Override
	public void onFilterUpdate(MainHomeHelper.FilterSex typeSex, MainHomeHelper.FilterRecommend typeRecommend)
	{
		LogFileUtil.v("onAreaUpdate typeSex = " + typeSex + ",typeRecommend = " + typeRecommend);

		refreshedNumber = 0;
		wHomeMainBean.setNum1(refreshedNumber);
		wHomeMainBean.setLength(DeleteConstant.defaultNumberLarge);

		wHomeMainBean.setUser_sex(typeSex.getIndex());
		wHomeMainBean.setUser_adv(typeRecommend.getIndex());

		doRequest();
	}

	private class MainHomeSingerAdapter extends WidgetRecyclerAdapter<VHomeMainBean.VHomeMainOneBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_main_home_singer;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
		{
			super.onBindViewHolder(viewHolder, position);

			ImageView imageView = viewHolder.get(R.id.iv_item_main_home_singer);
			Glide.with(getContext()).load(sList.get(position).getDt_img()).centerCrop().placeholder(R.drawable.global_load_failed).error(R.drawable.global_load_failed).into(imageView);
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
