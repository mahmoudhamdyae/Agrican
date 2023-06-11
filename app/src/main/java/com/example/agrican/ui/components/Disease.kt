package com.example.agrican.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.agrican.R
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

@Composable
fun DiseaseHeader(
    image: Int,
    diseaseName: String,
    buttonText: Int,
    onButtonClick: ()-> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = gray,
        shape = RoundedCornerShape(
            bottomStart = MaterialTheme.spacing.large,
            bottomEnd = MaterialTheme.spacing.large,
        ),
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(MaterialTheme.spacing.medium)
            ) {
                Text(
                    text = diseaseName,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = onButtonClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier.weight(1f)
                ) {
                    Row {
                        Text(
                            text = stringResource(id = buttonText),
                            color = greenDark
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                            contentDescription = null,
                            tint = greenDark
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainLabel(
    text: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = text),
        color = greenDark,
        modifier = modifier
    )
}

@Composable
fun DescriptionLabel(
    texts: List<String>,
    modifier: Modifier = Modifier
) {
    texts.forEach {
        Row(modifier = modifier) {
            if (texts.size > 1) {
                Box(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                        .clip(CircleShape)
                        .background(Color.Black)
                        .size(MaterialTheme.spacing.extraSmall)
                )
            }
            Text(text = it)
        }
    }
}