package com.github.boybeak.maps

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.boybeak.map.base.CameraPositionState
import com.github.boybeak.map.base.MapProperties
import com.github.boybeak.map.base.MapUiSettings
import com.github.boybeak.map.base.model.CameraPosition
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.overlay.*
import com.github.boybeak.map.compose.Map
import com.github.boybeak.maps.location.DeviceLocationSource
import com.github.boybeak.maps.ui.theme.MapsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapsComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AnyMap()
                }
            }
        }
    }
}

@Composable
fun AnyMap() {
    var cameraPositionState by remember {
        mutableStateOf(CameraPositionState(CameraPosition(LatLng(0.0, 0.0), 12F, 0F, 0F)))
    }
    var mapProperties by remember {
        mutableStateOf(MapProperties(isMyLocationEnabled = true, mapType = 1008))
    }
    var uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                compassEnabled = true,
                indoorLevelPickerEnabled = true,
                mapToolbarEnabled = true,
                myLocationButtonEnabled = true,
                rotationGesturesEnabled = true
            )
        )
    }
    var contentPadding by remember {
        mutableStateOf(PaddingValues(0.dp))
    }
    var isMapLoaded by remember {
        mutableStateOf(false)
    }
    var markerState by remember {
        mutableStateOf(MarkerState(position = LatLng(23.127985, 113.366341)))
    }
    val context = LocalContext.current
    Column {
        Map(
            modifier = Modifier.weight(1F, true),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            locationSource = DeviceLocationSource(context),
            uiSettings = uiSettings,
            onMapClick = {
                Toast.makeText(context, "onMapClick($it)", Toast.LENGTH_SHORT).show()
            },
            onMapLongClick = {
                Toast.makeText(context, "onMapLongClick($it)", Toast.LENGTH_SHORT).show()
            },
            onMapLoaded = {
                isMapLoaded = true
                Toast.makeText(context, "onMapLoaded", Toast.LENGTH_SHORT).show()
            },
            onMyLocationButtonClick = {
                Toast.makeText(context, "onMyLocationButtonClick", Toast.LENGTH_SHORT).show()
                true
            },
            onMyLocationClick = {
                Toast.makeText(context, "onMyLocationClick($it)", Toast.LENGTH_SHORT).show()
            },
            onPOIClick = {
                Toast.makeText(context, "onPOIClick(${it.name})", Toast.LENGTH_SHORT).show()
            },
            contentPadding = contentPadding
        ) {
            if (isMapLoaded) {
                /*Polygon(
                    points = listOf(
                        LatLng(39.984864, 116.305756),
                        LatLng(39.983618, 116.305848),
                        LatLng(39.982347, 116.305966),
                        LatLng(39.982412, 116.308111),
                        LatLng(39.984122, 116.308224),
                        LatLng(39.984955, 116.308099),
                        LatLng(39.984864, 116.305756)
                    )
                )*/
                Polyline(
                    points = listOf(
                        LatLng(39.984864, 116.305756),
                        LatLng(39.983618, 116.305848),
                        LatLng(39.982347, 116.305966),
                        LatLng(39.982412, 116.308111),
                        LatLng(39.984122, 116.308224),
                        LatLng(39.984955, 116.308099),
                        LatLng(39.984864, 116.305756)
                    ),
                    color = Color.Green,
                    width = 25F,
                )
            }
        }
        Button(onClick = {
            mapProperties = mapProperties.copy(mapType = 1000)
        }) {
            Text(text = "ChangeMapType")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MapsComposeTheme {
        AnyMap()
    }
}