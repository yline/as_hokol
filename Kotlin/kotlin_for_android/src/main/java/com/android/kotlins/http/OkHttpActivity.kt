package com.android.kotlins.http

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.android.kotlins.R
import com.yline.http.OkHttpManager
import com.yline.http.manager.XHttpAdapter
import com.yline.test.StrConstant
import com.yline.view.recycler.simple.SimpleCommonRecyclerAdapter
import okhttp3.Call
import okhttp3.Response

class OkHttpActivity : AppCompatActivity() {
    companion object {
        fun launcher(context: Context) {
            val intent = Intent()
            intent.setClass(context, OkHttpActivity::class.java);
            context.startActivity(intent)
        }
    }

    private val SONG_LIST = "http://localhost/android/git_api/kotlin/book_for_android/personlist.txt";

    private val mRecyclerAdapter: MainRecyclerAdapter = MainRecyclerAdapter();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)

        initView()
        initData()
    }

    private fun initView() {
        val recycler = findViewById<RecyclerView>(R.id.ok_http_recycler);
        recycler.layoutManager = LinearLayoutManager(this@OkHttpActivity);
        recycler.adapter = mRecyclerAdapter;

        mRecyclerAdapter.addAll(StrConstant.getListRandom(20), true);
    }

    private fun initData() {
        OkHttpManager.doGet(SONG_LIST, null, SongModel::class.java, object : XHttpAdapter<SongModel>() {
            override fun onSuccess(call: Call?, response: Response?, result: SongModel?) {
                
            }
        })
    }

    private class MainRecyclerAdapter : SimpleCommonRecyclerAdapter() {

    }

    data class SongModel(var str: List<SongItemModel>)

    data class SongItemModel(var avatar: String, var name: String)
}
