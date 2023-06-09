package com.example.agrican.ui.screens.home.agricanservices.treatment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

object SelectedCropDestination: NavigationDestination {
    override val route: String = "selected_crop"
    override val titleRes: Int = R.string.treatment
}

@Composable
fun SelectedCropScreen(
    modifier: Modifier = Modifier
) {

    var isTreatmentShown by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.selected_crop),
            color = greenDark
        )

        Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
            CropImage()
            Column {
                Surface(
                    shape = RoundedCornerShape(MaterialTheme.spacing.large),
                    shadowElevation = MaterialTheme.spacing.large,
                    modifier = Modifier.width(108.dp)
                ) {
                    Text(
                        text = "الأرز",
                        color = greenDark,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(
                            horizontal = MaterialTheme.spacing.medium,
                            vertical = MaterialTheme.spacing.small
                        )
                    )
                }

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                ) {
                    Text(
                        text = stringResource(id = R.string.configure),
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                    )
                }
            }
        }

        Text(
            text = stringResource(id = R.string.choose_disease_type),
            color = greenDark
        )

        DropDown(availabilityOptions = arrayOf(
            R.string.insects
        ))

        Button(
            onClick = { isTreatmentShown = true },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
        ) {
            Text(text = stringResource(id = R.string.show_treatment_button))
        }

        if (isTreatmentShown) {
            TreatmentList()
        }
    }
}

@Composable
fun CropImage(
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.large),
        shadowElevation = MaterialTheme.spacing.large,
        modifier = modifier.size(75.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_visibility_on),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TreatmentList(
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(3) {
            TreatmentListItem(modifier = Modifier.padding(MaterialTheme.spacing.small))
        }
    }
}

@Composable
fun TreatmentListItem(
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = MaterialTheme.spacing.medium,
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_sunny),
                contentDescription = null,
                modifier = Modifier.weight(1f)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                modifier = Modifier
                    .weight(3f)
                    .padding(MaterialTheme.spacing.small)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "علاج 1",
                        color = greenDark
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                    ) {
                        Text(text = stringResource(id = R.string.know_more))
                    }
                }

                Text(text = "هذه هى الكمية المطلوبة بناء و يتم تسميد الأرض باستخدام المنتجات المخصصة لذلك هذه هى الكمية المطلوبة بناء و يتم تسميد الأرض باستخدام المنتجات المخصصة لذلك")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectedCropScreenPreview() {
    SelectedCropScreen()
}

@Preview(showBackground = true)
@Composable
fun TreatmentListPreview() {
    TreatmentList()
}