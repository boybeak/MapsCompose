package com.github.boybeak.map.tencent.overlay

import android.util.Log
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PatternItem
import com.github.boybeak.map.base.overlay.IPolygon
import com.github.boybeak.map.tencent.ktx.toLocal
import com.github.boybeak.map.tencent.ktx.toRemote
import com.tencent.tencentmap.mapsdk.maps.model.Polygon

class TencentPolygon(private val polygon: Polygon,
                     private val overlayManager: OverlayManager) : IPolygon {

    companion object {
        private const val TAG = "TencentPolygon"
    }

    override var strokeWidth: Float
        get() = polygon.strokeWidth
        set(value) { polygon.strokeWidth = value }
    override var zIndex: Float
        get() = polygon.zIndex.toFloat()
        set(value) { polygon.zIndex = value.toInt() }
    override var fillColor: Int
        get() = polygon.fillColor
        set(value) { polygon.fillColor = value }
    override var strokeColor: Int
        get() = polygon.strokeColor
        set(value) { polygon.strokeColor = value }
    override var strokeJointType: Int
        get() {
            Log.e(TAG, "strokeJointType not supported by TencentMap")
            return 0
        }
        set(value) {}
    override var tag: Any?
        get() = polygon.tag
        set(value) { polygon.tag = value }
    override val id: String
        get() = polygon.id
    override var holes: List<List<LatLng>>
        get() {
            Log.e(TAG, "holes not supported by TencentMap")
            return emptyList()
        }
        set(value) {}
    override var points: List<LatLng>
        get() = polygon.points.map { it.toRemote() }
        set(value) { polygon.points = value.map { it.toLocal() } }
    override var strokePattern: List<PatternItem>?
        get() {
            Log.e(TAG, "strokeJointType not supported by TencentMap")
            return null
        }
        set(value) {}
    override var isGeodesic: Boolean
        get() {
            Log.e(TAG, "isGeodesic not supported by TencentMap")
            return false
        }
        set(value) {}
    override var isVisible: Boolean
        get() = polygon.isVisible
        set(value) { polygon.isVisible = value }
    override var isClickable: Boolean
        get() = polygon.isClickable
        set(value) { polygon.isClickable = value }

    override fun remove() {
        overlayManager.removePolygon(id)
        polygon.remove()
    }
}