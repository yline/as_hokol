package com.android.kotlins.http

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import com.android.kotlins.R
import com.facebook.drawee.view.SimpleDraweeView
import com.yline.http.OkHttpManager
import com.yline.http.manager.XHttpAdapter
import com.yline.test.StrConstant
import com.yline.test.UrlConstant
import com.yline.view.recycler.adapter.AbstractCommonRecyclerAdapter
import com.yline.view.recycler.holder.RecyclerViewHolder
import okhttp3.Call
import okhttp3.Response

class OkHttpActivity : AppCompatActivity() {
    companion object {
        val SONG_LIST = "http://localhost/android/git_api/kotlin/book_for_android/personlist.txt"

        fun launcher(context: Context) {
            val intent = Intent()
            intent.setClass(context, OkHttpActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val mRecyclerAdapter: MainRecyclerAdapter = MainRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)

        initView()
        initData()
    }

    private fun initView() {
        val recycler = findViewById<RecyclerView>(R.id.ok_http_recycler)
        recycler.layoutManager = LinearLayoutManager(this@OkHttpActivity)
        recycler.adapter = mRecyclerAdapter
    }

    private fun initData() {
        OkHttpManager.doGetNetPrior(SONG_LIST, null, SongModel::class.java, object : XHttpAdapter<SongModel>() {
            override fun onSuccess(call: Call?, response: Response?, result: SongModel?) {
                if (null != result) {
                    mRecyclerAdapter.setDataList(result.songs, true)
                }
            }
        })
    }

    private class MainRecyclerAdapter : AbstractCommonRecyclerAdapter<SongItemModel>() {
        override fun getItemRes(): Int {
            return R.layout.item_ok_http
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            // 接口中没有数据，故不是输出数据
            val itemModel = getItem(position)

            // 头像
            if (TextUtils.isEmpty(itemModel.avatar)) {
                itemModel.avatar = UrlConstant.getAvatar()
            }
            holder.get<SimpleDraweeView>(R.id.ok_http_fresco).setImageURI(itemModel.avatar)

            // 名字
            if (TextUtils.isEmpty(itemModel.name)) {
                itemModel.name = StrConstant.getStringByRandom()
            }
            holder.setText(R.id.ok_http_tv, itemModel.name)
        }
    }

    data class SongModel(var songs: List<SongItemModel>)

    data class SongItemModel(var avatar: String, var name: String)
}
