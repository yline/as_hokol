package com.android.kotlins.entrust

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yline.test.BaseTestActivity

/**
 * 委托模式
 * @author yline 2018/1/7 -- 13:44
 * @version 1.0.0
 */
class EntrustActivity : BaseTestActivity() {
    companion object {
        fun launcher(context: Context) {
            val intent = Intent()
            intent.setClass(context, EntrustActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun testStart(view: View?, savedInstanceState: Bundle?) {
        addButton("entrust") {
            val printManager = PrintManager(LogManager())
            printManager.v("tag", "print")
        }
    }

    class PrintManager(callback: OnLogCallback) : OnLogCallback by callback {

    }

    interface OnLogCallback {
        fun v(tag: String, content: String)
    }

    class LogManager : OnLogCallback {
        override fun v(tag: String, content: String) {
            Log.v("xxx-LogManager-$tag", content)
        }
    }
}