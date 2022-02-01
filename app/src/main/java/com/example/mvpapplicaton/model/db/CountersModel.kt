package com.example.mvpapplicaton.model.db

class CountersModel {
    private val counters = mutableListOf(0, 0, 0)

    private fun getCounter(index: Int) = counters[index]

    fun next(index: Int): Int {
        counters[index]++
        return getCounter(index)
    }

    fun set(index: Int, value: Int) {
        counters[index] = value
    }

}