package com.github.boybeak.map.compose

import android.location.Location
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.boybeak.map.base.*
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PointOfInterest
import com.github.boybeak.map.google.ktx.GoogleMapContext
import com.github.boybeak.map.google.ktx.toLocal
import com.github.boybeak.map.google.ktx.toRemote
import com.google.maps.android.compose.GoogleMap

private val DefaultMapProperties = run {
    initMapContext(GoogleMapContext())
    MapProperties()
}
private val DefaultMapUiSettings = MapUiSettings()

private val NoPadding = PaddingValues()

@Composable
fun Map(
    modifier: Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    contentDescription: String? = null,
    properties: MapProperties = DefaultMapProperties,
    locationSource: LocationSource? = null,
    uiSettings: MapUiSettings = DefaultMapUiSettings,
    indoorStateChangeListener: IndoorStateChangeListener = DefaultIndoorStateChangeListener,
    onMapClick: (LatLng) -> Unit = {},
    onMapLongClick: (LatLng) -> Unit = {},
    onMapLoaded: () -> Unit = {},
    onMyLocationButtonClick: () -> Boolean = { false },
    onMyLocationClick: (Location) -> Unit = {},
    onPOIClick: (PointOfInterest) -> Unit = {},
    contentPadding: PaddingValues = NoPadding,
    content: (@Composable () -> Unit)? = null
) {
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState.toLocal(),
        contentDescription = contentDescription,
        properties = properties.toLocal(),
        locationSource = locationSource?.toLocal(),
        uiSettings = uiSettings.toLocal(),
        indoorStateChangeListener = indoorStateChangeListener.toLocal(),
        onMapClick = { onMapClick.invoke(it.toRemote()) },
        onMapLongClick = { onMapLongClick.invoke(it.toRemote()) },
        onMapLoaded = { onMapLoaded.invoke() },
        onMyLocationButtonClick = { onMyLocationButtonClick.invoke() },
        onMyLocationClick = { onMyLocationClick.invoke(it) },
        onPOIClick = { onPOIClick.invoke(PointOfInterest(it.latLng.toRemote(), it.placeId, it.name)) },
        contentPadding = contentPadding,
        content = content
    )
}