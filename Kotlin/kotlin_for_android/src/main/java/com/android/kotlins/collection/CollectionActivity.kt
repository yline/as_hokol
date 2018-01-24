package com.android.kotlins.collection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yline.test.BaseTestActivity

class CollectionActivity : BaseTestActivity() {
    companion object {
        fun launcher(context: Context) {
            val intent = Intent()
            intent.setClass(context, CollectionActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun testStart(view: View?, savedInstanceState: Bundle?) {
        addButton("doFilterTest") {
            doFilterTest()
        }

        addButton("doMapTest") {
            doMapTest()
        }

        addButton("doElementTest") {
            doElementTest()
        }

        addButton("doProductTest") {
            doProductTest()
        }

        addButton("doOrderTest") {
            doOrderTest()
        }
    }

    private fun doOrderTest() {
        var result = false
        val tempList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

        result = listOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0) == tempList.reversed()
        print("reversed", result)

        result = tempList == tempList.sorted()
        print("sorted", result)

        result = listOf(0, 3, 6, 9, 1, 4, 7, 2, 5, 8) == tempList.sortedBy { it % 3 }
        print("sortedBy", result)

        result = listOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0) == tempList.sortedDescending()
        print("sortedDescending", result)

        result = listOf(2, 5, 8, 1, 4, 7, 0, 3, 6, 9) == tempList.sortedByDescending { it % 3 }
        print("sortedByDescending", result)
    }

    private fun doProductTest() {
        var result = false
        val tempList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

        result = Pair(listOf(0, 2, 4, 6, 8), listOf(1, 3, 5, 7, 9)) == tempList.partition { it % 2 == 0 }
        print("partition", result)

        result = tempList.plus(listOf(10, 11)) == tempList + listOf(10, 11)
        print("plus", result)

        result = tempList.zip(listOf(100, 101)) == listOf(Pair(0, 100), Pair(1, 101))
        print("zip", result)

        result = tempList.zip(listOf(100, 101)).unzip() == Pair(listOf(0, 1), listOf(100, 101))
        print("unzip" + tempList.zip(listOf(100, 101)).unzip(), result)
    }

    private fun doElementTest() {
        var result = false
        val tempList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

        result = tempList.contains(2)
        print("contains", result)

        result = 3 == tempList.elementAt(3)
        print("elementAt", result)

        result = 90 == tempList.elementAtOrElse(30, { 3 * it })
        print("elementAtOrElse", result)

        result = null == tempList.elementAtOrNull(10)
        print("elementAtOrNull", result)

        result = 0 == tempList.first { it % 3 == 0 }
        print("first", result)

        result = null == tempList.firstOrNull { it == 10 }
        print("firstOrNull", result)

        result = -1 == tempList.indexOf(10)
        print("indexOf", result)

        result = 0 == tempList.indexOfFirst { it % 2 == 0 }
        print("indexOfFirst", result)

        result = 8 == tempList.indexOfLast { it % 2 == 0 }
        print("indexOfLast", result)

        result = 8 == tempList.last { it % 2 == 0 }
        print("last", result)

        result = -1 == tempList.lastIndexOf(10)
        print("lastIndexOf", result)

        result = null == tempList.lastOrNull { it == 10 }
        print("lastOrNull", result)

        result = 5 == tempList.single { it == 5 }
        print("single", result)

        result = null == tempList.singleOrNull { it == 10 }
        print("singleOrNull", result)
    }

    private fun doMapTest() {
        var result = false
        val tempList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

        result = tempList == tempList.flatMap { listOf(it) }
        print("flatMap", result)

        result = mapOf("odd" to listOf(1, 3, 5, 7, 9),
                "even" to listOf(0, 2, 4, 6, 8)) ==
                tempList.groupBy { if (it % 2 == 0) "even" else "odd" }
        print("groupBy", result)

        result = tempList == tempList.map { it }
        print("map", result)

        result = tempList == tempList.mapIndexed { index, it -> it }
        print("mapIndexed", result)

        result = tempList == tempList.mapNotNull { it }
        print("mapNotNull", result)
    }

    private fun doFilterTest() {
        var result = false
        val tempList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

        result = listOf(8, 9) == tempList.drop(8)
        print("drop", result)

        result = listOf(8, 9) == tempList.dropWhile { it < 8 } // 一旦失败就停止
        print("dropWhile", result)

        result = listOf(0, 1, 2) == tempList.dropLastWhile {
            it > 2
        } // 一旦失败就停止
        print("dropLastWhile", result)

        result = listOf(0, 5) == tempList.filter {
            it % 5 == 0
        }
        print("filter", result)

        result = listOf(0, 5) == tempList.filterNot {
            it % 5 != 0
        }
        print("filterNot", result)

        result = tempList == tempList.filterNotNull()
        print("filterNotNull", result)

        result = listOf(1, 2, 3) == tempList.slice(listOf(1, 2, 3))
        print("slice", result)

        result = listOf(0, 1) == tempList.take(2)
        print("take", result)

        result = listOf(8, 9) == tempList.takeLast(2)
        print("takeLast", result)

        result = listOf(0) == tempList.takeWhile { it % 5 == 0 } // 一旦失败就停止
        print("takeWhile", result)

        result = true
    }

    fun print(tag: String, content: Boolean) {
        if (content) {
            Log.v("xxx-$tag", "result = $content")
        } else {
            Log.e("xxx-$tag", "result = $content")
        }
    }
}
