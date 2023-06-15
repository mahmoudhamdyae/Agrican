package com.example.agrican.ui.screens.welcome

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.LocaleListCompat
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.onboarding.OnboardingDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object WelcomeDestination: NavigationDestination {
    override val route: String = "welcome"
    override val titleRes: Int = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isEnglish: Boolean? by rememberSaveable {
        mutableStateOf(null)
    }
    Box(modifier = modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.welcome_screen_background),
            contentDescription = null,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(MaterialTheme.spacing.large)
        ) {
            Text(text = "Welcome to Agrican")
            Text(
                text = "أهلا بك فى أجريكان",
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            )

            Row {
                FilterChip(
                    selected = isEnglish == true,
                    onClick = { isEnglish = true },
                    colors = FilterChipDefaults.elevatedFilterChipColors(),
                    label = {
                        Text(
                            text = "English",
                            textAlign = TextAlign.Center,
                            color = greenLight,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    shape = RoundedCornerShape(MaterialTheme.spacing.large),
                    modifier = Modifier
                        .weight(1f)
                        .padding(MaterialTheme.spacing.medium)
                )

                FilterChip(
                    selected = isEnglish == false,
                    onClick = { isEnglish = false },
                    label = {
                        Text(
                            text = "العربية",
                            textAlign = TextAlign.Center,
                            color = greenLight,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    shape = RoundedCornerShape(MaterialTheme.spacing.large),
                    modifier = Modifier
                        .weight(1f)
                        .padding(MaterialTheme.spacing.medium)
                )
            }

            Row {
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        val language = if (isEnglish == true) "en" else "ar"
                        AppCompatDelegate.setApplicationLocales(
                            LocaleListCompat.forLanguageTags(language)
                        )
                        openAndClear(OnboardingDestination.route)
                              },
                    enabled = isEnglish != null,
                    colors= ButtonDefaults.buttonColors(containerColor = greenDark),
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                ) {
                    Text(text = "متابعة")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(
        openAndClear = { }
    )
}