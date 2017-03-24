package com.hokol.viewhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hokol.R;
import com.hokol.medium.widget.DropMenuWidget;
import com.hokol.medium.widget.LabelWidget;
import com.hokol.medium.widget.SecondaryWidget;
import com.hokol.medium.widget.labellayout.LabelFlowLayout;

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

	public MainHomeHelper(Context context)
	{
		this.context = context;
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%% 下拉菜单 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%*/
	private String headers[] = {"城市", "筛选"};

	private DropMenuWidget dropMenuWidget;

	private List<View> contentViewList = new ArrayList<>();

	private SecondaryWidget secondaryWidget;

	public void initSecondaryView(SecondaryWidget.OnSecondaryCallback listener)
	{
		secondaryWidget = new SecondaryWidget();
		View provinceView = secondaryWidget.start(context, listener);
		contentViewList.add(provinceView);
	}

	public void initFilterView(final OnMenuFilterCallback menuFilterCallback)
	{
		final View filterView = LayoutInflater.from(context).inflate(R.layout.fragment_main_home__menu_filter, null);

		// 性别
		final LabelWidget sexLabelWidget = new LabelWidget(context)
		{
			@Override
			protected LabelFlowLayout getLabelFlowLayout()
			{
				return (LabelFlowLayout) filterView.findViewById(R.id.label_flow_main_home_menu_sex);
			}
		};
		sexLabelWidget.setDataList(Arrays.asList(All.content, Boy.content, Girl.content));
		sexLabelWidget.setMaxSelectCount(1);
		sexLabelWidget.setMinSelectCount(1);
		sexLabelWidget.addSelectedPosition(0);

		// 推荐
		final LabelWidget recommendLabelWidget = new LabelWidget(context)
		{
			@Override
			protected LabelFlowLayout getLabelFlowLayout()
			{
				return (LabelFlowLayout) filterView.findViewById(R.id.label_flow_main_home_menu_recommend);
			}
		};
		recommendLabelWidget.setDataList(Arrays.asList(FilterRecommend.Popular.content, FilterRecommend.Newest.content));
		recommendLabelWidget.setMaxSelectCount(1);
		recommendLabelWidget.setMinSelectCount(1);
		recommendLabelWidget.addSelectedPosition(0);

		filterView.findViewById(R.id.btn_main_home_menu_commit).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				int sexPosition = sexLabelWidget.getSelectedFirstPosition();
				int recommendPosition = recommendLabelWidget.getSelectedFirstPosition();

				menuFilterCallback.onEnumFilterCommit(FilterSex.getFilterSex(sexPosition), FilterRecommend.getFilterRecommend(recommendPosition));
			}
		});

		contentViewList.add(filterView);
	}

	public void initTabDownMenuView(LinearLayout linearLayout)
	{
		dropMenuWidget = new DropMenuWidget();

		View dropView = dropMenuWidget.start(context, Arrays.asList(headers), contentViewList);
		linearLayout.addView(dropView);
	}

	public void setProvinceData(Map<String, List<String>> map)
	{
		secondaryWidget.setDataMap(map);
	}

	public void closeMenu()
	{
		dropMenuWidget.closeMenu();
	}

	public interface OnMenuFilterCallback
	{
		void onEnumFilterCommit(FilterSex typeSex, FilterRecommend typeRecommend);
	}

	public enum FilterSex
	{
		All("全部"), Boy("男"), Girl("女");

		private final String content;

		FilterSex(String content)
		{
			this.content = content;
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
	}

	public enum FilterRecommend
	{
		Popular("人气"), Newest("最新");

		private final String content;

		FilterRecommend(String content)
		{
			this.content = content;
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
	}
}
