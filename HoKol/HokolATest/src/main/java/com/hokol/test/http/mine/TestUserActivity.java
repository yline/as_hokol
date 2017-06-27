package com.hokol.test.http.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserFansAllBean;
import com.hokol.medium.http.bean.VUserMessageSystemBean;
import com.hokol.medium.http.bean.VUserTaskCollectionBean;
import com.hokol.medium.http.bean.WUserCareOrCancelBean;
import com.hokol.medium.http.bean.WUserCoinGiftBean;
import com.hokol.medium.http.bean.WUserFansAllBean;
import com.hokol.medium.http.bean.WUserMessageSystemBean;
import com.hokol.medium.http.bean.WUserTaskCollectionBean;
import com.hokol.test.common.BaseTestActivity;
import com.yline.http.XHttpAdapter;

public class TestUserActivity extends BaseTestActivity
{
	private void testmine_message()
	{
		final EditText editTextOne = addEditNumber("user_id", "2");
		final EditText editTextTwo = addEditNumber("num1", "0");
		final EditText editTextThree = addEditNumber("length", "2");

		addButton("我的消息", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = editTextOne.getText().toString().trim();
				int receiverNum = parseInt(editTextTwo, 0);
				int receiverLength = parseInt(editTextThree, 23);

				WUserMessageSystemBean wUserMessageBean = new WUserMessageSystemBean(userId, receiverNum, receiverLength);
				XHttpUtil.doUserMessageSystem(wUserMessageBean, new XHttpAdapter<VUserMessageSystemBean>()
				{
					@Override
					public void onSuccess(VUserMessageSystemBean vUserMessageBean)
					{

					}
				});
			}
		});
	}

	private void testpresent_coin()
	{
		final EditText editTextOne = addEditNumber("userId", "1");
		final EditText editTextTwo = addEditNumber("receiverUserId", "2");
		final EditText editTextThree = addEditNumber("dynamicId", "3");
		final EditText editTextFour = addEditNumber("赠送红豆数目", "100");
		addButton("用户赠送红豆", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = editTextOne.getText().toString().trim();
				String receiverUserId = editTextTwo.getText().toString().trim();
				String dynamicId = editTextThree.getText().toString().trim();
				int coinNum = parseInt(editTextFour, 0);
				XHttpUtil.doUserCoinGift(new WUserCoinGiftBean(userId, receiverUserId, dynamicId, coinNum), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}

	private void testuser_care_switch()
	{
		final EditText editTextOne = addEditNumber("user_id", "1");
		final EditText editTextTwo = addEditNumber("user_id_other", "0");
		final EditText editTextThree = addEditNumber("开关：[0:取消关注，1：关注]", "0");
		addButton("关注/取消关注某用户", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user_id = editTextOne.getText().toString().trim();
				String user_id_other = editTextTwo.getText().toString().trim();
				int care = parseInt(editTextThree, 0);

				XHttpUtil.doUserCareOrCancel(new WUserCareOrCancelBean(user_id, user_id_other, care), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}

	private void testuser_collect_task()
	{
		final EditText editTextOne = addEditNumber("user_id", "1");
		final EditText editTextTwo = addEditNumber("开始号", "0");
		final EditText editTextThree = addEditNumber("长度", "2");
		addButton("请求用户的收藏任务", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user_id = editTextOne.getText().toString().trim();
				int num1 = parseInt(editTextTwo, 0);
				int length = parseInt(editTextThree, 3);

				XHttpUtil.doUserCollection(new WUserTaskCollectionBean(user_id, num1, length), new XHttpAdapter<VUserTaskCollectionBean>()
				{
					@Override
					public void onSuccess(VUserTaskCollectionBean vUserCollectionBean)
					{

					}
				});
			}
		});
	}

	private void testuser_fans_info()
	{
		final EditText editTextOne = addEditNumber("user_id", "1");
		final EditText editTextTwo = addEditNumber("开始号", "0");
		final EditText editTextThree = addEditNumber("长度", "2");
		addButton("请求用户粉丝的信息（多条）", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user_id = editTextOne.getText().toString().trim();
				int num1 = parseInt(editTextTwo, 0);
				int length = parseInt(editTextThree, 3);

				XHttpUtil.doUserFansAll(new WUserFansAllBean(user_id, num1, length), new XHttpAdapter<VUserFansAllBean>()
				{
					@Override
					public void onSuccess(VUserFansAllBean vUserFansAllBean)
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

		testuser_fans_info();
		testuser_collect_task();
		testuser_care_switch();
		testpresent_coin();
		testmine_message();
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestUserActivity.class));
	}
}
