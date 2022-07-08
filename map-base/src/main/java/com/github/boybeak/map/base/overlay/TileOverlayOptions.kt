package com.github.boybeak.map.base.overlay

class TileOverlayOptions {
    var tileProvider: TileProvider? = null
        internal set
    var isVisible: Boolean = true
        internal set
    var zIndex: Float = 0F
        internal set
    var fadeIn: Boolean = true
        internal set
    var transparency: Float = 0F
        internal set
}