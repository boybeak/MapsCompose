package com.github.boybeak.map.google.ktx

import com.github.boybeak.map.base.MapContext
import com.google.maps.android.compose.MapType

class GoogleMapContext : MapContext {
    private val mapTypes = arrayOf(
        MapType.NONE.name,
        MapType.NORMAL.name,
        MapType.SATELLITE.name,
        MapType.TERRAIN.name,
        MapType.HYBRID.name
    )
    override fun getAvailableMapTypes(): Array<String> {
        return mapTypes
    }

    override fun getDefaultMapType(): String {
        return mapTypes[0]
    }
}