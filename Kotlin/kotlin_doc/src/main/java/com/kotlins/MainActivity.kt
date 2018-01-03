package com.kotlins

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.kotlins.sample.PersonModel

class MainActivity : AppCompatActivity() {
    lateinit var mX: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val (name, age) = PersonModel();

        C().caller(D()) // 输出 "D.foo in C"
        C1().caller(D()) // 输出 "D.foo in C1" —— 分发接收者虚拟解析
        C().caller(D1()) // 输出 "D.foo in C" —— 扩展接收者静态解析

        // List、Set、Map
        val numberList = listOf(1, 2, 3); // 不可修改
        val accountList = mutableListOf(1, 2, 3); // 可修改

        val numberSet = setOf(1, 2, 3);
        val accountSet = mutableSetOf<Int>(1, 2, 3);

        val numberMap = mapOf("1" to 1, "2" to 2);
        val accountMap = mutableMapOf<String, Int>("1" to 1, "2" to 2);

        // 区间
        for (i in 1..4) {
            // 1234
        }

        for (i in 4 downTo 1) {
            // 4321
        }

        for (i in 1..4 step 2) {
            // 13
        }

        for (i in 1 until 4) {
            // 123
        }

        // 类型校验
        val obj: Any = "";
        val isSame: Boolean = (obj is String);

        // 空安全
        val normalStr: String = "not null"; // 默认不允许字符为null
        val nullStr: String? = null; // 允许字符为null
        val length = nullStr?.length; // 不为null，才调用；如果为空，则返回null

        // 问号校验
        val lengthDefine = nullStr?.length ?: 0; // 不为null，才调用；如果为空，则返回默认值
        val aInt: Int? = (obj as? Int); // 如果obj为int类型就赋值，否则返回null

        // 集合
        val nullableList: List<Int?> = listOf(1, 2, null, 4)
        val intList: List<Int> = nullableList.filterNotNull() // 过滤空
    }

    class A { // 隐式标签 @A
        inner class B { // 隐式标签 @B
            fun Int.foo() { // 隐式标签 @foo
                val a = this@A // A 的 this
                val b = this@B // B 的 this
                val c = this // foo() 的接收者，⼀个 Int
                val c1 = this@foo // foo() 的接收者，⼀个 Int
                val funLit = lambda@ fun String.() {
                    val d = this // funLit 的接收者
                }
            }
        }
    }

    open class D {
    }

    class D1 : D() {
    }

    open class C {
        open fun D.foo() {
            Log.v("xxx-", "D.foo in C")
        }

        open fun D1.foo() {
            Log.v("xxx-", "D1.foo in C")
        }

        fun caller(d: D) {
            d.foo() // 调⽤扩展函数
        }
    }

    // 继承函数，子类能够重写父类，子类 > 父类
    // 扩展函数，子类不能够重写父类，父类 > 子类
    class C1 : C() {
        override fun D.foo() {
            Log.v("xxx-", "D.foo in C1")
        }

        override fun D1.foo() {
            Log.v("xxx-", "D1.foo in C1")
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
