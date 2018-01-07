package com.android.kotlins.db

/**
 * 单例类
 * @author yline 2018/1/5 -- 17:20
 * @version 1.0.0
 */
class DbManager private constructor() {
    companion object {
        private var mInstance: DbManager? = null

        fun getInstance(): DbManager {
            if (null == mInstance) {
                mInstance = DbManager()
            }
            return mInstance as DbManager
        }
    }
}