package com.hokol.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.base.log.LogFileUtil;
import com.hokol.http.bean.ResponseDeleteBean;
import com.hokol.http.xHttp;

/**
 * 首个欢迎界面
 *
 * @author yline 2017/2/8 --> 10:47
 * @version 1.0.0
 */
public class WelcomeActivity extends BaseAppCompatActivity
{
	// 跳转
	private Button btnActionMain;
	
	// Http
	private Button btnTestHttp;
	
	private TextView tvHttpHead, tvHttpUrl;
	
	private EditText etHttpInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		initView();
		setClickListener();
	}
	
	private void initView()
	{
		btnActionMain = (Button) findViewById(R.id.btn_action_main);
		
		btnTestHttp = (Button) findViewById(R.id.btn_main_http_request);
		tvHttpHead = (TextView) findViewById(R.id.tv_main_http_head);
		tvHttpUrl = (TextView) findViewById(R.id.tv_main_http_url);
		etHttpInput = (EditText) findViewById(R.id.et_main_http_input);
	}
	
	private void setClickListener()
	{
		btnActionMain.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MainActivity.actionStart(WelcomeActivity.this);
			}
		});
		
		btnTestHttp.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final String headStr = tvHttpHead.getText().toString().trim();
				final String headInput = etHttpInput.getText().toString().trim();
				final String headUrl = headStr + headInput;
				
				tvHttpUrl.setText(headUrl);
				LogFileUtil.v("HttpUrl = " + headUrl);
				
				doGet(headUrl);
			}
		});
	}

	private void doGet(String httpUrl)
	{
		new xHttp<ResponseDeleteBean>()
		{
			@Override
			public void onSuccess(ResponseDeleteBean bean)
			{
				LogFileUtil.v(bean.toString());
				Toast.makeText(WelcomeActivity.this, bean.toString(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailureCode(int code)
			{
				Toast.makeText(WelcomeActivity.this, "code = " + code, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(Exception ex)
			{
				Toast.makeText(WelcomeActivity.this, "ex = " + Log.getStackTraceString(ex), Toast.LENGTH_SHORT).show();
			}
		}.doRequest(httpUrl, ResponseDeleteBean.class);
	}
}
