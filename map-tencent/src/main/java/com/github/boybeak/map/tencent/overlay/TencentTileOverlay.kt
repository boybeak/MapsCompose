package com.github.boybeak.map.tencent.overlay

import android.util.Log
import com.github.boybeak.map.base.overlay.ITileOverlay
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlay

class TencentTileOverlay(private val tileOverlay: TileOverlay, private val overlayManager: OverlayManager) : ITileOverlay {

    companion object {
        private const val TAG = "TencentTileOverlay"
    }

    override var transparency: Float
        get() {
            Log.e(TAG, "getTransparency is not supported by TencentMap")
            return 0F
        }
        set(value) {
            Log.e(TAG, "setTransparency is not supported by TencentMap")
        }
    override var zIndex: Float
        get() {
            Log.e(TAG, "getZIndex is not supported by TencentMap")
            return 0F
        }
        set(value) { tileOverlay.setZindex(value.toInt()) }
    override val id: String
        get() = tileOverlay.id
    override var fadeIn: Boolean
        get() {
            Log.e(TAG, "getFadeIn is not supported by TencentMap")
            return false
        }
        set(value) {
            Log.e(TAG, "setFadeIn is not supported by TencentMap")
        }
    override var isVisible: Boolean
        get() {
            Log.e(TAG, "isVisible is not supported by TencentMap")
            return true
        }
        set(value) {
            Log.e(TAG, "setVisible is not supported by TencentMap")
        }

    override fun remove() {
        overlayManager.removeTileOverlay(id)
        tileOverlay.remove()
    }

    override fun cleanTileCache() {
        tileOverlay.clearTileCache()
    }
}