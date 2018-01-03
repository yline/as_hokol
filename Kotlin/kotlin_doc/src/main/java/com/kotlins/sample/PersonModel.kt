package com.kotlins.sample

class PersonModel {
    val name: String = "";
    val age: Int = 23;

    operator fun component1(): String {
        return name;
    }

    operator fun component2(): Int {
        return age;
    }
}