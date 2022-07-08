package com.github.boybeak.map.base.overlay

import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PatternItem

class PolygonOptions {
    val points: MutableList<LatLng> = mutableListOf()
    val holes: MutableList<List<LatLng>> = mutableListOf()
    var strokeWidth: Float = 0F
        internal set
    var strokeColor: Int = 0
        internal set
    var fillColor: Int = 0
        internal set
    var zIndex: Float = 0F
        internal set
    var isVisible: Boolean = true
        internal set
    var isGeodesic: Boolean = false
        internal set
    var isClickable: Boolean = false
        internal set
    var strokeJointType: Int = JointType.DEFAULT
        internal set
    var strokePattern: List<PatternItem>? = null
        internal set

    fun add(point: LatLng): PolygonOptions {
        points.add(point)
        return this
    }
    fun add(vararg points: LatLng): PolygonOptions {
        this.points.addAll(points)
        return this
    }
    fun addAll(points: Iterable<LatLng>) {
        this.points.addAll(points)
    }
    fun addHole(points: Iterable<LatLng>) {
        var list = mutableListOf<LatLng>()
        list.addAll(points)
        holes.add(list)
    }
}