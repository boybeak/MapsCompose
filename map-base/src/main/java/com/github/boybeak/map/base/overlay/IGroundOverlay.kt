package com.github.boybeak.map.base.overlay

import com.github.boybeak.map.base.IBitmapDescriptor
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.LatLngBounds

interface IGroundOverlay  {
    var bearing: Float
    var height: Float
    var width: Float
    var transparency: Float
    var zIndex: Float
    var position: LatLng
    var bounds: LatLngBounds
    var tag: Any?
    val id: String
    var isClickable: Boolean
    var isVisible: Boolean

    fun remove()
    fun setDimensions(width: Float)
    fun setDimensions(width: Float, height: Float)
    fun setImage(image: IBitmapDescriptor)
    fun setPositionFromBounds(bounds: LatLngBounds)

}