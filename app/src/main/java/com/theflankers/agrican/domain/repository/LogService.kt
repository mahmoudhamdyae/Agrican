package com.theflankers.agrican.domain.repository

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}