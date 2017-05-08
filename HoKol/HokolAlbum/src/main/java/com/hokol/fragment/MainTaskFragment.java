package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hokol.R;
import com.hokol.activity.TaskAssignedActivity;
import com.hokol.activity.TaskDetailActivity;
import com.hokol.activity.TaskPublishActivity;
import com.hokol.application.IApplication;
import com.hokol.medium.widget.ADWidget;
import com.hokol.medium.widget.recycler.OnRecyclerItemClickListener;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;
import com.hokol.viewhelper.MainTaskHelper;
import com.yline.base.BaseFragment;
import com.yline.utils.UIScreenUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainTaskFragment extends BaseFragment
{
	private MainTaskHelper mainTaskHelper;

	private List<Integer> data;

	private SuperSwipeRefreshLayout superRefreshLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_task, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		mainTaskHelper = new MainTaskHelper(getContext());
		initView(view);
	}

	private void initView(View parentView)
	{
		data = new ArrayList<>();
		data.add(R.drawable.delete_ad_img1);
		data.add(R.drawable.delete_ad_img2);
		data.add(R.drawable.delete_ad_img3);
		data.add(R.drawable.delete_ad_img4);
		data.add(R.drawable.delete_ad_img5);

		// 广告
		LinearLayout linearLayout = (LinearLayout) parentView.findViewById(R.id.ll_main_task_ad);
		ADWidget adWidget = new ADWidget()
		{
			@Override
			protected int getViewPagerHeight()
			{
				return UIScreenUtil.dp2px(getContext(), 150);
			}
		};
		View adView = adWidget.start(getContext(), 3);
		adWidget.setListener(new ADWidget.OnPageListener()
		{
			@Override
			public void onPageClick(View v, int position)
			{
				IApplication.toast("position = " + position);
			}

			@Override
			public void onPageInstance(ImageView imageView, int position)
			{
				imageView.setImageResource(data.get(position));
			}
		});
		linearLayout.addView(adView);

		// 下拉菜单
		TabLayout menuTabLayout = (TabLayout) parentView.findViewById(R.id.tab_main_task_menu);
		mainTaskHelper.initTabDownMenuView(menuTabLayout);

		mainTaskHelper.setAreaData(initData());
		
		// 刷新
		superRefreshLayout = (SuperSwipeRefreshLayout) parentView.findViewById(R.id.swipe_main_task);
		mainTaskHelper.initRefreshLayout(superRefreshLayout);

		// 内容
		RecyclerView recycleView = (RecyclerView) parentView.findViewById(R.id.recycle_main_task);
		mainTaskHelper.initRecycleView(recycleView);
		mainTaskHelper.setOnRecyclerClickListener(new OnRecyclerItemClickListener()
		{
			@Override
			public void onClick(RecyclerView.ViewHolder viewHolder, Object o, int position)
			{
				TaskDetailActivity.actionStart(getContext());
			}
		});
		mainTaskHelper.setRecycleData();

		// 头部
		parentView.findViewById(R.id.iv_main_task_action_task).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TaskPublishActivity.actionStart(getContext());
			}
		});
		parentView.findViewById(R.id.iv_main_task_action_history).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TaskAssignedActivity.actionStart(getContext());
			}
		});
	}

	private HashMap<String, List<String>> initData()
	{
		HashMap<String, List<String>> provinceMap = new HashMap<>();
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
		return provinceMap;
	}
}
