package com.theflankers.agrican.data.repository

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.theflankers.agrican.domain.repository.LogService
import javax.inject.Inject

class LogServiceImpl @Inject constructor() : LogService {
    override fun logNonFatalCrash(throwable: Throwable) =
        Firebase.crashlytics.recordException(throwable)
}