package com.github.boybeak.map.tencent

import android.graphics.Point
import android.location.Location
import android.util.Log
import android.view.View
import com.github.boybeak.map.base.*
import com.github.boybeak.map.base.model.*
import com.github.boybeak.map.base.overlay.*
import com.github.boybeak.map.tencent.ktx.toLocal
import com.github.boybeak.map.tencent.ktx.toRemote
import com.github.boybeak.map.tencent.model.TencentIndoorLevel
import com.github.boybeak.map.tencent.overlay.OverlayManager
import com.github.boybeak.map.tencent.overlay.TencentCircle
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory
import com.tencent.tencentmap.mapsdk.maps.TencentMap
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions
import com.tencent.tencentmap.mapsdk.maps.model.IndoorBuilding
import com.tencent.tencentmap.mapsdk.maps.model.Marker
import com.tencent.tencentmap.mapsdk.maps.model.RestrictBoundsFitMode
import java.lang.ref.WeakReference

class TencentMap(private val map: TencentMap, view: TencentMapView): IMap {

    companion object {
        private const val TAG = "TencentMap"
    }

    private val mapViewRef = WeakReference(view)
    private val overlayManager = OverlayManager.create(map)

    override val uiSettings: IUiSettingsApplier = object : IUiSettingsApplier {
        override var compassEnabled: Boolean
            get() = map.uiSettings.isCompassEnabled
            set(value) { map.uiSettings.isCompassEnabled = value }
        override var indoorLevelPickerEnabled: Boolean
            get() = map.uiSettings.isIndoorLevelPickerEnabled
            set(value) { map.uiSettings.isIndoorLevelPickerEnabled = value }
        override var mapToolbarEnabled: Boolean = false
            get() = field
            set(value) {
                Log.e(TAG, "mapToolbarEnabled not supported by TencentMap")
                field = value
            }
        override var myLocationButtonEnabled: Boolean
            get() = map.uiSettings.isMyLocationButtonEnabled
            set(value) { map.uiSettings.isMyLocationButtonEnabled = value }
        override var rotationGesturesEnabled: Boolean
            get() = map.uiSettings.isRotateGesturesEnabled
            set(value) { map.uiSettings.isRotateGesturesEnabled = value }
        override var scrollGesturesEnabled: Boolean
            get() = map.uiSettings.isScrollGesturesEnabled
            set(value) { map.uiSettings.isScrollGesturesEnabled = value }
        override var isScrollGesturesEnabledDuringRotateOrZoom: Boolean = false
            get() = field
            set(value) {
                Log.e(TAG, "isScrollGesturesEnabledDuringRotateOrZoom not supported by TencentMap")
                field = value
            }
        override var isTiltGesturesEnabled: Boolean
            get() = map.uiSettings.isTiltGesturesEnabled
            set(value) { map.uiSettings.isTiltGesturesEnabled = value }
        override var isZoomControlsEnabled: Boolean
            get() = map.uiSettings.isZoomControlsEnabled
            set(value) { map.uiSettings.isZoomControlsEnabled = value }
        override var isZoomGesturesEnabled: Boolean
            get() = map.uiSettings.isZoomGesturesEnabled
            set(value) { map.uiSettings.isZoomGesturesEnabled = value }
    }

    override var isBuildingEnabled: Boolean = false
        get() = field
        set(value) {
            map.setBuildingEnable(value)
            field = value
        }
    override var isIndoorEnabled: Boolean = false
        get() = field
        set(value) {
            map.setIndoorEnabled(value)
            field = value
        }
    override var isMyLocationEnabled: Boolean
        get() = map.isMyLocationEnabled
        set(value) {
            map.isMyLocationEnabled = value
        }
    override var isTrafficEnabled: Boolean
        get() = map.isTrafficEnabled
        set(value) {
            map.isTrafficEnabled = value
        }
    override var mapType: Int
        get() = map.mapType
        set(value) {
            map.mapType = value
        }

    override val cameraPosition: CameraPosition
        get() = map.cameraPosition.run {
            CameraPosition(LatLng(this.target.latitude, this.target.longitude),
                this.zoom, this.tilt, this.bearing)
        }

    override val projection: IProjection
        get() = map.projection.let { projection ->
            object : IProjection {
                override val visibleRegion: VisibleRegion
                    get() = projection.visibleRegion.let {
                        VisibleRegion(it.nearLeft.toRemote(), it.nearRight.toRemote(),
                            it.farLeft.toRemote(), it.farRight.toRemote(), it.latLngBounds.toRemote())
                    }

                override fun toScreenLocation(location: LatLng): Point {
                    return projection.toScreenLocation(location.toLocal())
                }

                override fun fromScreenLocation(point: Point): LatLng {
                    return projection.fromScreenLocation(point).toRemote()
                }
            }
        }

