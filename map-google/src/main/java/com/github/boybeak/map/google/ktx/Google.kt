package com.github.boybeak.map.google.ktx

import android.location.Location
import com.github.boybeak.map.base.*
import com.github.boybeak.map.base.model.IIndoorBuilding
import com.github.boybeak.map.base.model.IIndoorLevel
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.LatLngBounds
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.IndoorBuilding
import com.google.android.gms.maps.model.IndoorLevel
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapType

fun LatLng.toLocal(): com.google.android.gms.maps.model.LatLng {
    return com.google.android.gms.maps.model.LatLng(this.latitude, this.longitude)
}
fun com.google.android.gms.maps.model.LatLng.toRemote(): LatLng {
    return LatLng(this.latitude, this.longitude)
}
fun CameraPositionState.toLocal(): com.google.maps.android.compose.CameraPositionState {
    return com.google.maps.android.compose.CameraPositionState(
        position = CameraPosition(this.position.target.toLocal(), this.position.zoom,
            this.position.tilt, this.position.bearing),
    )
}
fun LatLngBounds.toLocal(): com.google.android.gms.maps.model.LatLngBounds {
    return com.google.android.gms.maps.model.LatLngBounds(this.southwest.toLocal(),
        this.northeast.toLocal())
}
fun MapProperties.toLocal(): com.google.maps.android.compose.MapProperties {
    return com.google.maps.android.compose.MapProperties(
        this.isIndoorEnabled, this.isIndoorEnabled, this.isMyLocationEnabled, this.isTrafficEnabled,
        this.latLngBoundsForCameraTarget?.toLocal(), this.mapStyleOptions as MapStyleOptions, MapType.valueOf(mapContext.getDefaultMapType()),
        this.maxZoomPreference, this.minZoomPreference
    )
}
fun LocationSource.toLocal(): com.google.android.gms.maps.LocationSource {
    return object : com.google.android.gms.maps.LocationSource {
        override fun activate(p0: com.google.android.gms.maps.LocationSource.OnLocationChangedListener) {
            this@toLocal.activate(object : LocationSource.OnLocationChangedListener {
                override fun onLocationChanged(location: Location) {
                    p0.onLocationChanged(location)
                }
            })
        }

        override fun deactivate() {
            this@toLocal.deactivate()
        }
    }
}
fun MapUiSettings.toLocal(): com.google.maps.android.compose.MapUiSettings {
    return com.google.maps.android.compose.MapUiSettings(
        this.compassEnabled, this.indoorLevelPickerEnabled, this.mapToolbarEnabled, this.myLocationButtonEnabled,
        this.rotationGesturesEnabled, this.scrollGesturesEnabled, this.scrollGesturesEnabledDuringRotateOrZoom,
        this.tiltGesturesEnabled, this.zoomControlsEnabled, this.zoomGesturesEnabled
    )
}

private class InnerIndoorLevel(private val indoorLevel: IndoorLevel) : IIndoorLevel {
    override val name: String
        get() = indoorLevel.name
    override val shortName: String
        get() = indoorLevel.shortName

    override fun activate() {
        indoorLevel.activate()
    }
}
fun IndoorStateChangeListener.toLocal(): com.google.maps.android.compose.IndoorStateChangeListener {
    return object : com.google.maps.android.compose.IndoorStateChangeListener {
        override fun onIndoorBuildingFocused() {
            this@toLocal.onIndoorBuildingFocused()
        }

        override fun onIndoorLevelActivated(building: IndoorBuilding) {
            this@toLocal.onIndoorLevelActivated(object : IIndoorBuilding {
                override val levels: List<IIndoorLevel>
                    get() = building.levels.map {
                        InnerIndoorLevel(it)
                    }
                override val activeLevelIndex: Int
                    get() = building.activeLevelIndex
                override val defaultLevelIndex: Int
                    get() = building.defaultLevelIndex
                override val isUnderGround: Boolean
                    get() = building.isUnderground
            })
        }
    }
}