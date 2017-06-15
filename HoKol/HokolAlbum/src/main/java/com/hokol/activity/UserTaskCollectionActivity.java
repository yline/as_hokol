package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hokol.R;
import com.hokol.medium.widget.recycler.FloatLinearItemDecoration;
import com.yline.base.BaseAppCompatActivity;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.adapter.CommonRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTaskCollectionActivity extends BaseAppCompatActivity
{
	private CollectionAdapter collectionAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_task_collection);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_task_collection);
		FloatLinearItemDecoration itemDecoration = new FloatLinearItemDecoration(this)
		{
			@Override
			public int getTitleHeight()
			{
				return UIScreenUtil.dp2px(UserTaskCollectionActivity.this, 30);
			}

			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_null_size_medium;
			}
		};
		recyclerView.addItemDecoration(itemDecoration);

		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		collectionAdapter = new CollectionAdapter();
		recyclerView.setAdapter(collectionAdapter);

		initData();

		itemDecoration.setKeys(keys);

		findViewById(R.id.iv_task_collection).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	private Map<Integer, String> keys;//存放所有key的位置和内容

	private List<String> itemDoubleList;

	private void initData()
	{
		keys = new HashMap<>();

		itemDoubleList = new ArrayList<>();
		List tempA = Arrays.asList("汉庭", "7天", "如家", "速8", "锦江之星", "飘HOME", "海友", "锦江", "世纪金源");
		keys.put(0, "A");
		itemDoubleList.addAll(tempA);

		List tempB = Arrays.asList("天安门商圈", "中关村商圈", "西直门商圈", "前门/崇文门", "首都机场地区", "西单/金融街", "奥体中心地区", "公主坟商圈", "西站/丽泽区", "国贸地区", "东直门/工体",
				"南站/永定门", "建国门区域", "劲松/潘家园", "三里屯商圈", "国展区域");
		keys.put(tempA.size(), "B");
		itemDoubleList.addAll(tempB);

		collectionAdapter.setDataList(itemDoubleList);
	}

	private class CollectionAdapter extends CommonRecyclerAdapter<String>
	{
		@Override
		public void onBindViewHolder(RecyclerViewHolder viewHolder, int position)
		{
			viewHolder.setText(R.id.tv_collection_title, position + " - " + sList.get(position));
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_user_task_collection_content;
		}
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserTaskCollectionActivity.class));
	}
}
