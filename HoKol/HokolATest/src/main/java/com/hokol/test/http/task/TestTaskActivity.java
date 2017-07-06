package com.hokol.test.http.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskMainAllBean;
import com.hokol.medium.http.bean.VTaskMainDetailBean;
import com.hokol.medium.http.bean.VTaskStaffCommentedInfoBean;
import com.hokol.medium.http.bean.VTaskUserAcceptBean;
import com.hokol.medium.http.bean.VTaskUserDeliveredBean;
import com.hokol.medium.http.bean.VTaskUserPublishedBean;
import com.hokol.medium.http.bean.VTaskUserSignUpDetailBean;
import com.hokol.medium.http.bean.WTaskActionMasterCommentBean;
import com.hokol.medium.http.bean.WTaskActionMasterFinishBean;
import com.hokol.medium.http.bean.WTaskActionMasterTakeOnBean;
import com.hokol.medium.http.bean.WTaskActionMasterTradeBean;
import com.hokol.medium.http.bean.WTaskActionStaffConfirmBean;
import com.hokol.medium.http.bean.WTaskActionStaffSignUpBean;
import com.hokol.medium.http.bean.WTaskActionStaffTradeBean;
import com.hokol.medium.http.bean.WTaskMainAllBean;
import com.hokol.medium.http.bean.WTaskMainCollectionBean;
import com.hokol.medium.http.bean.WTaskMainDetailBean;
import com.hokol.medium.http.bean.WTaskMainPublishBean;
import com.hokol.medium.http.bean.WTaskStaffCommentedInfoBean;
import com.hokol.medium.http.bean.WTaskUserAcceptBean;
import com.hokol.medium.http.bean.WTaskUserDeliveredBean;
import com.hokol.medium.http.bean.WTaskUserPublishedBean;
import com.hokol.medium.http.bean.WTaskUserSignUpDetailBean;
import com.hokol.test.common.BaseTestActivity;
import com.yline.http.XHttpAdapter;

import java.util.Arrays;

