package com.github.boybeak.map.base.overlay

import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PatternItem

class CircleOptions {
    var center: LatLng = LatLng(0.0, 0.0)
        internal set
    var clickable:  Boolean = false
        internal set
    var fillColor: Int = 0
        internal set
    var radius: Double = 0.0
        internal set
    var strokeColor: Int = 0
        internal set
    var strokePattern: List<PatternItem>? = null
        internal set
    var strokeWidth: Float = 0F
        internal set
    var visible: Boolean = true
        internal set
    var zIndex: Float = 0F
        internal set
}