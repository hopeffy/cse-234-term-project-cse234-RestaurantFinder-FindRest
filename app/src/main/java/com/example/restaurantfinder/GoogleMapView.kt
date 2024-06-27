package com.example.restaurantfinder

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

@SuppressLint("RememberReturnType")
@Composable
fun GoogleMapView(navController: NavHostController, restList2 : List<RestaurantData.Restaurant>) {


    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val id : String

    DisposableEffect(Unit) {
        mapView.onCreate(null)
        mapView.onResume()
        mapView.getMapAsync(OnMapReadyCallback { googleMap ->
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.uiSettings.isZoomGesturesEnabled = true

            val builder = LatLngBounds.Builder()

            for (rest in restList2) {
                val location = LatLng(rest.Lat, rest.Lng)
                val marker = googleMap.addMarker(MarkerOptions().position(location).title("Marker in ${rest.name}"))
                marker?.tag = rest.id
                builder.include(location)

            }

            val bounds = builder.build()
            val padding = 100 // Padding around the bounds

            // Move camera to show all markers
            val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
            googleMap.moveCamera(cameraUpdate)

            googleMap.setOnMarkerClickListener { marker ->
                val restaurantId = marker.tag as? String
                if (restaurantId != null) {
                    navController.navigate("selected/$restaurantId")
                }
                true
            }


        })
        onDispose {
            mapView.onPause()
            mapView.onDestroy()
        }
    }

    AndroidView({ mapView }) { mapView ->
        /** Update view if needed */
    }
}
