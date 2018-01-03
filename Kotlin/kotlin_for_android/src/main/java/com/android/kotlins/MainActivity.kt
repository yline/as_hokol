package com.android.kotlins

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.yline.test.StrConstant
import com.yline.view.recycler.simple.SimpleCommonRecyclerAdapter

class MainActivity : AppCompatActivity() {
    private val mRecyclerAdapter: MainRecyclerAdapter = MainRecyclerAdapter();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        val recycler = findViewById<RecyclerView>(R.id.main_recycler);
        recycler.layoutManager = LinearLayoutManager(this@MainActivity);
        recycler.adapter = mRecyclerAdapter;

        mRecyclerAdapter.addAll(StrConstant.getListRandom(20), true);
    }

    private class MainRecyclerAdapter : SimpleCommonRecyclerAdapter() {
    }
}
