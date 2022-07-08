package com.github.boybeak.map.base

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import com.github.boybeak.map.base.model.IIndoorBuilding
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PointOfInterest

object DefaultIndoorStateChangeListener : IndoorStateChangeListener

interface IndoorStateChangeListener {
    fun onIndoorBuildingFocused() {}
    fun onIndoorLevelActivated(building: IIndoorBuilding) {}
}

internal class MapClickListeners {
    var indoorStateChangeListener: IndoorStateChangeListener by mutableStateOf(DefaultIndoorStateChangeListener)
    var onMapClick: (LatLng) -> Unit by mutableStateOf({})
    var onMapLongClick: (LatLng) -> Unit by mutableStateOf({})
    var onMapLoaded: () -> Unit by mutableStateOf({})
    var onMyLocationButtonClick: () -> Boolean by mutableStateOf({ false })
    var onMyLocationClick: (Location) -> Unit by mutableStateOf({})
    var onPOIClick: (PointOfInterest) -> Unit by mutableStateOf({})
}