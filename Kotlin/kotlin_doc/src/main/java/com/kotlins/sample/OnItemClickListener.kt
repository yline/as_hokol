package com.kotlins.sample

interface OnItemClickListener {
    val prop: Int
    var str: String
        get() = ""
        set(value) {
            "fuck" + value
        }

    fun onItemClick()

    fun onLongItemClick() {
        // TODO
        onItemClick()
    }
}













