package com.example.restaurantfinder

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@SuppressLint("RememberReturnType")
@Composable
fun GoogleMapView() {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    DisposableEffect(Unit) {
        mapView.onCreate(null)
        mapView.onResume()
        mapView.getMapAsync(OnMapReadyCallback { googleMap ->
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.uiSettings.isZoomGesturesEnabled = true


            val sydney = LatLng(-34.0, 151.0)
            googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10f))
        })
        onDispose {
            mapView.onPause()
            mapView.onDestroy()
        }
    }

    AndroidView({ mapView }) { mapView ->
        // Update view if needed
    }
}
