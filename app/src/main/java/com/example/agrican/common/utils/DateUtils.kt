package com.example.agrican.common.utils

import aws.smithy.kotlin.runtime.util.length
import com.example.agrican.R
import java.util.Calendar

class DateUtils {

    val days = getIntegers(R.string.day, 31)
    val months = getIntegers(R.string.month, 12)
    private val _years = getIntegers(R.string.year, Calendar.getInstance().get(Calendar.YEAR) - 1990 + 2, firstValue = 1990)
    val years: List<Int> = listOf(_years[0]) + _years.slice(1.._years.length - 2).reversed()

    private fun getIntegers(valuesName: Int, numberOfRepeat: Int, firstValue: Int = 1): List<Int> {
        val numbers = mutableListOf<Int>()
        numbers.add(valuesName)
        repeat(numberOfRepeat) {
            numbers.add(it + firstValue)
        }
        return numbers.toList()
    }
}