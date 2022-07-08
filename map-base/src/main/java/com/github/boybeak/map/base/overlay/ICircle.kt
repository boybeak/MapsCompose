package com.github.boybeak.map.base.overlay

import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PatternItem

interface ICircle {
    var center: LatLng
    var isClickable: Boolean
    var fillColor: Int
    var radius: Double
    var strokeColor: Int
    var strokePattern: List<PatternItem>?
    var strokeWidth: Float
    var tag: Any?
    var isVisible: Boolean
    var zIndex: Float

    fun remove()
}