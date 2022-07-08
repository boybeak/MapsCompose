package com.github.boybeak.map.base.overlay

import com.github.boybeak.map.base.IBitmapDescriptor

interface ICap {
    val type: Int
    val bitmapDescriptor: IBitmapDescriptor?
    val bitmapRefWidth: Float
}

class ButtCap : ICap {
    override val type: Int
        get() = 0
    override val bitmapDescriptor: IBitmapDescriptor?
        get() = null
    override val bitmapRefWidth: Float
        get() = 0F
}

class SquareCap : ICap {
    override val type: Int
        get() = 1
    override val bitmapDescriptor: IBitmapDescriptor?
        get() = null
    override val bitmapRefWidth: Float
        get() = 0F
}

class RoundCap : ICap {
    override val type: Int
        get() = 2
    override val bitmapDescriptor: IBitmapDescriptor?
        get() = null
    override val bitmapRefWidth: Float
        get() = 0F
}

class CustomCap(
    override val bitmapDescriptor: IBitmapDescriptor?,
    override val bitmapRefWidth: Float = 10F
) : ICap {
    override val type: Int
        get() = 3
}