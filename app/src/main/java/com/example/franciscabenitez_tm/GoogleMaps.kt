package com.example.franciscabenitez_tm

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.location.FusedLocationProviderClient

@Composable
fun GoogleMaps() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){


        val miUbicacion = LatLng(-33.49936500787212, -70.61654033901539)


        val nombreLugar1 = "Lugar 1"
        val lugar1 = LatLng(-33.497672632070476, -70.6126025410391)

        val nombreLugar2 = "Lugar 2"
        val lugar2 = LatLng(-33.50104607891704, -70.61707122623334)

        val nombreLugar3 = "Lugar 3"
        val lugar3 = LatLng(-33.49774554586376, -70.6178305190539)

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(miUbicacion, 15f)
        }


        GoogleMap(
            modifier = Modifier.height(300.dp).fillMaxWidth(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = lugar1),
                title = nombreLugar1
            )
            Marker(
                state = MarkerState(position = lugar2),
                title = nombreLugar2
            )
            Marker(
                state = MarkerState(position = lugar3),
                title = nombreLugar3
            )
        }

    }
}

@SuppressLint("MissingPermission")
private fun getLastLocation(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient,
    onLocationResult: (LatLng?) -> Unit
) {
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: android.location.Location? ->
            if (location != null) {
                onLocationResult(LatLng(location.latitude, location.longitude))
            } else {
                onLocationResult(null)
            }
        }
        .addOnFailureListener {
            onLocationResult(null)
        }
}