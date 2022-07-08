package com.github.boybeak.map.base

private var mapContextInternal: MapContext? = null
val mapContext: MapContext get() {
    if (mapContextInternal == null) {
        throw IllegalStateException("Map context is not initialized, you must call initMapContext before use map")
    }
    return mapContextInternal!!
}

fun initMapContext(mapContext: MapContext) {
    if (mapContextInternal != null) {
        return
    }
    mapContextInternal = mapContext
}

interface MapContext {
    fun getAvailableMapTypes(): Array<String>
    fun getDefaultMapType(): String
}