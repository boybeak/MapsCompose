package com.github.boybeak.map.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.github.boybeak.map.base.model.CameraPosition
import com.github.boybeak.map.base.model.IProjection
import com.github.boybeak.map.base.model.LatLng

class CameraPositionState(
    position: CameraPosition = CameraPosition(LatLng(0.0, 0.0), 0F, 0F, 0F)
) {

    var isMoving: Boolean by mutableStateOf(false)
        internal set

    val projection: IProjection?
        get() = map?.projection

    internal var rawPosition by mutableStateOf(position)
    var position: CameraPosition
        get() = rawPosition
        set(value) {
            synchronized(lock) {
                val map = map
                if (map == null) {
                    rawPosition = value
                } else {
                    map.moveCamera(position)
                }
            }
        }

    private val lock = Any()

    private var map: IMap? = null
    private var onMapChanged: OnMapChangedCallback? = null

    private fun interface OnMapChangedCallback {
        fun onMapChangedLocked(newMap: IMap?)
        fun onCancelLocked() {}
    }
    fun setMap(map: IMap?) {
        synchronized(lock) {
            if (this.map == null && map == null) return
            if (this.map != null && map != null) {
                error("CameraPositionState may only be associated with one GoogleMap at a time")
            }
            this.map = map
            if (map == null) {
                isMoving = false
            } else {
                map.moveCamera(position)
            }
            onMapChanged?.let {
                // Clear this first since the callback itself might set it again for later
                onMapChanged = null
                it.onMapChangedLocked(map)
            }
        }
    }

    companion object {
        val Saver: Saver<CameraPositionState, CameraPosition> = Saver(
            save = { it.position },
            restore = { CameraPositionState(it) }
        )
    }

}

@Composable
inline fun rememberCameraPositionState(
    key: String? = null,
    crossinline  init: CameraPositionState.() -> Unit = {}
): CameraPositionState = rememberSaveable(key = key, saver = CameraPositionState.Saver) {
    CameraPositionState().apply(init)
}