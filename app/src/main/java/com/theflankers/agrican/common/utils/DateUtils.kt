package com.theflankers.agrican.common.utils

import aws.smithy.kotlin.runtime.util.length
import com.theflankers.agrican.R
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

fun Int.toMonth(): Int {
    return when(this) {
        1 -> R.string.january
        2 -> R.string.february
        3 -> R.string.march
        4 -> R.string.april
        5 -> R.string.may
        6 -> R.string.june
        7 -> R.string.july
        8 -> R.string.augustus
        9 -> R.string.september
        10 -> R.string.october
        11 -> R.string.november
        else -> R.string.december
    }
}

fun Int.getNumberOfDaysInMonth(year: Int): Int {
    return when(this) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        else -> if ((year % 100) % 4 == 0) 29 else 28
    }
}