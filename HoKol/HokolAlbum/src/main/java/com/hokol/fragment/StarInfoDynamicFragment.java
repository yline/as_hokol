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
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VDynamicUserAllBean;
import com.hokol.medium.http.bean.WDynamicUserAllBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.base.BaseFragment;
import com.yline.utils.UIResizeUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

public class StarInfoDynamicFragment extends BaseFragment
{
	private static final String KeyStarId = "DynamicStarId";

	private StarInfoDynamicAdapter starInfoDynamicAdapter;

	private SuperSwipeRefreshLayout superRefreshLayout;

	public static StarInfoDynamicFragment newInstance(String starId)
	{
		StarInfoDynamicFragment fragment = new StarInfoDynamicFragment();
		Bundle args = new Bundle();
		args.putString(KeyStarId, starId);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_star_info_dynamic, container, false);
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
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_star_info_dynamic);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(getContext())
		{
			@Override
			protected int getHeadNumber()
			{
				return 1;
			}

			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_null_size_little;
			}
		});

		starInfoDynamicAdapter = new StarInfoDynamicAdapter();
		starInfoDynamicAdapter.setShowEmpty(false);
		recyclerView.setAdapter(starInfoDynamicAdapter);
		starInfoDynamicAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VDynamicUserAllBean.VDynamicUserAllOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VDynamicUserAllBean.VDynamicUserAllOneBean vDynamicUserAllOneBean, int position)
			{
				StarDynamicActivity.actionStart(getContext(), vDynamicUserAllOneBean.getDt_id());
			}
		});
		
		View headView = new View(getContext());
		headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 6)));
		headView.setBackgroundResource(R.color.hokolGrayLittle);
		starInfoDynamicAdapter.addHeadView(headView);

		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_star_info_dynamic);
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
		String starId = getArguments().getString(KeyStarId);

		XHttpUtil.doDynamicUserAll(new WDynamicUserAllBean(starId, 0, DeleteConstant.defaultNumberSuper), new HokolAdapter<VDynamicUserAllBean>()
		{
			@Override
			public void onSuccess(VDynamicUserAllBean vDynamicUserAllBean)
			{
				List<VDynamicUserAllBean.VDynamicUserAllOneBean> resultList = vDynamicUserAllBean.getList();
				if (null != resultList)
				{
					starInfoDynamicAdapter.setDataList(resultList);
				}
			}
		});
	}

	private class StarInfoDynamicAdapter extends WidgetRecyclerAdapter<VDynamicUserAllBean.VDynamicUserAllOneBean>
	{
		private final int border_square;

		public StarInfoDynamicAdapter()
		{
			border_square = UIScreenUtil.getScreenWidth(getContext()) / 3;
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_star_info_dynamic;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder holder, final int position)
		{
			super.onBindViewHolder(holder, position);

			VDynamicUserAllBean.VDynamicUserAllOneBean dynamicBean = sList.get(position);

			ImageView imageView = holder.get(R.id.iv_item_star_info_dynamic);
			UIResizeUtil.build().setHeight(border_square).setWidth(border_square).commit(imageView);
			Glide.with(getContext()).load(dynamicBean.getDt_img()).error(R.drawable.global_load_failed).into(imageView);
		}
	}
}






















