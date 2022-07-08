package com.github.boybeak.map.base.overlay

interface ITile {
    val width: Int
    val height: Int
    val data: ByteArray
}