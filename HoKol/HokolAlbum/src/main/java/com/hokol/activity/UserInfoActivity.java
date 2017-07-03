package com.hokol.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserAvatarBean;
import com.hokol.medium.http.bean.WSettingUpdateInfoBean;
import com.hokol.medium.widget.DialogFootWidget;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.util.IntentUtil;
import com.hokol.viewhelper.UserInfoHelper;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.utils.FileUtil;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.recycler.holder.ViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserInfoActivity extends BaseAppCompatActivity
{
	private static final String KeyUserId = "userId";

	private static final String request_key = "UserInfo";

	private static final String camera_picture_name = "UserInfo_camera_picture.jpg";

	private static final String picture_zoom_name = "picture_zoom.jpg";

	private static final int request_code_nickname = 100;

	private static final int request_code_sign = 101;

	private static final int request_code_award = 102;

	private static final int request_code_area = 103;

	private static final int request_code_label = 104;

	private static final int request_code_camera = 1001;

	private static final int request_code_album = 1002;

	private static final int request_code_picture_zoom = 1003;

	private ViewHolder viewHolder;

	private UserInfoHelper userInfoHelper;

	private boolean isInfoBeanChange;

	private UserInfo updateInfoBean;

	private FlowWidget labelWidget;

	public static void actionResultUpdate(Activity activity, String result)
	{
		Intent intent = new Intent();
		intent.putExtra(request_key, result);
		activity.setResult(RESULT_OK, intent);
	}

	public static void actionResultUpdateStrList(Activity activity, ArrayList<String> dataList)
	{
		Intent intent = new Intent();
		intent.putExtra(request_key, dataList);
		activity.setResult(RESULT_OK, intent);
	}

	public static void actionResultUpdateIntList(Activity activity, ArrayList<Integer> dataList)
	{
		Intent intent = new Intent();
		intent.putIntegerArrayListExtra(request_key, dataList);
		activity.setResult(RESULT_OK, intent);
	}

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserInfoActivity.class).putExtra(KeyUserId, userId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);

		viewHolder = new ViewHolder(this);
		userInfoHelper = new UserInfoHelper(this);

		initView();
		initData();
	}

	private void initView()
	{
		// title
		viewHolder.setOnClickListener(R.id.iv_user_info, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				updateUserInfo();
				finish();
			}
		});

		// 头像
		viewHolder.setOnClickListener(R.id.circle_user_info_avatar, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DialogFootWidget dialogFootWidget = new DialogFootWidget(UserInfoActivity.this, Arrays.asList("相册", "拍照"))
				{
					@Override
					protected int getResourceId()
					{
						return R.layout.widget_dialog_foot_rec;
					}
				};
				dialogFootWidget.show(new DialogFootWidget.OnSelectedListener()
				{
					@Override
					public void onCancelSelected(DialogInterface dialog)
					{
						dialog.dismiss();
					}

					@Override
					public void onOptionSelected(DialogInterface dialog, int position, String content)
					{
						if (content.equals("拍照"))
						{
							IntentUtil.openCamera(UserInfoActivity.this, camera_picture_name, request_code_camera);
						}
						else // 相册
						{
							IntentUtil.openAlbum(UserInfoActivity.this, request_code_album);
						}
						dialog.dismiss();
					}
				});
			}
		});

		// 修改昵称
		viewHolder.setOnClickListener(R.id.ll_user_info_nickname, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String content = viewHolder.getText(R.id.tv_user_info_nickname);
				UserInfoUpdateNicknameActivity.actionStartForResult(UserInfoActivity.this, request_code_nickname, content);
			}
		});

		// 修改性别
		viewHolder.setOnClickListener(R.id.ll_user_info_sex, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DialogFootWidget dialogFootWidget = new DialogFootWidget(UserInfoActivity.this, Arrays.asList("女", "男"))
				{
					@Override
					protected int getResourceId()
					{
						return R.layout.widget_dialog_foot_rec;
					}
				};
				dialogFootWidget.show(new DialogFootWidget.OnSelectedListener()
				{
					@Override
					public void onCancelSelected(DialogInterface dialog)
					{
						dialog.dismiss();
					}

					@Override
					public void onOptionSelected(DialogInterface dialog, int position, String content)
					{
						if (!viewHolder.getText(R.id.tv_user_info_sex).equals(content))
						{
							isInfoBeanChange = true;
							viewHolder.setText(R.id.tv_user_info_sex, content);

							int intSex = HttpEnum.getUserSex(content).getIndex();
							updateInfoBean.setUser_sex(intSex);
						}
						dialog.dismiss();
					}
				});
			}
		});

		// 修改星座
		viewHolder.setOnClickListener(R.id.ll_user_info_constell, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				userInfoHelper.updateConstellation(new DialogInterface.OnDismissListener()
				{
					@Override
					public void onDismiss(DialogInterface dialog)
					{
						String content = userInfoHelper.getSelectContent();
						if (!viewHolder.getText(R.id.tv_user_info_constellation).equals(content))
						{
							isInfoBeanChange = true;
							viewHolder.setText(R.id.tv_user_info_constellation, content);

							int intConstell = HttpEnum.getUserConstell(content).getIndex();
							updateInfoBean.setUser_constell(intConstell);
						}
					}
				});
			}
		});

		// 修改所在地
		viewHolder.setOnClickListener(R.id.ll_user_info_area, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserInfoUpdateAreaActivity.actionStartForResult(UserInfoActivity.this, request_code_area);
			}
		});

		// 修改个性签名
		viewHolder.setOnClickListener(R.id.ll_user_info_sign, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String content = viewHolder.getText(R.id.tv_user_info_sign);
				UserInfoUpdateSignActivity.actionStartForResult(UserInfoActivity.this, request_code_sign, content);
			}
		});

		// 修改标签
		FlowLayout flowLayout = (FlowLayout) findViewById(R.id.flow_layout_user_info);
		labelWidget = new FlowWidget(this, flowLayout);
		viewHolder.setOnClickListener(R.id.ll_label, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserInfoUpdateLabelActivity.actionStart(UserInfoActivity.this, request_code_label, updateInfoBean.getUser_tag());
			}
		});

		// 修改获奖经历
		viewHolder.setOnClickListener(R.id.ll_user_info_award, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String content = viewHolder.getText(R.id.tv_user_info_award);
				UserInfoUpdateAwardActivity.actionStartForResult(UserInfoActivity.this, request_code_award, content);
			}
		});
	}

	private void initData()
	{
		isInfoBeanChange = false;

		String userId = getIntent().getStringExtra(KeyUserId);
		updateInfoBean = AppStateManager.getInstance().getUserInfoBean(this, userId);

		// 头像
		String userAvatar = AppStateManager.getInstance().getUserLoginAvatar(this);
		if (!TextUtils.isEmpty(userAvatar))
		{
			ImageView avatarImageView = viewHolder.get(R.id.circle_user_info_avatar);
			Glide.with(this).load(userAvatar).into(avatarImageView);
		}

		// 昵称
		viewHolder.setText(R.id.tv_user_info_nickname, updateInfoBean.getUser_nickname());

		// 性别
		String sexStr = HttpEnum.getUserSex(updateInfoBean.getUser_sex()).getContent();
		viewHolder.setText(R.id.tv_user_info_sex, sexStr);

		// 星座
		String constellStr = HttpEnum.getUserConstell(updateInfoBean.getUser_constell()).getContent();
		viewHolder.setText(R.id.tv_user_info_constellation, constellStr);

		// 所在地
		String provinceName = AppStateManager.getInstance().getUserProvinceName(this);
		String cityName = AppStateManager.getInstance().getUserCityName(this);
		if (TextUtils.isEmpty(provinceName))
		{
			viewHolder.setText(R.id.tv_user_info_city, "");
		}
		else if (TextUtils.isEmpty(cityName))
		{
			viewHolder.setText(R.id.tv_user_info_city, provinceName);
		}
		else
		{
			viewHolder.setText(R.id.tv_user_info_city, String.format("%s %s", provinceName, cityName));
		}

		// 个性签名
		String signStr = updateInfoBean.getUser_sign();
		if (TextUtils.isEmpty(signStr))
		{
			signStr = "";
		}
		viewHolder.setText(R.id.tv_user_info_sign, signStr);

		// 标签
		List<String> labelStrList = AppStateManager.getInstance().getUserLoginLabel(this);
		labelWidget.setDataList(labelStrList);

		// 获奖经历
		String priseStr = updateInfoBean.getUser_prize();
		if (TextUtils.isEmpty(priseStr))
		{
			priseStr = "";
		}
		viewHolder.setText(R.id.tv_user_info_award, priseStr);
	}

	@Override
	public void onBackPressed()
	{
		updateUserInfo();
		super.onBackPressed();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		// 界面间跳转
		if (null != data)
		{
			if (resultCode == RESULT_OK)
			{
				if (request_code_sign == requestCode)
				{
					String resultContent = data.getStringExtra(request_key);
					if (!viewHolder.getText(R.id.tv_user_info_sign).equals(resultContent))
					{
						isInfoBeanChange = true;
						viewHolder.setText(R.id.tv_user_info_sign, resultContent);
						updateInfoBean.setUser_sign(resultContent);
					}
				}
				else if (request_code_award == requestCode)
				{
					String resultContent = data.getStringExtra(request_key);
					if (!viewHolder.getText(R.id.tv_user_info_award).equals(resultContent))
					{
						isInfoBeanChange = true;
						viewHolder.setText(R.id.tv_user_info_award, resultContent);
						updateInfoBean.setUser_prize(resultContent);
					}
				}
				else if (request_code_nickname == requestCode)
				{
					String resultContent = data.getStringExtra(request_key);
					if (!viewHolder.getText(R.id.tv_user_info_nickname).equals(resultContent))
					{
						isInfoBeanChange = true;
						viewHolder.setText(R.id.tv_user_info_nickname, resultContent);
						updateInfoBean.setUser_nickname(resultContent);
					}
				}
				else if (request_code_area == requestCode)
				{
					List<String> resultList = data.getStringArrayListExtra(request_key);
					if (resultList.size() != 4)
					{
						return;
					}

					String pCode = resultList.get(0);
					String pName = resultList.get(1);
					String cCode = resultList.get(2);
					String cName = resultList.get(3);

					String resultContent = String.format("%s %s", pName, cName);
					if (!viewHolder.getText(R.id.tv_user_info_city).equals(resultContent))
					{
						isInfoBeanChange = true;
						viewHolder.setText(R.id.tv_user_info_city, resultContent);

						updateInfoBean.setP_code(pCode);
						updateInfoBean.setP_name(pName);
						updateInfoBean.setC_code(cCode);
						updateInfoBean.setC_name(cName);
					}
				}
				else if (request_code_label == requestCode)
				{
					// 标签选择
					ArrayList<Integer> dataList = data.getIntegerArrayListExtra(request_key);
					if (!dataList.equals(updateInfoBean.getUser_tag()))
					{
						isInfoBeanChange = true;
						List<String> updateStringList = new ArrayList<>();
						for (Integer contentInt : dataList)
						{
							updateStringList.add(HttpEnum.getUserTag(contentInt).getContent());
						}
						labelWidget.setDataList(updateStringList);
						updateInfoBean.setUser_tag(dataList);
					}
				}
			}
		}

		// 调用系统的；应用
		if (request_code_camera == requestCode)
		{
			if (null == data)
			{
				Uri cacheUri = Uri.fromFile(FileUtil.create(getExternalCacheDir(), camera_picture_name));
				IntentUtil.openPictureZoom(UserInfoActivity.this, cacheUri, picture_zoom_name, request_code_picture_zoom);
			}
			else
			{
				LogFileUtil.v("user camera cancel");
			}
		}
		else if (request_code_album == requestCode)
		{
			if (null != data && null != data.getData())
			{
				IntentUtil.openPictureZoom(UserInfoActivity.this, data.getData(), picture_zoom_name, request_code_picture_zoom);
			}
			else
			{
				LogFileUtil.v("user album choose cancel");
			}
		}
		else if (request_code_picture_zoom == requestCode)
		{
			if (null != data)
			{
				File zoomFile = FileUtil.create(getExternalCacheDir(), picture_zoom_name);

				ImageView imageView = viewHolder.get(R.id.circle_user_info_avatar);
				Glide.with(this).load(zoomFile).skipMemoryCache(true) // 跳过内存缓存
						.diskCacheStrategy(DiskCacheStrategy.NONE) // 禁用磁盘缓存
						.into(imageView);
				LogFileUtil.v("cache file = " + zoomFile);

				// 完成图片上传
				XHttpUtil.doSettingUpdateAvatar(updateInfoBean.getUser_id(), zoomFile, new XHttpAdapter<VUserAvatarBean>()
				{
					@Override
					public void onSuccess(VUserAvatarBean vUserAvatarBean)
					{
						LogFileUtil.v("vUserAvatarBean = " + vUserAvatarBean.getUser_logo());
						AppStateManager.getInstance().updateKeyUserLoginAvatar(UserInfoActivity.this, vUserAvatarBean.getUser_logo());
					}
				});
			}
			else
			{
				LogFileUtil.v("user picture zoom cancel");
			}
		}
	}

	private void updateUserInfo()
	{
		if (isInfoBeanChange)
		{
			// 更新本地数据
			AppStateManager.getInstance().updateUserInfo(UserInfoActivity.this, updateInfoBean);
			XHttpUtil.doSettingUpdateInfo(updateInfoBean, new XHttpAdapter<String>()
			{
				@Override
				public void onSuccess(String s)
				{
					// 数据更新，成功
				}
			});
		}
		else
		{
			LogFileUtil.v("do not update userInfo");
		}
	}

	/**
	 * 除了上传的数据，还需要其他辅助数据
	 */
	public static class UserInfo extends WSettingUpdateInfoBean
	{
		/* 用户城市 */private String c_name;

		/* 用户省份*/private String p_name;

		public UserInfo(String user_id, String user_nickname, int user_sex, String c_code, String p_code, String user_sign, ArrayList<Integer> user_tag, String user_prize, int user_constell, String c_name, String p_name)
		{
			super(user_id, user_nickname, user_sex, c_code, p_code, user_sign, user_tag, user_prize, user_constell);
			this.c_name = c_name;
			this.p_name = p_name;
		}

		public String getC_name()
		{
			return c_name;
		}

		public void setC_name(String c_name)
		{
			this.c_name = c_name;
		}

		public String getP_name()
		{
			return p_name;
		}

		public void setP_name(String p_name)
		{
			this.p_name = p_name;
		}
	}
}
