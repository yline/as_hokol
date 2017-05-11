package com.hokol.test.http.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VLoginPhonePwdBean;
import com.hokol.medium.http.bean.WLoginPhonePwdBean;
import com.hokol.test.common.BaseTestActivity;
import com.hokol.test.httpattach.AvatarActivity;
import com.yline.http.XHttpAdapter;

public class TestLoginActivity extends BaseTestActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// 手机号 + 密码登陆
		final EditText editTextOne = addEditNumber("手机号", "13656786656");
		final EditText editTextTwo = addEditNumber("密码", "lj123456");
		addButton("手机号+密码登陆", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String username = editTextOne.getText().toString().trim();
				String password = editTextTwo.getText().toString().trim();

				final WLoginPhonePwdBean requestBean = new WLoginPhonePwdBean(username, password);

				// 这样的方法,并不会被执行
				XHttpUtil.doLoginPhonePwd(requestBean, new XHttpAdapter<VLoginPhonePwdBean>()
				{
					@Override
					public void onSuccess(VLoginPhonePwdBean vLoginPhonePwdBean)
					{
						AvatarActivity.actionStart(TestLoginActivity.this, vLoginPhonePwdBean.getUser_logo());
					}
				});
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestLoginActivity.class));
	}
}
