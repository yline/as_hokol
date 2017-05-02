package com.hokol.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hokol.R;
import com.hokol.medium.widget.DialogFootWidget;
import com.hokol.medium.widget.LabelWidget;
import com.hokol.medium.widget.labellayout.FlowLayout;
import com.hokol.util.IntentUtil;
import com.hokol.viewhelper.UserInfoHelper;
import com.yline.base.BaseAppCompatActivity;
import com.yline.common.ViewHolder;
import com.yline.log.LogFileUtil;
import com.yline.utils.FileUtil;

import java.io.File;
import java.util.Arrays;

public class UserInfoActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	private UserInfoHelper userInfoHelper;

	private static final String request_key = "UserInfo";

	private static final String request_key_second = "UserInfoSecond";

	private static final String camera_picture_name = "camera_picture.jpg";

	private static final String picture_zoom_name = "picture_zoom.jpg";

	private static final int request_code_nickname = 100;

	private static final int request_code_sign = 101;

	private static final int request_code_award = 102;

	private static final int request_code_area = 103;

	private static final int request_code_camera = 1001;

	private static final int request_code_album = 1002;

	private static final int request_code_picture_zoom = 1003;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);

		viewHolder = new ViewHolder(this);
		userInfoHelper = new UserInfoHelper(this);

		initView();
	}

	private void initView()
	{
		// title
		viewHolder.setOnClickListener(R.id.iv_user_info, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
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
						viewHolder.setText(R.id.tv_user_info_sex, content);
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
						viewHolder.setText(R.id.tv_user_info_constellation, userInfoHelper.getSelectContent());
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
		LabelWidget labelWidget = new LabelWidget(this, flowLayout);
		labelWidget.setDataList(Arrays.asList("网红", "模特"));
		viewHolder.setOnClickListener(R.id.ll_label, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserInfoUpdateLabelActivity.actionStart(UserInfoActivity.this);
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
					viewHolder.setText(R.id.tv_user_info_sign, resultContent);
				}
				else if (request_code_award == requestCode)
				{
					String resultContent = data.getStringExtra(request_key);
					viewHolder.setText(R.id.tv_user_info_award, resultContent);
				}
				else if (request_code_nickname == requestCode)
				{
					String resultContent = data.getStringExtra(request_key);
					viewHolder.setText(R.id.tv_user_info_nickname, resultContent);
				}
				else if (request_code_area == requestCode)
				{
					String resultFirst = data.getStringExtra(request_key);
					String resultSecond = data.getStringExtra(request_key_second);
					viewHolder.setText(R.id.tv_city, resultFirst + " " + resultSecond);
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
			if (null != data.getData())
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
				Glide.with(this).load(zoomFile)
						.skipMemoryCache(true) // 跳过内存缓存
						.diskCacheStrategy(DiskCacheStrategy.NONE) // 禁用磁盘缓存
						.into(imageView);
				LogFileUtil.v("cache file = " + zoomFile);
				// 完成图片上传
			}
			else
			{
				LogFileUtil.v("user picture zoom cancel");
			}
		}
	}

	public static void actionResultUpdate(Activity activity, String result)
	{
		Intent intent = new Intent();
		intent.putExtra(request_key, result);
		activity.setResult(RESULT_OK, intent);
	}

	public static void actionResultUpdate(Activity activity, String first, String second)
	{
		Intent intent = new Intent();
		intent.putExtra(request_key, first);
		intent.putExtra(request_key_second, second);
		activity.setResult(RESULT_OK, intent);
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserInfoActivity.class));
	}
}
