package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.UserCareActivity;
import com.hokol.activity.UserFansActivity;
import com.hokol.application.AppStateManager;
import com.yline.base.BaseFragment;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.ArrayList;

public class StarInfoDatumFragment extends BaseFragment
{
	/* 粉丝 */
	private static final String KeyFansNum = "StarFansNum";

	/* 关注的人 */
	private static final String KeyCaresNum = "StarFansNum";

	/* 用户点赞数 */
	private static final String KeyPraiseNum = "StarPraiseNum";

	/* 用户星座 */
	private static final String KeyConstell = "StarConstell";

	/* 用户所在城市 [石家庄市，130100] */
	private static final String KeyProvince = "StarProvince";

	/* 用户所在省份 [北京市，110000] */
	private static final String KeyCity = "StarCity";

	/* 用户签名 */
	private static final String KeySign = "StarSign";

	/* 用户获奖经历 */
	private static final String KeyPrize = "StarPrize";

	private ViewHolder viewHolder;

	public static StarInfoDatumFragment newInstance()
	{
		StarInfoDatumFragment fragment = new StarInfoDatumFragment();
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_star_info_datum, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		viewHolder = new ViewHolder(view);
		updateStarInfo();

		final String userId = AppStateManager.getInstance().getUserLoginId(getContext());

		viewHolder.setOnClickListener(R.id.ll_star_info_datum_fans, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserFansActivity.actionStart(getContext(), userId);
			}
		});

		viewHolder.setOnClickListener(R.id.ll_star_info_datum_care_people, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserCareActivity.actionStart(getContext(), userId);
			}
		});
	}

	/**
	 * 内部调用一次，确保数据展示了
	 */
	private void updateStarInfo()
	{
		Bundle bundle = getArguments();
		if (null != bundle)
		{
			viewHolder.setText(R.id.tv_star_info_datum_fans_number, bundle.getInt(KeyFansNum) + "");
			viewHolder.setText(R.id.tv_star_info_datum_cares_number, bundle.getInt(KeyCaresNum) + "");
			viewHolder.setText(R.id.tv_star_info_datum_praise_number, bundle.getInt(KeyPraiseNum) + "");
			viewHolder.setText(R.id.tv_star_info_datum_constell, bundle.getString(KeyConstell));
			viewHolder.setText(R.id.tv_star_info_datum_area, String.format("%s %s", bundle.getStringArrayList(KeyProvince).get(0), bundle.getStringArrayList(KeyCity).get(0)));
			viewHolder.setText(R.id.tv_star_info_datum_sign, bundle.getString(KeySign));
			viewHolder.setText(R.id.tv_star_info_datum_prize, bundle.getString(KeyPrize));
		}
	}

	/**
	 * 判空处理一次，如果viewHolder还没有被初始化，则传值
	 *
	 * @param fansNum
	 * @param caresNum
	 * @param praiseNum
	 * @param constell
	 * @param province
	 * @param city
	 * @param sign
	 * @param prize
	 */
	public void updateStarInfo(int fansNum, int caresNum, int praiseNum, String constell, ArrayList<String> province, ArrayList<String> city, String sign, String prize)
	{
		if (null != viewHolder)
		{
			viewHolder.setText(R.id.tv_star_info_datum_fans_number, fansNum + "");
			viewHolder.setText(R.id.tv_star_info_datum_cares_number, caresNum + "");
			viewHolder.setText(R.id.tv_star_info_datum_praise_number, praiseNum + "");
			viewHolder.setText(R.id.tv_star_info_datum_constell, constell);
			viewHolder.setText(R.id.tv_star_info_datum_area, String.format("%s %s", province.get(0), city.get(0)));
			viewHolder.setText(R.id.tv_star_info_datum_sign, sign);
			viewHolder.setText(R.id.tv_star_info_datum_prize, prize);
		}
		else
		{
			Bundle argBundle = new Bundle();

			argBundle.putInt(KeyPraiseNum, fansNum);
			argBundle.putInt(KeyCaresNum, caresNum);
			argBundle.putInt(KeyPraiseNum, praiseNum);
			argBundle.putString(KeyConstell, constell);
			argBundle.putStringArrayList(KeyProvince, province);
			argBundle.putStringArrayList(KeyCity, city);
			argBundle.putString(KeySign, sign);
			argBundle.putString(KeyPrize, prize);

			setArguments(argBundle);
		}
	}
}