    private val paddingValues = IntArray(4)
    private val logoMargins = IntArray(2)

    private val onCameraListener = object : TencentMap.OnCameraChangeListener {
        var onCameraStartedListener: ((Int) -> Unit)? = null
        var onCameraIdleListener: (() -> Unit)? = null
        var onCameraMoveListener: (() -> Unit)? = null
        private var isMoving = false
        override fun onCameraChange(p0: com.tencent.tencentmap.mapsdk.maps.model.CameraPosition?) {
            if (!isMoving) {
                onCameraStartedListener?.invoke(0)
                isMoving = true
            }
            onCameraMoveListener?.invoke()
        }

        override fun onCameraChangeFinished(p0: com.tencent.tencentmap.mapsdk.maps.model.CameraPosition?) {
            isMoving = false
            onCameraIdleListener?.invoke()
        }
    }

    private val onMapLoadedCallback = object : TencentMap.OnMapLoadedCallback {
        var outerCallback: (() -> Unit)? = null
        override fun onMapLoaded() {
            outerCallback?.invoke()
        }
    }

    init {
        map.uiSettings.apply {
            setLogoPosition(TencentMapOptions.LOGO_POSITION_BOTTOM_LEFT)
            setScaleViewPosition(TencentMapOptions.SCALEVIEW_POSITION_BOTTOM_RIGHT)
        }
        map.setOnCameraChangeListener(onCameraListener)
    }

    override fun onCreate() {
        map.addOnMapLoadedCallback(onMapLoadedCallback)
    }

    override fun onDestroy() {
        map.removeOnMapLoadedCallback(onMapLoadedCallback)
    }

    override fun clear() {
        // TODO
    }

    override fun setMapStyle(mapStyleOptions: Any?) {
        if (mapStyleOptions !is Int) {
            return
        }
        map.mapStyle = mapStyleOptions
    }

    override fun setLatLngBoundsForCameraTarget(latLngBounds: LatLngBounds?) {
        val bounds = latLngBounds?.toLocal()
        val fitMode = if (map.mapWidth <= map.mapHeight) {
            RestrictBoundsFitMode.FIT_WIDTH
        } else {
            RestrictBoundsFitMode.FIT_HEIGHT
        }
        map.setRestrictBounds(bounds, fitMode)
    }

    override fun setLocationSource(locationSource: LocationSource?) {
        val ls = locationSource?.run {
            object : com.tencent.tencentmap.mapsdk.maps.LocationSource {
                override fun activate(listener: com.tencent.tencentmap.mapsdk.maps.LocationSource.OnLocationChangedListener?) {
                    locationSource.activate(object : LocationSource.OnLocationChangedListener {
                        override fun onLocationChanged(location: Location) {
                            listener?.onLocationChanged(location)
                        }
                    })
                }

                override fun deactivate() {
                    locationSource.deactivate()
                }
            }
        }
        map.setLocationSource(ls)
    }

    override fun moveCamera(cameraPosition: CameraPosition) {
        map.moveCamera(CameraUpdateFactory.newCameraPosition(
            cameraPosition.toLocal()
        ))
    }

    override fun setContentDescription(contentDescription: String?) {
        mapViewRef.get()?.contentDescription = contentDescription
    }

    override fun setMaxZoomPreference(maxZoom: Float) {
        map.setMaxZoomLevel(maxZoom.toInt())
    }

    override fun setMinZoomPreference(minZoom: Float) {
        map.setMinZoomLevel(minZoom.toInt())
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        paddingValues[0] = left
        paddingValues[1] = top
        paddingValues[2] = right
        paddingValues[3] = bottom

        logoMargins[0] = bottom
        logoMargins[1] = left

        map.uiSettings.apply {
            setLogoPosition(TencentMapOptions.LOGO_POSITION_BOTTOM_LEFT, logoMargins)
            setScaleViewPositionWithMargin(TencentMapOptions.SCALEVIEW_POSITION_BOTTOM_RIGHT, 0, 0, right, bottom)
        }
    }

    override fun addCircle(circle: CircleOptions): ICircle {
        return TencentCircle(map.addCircle(circle.toLocal()))
    }

    override fun addMarker(marker: MarkerOptions): IMarker {
        return overlayManager.addMarker(marker)
    }

    override fun addPolygon(polygon: PolygonOptions): IPolygon {
        return overlayManager.addPolygon(polygon)
    }

    override fun addPolyline(polyline: PolylineOptions): IPolyline {
        return overlayManager.addPolyline(polyline)
    }

    override fun addTileOverlay(tileOverlay: TileOverlayOptions): ITileOverlay {
        return overlayManager.addTileOverlay(tileOverlay)
    }

