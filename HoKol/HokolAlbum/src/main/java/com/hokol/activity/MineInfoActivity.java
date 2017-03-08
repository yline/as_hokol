package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hokol.R;

public class MineInfoActivity extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mine_info);
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, MineInfoActivity.class));
	}
}