public class TestTaskActivity extends BaseTestActivity
{
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TestTaskActivity.class));
	}

	private void testpost_task()
	{
		final EditText editTextOne = addEditNumber("user_id", "1");
		final EditText editTextTwo = addEditNumber("task_id", "2");
		final EditText editTextThree = addEditNumber("选择开关(0-未完成交易,1-完成交易)", "1");

		addButton("雇员确认交易", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user_id = editTextOne.getText().toString().trim();
				String task_id = editTextTwo.getText().toString().trim();
				int switchs = parseInt(editTextThree, 1);

				WTaskActionStaffTradeBean wTaskActionStaffTradeBean = new WTaskActionStaffTradeBean(user_id, task_id, switchs);
				XHttpUtil.doTaskActionStaffTrade(wTaskActionStaffTradeBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}

	private void testis_confirm_task()
	{
		final EditText editTextOne = addEditNumber("user_id", "1");
		final EditText editTextTwo = addEditNumber("task_id", "2");
		final EditText editTextThree = addEditNumber("选择开关(0-拒绝接单,1-确定接单)", "1");

		addButton("雇员确认、拒绝接单", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user_id = editTextOne.getText().toString().trim();
				String task_id = editTextTwo.getText().toString().trim();
				int switchs = parseInt(editTextThree, 1);

				WTaskActionStaffConfirmBean wTaskActionStaffConfirmBean = new WTaskActionStaffConfirmBean(user_id, task_id, switchs);
				XHttpUtil.doTaskActionStaffConfirm(wTaskActionStaffConfirmBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}

	private void testis_success_task()
	{
		final EditText editTextOne = addEditNumber("user_id", "3");
		final EditText editTextTwo = addEditNumber("列表选择开关(0-全部,1-待交易,2-待评论)", "0");
		final EditText editTextThree = addEditNumber("num1", "0");
		final EditText editTextFour = addEditNumber("length", "4");

		addButton("用户已投递的任务", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user_id = editTextOne.getText().toString().trim();
				int switchs = parseInt(editTextTwo, 0);
				int num1 = parseInt(editTextThree, 0);
				int length = parseInt(editTextFour, 4);

				WTaskUserDeliveredBean wTaskUserDeliveredBean = new WTaskUserDeliveredBean(user_id, switchs, num1, length);
				XHttpUtil.doTaskUserDelivered(wTaskUserDeliveredBean, new XHttpAdapter<VTaskUserDeliveredBean>()
				{
					@Override
					public void onSuccess(VTaskUserDeliveredBean vTaskUserDeliveredBean)
					{

					}
				});
			}
		});
	}
	
	private void testconfirm_task()
	{
		final EditText editTextOne = addEditNumber("user_id", "3");
		final EditText editTextTwo = addEditNumber("task_id", "2");
		final EditText editTextThree = addEditNumber("被确认用户ID", "4");
		final EditText editTextFour = addEditNumber("完成状态(0-未完成任务, 1-完成任务)", "4");

		addButton("雇主确定交易", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user_id = editTextOne.getText().toString().trim();
				String task_id = editTextTwo.getText().toString().trim();

				String confirm_user_id = editTextThree.getText().toString().trim();
				int confirm_status = parseInt(editTextFour, 0);
				WTaskActionMasterTradeBean.WTaskActionMasterTradeInfoBean infoBean = new WTaskActionMasterTradeBean.WTaskActionMasterTradeInfoBean(confirm_user_id, confirm_status);

				WTaskActionMasterTradeBean wTaskActionMasterTradeBean = new WTaskActionMasterTradeBean(user_id, task_id, Arrays.asList(infoBean));
				XHttpUtil.doTaskActionMasterTrade(wTaskActionMasterTradeBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}
	
	private void testfinish_task_employe()
	{
		final EditText editTextOne = addEditNumber("user_id", "3");
		final EditText editTextTwo = addEditNumber("task_id", "2");
		final EditText editTextThree = addEditNumber("操作开关(0-实现结束报名,1-实现取消任务)", "4");

		addButton("雇主结束报名/取消任务", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user_id = editTextOne.getText().toString().trim();
				String task_id = editTextTwo.getText().toString().trim();
				int switchs = parseInt(editTextThree, 0);

				WTaskActionMasterFinishBean wTaskActionMasterFinishBean = new WTaskActionMasterFinishBean(user_id, task_id, switchs);
				XHttpUtil.doTaskActionMasterFinish(wTaskActionMasterFinishBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}
	
	private void testtask_comment_peo_info()
	{
		final EditText editTextTwo = addEditNumber("task_id", "2");
		addButton("获取该任务雇员信息", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String task_id = editTextTwo.getText().toString().trim();
				WTaskStaffCommentedInfoBean wTaskStaffCommentedInfoBean = new WTaskStaffCommentedInfoBean(task_id);
				XHttpUtil.doTaskStaffCommentedInfo(wTaskStaffCommentedInfoBean, new XHttpAdapter<VTaskStaffCommentedInfoBean>()
				{
					@Override
					public void onSuccess(VTaskStaffCommentedInfoBean vTaskStaffCommentedInfoBean)
					{

					}
				});
			}
		});
	}
	
	private void testtask_comment_employer()
	{
		final EditText editTextOne = addEditNumber("user_id", "3");
		final EditText editTextTwo = addEditNumber("task_id", "2");
		final EditText editTextThree = addEditNumber("被评论用户ID", "4");

		addButton("雇主评价雇员", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user_id = editTextOne.getText().toString().trim();
				String task_id = editTextTwo.getText().toString().trim();
				String commentedUserId = editTextThree.getText().toString().trim();
				WTaskActionMasterCommentBean.MasterCommentContentBean bean = new WTaskActionMasterCommentBean.MasterCommentContentBean(commentedUserId, 3, 3, 3);

				WTaskActionMasterCommentBean wTaskActionMasterCommentBean = new WTaskActionMasterCommentBean(user_id, task_id, Arrays.asList(bean));
				XHttpUtil.doTaskActionMasterComment(wTaskActionMasterCommentBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}
	
	private void testtask_employe()
	{
		final EditText editTextOne = addEditNumber("任务ID", "1");
		final EditText editTextTwo = addEditNumber("报名用户ID", "1");
		final EditText editTextThree = addEditNumber("发布任务用户ID", "2");

		addButton("雇主录用报名者", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String task_id = editTextOne.getText().toString().trim();
				String user_id_join = editTextTwo.getText().toString().trim();
				String user_id = editTextThree.getText().toString().trim();

				WTaskActionMasterTakeOnBean wTaskActionMasterTakeOnBean = new WTaskActionMasterTakeOnBean(task_id, user_id_join, user_id);
				XHttpUtil.doTaskActionMasterTakeOn(wTaskActionMasterTakeOnBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}
	
	private void testtask_join()
	{
		final EditText editTextOne = addEditNumber("task_id", "2");
		final EditText editTextTwo = addEditNumber("user_id", "0");

		addButton("任务报名", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String taskId = editTextOne.getText().toString().trim();
				String userId = editTextTwo.getText().toString().trim();

				WTaskActionStaffSignUpBean wTaskActionStaffSignUpBean = new WTaskActionStaffSignUpBean(userId, taskId);
				XHttpUtil.doTaskActionStaffSignUp(wTaskActionStaffSignUpBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}
	
	private void testtask_published()
	{
		final EditText editTextOne = addEditNumber("user_id", "2");
		final EditText editTextTwo = addEditNumber("列表选择开关：[0:全部,1,待报名,2:待交易,3:待评价] ", "0");
		final EditText editTextThree = addEditNumber("数据上限", "0");
		final EditText editTextFour = addEditNumber("数据长度", "2");

		addButton("用户已发布任务", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String task_id = editTextOne.getText().toString().trim();
				int switchs = parseInt(editTextTwo, 0);
				int num1 = parseInt(editTextThree, 0);
				int length = parseInt(editTextFour, 2);

				WTaskUserPublishedBean wTaskUserPublishedBean = new WTaskUserPublishedBean(task_id, num1, length);
				XHttpUtil.doTaskUserPublishedAll(wTaskUserPublishedBean, new XHttpAdapter<VTaskUserPublishedBean>()
				{
					@Override
					public void onSuccess(VTaskUserPublishedBean vTaskUserPublishedBean)
					{
					}
				});
			}
		});
	}
	
	private void testtask_join_detail()
	{
		final EditText editTextOne = addEditNumber("task_id", "2");
		final EditText editTextTwo = addEditNumber("数据上限", "0");
		final EditText editTextThree = addEditNumber("数据长度", "2");
		addButton("任务报名详情", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String task_id = editTextOne.getText().toString().trim();
				int num1 = parseInt(editTextTwo, 0);
				int length = parseInt(editTextThree, 2);

				WTaskUserSignUpDetailBean wTaskUserSignUpBean = new WTaskUserSignUpDetailBean(task_id, num1, length);
				XHttpUtil.doTaskUserSignUpDetail(wTaskUserSignUpBean, new XHttpAdapter<VTaskUserSignUpDetailBean>()
				{
					@Override
					public void onSuccess(VTaskUserSignUpDetailBean vTaskUserSignUpBean)
					{

					}
				});
			}
		});
	}
	
	private void testtask_confirm_detail()
	{
		final EditText editTextOne = addEditNumber("task_id", "1");
		final EditText editTextTwo = addEditNumber("数据上限", "0");
		final EditText editTextThree = addEditNumber("数据长度", "2");

		addButton("任务接单详情", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String task_id = editTextOne.getText().toString().trim();
				int num1 = parseInt(editTextTwo, 0);
				int length = parseInt(editTextThree, 2);

				WTaskUserAcceptBean wTaskUserAcceptBean = new WTaskUserAcceptBean(task_id, num1, length);
				XHttpUtil.doTaskUserAcceptDetail(wTaskUserAcceptBean, new XHttpAdapter<VTaskUserAcceptBean>()
				{
					@Override
					public void onSuccess(VTaskUserAcceptBean vTaskUserAcceptBean)
					{

					}
				});
			}
		});
	}
	
	private void testtask_collect_switch()
	{
		final EditText editTextOne = addEditNumber("user_id", "2");
		final EditText editTextTwo = addEditNumber("task_id", "1");
		final EditText editTextThree = addEditNumber("collect", "1");

		addButton("任务收藏/取消收藏", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String user_id = editTextOne.getText().toString().trim();
				final String task_id = editTextTwo.getText().toString().trim();

				final int collect = parseInt(editTextThree, 0);

				XHttpUtil.doTaskMainCollection(new WTaskMainCollectionBean(user_id, task_id, collect), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}
	
	private void testtask_pub()
	{
		final EditText editTextOne = addEditNumber("task_user_id", "2");
		final EditText editTextTwo = addEditNumber("任务类型：[1:网红,2:主播,3:演员,4:模特,5:歌手,6:体育] ", "1");
		final EditText editTextThree = addEditNumber("任务薪酬", "10000");
		final EditText editTextFour = addEditNumber("男性数量", "1");
		final EditText editTextFive = addEditNumber("女性数量", "1");

		addButton("任务发布", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String task_user_id = editTextOne.getText().toString().trim();
				int task_type = parseInt(editTextTwo, 1);
				float task_fee = parseFloat(editTextThree, 10000);
				int task_man_num = parseInt(editTextFour, 1);
				int task_woman_num = parseInt(editTextFive, 1);

				WTaskMainPublishBean wTaskMainPublishBean = new WTaskMainPublishBean(task_user_id, Arrays.asList(task_type), task_fee, task_man_num, task_woman_num);
				XHttpUtil.doTaskMainPublish(wTaskMainPublishBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}
	
	private void testtask_detail()
	{
		final EditText editTextOne = addEditNumber("task_id", "2");
		addButton("获取任务详情(单条)", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String task_id = editTextOne.getText().toString().trim();

				XHttpUtil.doTaskMainDetail(new WTaskMainDetailBean(task_id, WTaskMainDetailBean.UnLoginState), new XHttpAdapter<VTaskMainDetailBean>()
				{
					@Override
					public void onSuccess(VTaskMainDetailBean vTaskMainDetailBean)
					{

					}
				});
			}
		});
	}
	
	private void testtask_index()
	{
		final EditText editTextFour = addEditNumber("num1", "0");
		final EditText editTextFive = addEditNumber("length", "8");

		addButton("获取任务列表(多条)", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final int num1 = Integer.parseInt(editTextFour.getText().toString().trim());
				final int length = Integer.parseInt(editTextFive.getText().toString().trim());

				WTaskMainAllBean wTaskMainAll = new WTaskMainAllBean(num1, length);
				wTaskMainAll.setP_code("浙江省");
				wTaskMainAll.setC_code(Arrays.asList("杭州市"));
				XHttpUtil.doTaskMainAll(wTaskMainAll, new XHttpAdapter<VTaskMainAllBean>()
				{
					@Override
					public void onSuccess(VTaskMainAllBean vTaskMainAll)
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

		testtask_index();
		testtask_detail();
		testtask_pub();
		testtask_collect_switch();

		//  新增 的
		testtask_confirm_detail();
		testtask_join_detail();
		testtask_published();
		testtask_join();
		testtask_employe();
		testtask_comment_employer();
		testtask_comment_peo_info();
		testfinish_task_employe();
		testconfirm_task();
		testis_success_task();
		testis_confirm_task();
		testpost_task();
	}
}