    override fun addGroundOverlay(groundOverlay: GroundOverlayOptions): IGroundOverlay? {
        return overlayManager.addGroundOverlay(groundOverlay)
    }

    override fun setOnCameraIdleListener(listener: (() -> Unit)?) {
        onCameraListener.onCameraIdleListener = listener
    }

    override fun setOnCameraMoveCanceledListener(listener: (() -> Unit)?) {
        Log.e(TAG, "setOnCameraMoveCanceledListener not supported by TencentMap")
    }

    override fun setOnCameraMoveStartedListener(listener: ((reason: Int) -> Unit)?) {
        onCameraListener.onCameraStartedListener = listener
    }

    override fun setOnCameraMoveListener(listener: (() -> Unit)?) {
        onCameraListener.onCameraMoveListener = listener
    }

    override fun setOnMapClickListener(listener: ((latLng: LatLng) -> Unit)?) {
        Log.v(TAG, "setOnMapClickListener listener=$listener")
        val onClick = listener?.run {
            TencentMap.OnMapClickListener {
                invoke(it.toRemote())
            }
        }
        map.setOnMapClickListener(onClick)
    }

    override fun setOnMapLongClickListener(listener: ((latLng: LatLng) -> Unit)?) {
        val onLongClick = listener?.run {
            TencentMap.OnMapLongClickListener {
                invoke(it.toRemote())
            }
        }
        map.setOnMapLongClickListener(onLongClick)
    }

    override fun setOnMapLoadedCallback(listener: (() -> Unit)?) {
        onMapLoadedCallback.outerCallback = listener
    }

    override fun setOnMyLocationButtonClickListener(listener: (() -> Unit)?) {
        Log.e(TAG, "setOnMyLocationButtonClickListener not supported for TencentMap")
    }

    override fun setOnMyLocationClickListener(listener: ((location: Location) -> Unit)?) {
        val myLocationClick = listener?.run {
            TencentMap.OnMyLocationClickListener {
                invoke(map.myLocation)
                true
            }
        }
        map.setMyLocationClickListener(myLocationClick)
    }

    override fun setOnPoiClickListener(listener: ((point: PointOfInterest) -> Unit)?) {
        Log.v(TAG, "setOnPoiClickListener $listener")
        val poiClick = listener?.run {
            TencentMap.OnMapPoiClickListener {
                invoke(PointOfInterest(it.position.toRemote(), it.name + it.position.toString(), it.name))
            }
        }
        map.setOnMapPoiClickListener(poiClick)
    }

    override fun setOnIndoorStateChangeListener(listener: IndoorStateChangeListener?) {
        val indoorListener = listener?.run {
            object : TencentMap.OnIndoorStateChangeListener {
                override fun onIndoorBuildingFocused(): Boolean {
                    listener.onIndoorBuildingFocused()
                    return true
                }

                override fun onIndoorLevelActivated(p0: IndoorBuilding?): Boolean {
                    listener.onIndoorLevelActivated(object : IIndoorBuilding {
                        override val levels: List<IIndoorLevel>
                            get() = p0?.levels?.run {
                                List(size) {
                                    val level = this[0]
                                    TencentIndoorLevel(level)
                                }
                            } ?: emptyList()
                        override val activeLevelIndex: Int
                            get() = p0?.activeLevelIndex ?: 0
                        override val defaultLevelIndex: Int
                            get() {
                                Log.v(TAG, "defaultLevelIndex is not supported for TencentMap")
                                return 0
                            }
                        override val isUnderGround: Boolean
                            get() {
                                Log.v(TAG, "isUnderGround is not supported for TencentMap")
                                return false
                            }
                    })
                    return true
                }

                override fun onIndoorBuildingDeactivated(): Boolean {
                    return true
                }
            }
        }
        map.setOnIndoorStateChangeListener(indoorListener)
    }

    override fun setOnCircleClickListener(listener: (ICircle) -> Unit) {
        Log.v(TAG, "setOnCircleClickListener is not supported by TencentMap")
    }

    override fun setInfoWindowAdapter(adapter: IMap.InfoWindowAdapter?) {
        val winAdapter = adapter?.let {
            object : TencentMap.InfoWindowAdapter {

                private fun emptyView(): View {
                    return View(map.mapContext.context)
                }

                override fun getInfoWindow(marker: Marker?): View {
                    marker ?: return emptyView()
                    val m = overlayManager.obtainMarker(marker) ?: return emptyView()
                    return it.getInfoWindow(m) ?: emptyView()
                }

                override fun getInfoContents(marker: Marker?): View {
                    marker ?: return emptyView()
                    val m = overlayManager.obtainMarker(marker) ?: return emptyView()
                    return it.getInfoContents(m) ?: emptyView()
                }
            }
        }
        map.setInfoWindowAdapter(winAdapter)
    }

}