package com.github.boybeak.map.base.model

class VisibleRegion(
    val nearLeft: LatLng,
    val nearRight: LatLng,
    val farLeft: LatLng,
    val farRight: LatLng,
    val latLngBounds: LatLngBounds
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VisibleRegion

        if (nearLeft != other.nearLeft) return false
        if (nearRight != other.nearRight) return false
        if (farLeft != other.farLeft) return false
        if (farRight != other.farRight) return false
        if (latLngBounds != other.latLngBounds) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nearLeft.hashCode()
        result = 31 * result + nearRight.hashCode()
        result = 31 * result + farLeft.hashCode()
        result = 31 * result + farRight.hashCode()
        result = 31 * result + latLngBounds.hashCode()
        return result
    }
}