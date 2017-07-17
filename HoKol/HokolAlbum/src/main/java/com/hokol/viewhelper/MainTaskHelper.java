package com.hokol.viewhelper;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.bean.VAreaAllBean;
import com.hokol.medium.http.bean.VTaskMainAllBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.DropMenuWidget;
import com.hokol.medium.widget.FlowAbleWidget;
import com.hokol.medium.widget.SecondaryWidget;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.hokol.util.HokolTimeConvertUtil;
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

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 下拉菜单 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	private String headers[] = {"分类", "男/女", "地区"};

	private DropMenuWidget dropMenuWidget;

	private SecondaryWidget secondaryWidget;

	private OnTaskFilterCallback taskFilterCallback;

	private VAreaAllBean areaAllBean;

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% RecyclerView %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	private TaskRecycleAdapter taskRecycleAdapter;

	public MainTaskHelper(Context context)
	{
		this.sContext = context;
	}

	public void initTabDownMenuView(TabLayout menuTabLayout)
	{
		dropMenuWidget = new DropMenuWidget(sContext, menuTabLayout);

		List<View> contentViewList = new ArrayList<>();

		View classifyView = initClassifyView();
		contentViewList.add(classifyView);

		View sexView = initSexView();
		contentViewList.add(sexView);

		initAreaView(contentViewList);

		dropMenuWidget.show(Arrays.asList(headers), contentViewList);
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
		flowAbleWidget.setDataList(Arrays.asList(HttpEnum.UserTag.All.getContent(), HttpEnum.UserTag.Red.getContent(), HttpEnum.UserTag.Author.getContent(), HttpEnum.UserTag.Performer.getContent(), HttpEnum.UserTag.Model.getContent(), HttpEnum.UserTag.Singer.getContent(), HttpEnum.UserTag.Sport.getContent()));
		flowAbleWidget.addSelectedPosition(0);

		Button btnClassify = (Button) areaView.findViewById(R.id.btn_main_task_menu_classify);
		btnClassify.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dropMenuWidget.closeMenu();

				if (null != taskFilterCallback)
				{
					int position = flowAbleWidget.getSelectedFirst();
					taskFilterCallback.onFilterClassify(HttpEnum.getUserTag(position));
				}
			}
		});

		return areaView;
	}

	// 男、女 过滤
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
				dropMenuWidget.closeMenu();

				if (null != taskFilterCallback)
				{
					int position = labelClickableWidget.getSelectedFirst();
					taskFilterCallback.onFilterSex(HttpEnum.getUserSex(position));
				}
			}
		});

		return sexView;
	}

	// 地区
	private void initAreaView(List<View> contentViewList)
	{
		secondaryWidget = new SecondaryWidget(sContext, contentViewList);
		secondaryWidget.setOnConfirmListener(new SecondaryWidget.OnConfirmListener()
		{
			@Override
			public void onConfirmClick(String firstName, List<String> secondList, String titleName)
			{
				dropMenuWidget.updateTitle(2, titleName);
				dropMenuWidget.closeMenu();

				if (null != taskFilterCallback)
				{
					// 省份过滤
					if (SecondaryWidget.DefaultFirst.equals(firstName) || TextUtils.isEmpty(firstName))
					{
						taskFilterCallback.onFilterArea(null, new ArrayList<String>());
					}
					else
					{
						String firstCode = areaAllBean.getProvinceCode(firstName);
						// 城市过滤
						if (secondList.contains(SecondaryWidget.DefaultFirst))
						{
							taskFilterCallback.onFilterArea(firstCode, new ArrayList<String>());
						}
						else
						{
							List<String> secondCodeList = new ArrayList<>();
							String secondCode;
							for (String secondName : secondList)
							{
								secondCode = areaAllBean.getCityCode(firstName, secondName);
								secondCodeList.add(secondCode);
							}
							taskFilterCallback.onFilterArea(firstCode, secondCodeList);
						}
					}
				}
			}
		});
	}

	public void setAreaData(VAreaAllBean vAreaAllBean)
	{
		this.areaAllBean = vAreaAllBean;
		Map provinceMap = vAreaAllBean.getWidgetMap();
		secondaryWidget.setData(provinceMap);
	}

	public void setOnTaskFilterCallback(OnTaskFilterCallback taskFilterCallback)
	{
		this.taskFilterCallback = taskFilterCallback;
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

	public void setRecyclerData(List<VTaskMainAllBean.TaskMainAllOne> result)
	{
		taskRecycleAdapter.setDataList(result);
	}

	public void setRecyclerShowEmpty(boolean isShowEmpty)
	{
		taskRecycleAdapter.setShowEmpty(isShowEmpty);
	}

	public interface OnTaskFilterCallback
	{
		/**
		 * @param userTag 分类(网红等)
		 */
		void onFilterClassify(HttpEnum.UserTag userTag);

		/**
		 * @param userSex 用户性别
		 */
		void onFilterSex(HttpEnum.UserSex userSex);

		/**
		 * @param firstCode      null if choose is no-limit; else return the code
		 * @param secondCodeList return the code list, size is zero if choose is no limit
		 */
		void onFilterArea(String firstCode, List<String> secondCodeList);
	}

	private class TaskRecycleAdapter extends WidgetRecyclerAdapter<VTaskMainAllBean.TaskMainAllOne>
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

			VTaskMainAllBean.TaskMainAllOne taskBean = sList.get(position);

			// 价格 + 人数
			viewHolder.setText(R.id.tv_item_main_task_price, String.format("￥%d × %d", taskBean.getTask_fee(), taskBean.getTask_peo_num()));

			// 标题
			viewHolder.setText(R.id.tv_item_main_task_title, taskBean.getTask_title());

			// 头像
			ImageView imageView = viewHolder.get(R.id.iv_item_main_task_avatar);
			Glide.with(sContext).load(taskBean.getUser_logo()).error(R.drawable.global_load_avatar).into(imageView);

			// 用户名
			viewHolder.setText(R.id.tv_item_main_task_user, taskBean.getUser_nickname());

			// 报名人数
			viewHolder.setText(R.id.tv_item_main_task_sign_ups, String.format("%d人报名", taskBean.getEmployee_num()));

			// 截至时间
			long restTime = taskBean.getTask_rem_time();
			if (restTime > 0)
			{
				long deadLineTime = System.currentTimeMillis() + restTime * 1000;
				String deadLineTimeStr = HokolTimeConvertUtil.stampToRestFormatTime(deadLineTime);
				viewHolder.setText(R.id.tv_item_main_task_time, "剩余" + deadLineTimeStr);
			}
			else
			{
				viewHolder.setText(R.id.tv_item_main_task_time, "任务已截止");
			}
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
