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
import com.yline.base.BaseAppCompatActivity;
import com.yline.common.ViewHolder;
import com.yline.log.LogFileUtil;
import com.yline.utils.FileUtil;

import java.io.File;
import java.util.Arrays;

import static com.hokol.R.id.circle_user_info_avatar;

public class UserInfoActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	private static final String request_key = "UserInfo";

	private static final String camera_picture_name = "camera_picture.jpg";

	private static final String picture_zoom_name = "picture_zoom.jpg";

	private static final int request_code_nickname = 100;

	private static final int request_code_sign = 101;

	private static final int request_code_award = 102;

	private static final int request_code_camera = 1001;

	private static final int request_code_album = 1002;

	private static final int request_code_picture_zoom = 1003;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);

		initView();
	}

	private void initView()
	{
		viewHolder = new ViewHolder(this);

		FlowLayout flowLayout = (FlowLayout) findViewById(R.id.flow_layout_user_info);
		LabelWidget labelWidget = new LabelWidget(this, flowLayout);
		labelWidget.setDataList(Arrays.asList("网红", "模特"));

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
		viewHolder.setOnClickListener(circle_user_info_avatar, new View.OnClickListener()
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
		activity.setResult(request_code_sign, intent);
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserInfoActivity.class));
	}
}
