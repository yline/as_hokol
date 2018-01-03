package com.kotlins.sample

class ExtendFunction {
    fun Son.sum(indexA: Int, indexB: Int): Int {
        val temp = indexA + indexB;
        return temp * temp;
    }

    fun test(indexA: Int, indexB: Int): Int {
        var son = Son();
        return son.sum(indexA, indexB);
    }
}