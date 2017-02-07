package com.nhjh7.e.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nhjh7.e.R;
import com.nhjh7.e.application.IApplication;
import com.nhjh7.e.bean.CodeBean;
import com.nhjh7.e.http.HttpUtils;
import com.nhjh7.e.service.FloatService;
import com.yline.log.LogFileUtil;

public class MainActivity extends AppCompatActivity
{
	private TextView tvShow;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initData();

		String code = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
		LogFileUtil.v("code = " + code);

		// final String url = "192.168.0.104/crest/index.php/api/example/users/" + code; // key=
		final String url = "120.92.77.154/crest/index.php/api/example/users/N#887a19d10a6601b2";

		findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				HttpUtils.doGetAsync(url, new HttpUtils.Callback()
				{
					@Override
					public void onSuccess(String result)
					{
						IApplication.toast("result = " + result);
						LogFileUtil.v("result = " + result);

						Gson gson = new Gson();
						CodeBean bean = gson.fromJson(result, CodeBean.class);

						LogFileUtil.v("bean = " + bean.toString());
						tvShow.setText(bean.toString());
					}

					@Override
					public void onError(Exception ex)
					{
						IApplication.toast("ex = " + ex);
					}
				});
			}
		});

		findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				FloatService.actionStart(MainActivity.this);
			}
		});

		findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				FloatService.actionStop(MainActivity.this);
			}
		});
	}

	private void initView()
	{
		tvShow = (TextView) findViewById(R.id.tv_show);
	}

	private void initData()
	{

	}


}
