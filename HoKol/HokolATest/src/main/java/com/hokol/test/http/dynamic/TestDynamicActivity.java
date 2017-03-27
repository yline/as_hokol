package com.hokol.test.http.dynamic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttp;
import com.hokol.medium.http.bean.VDynamicCareAllBean;
import com.hokol.medium.http.bean.VDynamicCareSingleBean;
import com.hokol.medium.http.bean.VDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.VDynamicUserAllBean;
import com.hokol.medium.http.bean.VDynamicUserDetailBean;
import com.hokol.medium.http.bean.VUserCareAllBean;
import com.hokol.medium.http.bean.WDynamicCareAllBean;
import com.hokol.medium.http.bean.WDynamicCareSingleBean;
import com.hokol.medium.http.bean.WDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.WDynamicUserAllBean;
import com.hokol.medium.http.bean.WDynamicUserDetailBean;
import com.hokol.medium.http.bean.WUserCareAllBean;
import com.hokol.test.common.BaseTestActivity;

/**
 * 动态接口
 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
 * 请求关注的人的多条动态 --> dongtai --> url_dynamic_care_all --> WDynamicCareAllBean - VDynamicCareAllBean + VDynamicCareBean --> 长度<=0情况未处理、
 * 请求用户详情信息 --> user_info --> url_dynamic_user_detail --> WDynamicUserDetailBean - VDynamicUserDetailBean --> ok + 没有参数也能请求到数据
 * 请求关注的人的信息（多条） --> care_peo_info --> url_user_care_all --> WUserCareAllBean - VUserCareAllBean --> ok
 * 单条动态(取消)点赞功能 --> dt_zan_switch --> url_dynamic_praise_single --> WDynamicPraiseSingleBean - VDynamicPraiseSingleBean --> ok
 * 请求单条动态的信息 --> dt_one --> url_dynamic_single --> WDynamicCareSingleBean - VDynamicCareSingleBean --> ok
 * 请求用户多条动态信息 --> dt_nums --> url_dynamic_user_all --> WDynamicUserAllBean - VDynamicUserAllBean --> ok
 * 发布动态 --> dt_pub --> url_dynamic_publish --> ？？？ - ？？？ --> 稍后测试
 */
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

	private void testdt_nums()
	{
		final EditText editTextOne = addEditNumber("User_id");
		final EditText editTextTwo = addEditNumber("开始号");
		final EditText editTextThree = addEditNumber("长度");

		addButton("请求用户多条动态信息", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String httpUrl = HttpConstant.url_dynamic_user_all;

				final String user_id = editTextOne.getText().toString().trim();
				final String num1String = editTextTwo.getText().toString().trim();
				final int num1 = Integer.parseInt(num1String);

				final String lengthString = editTextThree.getText().toString().trim();
				final int length = Integer.parseInt(lengthString);

				new XHttp<VDynamicUserAllBean>()
				{
					@Override
					protected Object getRequestPostParam()
					{
						return new WDynamicUserAllBean(user_id, num1, length);
					}

					@Override
					public void onSuccess(VDynamicUserAllBean vDynamicUserAllBean)
					{

					}
				}.doRequest(httpUrl, VDynamicUserAllBean.class);
			}
		});
	}

	private void testdt_one()
	{
		final EditText editTextOne = addEditNumber("动态id");
		addButton("请求单条动态的信息", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String httpUrl = HttpConstant.url_dynamic_single;
				final String dt_id = editTextOne.getText().toString().trim();

				new XHttp<VDynamicCareSingleBean>()
				{
					@Override
					protected Object getRequestPostParam()
					{
						return new WDynamicCareSingleBean(dt_id);
					}

					@Override
					public void onSuccess(VDynamicCareSingleBean vDynamicCareSingleBean)
					{

					}
				}.doRequest(httpUrl, VDynamicCareSingleBean.class);
			}
		});
	}

	private void testdt_zan_switch()
	{
		final EditText editPraise1 = addEditText("user_id");
		final EditText editPraise2 = addEditText("dt_id");
		final EditText editPraise3 = addEditNumber("0 --> 点赞; 其它 --> 取消点赞");
		addButton("单条动态(取消)点赞功能", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String userId = editPraise1.getText().toString().trim();
				final String dynamicId = editPraise2.getText().toString().trim();
				final boolean praiseAction = editPraise3.getText().toString().trim().equals("0") ? true : false;

				String httpUrl = HttpConstant.url_dynamic_praise_single;
				final int dynamicPraise = praiseAction ? 1 : 0;

				new XHttp<VDynamicPraiseSingleBean>()
				{
					@Override
					protected Object getRequestPostParam()
					{
						return new WDynamicPraiseSingleBean(userId, dynamicId, dynamicPraise);
					}

					@Override
					public void onSuccess(VDynamicPraiseSingleBean vUserDynamicPraiseBean)
					{

					}
				}.doRequest(httpUrl, VDynamicPraiseSingleBean.class);
			}
		});
	}


	private void testcare_peo_info()
	{
		final EditText editOne = addEditNumber("user_id");
		final EditText editTwo = addEditNumber("开始号");
		final EditText editThree = addEditNumber("长度");
		addButton("请求关注的人的信息（多条）", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String httpUrl = HttpConstant.url_user_care_all;
				final String user_id = editOne.getText().toString().trim();

				String numString = editTwo.getText().toString().trim();
				final int num1 = Integer.parseInt(numString);

				String lengthString = editThree.getText().toString().trim();
				final int length = Integer.parseInt(lengthString);

				new XHttp<VUserCareAllBean>()
				{
					@Override
					protected Object getRequestPostParam()
					{
						return new WUserCareAllBean(user_id, num1, length);
					}

					@Override
					public void onSuccess(VUserCareAllBean vUserCareAllBean)
					{

					}
				}.doRequest(httpUrl, VUserCareAllBean.class);
			}
		});
	}

	private void testuser_info()
	{
		final EditText editUser1 = addEditNumber("user_id");
		final EditText editUser2 = addEditNumber("user_id_find");
		addButton("请求用户详情信息", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String httpUrl = HttpConstant.url_dynamic_user_detail;
				final String user_id = editUser1.getText().toString().trim();
				final String user_id_find = editUser2.getText().toString().trim();

				new XHttp<VDynamicUserDetailBean>()
				{
					@Override
					protected Object getRequestPostParam()
					{
						return new WDynamicUserDetailBean(user_id, user_id_find);
					}

					@Override
					public void onSuccess(VDynamicUserDetailBean vDynamicUserDetailBean)
					{

					}
				}.doRequest(httpUrl, VDynamicUserDetailBean.class);
			}
		});
	}

	private void testdongtai()
	{
		final EditText editCare1 = addEditText("user_id");
		final EditText editCare2 = addEditNumber("start");
		final EditText editCare3 = addEditNumber("length");
		addButton("请求关注的人的多条动态", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String userId = editCare1.getText().toString().trim();
				final int start = Integer.parseInt(editCare2.getText().toString().trim());
				final int length = Integer.parseInt(editCare3.getText().toString().trim());

				String httpUrl = HttpConstant.url_dynamic_care_all;

				new XHttp<VDynamicCareAllBean>()
				{
					@Override
					protected Object getRequestPostParam()
					{
						return new WDynamicCareAllBean(userId, start, length);
					}

					@Override
					public void onSuccess(VDynamicCareAllBean vUserDynamicCareBean)
					{

					}
				}.doRequest(httpUrl, VDynamicCareAllBean.class);
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
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestDynamicActivity.class));
	}
}
