package com.github.boybeak.map.base.overlay

import com.github.boybeak.map.base.IBitmapDescriptor
import com.github.boybeak.map.base.model.LatLng

interface IMarker {
    var alpha: Float
    var rotation: Float
    var zIndex: Float
    var position: LatLng?
    var tag: Any?
    val id: String
    var snippet: String?
    var title: String?
    var isDraggable: Boolean
    var isVisible: Boolean
    var flat: Boolean
    val isInfoWindowShown: Boolean

    fun showInfoWindow()
    fun hideInfoWindow()
    fun remove()

    fun setAnchor(u: Float, v: Float)

    fun setIcon(icon: IBitmapDescriptor?)

    fun setInfoWindowAnchor(u: Float, v: Float)

}