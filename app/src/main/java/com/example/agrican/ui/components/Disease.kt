package com.example.agrican.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.agrican.R
import com.example.agrican.ui.theme.black
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white

@Composable
fun DiseaseHeader(
    image: Int?,
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
        Box(modifier = Modifier.fillMaxWidth().height(MaterialTheme.spacing.dp_150)) {
            if (image == null) {
                EmptyImage(modifier = Modifier.fillMaxSize())
            } else {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(MaterialTheme.spacing.medium)
            ) {
                // Disease Name
                Text(
                    text = diseaseName,
                    color = white,
                    style = MaterialTheme.typography.body,
                    fontSize = MaterialTheme.spacing.sp_16,
                    modifier = Modifier.weight(1f)
                )

                // Search for more Button
                Button(
                    onClick = onButtonClick,
                    colors = ButtonDefaults.buttonColors(containerColor = white),
                    modifier = Modifier.weight(1f)
                ) {
                    Row {
                        Text(
                            text = stringResource(id = buttonText),
                            color = greenDark,
                            style = MaterialTheme.typography.body,
                            fontSize = MaterialTheme.spacing.sp_14
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
        style = MaterialTheme.typography.title,
        fontSize = MaterialTheme.spacing.sp_14,
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
                        .background(black)
                        .size(MaterialTheme.spacing.extraSmall)
                )
            }
            Text(
                text = it,
                color = black,
                style = MaterialTheme.typography.body,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}