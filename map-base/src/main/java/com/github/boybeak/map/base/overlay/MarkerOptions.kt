package com.github.boybeak.map.base.overlay

import com.github.boybeak.map.base.IBitmapDescriptor
import com.github.boybeak.map.base.model.LatLng

class MarkerOptions {
    var position: LatLng = LatLng()
        internal set

    var title: String? = null
        internal set

    var snippet: String? = null
        internal set

    var icon: IBitmapDescriptor? = null
        internal set

    var anchorU: Float = 0F
        internal set

    var anchorV: Float = 0F
        internal set

    var draggable: Boolean = false
        internal set

    var visible: Boolean = true
        internal set

    var flat: Boolean = true
        internal set

    var rotation: Float = 0F
        internal set

    var infoWindowAnchorU: Float = 0F
        internal set

    var infoWindowAnchorV: Float = 0F
        internal set

    var alpha: Float = 1F
        internal set

    var zIndex: Float = 0F
        internal set

    var tag: Any? = null
        internal set

    fun anchor(u: Float, v: Float) {
        this.anchorU = u
        this.anchorV = v
    }
    fun infoWindowAnchor(u: Float, v: Float) {
        this.infoWindowAnchorU = u
        this.infoWindowAnchorV = v
    }
}