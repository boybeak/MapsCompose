package com.github.boybeak.map.base

import java.util.*

class MapUiSettings(
    val compassEnabled: Boolean = true,
    val indoorLevelPickerEnabled: Boolean = true,
    val mapToolbarEnabled: Boolean = true,
    val myLocationButtonEnabled: Boolean = true,
    val rotationGesturesEnabled: Boolean = true,
    val scrollGesturesEnabled: Boolean = true,
    val scrollGesturesEnabledDuringRotateOrZoom: Boolean = true,
    val tiltGesturesEnabled: Boolean = true,
    val zoomControlsEnabled: Boolean = true,
    val zoomGesturesEnabled: Boolean = true,
) {
    override fun equals(other: Any?): Boolean = other is MapUiSettings &&
            compassEnabled == other.compassEnabled &&
            indoorLevelPickerEnabled == other.indoorLevelPickerEnabled &&
            mapToolbarEnabled == other.mapToolbarEnabled &&
            myLocationButtonEnabled == other.myLocationButtonEnabled &&
            rotationGesturesEnabled == other.rotationGesturesEnabled &&
            scrollGesturesEnabled == other.scrollGesturesEnabled &&
            scrollGesturesEnabledDuringRotateOrZoom == other.scrollGesturesEnabledDuringRotateOrZoom &&
            tiltGesturesEnabled == other.tiltGesturesEnabled &&
            zoomControlsEnabled == other.zoomControlsEnabled &&
            zoomGesturesEnabled == other.zoomGesturesEnabled

    override fun hashCode(): Int = Objects.hash(
        compassEnabled,
        indoorLevelPickerEnabled,
        mapToolbarEnabled,
        myLocationButtonEnabled,
        rotationGesturesEnabled,
        scrollGesturesEnabled,
        scrollGesturesEnabledDuringRotateOrZoom,
        tiltGesturesEnabled,
        zoomControlsEnabled,
        zoomGesturesEnabled
    )

    fun copy(
        compassEnabled: Boolean = this.compassEnabled,
        indoorLevelPickerEnabled: Boolean = this.indoorLevelPickerEnabled,
        mapToolbarEnabled: Boolean = this.mapToolbarEnabled,
        myLocationButtonEnabled: Boolean = this.myLocationButtonEnabled,
        rotationGesturesEnabled: Boolean = this.rotationGesturesEnabled,
        scrollGesturesEnabled: Boolean = this.scrollGesturesEnabled,
        scrollGesturesEnabledDuringRotateOrZoom: Boolean = this.scrollGesturesEnabledDuringRotateOrZoom,
        tiltGesturesEnabled: Boolean = this.tiltGesturesEnabled,
        zoomControlsEnabled: Boolean = this.zoomControlsEnabled,
        zoomGesturesEnabled: Boolean = this.zoomGesturesEnabled
    ): MapUiSettings = MapUiSettings(
        compassEnabled = compassEnabled,
        indoorLevelPickerEnabled = indoorLevelPickerEnabled,
        mapToolbarEnabled = mapToolbarEnabled,
        myLocationButtonEnabled = myLocationButtonEnabled,
        rotationGesturesEnabled = rotationGesturesEnabled,
        scrollGesturesEnabled = scrollGesturesEnabled,
        scrollGesturesEnabledDuringRotateOrZoom = scrollGesturesEnabledDuringRotateOrZoom,
        tiltGesturesEnabled = tiltGesturesEnabled,
        zoomControlsEnabled = zoomControlsEnabled,
        zoomGesturesEnabled = zoomGesturesEnabled
    )
}