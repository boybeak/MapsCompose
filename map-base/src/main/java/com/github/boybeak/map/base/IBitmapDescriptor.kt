package com.github.boybeak.map.base

interface IBitmapDescriptor {
    companion object {
        const val TYPE_NONE = -1
        const val TYPE_DEFAULT = 0
        const val TYPE_RESOURCE = 1
        const val TYPE_ASSET = 2
        const val TYPE_FILE = 3
        const val TYPE_PATH = 4
        const val TYPE_BITMAP = 5
    }
    val type: Int
    fun get(): Any
}