package com.theflankers.agrican

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.kotlin.core.Amplify
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AgricanApplication: Application() {

    private var sAnalytics: GoogleAnalytics? = null
    private var sTracker: Tracker? = null

    override fun onCreate() {
        super.onCreate()

        sAnalytics = GoogleAnalytics.getInstance(this);

        // Install Amplify
        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Log.i("MyAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }
    }

    /**
     * Gets the default [Tracker] for this [Application].
     * @return tracker
     */
    @Synchronized
    fun getDefaultTracker(): Tracker? {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics?.newTracker(R.xml.global_tracker)
        }
        return sTracker
    }
}