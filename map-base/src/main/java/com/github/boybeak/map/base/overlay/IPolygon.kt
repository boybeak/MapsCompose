package com.github.boybeak.map.base.overlay

import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PatternItem

interface IPolygon {
    var strokeWidth: Float
    var zIndex: Float
    var fillColor: Int
    var strokeColor: Int
    var strokeJointType: Int
    var tag: Any?
    val id: String
    var holes: List<List<LatLng>>
    var points: List<LatLng>
    var strokePattern: List<PatternItem>?
    var isGeodesic: Boolean
    var isVisible: Boolean
    var isClickable: Boolean

    fun remove()
}