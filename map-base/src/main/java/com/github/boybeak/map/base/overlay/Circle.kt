package com.github.boybeak.map.base.overlay

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.github.boybeak.map.base.MapApplier
import com.github.boybeak.map.base.MapNode
import com.github.boybeak.map.base.ktx.addCircle
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.PatternItem

internal class CircleNode(
    val circle: ICircle,
    var onCircleClick: (ICircle) -> Unit
) : MapNode {
    override fun onRemoved() {
        circle.remove()
    }
}

@Composable
fun Circle(
    center: LatLng,
    clickable: Boolean = false,
    fillColor: Color = Color.Transparent,
    radius: Double = 0.0,
    strokeColor: Color = Color.Black,
    strokePattern: List<PatternItem>? = null,
    strokeWidth: Float = 10f,
    tag: Any? = null,
    visible: Boolean = true,
    zIndex: Float = 0f,
    onClick: (ICircle) -> Unit = {},
) {
    val mapApplier = currentComposer.applier as? MapApplier
    ComposeNode<CircleNode, MapApplier>(
        factory = {
            val circle = mapApplier?.map?.addCircle {
                this.center = center
                this.clickable = clickable
                this.fillColor = fillColor.toArgb()
                this.radius = radius
                this.strokeColor = strokeColor.toArgb()
                this.strokePattern = strokePattern
                this.strokeWidth = strokeWidth
                this.visible = visible
                this.zIndex = zIndex
            } ?: error("Error adding circle")
            circle.tag = tag
            CircleNode(circle, onClick)
        },
        update = {
            update(onClick) { this.onCircleClick = it }

            set(center) { this.circle.center = it }
            set(clickable) { this.circle.isClickable = it }
            set(fillColor) { this.circle.fillColor = it.toArgb() }
            set(radius) { this.circle.radius = it }
            set(strokeColor) { this.circle.strokeColor = it.toArgb() }
            set(strokePattern) { this.circle.strokePattern = it }
            set(strokeWidth) { this.circle.strokeWidth = it }
            set(tag) { this.circle.tag = it }
            set(visible) { this.circle.isVisible = it }
            set(zIndex) { this.circle.zIndex = it }
        }
    )
}