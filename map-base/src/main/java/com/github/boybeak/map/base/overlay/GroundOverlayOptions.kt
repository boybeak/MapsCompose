package com.github.boybeak.map.base.overlay

import com.github.boybeak.map.base.BitmapDescriptorFactory
import com.github.boybeak.map.base.IBitmapDescriptor
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.LatLngBounds

class GroundOverlayOptions {
    var imageDescriptor: IBitmapDescriptor = BitmapDescriptorFactory.defaultMarker()
        internal set
    var location: LatLng = LatLng()
        internal set
    var width: Float = 0F
        internal set
    var height: Float = 0F
        internal set
    var bounds: LatLngBounds? = null
        internal set
    var bearing: Float = 0F
        internal set
    var zIndex: Float = 0F
        internal set
    var isVisible: Boolean = true
        internal set
    var transparency: Float = 0F
        internal set
    var anchorU: Float = 0.5F
        internal set
    var anchorV: Float = 0.5F
        internal set
    var isClickable: Boolean = false
        internal set

    fun getImage(): IBitmapDescriptor {
        return imageDescriptor
    }

    fun anchor(u: Float, v: Float): GroundOverlayOptions {
        this.anchorU = u
        this.anchorV = v
        return this
    }

    fun bearing(bearing: Float): GroundOverlayOptions {
        this.bearing = (bearing % 360.0f + 360.0f) % 360.0f
        return this
    }

    fun position(location: LatLng, width: Float): GroundOverlayOptions {
        // TODO
        return this
    }

    fun position(location: LatLng, width: Float, height: Float): GroundOverlayOptions {
        // TODO
        return this
    }

    fun positionFromBounds(bounds: LatLngBounds): GroundOverlayOptions {
        // TODO
        return this
    }

    fun transparency(transparency: Float): GroundOverlayOptions {
        this.transparency = transparency
        return this
    }

    fun clickable(clickable: Boolean): GroundOverlayOptions {
        this.isClickable = clickable
        return this
    }

    fun image(image: IBitmapDescriptor): GroundOverlayOptions {
        this.imageDescriptor = image
        return this
    }

    fun visible(visible: Boolean): GroundOverlayOptions {
        this.isVisible = visible
        return this
    }

    fun zIndex(zIndex: Float): GroundOverlayOptions {
        this.zIndex = zIndex
        return this
    }

}