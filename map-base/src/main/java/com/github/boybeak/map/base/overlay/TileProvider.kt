package com.github.boybeak.map.base.overlay

interface TileProvider {

    fun getTile(a: Int, b: Int, c: Int): ITile?

    companion object {
        val NO_TILE: ITile
        init {
            NO_TILE = object : ITile {
                override val width: Int
                    get() = -1
                override val height: Int
                    get() = -1
                override val data: ByteArray
                    get() = ByteArray(0)
            }
        }
    }
}