package com.theflankers.agrican.ui.screens.home.profile.engineer_map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    latitude1: Double? = null,
    longitude1: Double? = null,
) {
    val latitude by rememberSaveable { mutableStateOf(latitude1) }
    val longitude by rememberSaveable { mutableStateOf(longitude1) }

    var myLocation =
        LatLng(latitude ?: 31.045162, longitude ?: 31.399642)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(myLocation, 1f)
    }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = {
            myLocation = LatLng(it.latitude, it.longitude)
        }
    ) {
        if (latitude != null) {
            Marker(
                state = MarkerState(position = myLocation),
                title = "My Last Location",
                snippet = "Marker in my last location"
            )
        }
    }
}