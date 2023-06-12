package com.example.agrican

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.agrican.ui.AgricanApp
import com.example.agrican.ui.theme.AgricanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Amplify.Auth.fetchAuthSession(
//            { Log.i("AmplifyQuickstart", "Auth session = $it") },
//            { error -> Log.e("AmplifyQuickstart", "Failed to fetch auth session", error) }
//        )

        setContent {
            AgricanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AgricanApp()
                }
            }
        }
    }
}