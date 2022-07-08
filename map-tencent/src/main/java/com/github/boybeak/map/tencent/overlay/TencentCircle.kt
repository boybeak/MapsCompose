package com.github.boybeak.map.tencent.overlay

import android.util.Log
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PatternItem
import com.github.boybeak.map.base.overlay.ICircle
import com.github.boybeak.map.tencent.ktx.toLocal
import com.github.boybeak.map.tencent.ktx.toRemote
import com.tencent.tencentmap.mapsdk.maps.model.Circle

class TencentCircle(private val circle: Circle) : ICircle {

    companion object {
        private const val TAG = "TencentCircle"
    }

    override var center: LatLng
        get() = circle.center.toRemote()
        set(value) {
            circle.center = value.toLocal()
        }
    override var isClickable: Boolean
        get() = circle.isClickable
        set(value) {
            circle.isClickable = value
        }
    override var fillColor: Int
        get() = circle.fillColor
        set(value) {
            circle.fillColor = value
        }
    override var radius: Double
        get() = circle.radius
        set(value) {
            circle.radius = value
        }
    override var strokeColor: Int
        get() = circle.strokeColor
        set(value) {
            circle.strokeColor = value
        }
    override var strokePattern: List<PatternItem>?
        get() {
            Log.e(TAG, "strokePattern not supported by TencentMap")
            return null
        }
        set(value) {
            Log.e(TAG, "strokePattern not supported by TencentMap")
        }
    override var strokeWidth: Float
        get() = circle.strokeWidth
        set(value) {
            circle.strokeWidth = value
        }
    override var tag: Any?
        get() = circle.tag
        set(value) {
            circle.tag = value
        }
    override var isVisible: Boolean
        get() = circle.isVisible
        set(value) {
            circle.isVisible = value
        }
    override var zIndex: Float
        get() = circle.zIndex.toFloat()
        set(value) {
            circle.setZIndex(value.toInt())
        }

    override fun remove() {
        circle.remove()
    }

}