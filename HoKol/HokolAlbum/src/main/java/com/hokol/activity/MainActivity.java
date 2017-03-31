package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.widget.ImageView;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.fragment.MainCareFragment;
import com.hokol.fragment.MainHomeFragment;
import com.hokol.fragment.MainMineFragment;
import com.hokol.fragment.MainNewsFragment;
import com.hokol.fragment.MainTaskFragment;
import com.hokol.viewhelper.MainHelper;


/**
 * 主页面
 *
 * @author yline 2017/3/2 --> 10:01
 * @version 1.0.0
 */
public class MainActivity extends BaseAppCompatActivity
{
	private MainHelper mainHelper = new MainHelper();

	private FragmentManager fragmentManager = getSupportFragmentManager();

	private MainNewsFragment mainNewsFragment;

	private MainCareFragment mainCareFragment;

	private MainHomeFragment mainHomeFragment;

	private MainTaskFragment mainTaskFragment;

	private MainMineFragment mainMineFragment;

	private TabLayout tabLayout;

	public enum TAB
	{
		News(0, R.drawable.main_tab_news, R.string.main_tab_news),

		Care(1, R.drawable.main_tab_care, R.string.main_tab_care),

		Home(2, R.drawable.main_tab_home, R.string.main_tab_home),

		Task(3, R.drawable.main_tab_task, R.string.main_tab_task),

		Mine(4, R.drawable.main_tab_mine, R.string.main_tab_mine);

		private final int position;

		private final int icon;

		private final int text;

		/**
		 * @param position 位置
		 * @param icon     tab资源
		 * @param text     tab内容
		 */
		TAB(int position, int icon, int text)
		{
			this.position = position;
			this.icon = icon;
			this.text = text;
		}
	}

	private ImageView imageFlashView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imageFlashView = (ImageView) findViewById(R.id.iv_main_logo);

		tabLayout = (TabLayout) findViewById(R.id.tab_layout_main);

		mainHelper.initFlashAnimator(imageFlashView);

		initView();
		initData();

		initShowData();
	}

	/**
	 * 显示用户第一次看到的内容
	 */
	private void initShowData()
	{
		tabLayout.getTabAt(TAB.Home.position).select();
	}

	private void initView()
	{
		int[] text = {TAB.News.text, TAB.Care.text, TAB.Home.text, TAB.Task.text, TAB.Mine.text};
		int[] icons = {TAB.News.icon, TAB.Care.icon, TAB.Home.icon, TAB.Task.icon, TAB.Mine.icon};
		mainHelper.initTabLayout(this, tabLayout, icons, text);

		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab)
			{
				int position = tab.getPosition();
				if (position == TAB.News.position)
				{
					fragmentManager.beginTransaction().show(mainNewsFragment).commit();
				}
				else if (position == TAB.Care.position)
				{
					fragmentManager.beginTransaction().show(mainCareFragment).commit();
				}
				else if (position == TAB.Home.position)
				{
					fragmentManager.beginTransaction().show(mainHomeFragment).commit();
				}
				else if (position == TAB.Task.position)
				{
					fragmentManager.beginTransaction().show(mainTaskFragment).commit();
				}
				else if (position == TAB.Mine.position)
				{
					fragmentManager.beginTransaction().show(mainMineFragment).commit();
				}
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab)
			{
				int position = tab.getPosition();
				if (position == TAB.News.position)
				{
					fragmentManager.beginTransaction().hide(mainNewsFragment).commit();
				}
				else if (position == TAB.Care.position)
				{
					fragmentManager.beginTransaction().hide(mainCareFragment).commit();
				}
				else if (position == TAB.Home.position)
				{
					fragmentManager.beginTransaction().hide(mainHomeFragment).commit();
				}
				else if (position == TAB.Task.position)
				{
					fragmentManager.beginTransaction().hide(mainTaskFragment).commit();
				}
				else if (position == TAB.Mine.position)
				{
					fragmentManager.beginTransaction().hide(mainMineFragment).commit();
				}
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab)
			{

			}
		});
	}

	private void initData()
	{
		mainNewsFragment = new MainNewsFragment();
		mainCareFragment = new MainCareFragment();
		mainHomeFragment = new MainHomeFragment();
		mainTaskFragment = new MainTaskFragment();
		mainMineFragment = new MainMineFragment();

		fragmentManager.beginTransaction().add(R.id.fl_main_content, mainNewsFragment).hide(mainNewsFragment)
				.add(R.id.fl_main_content, mainCareFragment).hide(mainCareFragment)
				.add(R.id.fl_main_content, mainHomeFragment).hide(mainHomeFragment)
				.add(R.id.fl_main_content, mainTaskFragment).hide(mainTaskFragment)
				.add(R.id.fl_main_content, mainMineFragment).hide(mainMineFragment).commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == android.R.id.home)
		{
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, MainActivity.class));
	}
}
