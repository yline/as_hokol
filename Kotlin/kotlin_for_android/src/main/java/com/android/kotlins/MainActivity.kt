package com.android.kotlins

import android.os.Bundle
import android.view.View
import com.android.kotlins.http.OkHttpActivity
import com.yline.test.BaseTestActivity

class MainActivity : BaseTestActivity() {
    override fun testStart(view: View?, savedInstanceState: Bundle?) {
        addButton("Http", View.OnClickListener {
            OkHttpActivity.launcher(this@MainActivity)
        })

        // 写全
        addButton("new", object : View.OnClickListener {
            override fun onClick(v: View?) {
                // TODO("not implemented")
            }
        })
    }
}
