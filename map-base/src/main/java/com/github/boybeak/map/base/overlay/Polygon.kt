package com.github.boybeak.map.base.overlay

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.github.boybeak.map.base.MapApplier
import com.github.boybeak.map.base.MapNode
import com.github.boybeak.map.base.ktx.addPolygon
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PatternItem

internal class PolygonNode(
    val polygon: IPolygon,
    var onPolygonClick: (IPolygon) -> Unit
) : MapNode {
    override fun onRemoved() {
        polygon.remove()
    }
}

/**
 * A composable for a polygon on the map.
 *
 * @param points the points comprising the vertices of the polygon
 * @param clickable boolean indicating if the polygon is clickable or not
 * @param fillColor the fill color of the polygon
 * @param geodesic specifies whether to draw each segment as a geodesic
 * @param holes the holes for the polygon
 * @param strokeColor the stroke color of the polygon
 * @param strokeJointType the joint type for all vertices of the polygon's outline
 * @param strokePattern the stroke pattern for the polygon's outline
 * @param strokeWidth specifies the polygon's stroke width, in display pixels
 * @param tag optional tag to associate wiht the polygon
 * @param visible the visibility of the polygon
 * @param zIndex the z-index of the polygon
 * @param onClick a lambda invoked when the polygon is clicked
 */
@Composable
fun Polygon(
    points: List<LatLng>,
    clickable: Boolean = false,
    fillColor: Color = Color.Black,
    geodesic: Boolean = false,
    holes: List<List<LatLng>> = emptyList(),
    strokeColor: Color = Color.Black,
    strokeJointType: Int = JointType.DEFAULT,
    strokePattern: List<PatternItem>? = null,
    strokeWidth: Float = 10f,
    tag: Any? = null,
    visible: Boolean = true,
    zIndex: Float = 0f,
    onClick: (IPolygon) -> Unit = {}
) {
    val mapApplier = currentComposer.applier as MapApplier?
    ComposeNode<PolygonNode, MapApplier>(
        factory = {
            val polygon = mapApplier?.map?.addPolygon {
                addAll(points)
                this.isClickable = clickable
                this.fillColor = fillColor.toArgb()
                this.isGeodesic = geodesic
                holes.forEach {
                    addHole(it)
                }
                this.strokeColor = strokeColor.toArgb()
                this.strokeJointType = strokeJointType
                this.strokePattern = strokePattern
                this.strokeWidth = strokeWidth
                this.isVisible = visible
                this.zIndex = zIndex
            } ?: error("Error adding polygon")
            polygon.tag = tag
            PolygonNode(polygon, onClick)
        },
        update = {
            update(onClick) { this.onPolygonClick = it }

            set(points) { this.polygon.points = it }
            set(clickable) { this.polygon.isClickable = it }
            set(fillColor) { this.polygon.fillColor = it.toArgb() }
            set(geodesic) { this.polygon.isGeodesic = it }
            set(holes) { this.polygon.holes = it }
            set(strokeColor) { this.polygon.strokeColor = it.toArgb() }
            set(strokeJointType) { this.polygon.strokeJointType = it }
            set(strokePattern) { this.polygon.strokePattern = it }
            set(strokeWidth) { this.polygon.strokeWidth = it }
            set(tag) { this.polygon.tag = it }
            set(visible) { this.polygon.isVisible = it }
            set(zIndex) { this.polygon.zIndex = it }
        }
    )
}