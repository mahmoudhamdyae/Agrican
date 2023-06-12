package com.example.agrican

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AgricanApplication: Application() {

    override fun onCreate() {
        super.onCreate()

//        try {
//            Amplify.configure(applicationContext)
//            Amplify.addPlugin(AWSCognitoAuthPlugin())
//            Log.i("MyAmplifyApp", "Initialized Amplify")
//        } catch (error: AmplifyException) {
//            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
//        }
    }
}