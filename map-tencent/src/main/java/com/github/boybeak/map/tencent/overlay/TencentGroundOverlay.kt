package com.github.boybeak.map.tencent.overlay

import android.util.Log
import com.github.boybeak.map.base.IBitmapDescriptor
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.LatLngBounds
import com.github.boybeak.map.base.overlay.IGroundOverlay
import com.github.boybeak.map.tencent.ktx.toLocal
import com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay

class TencentGroundOverlay(private val groundOverlay: GroundOverlay, private val overlayManager: OverlayManager) : IGroundOverlay {

    companion object {
        private const val TAG = "TencentGroundOverlay"
    }

    override var bearing: Float
        get() {
            Log.e(TAG, "bearing is not supported by TencentMap")
            return 0F
        }
        set(value) { Log.e(TAG, "bearing is not supported by TencentMap") }
    override var height: Float
        get() {
            Log.e(TAG, "height is not supported by TencentMap")
            return 0F
        }
        set(value) { Log.e(TAG, "height is not supported by TencentMap") }
    override var width: Float
        get() {
            Log.e(TAG, "width is not supported by TencentMap")
            return 0F
        }
        set(value) { Log.e(TAG, "width is not supported by TencentMap") }
    override var transparency: Float = 0F
        set(value) {
            groundOverlay.setAlpha(value)
            field = value
        }
    override var zIndex: Float = 0F
        set(value) {
            groundOverlay.setZindex(value.toInt())
            field = value
        }
    override var position: LatLng = LatLng()
        set(value) {
            groundOverlay.setPosition(value.toLocal())
            field = value
        }
    override var bounds: LatLngBounds = LatLngBounds(LatLng(), LatLng())
        set(value) {
            groundOverlay.setLatLongBounds(value.toLocal())
            field = value
        }
    override var tag: Any? = null
        get() {
            Log.e(TAG, "tag is not supported by TencentMap")
            return field
        }
        set(value) {
            field = value
        }
    override val id: String
        get() = groundOverlay.toString()
    override var isClickable: Boolean
        get() {
            Log.e(TAG, "isClickable is not supported by TencentMap")
            return false
        }
        set(value) {
            Log.e(TAG, "isClickable is not supported by TencentMap")
        }
    override var isVisible: Boolean = true
        get() {
            Log.e(TAG, "isClickable is not supported by TencentMap")
            return true
        }
        set(value) {
            groundOverlay.setVisibility(value)
            field = value
        }

    override fun remove() {
        groundOverlay.remove()
        overlayManager.removeGroundOverlay(id)
    }

    override fun setDimensions(width: Float) {
        Log.e(TAG, "setDimensions is not supported by TencentMap")
    }

    override fun setDimensions(width: Float, height: Float) {
        Log.e(TAG, "setDimensions is not supported by TencentMap")
    }

    override fun setImage(image: IBitmapDescriptor) {
        Log.e(TAG, "setImage is not supported by TencentMap")
    }

    override fun setPositionFromBounds(bounds: LatLngBounds) {
        Log.e(TAG, "setPositionFromBounds is not supported by TencentMap")
    }
}