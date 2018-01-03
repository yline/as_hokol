package com.kotlins.sample

import android.util.Log

/**
 * 函数学习；
 * 1，函数本身
 * 2，局部变量
 *
 * @author yline 2017/12/28 -- 10:37
 * @version 1.0.0
 */
// 类名、可见性、传参、默认值
class FunctionDefine public constructor(var filePath: String = "") {

    init {
        // TODO
    }

    var fileName = filePath + "yline"


    //  TAG = "xxx-kotlin-funtion";

    /**
     * 返回，int类型
     */
    fun testReturnInt(): Int {
        return 0;
    }

    /**
     * 返回，Void（可省略）
     */
    fun testReturnVoid(): Unit {
        Log.v("xxx-kotlin", "testReturnVoid");
    }

    /**
     * 传入参数
     * 返回Int类型
     */
    fun testReturnInt(a: Int, b: Int): Int {
        return a + b;
    }

    /**
     * 传入参数
     * 返回，某个表达式
     */
    fun testReturnInt(a: Int) = a + 1;

    /**
     * 返回，Void（可省略）
     */
    fun testReturnVoid(a: Int) {
        Log.v("xxx-kotlin", "testReturnVoid");
    }

    /**
     * 局部变量定义
     */
    fun testVariable() {
        var a: Int = 1;
        var b = 2;
        var c: Int;
    }
}