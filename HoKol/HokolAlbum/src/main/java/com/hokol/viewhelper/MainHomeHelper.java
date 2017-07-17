package com.hokol.viewhelper;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;

import com.hokol.R;
import com.hokol.medium.widget.DropMenuWidget;
import com.hokol.medium.widget.FlowAbleWidget;
import com.hokol.medium.widget.SecondaryWidget;
import com.yline.view.layout.label.LabelFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.hokol.viewhelper.MainHomeHelper.FilterSex.All;
import static com.hokol.viewhelper.MainHomeHelper.FilterSex.Boy;
import static com.hokol.viewhelper.MainHomeHelper.FilterSex.Girl;

public class MainHomeHelper
{
	private Context context;

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%% 下拉菜单 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%*/
	private String headers[] = {"地区", "筛选"};

	private DropMenuWidget dropMenuWidget;

	private List<View> contentViewList = new ArrayList<>();

	private SecondaryWidget secondaryWidget;

	public MainHomeHelper(Context context)
	{
		this.context = context;
	}

	public void initSecondaryView(SecondaryWidget.OnConfirmListener listener)
	{
		secondaryWidget = new SecondaryWidget(context, contentViewList);
		secondaryWidget.setOnConfirmListener(listener);
	}

	public void initFilterView(final OnMenuFilterCallback menuFilterCallback)
	{
		final View filterView = LayoutInflater.from(context).inflate(R.layout.fragment_main_home__menu_filter, null);

		// 性别
		final FlowAbleWidget sexFlowAbleWidget = new FlowAbleWidget(context, (LabelFlowLayout) filterView.findViewById(R.id.label_flow_main_home_menu_sex))
		{
			@Override
			protected int getItemResourceId()
			{
				return R.layout.item_main_home__filter;
			}
		};
		sexFlowAbleWidget.setDataList(Arrays.asList(All.content, Boy.content, Girl.content));
		sexFlowAbleWidget.setMaxSelectCount(1);
		sexFlowAbleWidget.setMinSelectCount(1);
		sexFlowAbleWidget.addSelectedPosition(0);
		
		// 推荐
		final FlowAbleWidget recommendFlowAbleWidget = new FlowAbleWidget(context, (LabelFlowLayout) filterView.findViewById(R.id.label_flow_main_home_menu_recommend))
		{
			@Override
			protected int getItemResourceId()
			{
				return R.layout.item_main_home__filter;
			}
		};
		recommendFlowAbleWidget.setDataList(Arrays.asList(FilterRecommend.Popular.content, FilterRecommend.Newest.content));
		recommendFlowAbleWidget.setMaxSelectCount(1);
		recommendFlowAbleWidget.setMinSelectCount(1);
		recommendFlowAbleWidget.addSelectedPosition(0);

		filterView.findViewById(R.id.btn_main_home_menu_commit).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				int sexPosition = sexFlowAbleWidget.getSelectedFirst();
				int recommendPosition = recommendFlowAbleWidget.getSelectedFirst();

				menuFilterCallback.onEnumFilterCommit(FilterSex.getFilterSex(sexPosition), FilterRecommend.getFilterRecommend(recommendPosition));
			}
		});

		contentViewList.add(filterView);
	}

	public void initTabDownMenuView(TabLayout tabLayout)
	{
		dropMenuWidget = new DropMenuWidget(context, tabLayout);
		dropMenuWidget.show(Arrays.asList(headers), contentViewList);
	}

	public void updateProvinceTitle(String title)
	{
		dropMenuWidget.updateTitle(0, title);
	}

	public void setProvinceData(Map<String, List<String>> map)
	{
		secondaryWidget.setData(map);
	}

	public void closeMenu()
	{
		dropMenuWidget.closeMenu();
	}

	public enum FilterSex
	{
		All("全部", 0), Boy("男", 1), Girl("女", 2);

		// 显示内容
		private final String content;

		// 后台编号
		private final int index;

		FilterSex(String content, int index)
		{
			this.content = content;
			this.index = index;
		}

		public static FilterSex getFilterSex(int position)
		{
			if (position == 0)
			{
				return All;
			}
			else if (position == 1)
			{
				return Boy;
			}
			else
			{
				return Girl;
			}
		}

		public String getContent()
		{
			return content;
		}

		public int getIndex()
		{
			return index;
		}
	}

	public enum FilterRecommend
	{
		Popular("人气", 1), Newest("最新", 2);

		private final String content;

		// 后台编号
		private final int index;

		FilterRecommend(String content, int index)
		{
			this.content = content;
			this.index = index;
		}

		public static FilterRecommend getFilterRecommend(int position)
		{
			if (position == 0)
			{
				return Popular;
			}
			else
			{
				return Newest;
			}
		}

		public String getContent()
		{
			return content;
		}

		public int getIndex()
		{
			return index;
		}
	}

	public interface OnMenuFilterCallback
	{
		void onEnumFilterCommit(FilterSex typeSex, FilterRecommend typeRecommend);
	}
}
