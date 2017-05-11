package com.hokol.test.http.setting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.WSettingResetPhoneBean;
import com.hokol.medium.http.bean.WSettingResetPwdBean;
import com.hokol.medium.http.bean.WSettingSubmitProposalBean;
import com.hokol.medium.http.bean.WSettingUpdateInfoBean;
import com.hokol.test.common.BaseTestActivity;
import com.hokol.test.common.IApplication;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.utils.FileUtil;

import java.io.File;

public class TestSettingActivity extends BaseTestActivity
{
	private EditText editTextResetUserLogo;

	private static final int ResetUserLogoRequestCode = 1;

	private void testreset_user_logo()
	{
		editTextResetUserLogo = addEditNumber("userId", "2");
		addButton("用户头像修改", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = editTextResetUserLogo.getText().toString().trim();

				Intent tempIntent = new Intent();
				tempIntent.setAction(Intent.ACTION_GET_CONTENT);
				tempIntent.setType("*/*");
				tempIntent.addCategory(Intent.CATEGORY_OPENABLE); // 可选择的选项

				if (null != tempIntent.resolveActivity(TestSettingActivity.this.getPackageManager()))
				{
					Intent target = Intent.createChooser(tempIntent, "chooser");
					TestSettingActivity.this.startActivityForResult(target, ResetUserLogoRequestCode);
				}
				else
				{
					IApplication.toast("跳转失败");
				}
			}
		});
	}

	private void testreset_user_info()
	{
		final EditText editTextOne = addEditNumber("userId", "4");
		final EditText editTextTwo = addEditText("用户昵称", "yline");
		addButton("用户信息修改", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = editTextOne.getText().toString().trim();
				String user_nickname = editTextTwo.getText().toString().trim();

				WSettingUpdateInfoBean wSettingUpdateInfoBean = new WSettingUpdateInfoBean(userId);
				wSettingUpdateInfoBean.setUser_nickname(user_nickname);

				XHttpUtil.doSettingUpdateInfo(wSettingUpdateInfoBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}

	private void testreset_tel()
	{
		addButton("用户重置手机", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				WSettingResetPhoneBean wSettingResetPhoneBean = null;
				XHttpUtil.doSettingResetPhone(wSettingResetPhoneBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}

	private void testreset_pwd()
	{
		final EditText editTextOne = addEditNumber("user_id", "2");
		final EditText editTextTwo = addEditNumber("old_pwd", "lj123456");
		final EditText editTextThree = addEditNumber("new_pwd", "lj1234567");
		addButton("用户重置密码", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user_id = editTextOne.getText().toString().trim();
				String old_pwd = editTextTwo.getText().toString().trim();
				String new_pwd = editTextThree.getText().toString().trim();
				XHttpUtil.doSettingResetPwd(new WSettingResetPwdBean(user_id, old_pwd, new_pwd), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}

	private void testuser_advice()
	{
		final EditText editTextOne = addEditNumber("user_id", "2");
		final EditText editTextTwo = addEditNumber("[1:应用崩溃,2:功能问题,3:注册登录,4:改善建议,5:订单支付]", "1");
		final EditText editTextThree = addEditNumber("advice_content", "content");
		final EditText editTextFour = addEditNumber("user_connection", "pop@email.com");
		addButton("用户发表意见", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user_id = editTextOne.getText().toString().trim();
				int advice_type = parseInt(editTextTwo, 1);
				String advice_content = editTextThree.getText().toString().trim();
				String user_connection = editTextFour.getText().toString().trim();

				XHttpUtil.doSettingSubmitProposal(new WSettingSubmitProposalBean(user_id, advice_type, advice_content, user_connection), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		testuser_advice();
		testreset_pwd();
		testreset_tel();

		testreset_user_info();
		testreset_user_logo();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		LogFileUtil.v(IApplication.TAG, "onActivityResult data is null ? -> " + (data == null));
		if (null != data)
		{
			if (requestCode == ResetUserLogoRequestCode)
			{
				String contentPath = data.getDataString();
				String filePath = FileUtil.uri2File(this, Uri.parse(contentPath));
				String userId = editTextResetUserLogo.getText().toString().trim();
				LogFileUtil.v("contentPath = " + contentPath + ", filePath = " + filePath + ", userId = " + userId);

				File file = new File(filePath);
				if (null == file || !file.exists())
				{
					LogFileUtil.v("file is null or not exist");
				}
				else
				{
					XHttpUtil.doSettingUpdateAvatar(userId, file, new XHttpAdapter<String>()
					{
						@Override
						public void onSuccess(String s)
						{

						}
					});
				}
			}
		}
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestSettingActivity.class));
	}
}
