package com.hokol.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.fragment.MainMineFragment;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.medium.widget.DialogIosWidget;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.log.LogFileUtil;
import com.yline.utils.FileUtil;
import com.yline.view.recycler.holder.ViewHolder;

import java.io.File;

public class UserDynamicPublishActivity extends BaseAppCompatActivity
{
	private static final String KeyInnerCode = "InnerCode";

	private static final String KeyInnerFileName = "InnerFileName";

	private ViewHolder viewHolder;

	private long lastPublishClickTime;

	public static void actionStart(Context context, int innerCode, String filename)
	{
		context.startActivity(new Intent(context, UserDynamicPublishActivity.class).putExtra(KeyInnerCode, innerCode).putExtra(KeyInnerFileName, filename));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_dynamic_publish);

		viewHolder = new ViewHolder(this);
		initView();
	}

	private void initView()
	{
		String zoomFileName = getIntent().getStringExtra(KeyInnerFileName);
		final File zoomFile = FileUtil.create(getExternalCacheDir(), zoomFileName);

		ImageView imageView = viewHolder.get(R.id.iv_user_dynamic_publish_icon);
		Glide.with(this).load(zoomFile).skipMemoryCache(true) // 跳过内存缓存
				.diskCacheStrategy(DiskCacheStrategy.NONE) // 禁用磁盘缓存
				.into(imageView);

		// 取消
		viewHolder.setOnClickListener(R.id.tv_user_dynamic_publish_cancel, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DialogIosWidget dialog = new DialogIosWidget(UserDynamicPublishActivity.this)
				{
					@Override
					protected void initXView(TextView tvTitle, TextView tvMsg, Button btnNegative, Button btnPositive, Dialog dialog)
					{
						super.initXView(tvTitle, tvMsg, btnNegative, btnPositive, dialog);

						tvTitle.setText("退出此次编辑");
						btnPositive.setText("退出");
					}
				};
				dialog.setOnPositiveListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						finish();
					}
				});
				dialog.show();
			}
		});

		final int zoomCode = getIntent().getIntExtra(KeyInnerCode, MainMineFragment.KeyDynamicPictureZoomCode);
		// 发布
		viewHolder.setOnClickListener(R.id.tv_user_dynamic_publish, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (System.currentTimeMillis() - lastPublishClickTime > 6000)
				{
					lastPublishClickTime = System.currentTimeMillis();

					String userId = AppStateManager.getInstance().getUserLoginId(UserDynamicPublishActivity.this);
					String content = viewHolder.getText(R.id.et_user_dynamic_publish_content);

					if (zoomCode == MainMineFragment.KeyDynamicPictureZoomCode)
					{
						XHttpUtil.doDynamicPublish(userId, content, zoomFile, new HokolAdapter<String>()
						{
							@Override
							public void onSuccess(String str)
							{
								finish();
							}
						});
					}
					else if (zoomCode == MainMineFragment.KeyPrivatePictureZoomCode)
					{
						XHttpUtil.doDynamicPrivatePublish(userId, content, zoomFile, new HokolAdapter<String>()
						{
							@Override
							public void onSuccess(String s)
							{
								finish();
							}
						});
					}
					else
					{
						// 有 bug 才走这个
						LogFileUtil.e("UserDynamicPublish", "zoomCode error");
						finish();
					}
				}
				else
				{
					SDKManager.toast("正在发布...");
				}
			}
		});
	}
}
