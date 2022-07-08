package com.github.boybeak.map.base.model

class LatLngBounds(
    val southwest: LatLng,
    val northeast: LatLng
) {
    val center: LatLng
        get() {
            val midLat = (southwest.latitude + northeast.latitude) / 2.0
            val swLong = southwest.longitude
            val neLong = northeast.longitude
            val midLong = if (neLong <= swLong) {
                (swLong + neLong) / 2.0
            } else {
                (swLong + 360.0 + neLong) / 2.0
            }
            return LatLng(midLat, midLong)
        }

    fun contains(point: LatLng): Boolean {
        val pLat = point.latitude
        return southwest.latitude <= pLat && pLat <= northeast.latitude
                && isLongitudeInside(point.longitude)
    }

    private fun isLongitudeInside(longitude: Double): Boolean {
        val swLong = southwest.longitude
        val neLong = northeast.longitude
        return if (swLong < neLong) {
            longitude in swLong..neLong
        } else {
            swLong <= longitude || longitude <= neLong
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LatLngBounds

        if (southwest != other.southwest) return false
        if (northeast != other.northeast) return false

        return true
    }

    override fun hashCode(): Int {
        var result = southwest.hashCode()
        result = 31 * result + northeast.hashCode()
        return result
    }

}