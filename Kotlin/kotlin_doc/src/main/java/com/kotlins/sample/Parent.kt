package com.kotlins.sample

/**
 * Created by yline on 2017/12/29.
 */
abstract class Parent {
    // 默认所有变量，实现 set get 方法
    open var x: Int
        get() {
            // TODO
            return 0;
        }
        set(value) {
            value + 1;
        }
}