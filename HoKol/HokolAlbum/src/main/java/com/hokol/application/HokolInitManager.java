package com.hokol.application;

import android.content.Context;
import android.text.TextUtils;

import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VInitHokolBean;
import com.hokol.medium.http.bean.WInitHokolBean;
import com.hokol.medium.http.hokol.HokolAdapter;

public class HokolInitManager
{
	public HokolInitManager()
	{

	}

	public void initData(final Context context)
	{
		String userId = AppStateManager.getInstance().getUserLoginId(context);
		if (!TextUtils.isEmpty(userId))
		{
			XHttpUtil.doInitHokol(new WInitHokolBean(userId), new HokolAdapter<VInitHokolBean>()
			{
				@Override
				public void onSuccess(VInitHokolBean vInitHokolBean)
				{
					AppStateManager.getInstance().setLoginUserInfo(context, vInitHokolBean.getUser_info());
				}
			});
		}
	}
}
