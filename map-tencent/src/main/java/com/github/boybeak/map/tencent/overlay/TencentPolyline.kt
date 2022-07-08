package com.github.boybeak.map.tencent.overlay

import android.util.Log
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PatternItem
import com.github.boybeak.map.base.overlay.ICap
import com.github.boybeak.map.base.overlay.IPolyline
import com.github.boybeak.map.tencent.ktx.toLocal
import com.github.boybeak.map.tencent.ktx.toRemote
import com.tencent.tencentmap.mapsdk.maps.model.Polyline

class TencentPolyline(private val polyline: Polyline, private val overlayManager: OverlayManager) : IPolyline {

    companion object {
        private const val TAG = "TencentPolyline"
    }

    override var width: Float
        get() = polyline.width
        set(value) { polyline.width = value }
    override var zIndex: Float
        get() = polyline.zIndex.toFloat()
        set(value) { polyline.setZIndex(value) }
    override var color: Int
        get() = polyline.color
        set(value) { polyline.color = value }
    override var jointType: Int
        get() {
            Log.e(TAG, "jointType is not supported by TencentMap")
            return 0
        }
        set(value) {}
    override var endCap: ICap?
        get() {
            Log.e(TAG, "endCap is not supported by TencentMap")
            return null
        }
        set(value) {}
    override var startCap: ICap?
        get() {
            Log.e(TAG, "startCap is not supported by TencentMap")
            return null
        }
        set(value) {}
    override var tag: Any?
        get() = polyline.tag
        set(value) { polyline.tag = value }
    override val id: String
        get() = polyline.id
    override var pattern: List<PatternItem>?
        get() = polyline.pattern?.map { PatternItem(0, it.toFloat()) }
        set(value) { polyline.pattern(value?.map { it.length.toInt() }) }
    override var points: List<LatLng>
        get() = polyline.points.map { it.toRemote() }
        set(value) { polyline.points = value.map { it.toLocal() } }
    override var isClickable: Boolean
        get() = polyline.isClickable
        set(value) { polyline.isClickable = value }
    override var isGeodesic: Boolean
        get() {
            Log.e(TAG, "isGeodesic is not supported by TencentMap")
            return false
        }
        set(value) {}
    override var isVisible: Boolean
        get() = polyline.isVisible
        set(value) { polyline.isVisible = value }

    override fun remove() {
        overlayManager.removePolyline(id)
        polyline.remove()
    }
}