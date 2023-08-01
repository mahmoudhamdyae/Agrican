package com.theflankers.agrican

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.amplifyframework.auth.AuthException
import com.amplifyframework.kotlin.core.Amplify
import com.google.android.material.snackbar.Snackbar
import com.theflankers.agrican.ui.AgricanApp
import com.theflankers.agrican.ui.screens.splash.SplashViewModel
import com.theflankers.agrican.ui.theme.AgricanTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            Snackbar.make(
                findViewById<View>(android.R.id.content).rootView,
                "Please grant Notification permission from App Settings",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askNotificationPermission()

        // Fetch Amplify Session
        lifecycleScope.launch {
            try {
                val session = Amplify.Auth.fetchAuthSession()
                Log.i("AmplifyQuickstart", "Auth session = $session")
            }
            catch (error: AuthException) {
                Log.e("AmplifyQuickstart", "Failed to fetch auth session", error)
            }
        }

        val viewModel: SplashViewModel by viewModels()

        // Splash Screen
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.uiState.value.isLoading
            }
        }

        setContent {
            val uiStat by viewModel.uiState.collectAsStateWithLifecycle()

            AgricanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    uiStat.startDestination?.let { AgricanApp(startDestination = it) }
//                    SplashScreen()
                }
            }
        }
    }
}