package com.github.boybeak.map.base.overlay

interface ITileOverlay {
    var transparency: Float
    var zIndex: Float
    val id: String
    var fadeIn: Boolean
    var isVisible: Boolean

    fun remove()
    fun cleanTileCache()

}