package com.github.boybeak.map.tencent.overlay

import android.util.Log
import com.github.boybeak.map.base.IBitmapDescriptor
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.overlay.IMarker
import com.github.boybeak.map.tencent.ktx.toLocal
import com.github.boybeak.map.tencent.ktx.toRemote
import com.tencent.tencentmap.mapsdk.maps.model.Marker

class TencentMarker(private val marker: Marker,
                    private val isFlat: Boolean,
                    private val overlayManager: OverlayManager) : IMarker {

    companion object {
        private const val TAG = "TencentMarker"
    }

    override var alpha: Float
        get() = marker.alpha
        set(value) { marker.alpha = value }
    override var rotation: Float
        get() = marker.rotation
        set(value) { marker.rotation = value }
    override var zIndex: Float
        get() = marker.zIndex.toFloat()
        set(value) { marker.zIndex = value.toInt() }
    override var position: LatLng?
        get() = marker.position.toRemote()
        set(value) { marker.position = value?.toLocal() }
    override var tag: Any?
        get() = marker.tag
        set(value) { marker.tag = value }
    override val id: String
        get() = marker.id
    override var snippet: String?
        get() = marker.snippet
        set(value) { marker.snippet = value }
    override var title: String?
        get() = marker.title
        set(value) { marker.title = value }
    override var isDraggable: Boolean
        get() = marker.isDraggable
        set(value) { marker.isDraggable = value }
    override var isVisible: Boolean
        get() = marker.isVisible
        set(value) { marker.isVisible = value }
    override var flat: Boolean
        get() = isFlat
        set(value) {
            Log.e(TAG, "set flat is not supported by TencentMap")
        }
    override val isInfoWindowShown: Boolean
        get() = marker.isInfoWindowShown

    override fun showInfoWindow() {
        marker.showInfoWindow()
    }

    override fun hideInfoWindow() {
        marker.hideInfoWindow()
    }

    override fun remove() {
        overlayManager.removeMarker(id)
        marker.remove()
    }

    override fun setAnchor(u: Float, v: Float) {
        marker.setAnchor(u, v)
    }

    override fun setIcon(icon: IBitmapDescriptor?) {
        marker.setIcon(icon?.toLocal())
    }

    override fun setInfoWindowAnchor(u: Float, v: Float) {
        marker.setInfoWindowAnchor(u, v)
    }

    override fun equals(other: Any?): Boolean {
        return other != null && other is IMarker && other.id == id
    }

}