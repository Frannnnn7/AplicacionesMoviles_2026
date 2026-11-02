package com.example.franciscabenitez_tm

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMaps() {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var miUbicacion by remember { mutableStateOf(LatLng(-33.49936500787212, -70.61654033901539)) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(miUbicacion, 15f)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            getLastLocation(context, fusedLocationClient) { location ->
                location?.let {
                    val nuevaUbicacion = LatLng(it.latitude, it.longitude)
                    miUbicacion = nuevaUbicacion
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(nuevaUbicacion, 15f)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    val nombreLugar1 = "Lugar 1"
    val lugar1 = LatLng(-33.497672632070476, -70.6126025410391)

    val nombreLugar2 = "Lugar 2"
    val lugar2 = LatLng(-33.50104607891704, -70.61707122623334)

    val nombreLugar3 = "Lugar 3"
    val lugar3 = LatLng(-33.49774554586376, -70.6178305190539)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        GoogleMap(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = miUbicacion),
                title = "Tu Ubicación Actual"
            )

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
    onLocationResult: (Location?) -> Unit
) {
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: Location? ->
            onLocationResult(location)
        }
        .addOnFailureListener {
            onLocationResult(null)
        }
}