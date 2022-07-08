package com.github.boybeak.map.compose

import android.location.Location
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.boybeak.map.base.*
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PointOfInterest
import com.github.boybeak.map.tencent.TencentMap
import com.github.boybeak.map.tencent.TencentMapContext

private val DefaultMapProperties = run {
    initMapContext(TencentMapContext())
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
    TencentMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        contentDescription = contentDescription,
        properties = properties,
        locationSource = locationSource,
        uiSettings = uiSettings,
        indoorStateChangeListener = indoorStateChangeListener,
        onMapClick = onMapClick,
        onMapLongClick = onMapLongClick,
        onMapLoaded = onMapLoaded,
        onMyLocationButtonClick = onMyLocationButtonClick,
        onMyLocationClick = onMyLocationClick,
        onPOIClick = onPOIClick,
        contentPadding = contentPadding,
        content = content
    )
}