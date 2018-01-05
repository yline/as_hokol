package com.android.kotlins

import com.facebook.drawee.backends.pipeline.Fresco
import com.yline.application.BaseApplication

class IApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}