package com.github.boybeak.map.base

import androidx.compose.runtime.AbstractApplier
import androidx.compose.runtime.Applier
import com.github.boybeak.map.base.overlay.*
import com.github.boybeak.map.base.overlay.CircleNode
import com.github.boybeak.map.base.overlay.ComposeInfoWindowAdapter
import com.github.boybeak.map.base.overlay.MarkerNode

internal interface MapNode {
    fun onAttached() {}
    fun onRemoved() {}
    fun onCleared() {}
}

private object MapNodeRoot : MapNode

// TODO fill those methods
internal class MapApplier(
    val map: IMap,
    private val mapView: IMapView
): AbstractApplier<MapNode>(MapNodeRoot) {

    private val decorations = mutableListOf<MapNode>()

    init {
        attachClickListeners()
    }

    override fun onClear() {
        map.clear()
        decorations.forEach { it.onCleared() }
        decorations.clear()
    }

    override fun insertBottomUp(index: Int, instance: MapNode) {
        decorations.add(index, instance)
        instance.onAttached()
    }

    override fun insertTopDown(index: Int, instance: MapNode) {
        // insertBottomUp is preferred
    }

    override fun move(from: Int, to: Int, count: Int) {
        decorations.move(from, to, count)
    }

    override fun remove(index: Int, count: Int) {
        repeat(count) {
            decorations[index + it].onRemoved()
        }
        decorations.remove(index, count)
    }

    private fun attachClickListeners() {
        // TODO("Not yet implemented")
        map.setOnCircleClickListener {
            decorations.nodeForCircle(it)
                ?.onCircleClick
                ?.invoke(it)
        }
        /*map.setOnGroundOverlayClickListener {
            decorations.nodeForGroundOverlay(it)
                ?.onGroundOverlayClick
                ?.invoke(it)
        }
        map.setOnPolygonClickListener {
            decorations.nodeForPolygon(it)
                ?.onPolygonClick
                ?.invoke(it)
        }
        map.setOnPolylineClickListener {
            decorations.nodeForPolyline(it)
                ?.onPolylineClick
                ?.invoke(it)
        }

        // Marker
        map.setOnMarkerClickListener { marker ->
            decorations.nodeForMarker(marker)
                ?.onMarkerClick
                ?.invoke(marker)
                ?: false
        }
        map.setOnInfoWindowClickListener { marker ->
            decorations.nodeForMarker(marker)
                ?.onInfoWindowClick
                ?.invoke(marker)
        }
        map.setOnInfoWindowCloseListener { marker ->
            decorations.nodeForMarker(marker)
                ?.onInfoWindowClose
                ?.invoke(marker)
        }
        map.setOnInfoWindowLongClickListener { marker ->
            decorations.nodeForMarker(marker)
                ?.onInfoWindowLongClick
                ?.invoke(marker)
        }
        map.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDrag(marker: Marker) {
                with(decorations.nodeForMarker(marker)) {
                    this?.markerState?.position = marker.position
                    this?.markerState?.dragState = DragState.DRAG
                }
            }

            override fun onMarkerDragEnd(marker: Marker) {
                with(decorations.nodeForMarker(marker)) {
                    this?.markerState?.position = marker.position
                    this?.markerState?.dragState = DragState.END
                }
            }

            override fun onMarkerDragStart(marker: Marker) {
                with(decorations.nodeForMarker(marker)) {
                    this?.markerState?.position = marker.position
                    this?.markerState?.dragState = DragState.START
                }
            }
        })*/
        map.setInfoWindowAdapter(
            ComposeInfoWindowAdapter(
                mapView,
                markerNodeFinder = { decorations.nodeForMarker(it) }
            )
        )
    }

}

private fun MutableList<MapNode>.nodeForCircle(circle: ICircle): CircleNode? =
    firstOrNull { it is CircleNode && it.circle == circle } as? CircleNode

private fun MutableList<MapNode>.nodeForMarker(marker: IMarker): MarkerNode? =
    firstOrNull { it is MarkerNode && it.marker == marker } as? MarkerNode
