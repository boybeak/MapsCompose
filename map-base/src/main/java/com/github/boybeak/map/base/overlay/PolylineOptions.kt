package com.github.boybeak.map.base.overlay

import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PatternItem

class PolylineOptions {
    val points = mutableListOf<LatLng>()
    var width: Float = 0F
        internal set
    var color: Int = 0
        internal set
    var zIndex: Float = 0F
        internal set
    var isVisible: Boolean = true
        internal set
    var isGeodesic: Boolean = false
        internal set
    var isClickable: Boolean = false
        internal set
    var startCap: ICap? = null
        internal set
    var endCap: ICap? = null
        internal set
    var jointType: Int = 0
        internal set
    var pattern: List<PatternItem>? = null
        internal set

    fun add(point: LatLng): PolylineOptions {
        points.add(point)
        return this
    }

    fun add(vararg points: LatLng): PolylineOptions {
        this.points.addAll(points)
        return this
    }

    fun addAll(points: Iterable<LatLng>): PolylineOptions {
        this.points.addAll(points)
        return this
    }

}