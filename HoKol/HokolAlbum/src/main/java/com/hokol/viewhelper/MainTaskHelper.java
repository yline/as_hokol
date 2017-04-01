package com.hokol.viewhelper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.medium.widget.DropMenuWidget;
import com.hokol.medium.widget.LabelClickableWidget;
import com.hokol.medium.widget.SecondaryWidget;
import com.hokol.medium.widget.labellayout.LabelFlowLayout;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.HeadFootRecycleAdapter;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.transform.CircleTransform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Task helper
 *
 * @author yline 2017/3/7 --> 16:02
 * @version 1.0.0
 */
public class MainTaskHelper
{
	private Context context;

	public MainTaskHelper(Context context)
	{
		this.context = context;
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 下拉菜单 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	private String headers[] = {"分类", "男/女", "地区"};

	private String provinceList[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州",
			"武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州",
			"武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};

	private DropMenuWidget dropMenuWidget;

	public void initTabDownMenuView(LinearLayout linearLayout)
	{
		dropMenuWidget = new DropMenuWidget();

		List<View> contentViewList = new ArrayList<>();

		View classifyView = initClassifyView();
		contentViewList.add(classifyView);

		View sexView = initSexView();
		contentViewList.add(sexView);
		
		View areaView = initAreaView();
		contentViewList.add(areaView);

		View dropMenuView = dropMenuWidget.start(context, Arrays.asList(headers), contentViewList);
		linearLayout.addView(dropMenuView);
	}

	// 分类
	private View initClassifyView()
	{
		View areaView = LayoutInflater.from(context).inflate(R.layout.fragment_main_task__menu_classify, null);

		final LabelFlowLayout labelFlowLayout = (LabelFlowLayout) areaView.findViewById(R.id.label_flow_main_task_menu_classify);
		LabelClickableWidget labelClickableWidget = new LabelClickableWidget(context)
		{
			@Override
			protected LabelFlowLayout getLabelFlowLayout()
			{
				return labelFlowLayout;
			}
		};
		labelClickableWidget.setDataList(Arrays.asList("全部", "网红", "主播", "演员", "模特", "歌手", "体育"));

		Button btnClassify = (Button) areaView.findViewById(R.id.btn_main_task_menu_classify);
		btnClassify.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dropMenuWidget.closeMenu();
			}
		});

		return areaView;
	}

	// 男女
	private View initSexView()
	{
		View sexView = LayoutInflater.from(context).inflate(R.layout.fragment_main_task__menu_sex, null);

		final LabelFlowLayout labelFlowLayout = (LabelFlowLayout) sexView.findViewById(R.id.label_flow_main_task_menu_sex);
		LabelClickableWidget labelClickableWidget = new LabelClickableWidget(context)
		{
			@Override
			protected LabelFlowLayout getLabelFlowLayout()
			{
				return labelFlowLayout;
			}
		};
		labelClickableWidget.setDataList(Arrays.asList("不限", "女神", "男神"));

		Button btnSex = (Button) sexView.findViewById(R.id.btn_main_task_menu_sex);
		btnSex.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dropMenuWidget.closeMenu();
			}
		});

		return sexView;
	}

	private SecondaryWidget secondaryWidget;

	// 地区
	private View initAreaView()
	{
		// View areaView = LayoutInflater.from(context).inflate(R.layout.fragment_main_task__menu_area, null);

		secondaryWidget = new SecondaryWidget();
		View secondaryView = secondaryWidget.start(context, new SecondaryWidget.OnSecondaryCallback()
		{
			@Override
			public void onSecondarySelected(String first, String second)
			{
				dropMenuWidget.closeMenu();
			}
		});
		
		return secondaryView;
	}

	public void setAreaData(Map<String, List<String>> map)
	{
		secondaryWidget.setDataMap(map);
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Refresh %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	public void initRefreshLayout(final SuperSwipeRefreshLayout swipeRefreshLayout)
	{
		swipeRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						swipeRefreshLayout.setRefreshing(false);
					}
				}, 3000);
			}
		});
		swipeRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						swipeRefreshLayout.setLoadMore(false);
					}
				}, 3000);
			}
		});
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% RecyclerView %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	private TaskRecycleAdapter taskRecycleAdapter;

	public void initRecycleView(RecyclerView recycleView)
	{
		recycleView.setLayoutManager(new LinearLayoutManager(context));
		recycleView.addItemDecoration(new DefaultLinearItemDecoration(context));
		taskRecycleAdapter = new TaskRecycleAdapter();
		
		recycleView.setAdapter(taskRecycleAdapter);
	}

	public void setOnRecyclerClickListener(CommonRecyclerAdapter.OnClickListener listener)
	{
		taskRecycleAdapter.setOnClickListener(listener);
	}

	public void setRecycleData()
	{
		List list = new ArrayList();
		for (int i = 0; i < 20; i++)
		{
			list.add("i");
		}
		taskRecycleAdapter.setDataList(list);
	}

	private class TaskRecycleAdapter extends HeadFootRecycleAdapter
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_task;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, final int position)
		{
			// 设置点击时间
			ImageView imageView = viewHolder.get(R.id.iv_item_main_task_avatar);
			Glide.with(context).load(DeleteConstant.url_default_avatar).centerCrop()
					.transform(new CircleTransform(context)).placeholder(R.mipmap.ic_launcher)
					.into(imageView);
		}
	}
}
