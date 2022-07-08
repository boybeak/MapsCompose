package com.github.boybeak.map.base.overlay

import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PatternItem

interface IPolyline {
    var width: Float
    var zIndex: Float
    var color: Int
    var jointType: Int
    // TODO
    var endCap: ICap?
    var startCap: ICap?
    var tag: Any?
    val id: String
    var pattern: List<PatternItem>?
    var points: List<LatLng>
    var isClickable: Boolean
    var isGeodesic: Boolean
    var isVisible: Boolean

    fun remove()

}