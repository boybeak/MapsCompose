package com.github.boybeak.map.base.model

import android.graphics.Point

interface IProjection {
    val visibleRegion: VisibleRegion
    fun toScreenLocation(location: LatLng): Point
    fun fromScreenLocation(point: Point): LatLng
}