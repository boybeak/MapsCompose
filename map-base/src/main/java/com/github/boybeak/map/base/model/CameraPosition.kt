package com.github.boybeak.map.base.model

import android.os.Parcel
import android.os.Parcelable

class CameraPosition(val target: LatLng,
                     val zoom: Float,
                     val tilt: Float,
                     val bearing: Float) {
    companion object {
        fun fromLatLngZoom(target: LatLng, zoom: Float): CameraPosition {
            return CameraPosition(target, zoom, 0F, 0F)
        }
    }
}