package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hokol.R;
import com.hokol.medium.http.HttpConstant;
import com.yline.base.BaseAppCompatActivity;

public class HokolProtocolActivity extends BaseAppCompatActivity
{
	private static final String KeyTypeProtocol = "TypeHokolProtocol";

	public static void actionStart(Context context, TypeProtocol type)
	{
		context.startActivity(new Intent(context, HokolProtocolActivity.class).putExtra(KeyTypeProtocol, type.getContent()));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hokol_protocol);

		WebView webView = (WebView) findViewById(R.id.web_view_hokol_protocol);

		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		String typeContent = getIntent().getStringExtra(KeyTypeProtocol);
		webView.loadUrl(HttpConstant.LocalUrlHead + typeContent);

		findViewById(R.id.iv_hokol_protocol_back).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	public enum TypeProtocol
	{
		Charge("protocol/protocol_charge.html"),
		ChargeUser("protocol/protocol_charge_user.html"),
		Register("protocol/protocol_register.html"),
		Service("protocol/protocol_service.html");

		private final String content;

		TypeProtocol(String content)
		{
			this.content = content;
		}

		public String getContent()
		{
			return content;
		}
	}
}
