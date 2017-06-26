package com.hokol.test.http.dynamic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VDynamicCareAllBean;
import com.hokol.medium.http.bean.VDynamicCareSingleBean;
import com.hokol.medium.http.bean.VDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.VDynamicUserAllBean;
import com.hokol.medium.http.bean.VDynamicUserDetailBean;
import com.hokol.medium.http.bean.VDynamicUserPrivateAllBean;
import com.hokol.medium.http.bean.VUserCareAllBean;
import com.hokol.medium.http.bean.WDynamicCareAllBean;
import com.hokol.medium.http.bean.WDynamicCareSingleBean;
import com.hokol.medium.http.bean.WDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.WDynamicUserAllBean;
import com.hokol.medium.http.bean.WDynamicUserDetailBean;
import com.hokol.medium.http.bean.WDynamicUserPrivateAllBean;
import com.hokol.medium.http.bean.WUserCareAllBean;
import com.hokol.test.common.BaseTestActivity;
import com.yline.http.XHttpAdapter;

public class TestDynamicActivity extends BaseTestActivity
{
	/**
	 * 发布动态
	 */
	private void testdt_pub()
	{
		addButton("", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String httpUrl = HttpConstant.url_dynamic_publish;
			}
		});
	}

	private void testdt_private_nums()
	{
		final EditText editTextOne = addEditNumber("用户唯一标识", "2");
		final EditText editTextTwo = addEditNumber("被查看用户ID", "3");
		final EditText editTextThree = addEditNumber("开始号", "0");
		final EditText editTextFour = addEditNumber("请求长度", "0");
		
		addButton("请求用户多条私密动态信息", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user_id = editTextOne.getText().toString().trim();
				String user_id_find = editTextTwo.getText().toString().trim();
				int num1 = parseInt(editTextThree, 0);
				int length = parseInt(editTextFour, 0);
				XHttpUtil.doDynamicUserPrivateAll(new WDynamicUserPrivateAllBean(user_id, user_id_find, num1, length), new XHttpAdapter<VDynamicUserPrivateAllBean>()
				{
					@Override
					public void onSuccess(VDynamicUserPrivateAllBean vDynamicUserPrivateAllBean)
					{

					}
				});
			}
		});


	}

	private void testdt_nums()
	{
		final EditText editTextOne = addEditNumber("User_id", "2");
		final EditText editTextTwo = addEditNumber("开始号", "0");
		final EditText editTextThree = addEditNumber("长度", "2");

		addButton("请求用户多条动态信息", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String user_id = editTextOne.getText().toString().trim();
				final String num1String = editTextTwo.getText().toString().trim();
				final int num1 = Integer.parseInt(num1String);

				final String lengthString = editTextThree.getText().toString().trim();
				final int length = Integer.parseInt(lengthString);

				XHttpUtil.doDynamicUserAll(new WDynamicUserAllBean(user_id, num1, length), new XHttpAdapter<VDynamicUserAllBean>()
				{
					@Override
					public void onSuccess(VDynamicUserAllBean vDynamicUserAllBean)
					{

					}
				});
			}
		});
	}

	private void testdt_one()
	{
		final EditText editTextOne = addEditNumber("动态id", "2");
		final EditText editTextTwo = addEditNumber("用户id", "2");
		addButton("请求单条动态的信息", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String dt_id = editTextOne.getText().toString().trim();
				final String user_id = editTextTwo.getText().toString().trim();

				XHttpUtil.doDynamicSingle(new WDynamicCareSingleBean(user_id, dt_id), new XHttpAdapter<VDynamicCareSingleBean>()
				{
					@Override
					public void onSuccess(VDynamicCareSingleBean vDynamicCareSingleBean)
					{

					}
				});
			}
		});
	}

	private void testdt_zan_switch()
	{
		final EditText editPraise1 = addEditText("user_id", "2");
		final EditText editPraise2 = addEditText("dt_id", "2");
		final EditText editPraise3 = addEditNumber("0 --> 点赞; 其它 --> 取消点赞");
		addButton("单条动态(取消)点赞功能", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String userId = editPraise1.getText().toString().trim();
				final String dynamicId = editPraise2.getText().toString().trim();

				final boolean praiseAction = editPraise3.getText().toString().trim().equals("0") ? true : false;
				final int dynamicPraise = praiseAction ? 1 : 0;

				XHttpUtil.doDynamicPraiseSingle(new WDynamicPraiseSingleBean(userId, dynamicId, dynamicPraise), new XHttpAdapter<VDynamicPraiseSingleBean>()
				{
					@Override
					public void onSuccess(VDynamicPraiseSingleBean vDynamicPraiseSingleBean)
					{

					}
				});
			}
		});
	}


	private void testcare_peo_info()
	{
		final EditText editOne = addEditNumber("user_id", "2");
		final EditText editTwo = addEditNumber("开始号", "0");
		final EditText editThree = addEditNumber("长度", "2");
		addButton("请求关注的人的信息（多条）", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String user_id = editOne.getText().toString().trim();

				String numString = editTwo.getText().toString().trim();
				final int num1 = Integer.parseInt(numString);

				String lengthString = editThree.getText().toString().trim();
				final int length = Integer.parseInt(lengthString);

				XHttpUtil.doUserCareAll(new WUserCareAllBean(user_id, num1, length), new XHttpAdapter<VUserCareAllBean>()
				{
					@Override
					public void onSuccess(VUserCareAllBean vUserCareAllBean)
					{

					}
				});
			}
		});
	}

	private void testuser_info()
	{
		final EditText editUser1 = addEditNumber("user_id", "2");
		final EditText editUser2 = addEditNumber("user_id_find", "1");
		addButton("请求用户详情信息", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String httpUrl = HttpConstant.url_dynamic_user_detail;
				final String user_id = editUser1.getText().toString().trim();
				final String user_id_find = editUser2.getText().toString().trim();

				XHttpUtil.doDynamicUserDetail(new WDynamicUserDetailBean(user_id, user_id_find), new XHttpAdapter<VDynamicUserDetailBean>()
				{
					@Override
					public void onSuccess(VDynamicUserDetailBean vDynamicUserDetailBean)
					{

					}
				});
			}
		});
	}

	private void testdongtai()
	{
		final EditText editCare1 = addEditText("user_id", "2");
		final EditText editCare2 = addEditNumber("start", "0");
		final EditText editCare3 = addEditNumber("length", "2");
		addButton("请求关注的人的多条动态", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String userId = editCare1.getText().toString().trim();
				final int start = Integer.parseInt(editCare2.getText().toString().trim());
				final int length = Integer.parseInt(editCare3.getText().toString().trim());

				XHttpUtil.doDynamicCareAll(new WDynamicCareAllBean(userId, start, length), new XHttpAdapter<VDynamicCareAllBean>()
				{
					@Override
					public void onSuccess(VDynamicCareAllBean vDynamicCareAllBean)
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

		testdongtai();
		testuser_info();
		testcare_peo_info();
		testdt_zan_switch();
		testdt_one();
		testdt_nums();
		testdt_private_nums();
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestDynamicActivity.class));
	}
}
