package com.example.agrican

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.amplifyframework.auth.AuthException
import com.amplifyframework.kotlin.core.Amplify
import com.example.agrican.ui.AgricanApp
import com.example.agrican.ui.screens.HomeViewModel
import com.example.agrican.ui.theme.AgricanTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        // Splash Screen
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.uiState.value.isLoading
            }

//            setOnExitAnimationListener { splashScreen ->
//                // Access to the splash screen and animate it
//                ObjectAnimator.ofFloat(
//                    splashScreen.view,
//                    View.TRANSLATION_Y,
//                    0f,
//                    splashScreen.view.height.toFloat()
//                ).apply {
//                    interpolator = DecelerateInterpolator()
//                    duration = 500L
//                    doOnEnd { splashScreen.remove() }
//                    start()
//                }
//            }
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
                }
            }
        }
    }
}