package com.hokol.viewhelper;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.bean.VTaskMainAll;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.DropMenuWidget;
import com.hokol.medium.widget.FlowAbleWidget;
import com.hokol.medium.widget.SecondaryWidget;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.layout.label.LabelFlowLayout;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

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
	private Context sContext;

	public MainTaskHelper(Context context)
	{
		this.sContext = context;
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 下拉菜单 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	private String headers[] = {"分类", "男/女", "地区"};

	private DropMenuWidget dropMenuWidget;

	public void initTabDownMenuView(TabLayout menuTabLayout)
	{
		dropMenuWidget = new DropMenuWidget(sContext, menuTabLayout);

		List<View> contentViewList = new ArrayList<>();

		View classifyView = initClassifyView();
		contentViewList.add(classifyView);

		View sexView = initSexView();
		contentViewList.add(sexView);

		initAreaView(contentViewList);

		dropMenuWidget.start(Arrays.asList(headers), contentViewList);
	}

	// 分类
	private View initClassifyView()
	{
		View areaView = LayoutInflater.from(sContext).inflate(R.layout.fragment_main_task__menu_classify, null);

		final LabelFlowLayout labelFlowLayout = (LabelFlowLayout) areaView.findViewById(R.id.label_flow_main_task_menu_classify);
		labelFlowLayout.setLabelGravity(FlowLayout.LabelGravity.EQUIDISTANT);
		labelFlowLayout.setMaxCountEachLine(4);
		final FlowAbleWidget flowAbleWidget = new FlowAbleWidget(sContext, labelFlowLayout)
		{
			@Override
			protected int getItemResourceId()
			{
				return R.layout.activity_user_info__flow_able;
			}
		};
		flowAbleWidget.setMaxSelectCount(1);
		flowAbleWidget.setMinSelectCount(1);
		flowAbleWidget.setDataList(HttpEnum.getUserTagListAll());
		flowAbleWidget.addSelectedPosition(0);

		Button btnClassify = (Button) areaView.findViewById(R.id.btn_main_task_menu_classify);
		btnClassify.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != taskFilterCallback)
				{
					int position = flowAbleWidget.getSelectedFirst();
					taskFilterCallback.onFilterClassify(HttpEnum.getUserTag(position));
				}
				dropMenuWidget.closeMenu();
			}
		});

		return areaView;
	}

	// 男女
	private View initSexView()
	{
		View sexView = LayoutInflater.from(sContext).inflate(R.layout.fragment_main_task__menu_sex, null);

		final LabelFlowLayout labelFlowLayout = (LabelFlowLayout) sexView.findViewById(R.id.label_flow_main_task_menu_sex);
		final FlowAbleWidget labelClickableWidget = new FlowAbleWidget(sContext, labelFlowLayout)
		{
			@Override
			protected int getItemResourceId()
			{
				return R.layout.fragment_main_task__flow_able;
			}
		};
		labelClickableWidget.setMaxSelectCount(1);
		labelClickableWidget.setMinSelectCount(1);
		labelClickableWidget.setDataList(HttpEnum.getUserSexListAll());
		labelClickableWidget.addSelectedPosition(0);
		
		Button btnSex = (Button) sexView.findViewById(R.id.btn_main_task_menu_sex);
		btnSex.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != taskFilterCallback)
				{
					int position = labelClickableWidget.getSelectedFirst();
					taskFilterCallback.onFilterSex(HttpEnum.getUserSex(position));
				}
				dropMenuWidget.closeMenu();
			}
		});

		return sexView;
	}

	private SecondaryWidget secondaryWidget;

	// 地区
	private void initAreaView(List<View> contentViewList)
	{
		secondaryWidget = new SecondaryWidget(sContext, contentViewList);
		secondaryWidget.setOnConfirmListener(new SecondaryWidget.OnConfirmListener()
		{
			@Override
			public void onConfirmClick(String firstName, List<String> secondList, String titleName)
			{
				if (null != taskFilterCallback)
				{
					taskFilterCallback.onFilterArea(firstName, secondList);
				}

				dropMenuWidget.updateTitle(2, titleName);
				dropMenuWidget.closeMenu();
			}
		});
	}

	public void setAreaData(Map<String, List<String>> map)
	{
		secondaryWidget.setData(map);
	}

	private OnTaskFilterCallback taskFilterCallback;

	public void setOnTaskFilterCallback(OnTaskFilterCallback taskFilterCallback)
	{
		this.taskFilterCallback = taskFilterCallback;
	}

	public interface OnTaskFilterCallback
	{
		void onFilterClassify(HttpEnum.UserTag userTag);

		void onFilterSex(HttpEnum.UserSex userSex);

		void onFilterArea(String first, List<String> second);
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
		recycleView.setLayoutManager(new LinearLayoutManager(sContext));
		taskRecycleAdapter = new TaskRecycleAdapter();
		
		recycleView.setAdapter(taskRecycleAdapter);
	}

	public void setOnRecyclerClickListener(OnRecyclerItemClickListener listener)
	{
		taskRecycleAdapter.setOnRecyclerItemClickListener(listener);
	}

	public void setRecyclerData(List<VTaskMainAll.TaskMainAllOne> result)
	{
		taskRecycleAdapter.setDataList(result);
	}

	public void setRecyclerShowEmpty(boolean isShowEmpty)
	{
		taskRecycleAdapter.setShowEmpty(isShowEmpty);
	}

	private class TaskRecycleAdapter extends WidgetRecyclerAdapter<VTaskMainAll.TaskMainAllOne>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_main_task;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
		{
			super.onBindViewHolder(viewHolder, position);

			// 价格 + 人数
			viewHolder.setText(R.id.tv_item_main_task_price, String.format("￥%d × %d", sList.get(position).getTask_fee(), sList.get(position).getEmployee_num()));

			// 标题
			viewHolder.setText(R.id.tv_item_main_task_title, sList.get(position).getTask_title());

			// 头像
			ImageView imageView = viewHolder.get(R.id.iv_item_main_task_avatar);
			Glide.with(sContext).load(sList.get(position).getUser_logo()).error(R.drawable.global_load_failed).into(imageView);

			// 用户名
			viewHolder.setText(R.id.tv_item_main_task_user, sList.get(position).getUser_nickname());

			// 报名人数
			viewHolder.setText(R.id.tv_item_main_task_sign_ups, String.format("%d人报名", sList.get(position).getEmployee_num()));

			// 截至时间
			viewHolder.setText(R.id.tv_item_main_task_time, sList.get(position).getTask_rem_time() + "");
		}

		@Override
		public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
		{
			viewHolder.get(R.id.btn_loading_cover).setVisibility(View.INVISIBLE);
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
