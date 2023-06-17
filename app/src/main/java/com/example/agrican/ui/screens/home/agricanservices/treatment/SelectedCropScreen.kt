package com.example.agrican.ui.screens.home.agricanservices.treatment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.common.enums.DiseaseType
import com.example.agrican.domain.model.Treatment
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.components.EmptyImage
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing
import com.example.agrican.ui.theme.title

object SelectedCropDestination: NavigationDestination {
    override val route: String = "selected_crop"
    override val titleRes: Int = R.string.treatment
}

@Composable
fun SelectedCropScreen(
    modifier: Modifier = Modifier,
    viewModel: TreatmentViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SelectedCropScreenContent(
        uiState = uiState,
        updateDiseaseType = viewModel::updateDiseaseType,
        getTreatments = viewModel::getTreatments,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun SelectedCropScreenContent(
    uiState: TreatmentUiState,
    updateDiseaseType: (Int) -> Unit,
    getTreatments: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isTreatmentShown by rememberSaveable { mutableStateOf(false) }

    // Selected Crop
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.selected_crop),
            color = greenDark
        )

        Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
            CropImage()
            Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                Surface(
                    shape = RoundedCornerShape(MaterialTheme.spacing.large),
                    shadowElevation = MaterialTheme.spacing.large,
                    modifier = Modifier.fillMaxWidth()
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

                // Edit Button
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.configure),
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                    )
                }
            }
        }

        // Choose Disease Type Label
        Text(
            text = stringResource(id = R.string.choose_disease_type),
            style = MaterialTheme.typography.title,
        )

        // Choose Disease Type Drop Down
        DropDown(options = listOf(
            DiseaseType.INSECTS.title
        ),
            onSelect = updateDiseaseType,
            modifier = Modifier.width(MaterialTheme.spacing.dp_150).height(MaterialTheme.spacing.large)
        )

        // Show Treatment Button
        Button(
            onClick = {
                getTreatments()
                isTreatmentShown = true
                      },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
        ) {
            Text(text = stringResource(id = R.string.show_treatment_button))
        }

        if (isTreatmentShown) {
            TreatmentList(uiState.treatments)
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
        modifier = modifier.size(MaterialTheme.spacing.dp_75)
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
    treatments: List<Treatment>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(treatments.size) {
            TreatmentListItem(
                treatment = treatments[it],
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            )
        }
    }
}

@Composable
fun TreatmentListItem(
    treatment: Treatment,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = MaterialTheme.spacing.medium,
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max)
        ) {
            EmptyImage(
                modifier = Modifier.weight(1f).fillMaxHeight()
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
                    // Treatment Name
                    Text(
                        text = treatment.name,
                        style = MaterialTheme.typography.title,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    // Know More Button
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                    ) {
                        Text(text = stringResource(id = R.string.know_more))
                    }
                }

                // Treatment Description
                Text(
                    text = treatment.description,
                    style = MaterialTheme.typography.body,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SelectedCropScreenPreview() {
    SelectedCropScreenContent(uiState = TreatmentUiState(), updateDiseaseType = { }, getTreatments = { })
}

@Preview(showBackground = true)
@Composable
fun TreatmentListPreview() {
    TreatmentList(treatments = listOf())
}