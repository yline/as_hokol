package com.hokol.test.http.area;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VAreaAllBean;
import com.hokol.test.common.BaseTestActivity;
import com.yline.http.XHttpAdapter;

import java.util.List;

public class TestAreaActivity extends BaseTestActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		addButton("获取地区", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				XHttpUtil.doAreaAll(new XHttpAdapter<VAreaAllBean>()
				{
					@Override
					public void onSuccess(VAreaAllBean vAreaAllBean)
					{
						List<String> provinceName = vAreaAllBean.getProvinceNameList();
						String pName = provinceName.get(0);
						String pCode = vAreaAllBean.getProvinceCode(pName);

						List<String> cityName = vAreaAllBean.getCityNameList(pCode);
						String cName = cityName.get(0);
						
						vAreaAllBean.getCityCode(pName, cName);
					}
				});
			}
		});
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestAreaActivity.class));
	}
}
