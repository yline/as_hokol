package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.widget.SecondaryWidget;
import com.hokol.viewhelper.MainHomeHelper;
import com.yline.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainHomeFragment extends BaseFragment
{
	private MainHomeHelper mainHomeHelper;

	private List<BaseFragment> fragmentList;

	private Map<String, List<String>> provinceMap;

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
		LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_main_home_menu);
		mainHomeHelper.initSecondaryView(new SecondaryWidget.OnSecondaryCallback()
		{
			@Override
			public void onSecondarySelected(String first, String second)
			{
				IApplication.toast("first = " + first + ",second = " + second);
				mainHomeHelper.updateProvinceTitle(second);
				mainHomeHelper.closeMenu();
			}
		});

		mainHomeHelper.initFilterView(new MainHomeHelper.OnMenuFilterCallback()
		{
			@Override
			public void onEnumFilterCommit(MainHomeHelper.FilterSex typeSex, MainHomeHelper.FilterRecommend typeRecommend)
			{
				IApplication.toast("typeSex = " + typeSex + ",typeRecommend = " + typeRecommend);
				mainHomeHelper.closeMenu();
			}
		});
		mainHomeHelper.initTabDownMenuView(linearLayout);

		initData();
		IApplication.getHandler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				mainHomeHelper.setProvinceData(provinceMap);
			}
		}, 500);
	}
	
	private void initView(View view)
	{
		fragmentList = new ArrayList<>();
		fragmentList.add(new MainHomeRedFragment());
		fragmentList.add(new MainHomeAuthorFragment());
		fragmentList.add(new MainHomePerformerFragment());
		fragmentList.add(new MainHomeModelFragment());
		fragmentList.add(new MainHomeSingerFragment());
		fragmentList.add(new MainHomeSportFragment());

		// 主页 tab栏目
		TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_main_home);
		ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager_main_home);
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
		});
	}

	private void initData()
	{
		provinceMap = new HashMap<>();
		provinceMap.put("北京市", Arrays.asList("北京"));
		provinceMap.put("天津市", Arrays.asList("天津"));
		provinceMap.put("黑龙江省", Arrays.asList("哈尔滨市", "齐齐哈尔市", "佳木斯市", "鹤岗市", "大庆市", "鸡西市", "双鸭山市", "伊春市", "牡丹江市", "黑河市", "七台河市", "绥化市和大兴安岭地区"));
		provinceMap.put("河北省", Arrays.asList("石家庄", "唐山", "邯郸", "保定", "沧州", "邢台", "廊坊", "承德", "张家口", "衡水", "秦皇岛"));
		provinceMap.put("山西省", Arrays.asList("大同", "朔州", "忻州", "太原", "阳泉", "晋中", "吕梁", "长治", "临汾", "晋城", "运城"));
		provinceMap.put("内蒙古自治区", Arrays.asList("呼和浩特市", "包头市", "乌海市", "赤峰市", "通辽市", "鄂尔多斯市", "呼伦贝尔市", "巴彦淖尔市", "乌兰察布市"));
		provinceMap.put("吉林省", Arrays.asList("长春市", "吉林市", "四平市", "辽源市", "通化市", "白山市", "白城市", "通化市", "松原市"));
		provinceMap.put("江西省", Arrays.asList("南昌", "九江", "赣州", "吉安", "萍乡", "鹰潭", "新余", "宜春", "上饶", "景德镇", "抚州"));
		provinceMap.put("海南省", Arrays.asList("海口市", "三亚市", "万宁市", "琼海市", "文昌市", "儋州市", "东方市", "五指山市．定安县", "乐东县", "澄迈县", "屯昌县", "临高县", "白沙黎族自治县"));
		provinceMap.put("云南省", Arrays.asList("昆明市", "曲靖市", "玉溪市", "昭通市", "楚雄市", "普洱市", "景洪市", "大理市", "保山市", "丽江市", "临沧市", "宣威市", "个旧市", "文山市", "安宁市", "瑞丽市", "芒市"));
		provinceMap.put("陕西省", Arrays.asList("铜川市", "宝鸡市", "咸阳市", "渭南市", "汉中市", "安康市", "商洛市", "延安市", "榆林市"));
		provinceMap.put("青海省", Arrays.asList("格尔木市", "西宁市", "玉树", "果洛", "海东", "海西", "海南", "海北"));
	}
}
