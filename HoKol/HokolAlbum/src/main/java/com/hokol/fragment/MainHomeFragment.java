package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VAreaAllBean;
import com.hokol.medium.widget.SecondaryWidget;
import com.hokol.util.ArraysUtil;
import com.hokol.viewhelper.MainHomeHelper;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainHomeFragment extends BaseFragment
{
	private MainHomeHelper mainHomeHelper;

	private List<BaseFragment> fragmentList;

	private TabLayout tabLayout;

	private String oldAreaFirst;

	private List<String> oldSecondList;

	private VAreaAllBean areaAllBean;
	
	private MainHomeHelper.FilterSex oldTypeSex;
	
	private MainHomeHelper.FilterRecommend oldTypeRecommend;

	public static MainHomeFragment newInstance()
	{
		Bundle args = new Bundle();

		MainHomeFragment fragment = new MainHomeFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_home, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		mainHomeHelper = new MainHomeHelper(getContext());

		initView(view);

		// 下拉菜单栏
		mainHomeHelper.initSecondaryView(new SecondaryWidget.OnConfirmListener()
		{
			@Override
			public void onConfirmClick(String firstName, List<String> secondList, String titleName)
			{
				mainHomeHelper.updateProvinceTitle(titleName);
				mainHomeHelper.closeMenu();

				if (isAreaChanged(firstName, secondList))
				{
					for (int i = 0; i < fragmentList.size(); i++)
					{
						if (fragmentList.get(i) instanceof OnHomeFilterCallback)
						{
							// 省份过滤
							if (SecondaryWidget.DefaultFirst.equals(firstName) || TextUtils.isEmpty(firstName))
							{
								((OnHomeFilterCallback) fragmentList.get(i)).onAreaUpdate(null, new ArrayList<String>());
							}
							else
							{
								String firstCode = areaAllBean.getProvinceCode(firstName);
								// 城市过滤
								if (secondList.contains(SecondaryWidget.DefaultFirst))
								{
									((OnHomeFilterCallback) fragmentList.get(i)).onAreaUpdate(firstCode, new ArrayList<String>());
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
									((OnHomeFilterCallback) fragmentList.get(i)).onAreaUpdate(firstCode, secondCodeList);
								}
							}
						}
					}
				}
				else
				{
					LogFileUtil.v("isAreaChanged - false");
				}
			}
		});
		mainHomeHelper.initFilterView(new MainHomeHelper.OnMenuFilterCallback()
		{
			@Override
			public void onEnumFilterCommit(MainHomeHelper.FilterSex typeSex, MainHomeHelper.FilterRecommend typeRecommend)
			{
				mainHomeHelper.closeMenu();

				if (isFilterChanged(typeSex, typeRecommend))
				{
					for (int i = 0; i < fragmentList.size(); i++)
					{
						if (fragmentList.get(i) instanceof OnHomeFilterCallback)
						{
							((OnHomeFilterCallback) fragmentList.get(i)).onFilterUpdate(typeSex, typeRecommend);
						}
					}
				}
				else
				{
					LogFileUtil.v("isFilterChanged - false");
				}
			}
		});
		TabLayout menuTabLayout = (TabLayout) view.findViewById(R.id.tab_main_home_menu);
		mainHomeHelper.initTabDownMenuView(menuTabLayout);

		initData();
	}

	private void initView(View view)
	{
		fragmentList = new ArrayList<>();
		fragmentList.add(MainHomeRedFragment.newInstance());
		fragmentList.add(MainHomeAuthorFragment.newInstance());
		fragmentList.add(MainHomePerformerFragment.newInstance());
		fragmentList.add(MainHomeModelFragment.newInstance());
		fragmentList.add(MainHomeSingerFragment.newInstance());
		fragmentList.add(MainHomeSportFragment.newInstance());

		// 主页 tab栏目
		tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_main_home);
		ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager_main_home);
		viewPager.setOffscreenPageLimit(HttpEnum.UserTag.values().length);
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabTextColors(getResources().getColor(android.R.color.black), getResources().getColor(android.R.color.holo_red_light));
		tabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.holo_red_light));

		final String[] RES_MAIN_HOME_TAB = view.getResources().getStringArray(R.array.main_home_tab);
		viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager())
		{
			@Override
			public Fragment getItem(int position)
			{
				return fragmentList.get(position);
			}

			@Override
			public int getCount()
			{
				return fragmentList.size();
			}

			@Override
			public CharSequence getPageTitle(int position)
			{
				return RES_MAIN_HOME_TAB[position];
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position)
			{
				return super.instantiateItem(container, position);
			}
		});
	}

	private void initData()
	{
		XHttpUtil.doAreaAll(new XHttpAdapter<VAreaAllBean>()
		{
			@Override
			public void onSuccess(VAreaAllBean vAreaAllBean)
			{
				areaAllBean = vAreaAllBean;
				Map<String, List<String>> provinceMap = vAreaAllBean.getWidgetMap();
				mainHomeHelper.setProvinceData(provinceMap);
			}
		});
	}

	private boolean isAreaChanged(String first, List<String> second)
	{
		if (ArraysUtil.compare(first, oldAreaFirst))
		{
			if (ArraysUtil.compare(oldSecondList, second))
			{
				return false;
			}
		}

		oldAreaFirst = first;
		oldSecondList = second;
		return true;
	}

	private boolean isFilterChanged(MainHomeHelper.FilterSex typeSex, MainHomeHelper.FilterRecommend typeRecommend)
	{
		if (typeSex == oldTypeSex)
		{
			if (typeRecommend == oldTypeRecommend)
			{
				return false;
			}
		}

		oldTypeSex = typeSex;
		oldTypeRecommend = typeRecommend;
		return true;
	}

	// 数据选择类目，更新接口
	public interface OnHomeFilterCallback
	{
		/**
		 * @param firstCode      null if choose is no-limit; else return the code
		 * @param secondCodeList return the code list, size is zero if choose is no limit
		 */
		void onAreaUpdate(String firstCode, List<String> secondCodeList);

		/**
		 * 筛选选择，更新
		 *
		 * @param typeSex
		 * @param typeRecommend
		 */
		void onFilterUpdate(MainHomeHelper.FilterSex typeSex, MainHomeHelper.FilterRecommend typeRecommend);
	}
}
