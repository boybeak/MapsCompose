package com.github.boybeak.map.tencent

import android.location.Location
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.boybeak.map.base.*
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PointOfInterest
import com.tencent.tencentmap.mapsdk.maps.TencentMapInitializer

@Composable
fun TencentMap(modifier: Modifier,
               cameraPositionState: CameraPositionState,
               contentDescription: String?,
               properties: MapProperties,
               locationSource: LocationSource?,
               uiSettings: MapUiSettings,
               indoorStateChangeListener: IndoorStateChangeListener,
               onMapClick: (LatLng) -> Unit,
               onMapLongClick: (LatLng) -> Unit,
               onMapLoaded: () -> Unit,
               onMyLocationButtonClick: () -> Boolean,
               onMyLocationClick: (Location) -> Unit,
               onPOIClick: (PointOfInterest) -> Unit,
               contentPadding: PaddingValues,
               content: (@Composable () -> Unit)?
) {
    TencentMapInitializer.setAgreePrivacy(true)
    BaseMap(
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
        content = content,
        onCreateMapView = {
            TencentMapView(it)
        }
    )
}