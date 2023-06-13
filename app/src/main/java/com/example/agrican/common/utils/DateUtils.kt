package com.example.agrican.common.utils

import com.example.agrican.R

class DateUtils {

    val days = getIntegers(R.string.day, 31)
    val months = getIntegers(R.string.month, 12)
    val years = getIntegers(R.string.year, 100, firstValue = 1990)

    private fun getIntegers(valuesName: Int, numberOfRepeat: Int, firstValue: Int = 1): List<Int> {
        val numbers = mutableListOf<Int>()
        numbers.add(valuesName)
        repeat(numberOfRepeat) {
            numbers.add(it + firstValue)
        }
        return numbers.toList()
    }
}