package com.github.boybeak.map.base.overlay

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.github.boybeak.map.base.MapApplier
import com.github.boybeak.map.base.MapNode
import com.github.boybeak.map.base.ktx.addPolyline
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PatternItem

internal class PolylineNode(
    val polyline: IPolyline,
    var onPolylineClick: (IPolyline) -> Unit
) : MapNode {
    override fun onRemoved() {
        polyline.remove()
    }
}

/**
 * A composable for a polyline on the map.
 *
 * @param points the points comprising the polyline
 * @param clickable boolean indicating if the polyline is clickable or not
 * @param color the color of the polyline
 * @param endCap a cap at the end vertex of the polyline
 * @param geodesic specifies whether to draw the polyline as a geodesic
 * @param jointType the joint type for all vertices of the polyline except the start and end
 * vertices
 * @param pattern the pattern for the polyline
 * @param startCap the cap at the start vertex of the polyline
 * @param visible the visibility of the polyline
 * @param width the width of the polyline in screen pixels
 * @param zIndex the z-index of the polyline
 * @param onClick a lambda invoked when the polyline is clicked
 */
@Composable
fun Polyline(
    points: List<LatLng>,
    clickable: Boolean = false,
    color: Color = Color.Black,
    endCap: ICap = ButtCap(),
    geodesic: Boolean = false,
    jointType: Int = JointType.DEFAULT,
    pattern: List<PatternItem>? = null,
    startCap: ICap = ButtCap(),
    tag: Any? = null,
    visible: Boolean = true,
    width: Float = 10f,
    zIndex: Float = 0f,
    onClick: (IPolyline) -> Unit = {}
) {
    val mapApplier = currentComposer.applier as MapApplier?
    ComposeNode<PolylineNode, MapApplier>(
        factory = {
            val polyline = mapApplier?.map?.addPolyline {
                addAll(points)
                this.isClickable = clickable
                this.color = color.toArgb()
                this.endCap = endCap
                this.isGeodesic = geodesic
                this.jointType = jointType
                this.pattern = pattern
                this.startCap = startCap
                this.isVisible = visible
                this.width = width
                this.zIndex = zIndex
            } ?: error("Error adding Polyline")
            polyline.tag = tag
            PolylineNode(polyline, onClick)
        },
        update = {
            update(onClick) { this.onPolylineClick = it }

            set(points) { this.polyline.points = it }
            set(clickable) { this.polyline.isClickable = it }
            set(color) { this.polyline.color = it.toArgb() }
            set(endCap) { this.polyline.endCap = it }
            set(geodesic) { this.polyline.isGeodesic = it }
            set(jointType) { this.polyline.jointType = it }
            set(pattern) { this.polyline.pattern = it }
            set(startCap) { this.polyline.startCap = it }
            set(tag) { this.polyline.tag = it }
            set(visible) { this.polyline.isVisible = it }
            set(width) { this.polyline.width = it }
            set(zIndex) { this.polyline.zIndex = it }
        }
    )
}