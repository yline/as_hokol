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

public class UserContactVolumeRecordActivity extends BaseAppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_contact_volume_record);

		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		fragmentList.add(ContactVolumeRecordUnapplyFragment.newInstance());
		titleList.add("未使用(0)");

		fragmentList.add(ContactVolumeRecordApplyedFragment.newInstance());
		titleList.add("已使用(0)");

		fragmentList.add(ContactVolumeRecordPassedFragment.newInstance());
		titleList.add("已过期(0)");

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_contact_volume);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_contact_volume);

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

		findViewById(R.id.iv_contact_volume_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserContactVolumeRecordActivity.class));
	}
}
