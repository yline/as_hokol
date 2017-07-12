package com.hokol.test.widget;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.yline.log.LogFileUtil;
import com.yline.test.BaseTestFragment;

public class ModuleFragment extends BaseTestFragment
{

	@Override
	public void testStart(View view, Bundle savedInstanceState)
	{
		addButton("支付宝支付", new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new AsyncTask<Void, Void, Void>()
				{
					@Override
					protected void onPreExecute()
					{
						super.onPreExecute();
						LogFileUtil.v("支付宝支付 onPreExecute");
					}

					@Override
					protected Void doInBackground(Void... params)
					{
						/*PayTask alipay = new PayTask(DemoActivity.this);
						String result = alipay.payV2(orderInfo, true);

						Message msg = new Message();
						msg.what = SDK_PAY_FLAG;
						msg.obj = result;
						mHandler.sendMessage(msg);*/
						return null;
					}

					@Override
					protected void onPostExecute(Void aVoid)
					{
						super.onPostExecute(aVoid);
						LogFileUtil.v("支付宝支付 onPostExecute");
					}
				}.execute();
			}
		});
	}
}
