package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hokol.R;
import com.hokol.fragment.ContactVolumeRecordApplyedFragment;
import com.hokol.fragment.ContactVolumeRecordPassedFragment;
import com.hokol.fragment.ContactVolumeRecordUnapplyFragment;
import com.yline.base.BaseAppCompatActivity;
import com.yline.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class UserContactVolumeRecordActivity extends BaseAppCompatActivity implements ContactVolumeRecordUnapplyFragment.OnLoadRecordFinishCallback
{
	private static final String KeyVolumeRecordUserId = "VolumeRecordUserId";

	private TabLayout tabLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_contact_volume_record);

		findViewById(R.id.iv_contact_volume_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		initTabView();
	}

	private void initTabView()
	{
		String userId = getIntent().getStringExtra(KeyVolumeRecordUserId);

		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		fragmentList.add(ContactVolumeRecordUnapplyFragment.newInstance(userId));
		titleList.add("未使用");

		fragmentList.add(ContactVolumeRecordApplyedFragment.newInstance(userId));
		titleList.add("已使用");
		
		fragmentList.add(ContactVolumeRecordPassedFragment.newInstance(userId));
		titleList.add("已过期");

		tabLayout = (TabLayout) findViewById(R.id.tab_layout_contact_volume);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_contact_volume);
		viewPager.setOffscreenPageLimit(3);

		viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
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
				return titleList.get(position);
			}
		});
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.hokolGrayDrak), ContextCompat.getColor(this, R.color.hokolRed));
		tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.hokolRed));
	}

	@Override
	public void onLoadFinish(int number, int position)
	{
		if (position == 0)
		{
			tabLayout.getTabAt(position).setText(String.format("未使用(%d)", number));
		}
		else if (position == 1)
		{
			tabLayout.getTabAt(position).setText(String.format("已使用(%d)", number));
		}
		else if (position == 2)
		{
			tabLayout.getTabAt(position).setText(String.format("已过期(%d)", number));
		}
	}

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserContactVolumeRecordActivity.class).putExtra(KeyVolumeRecordUserId, userId));
	}
}
