package com.github.boybeak.map.base

import android.location.Location
import android.view.View
import com.github.boybeak.map.base.model.*
import com.github.boybeak.map.base.overlay.*

interface IMap {

    val uiSettings: IUiSettingsApplier

    var isBuildingEnabled: Boolean
    var isIndoorEnabled: Boolean
    var isMyLocationEnabled: Boolean
    var isTrafficEnabled: Boolean
    var mapType: Int

    val cameraPosition: CameraPosition

    val projection: IProjection

    fun onCreate()
    fun onDestroy()

    fun clear()

    fun setMapStyle(mapStyleOptions: Any?)
    fun setLatLngBoundsForCameraTarget(latLngBounds: LatLngBounds?)
    fun setLocationSource(locationSource: LocationSource?)
    fun moveCamera(cameraPosition: CameraPosition)
    fun setContentDescription(contentDescription: String?)
    fun setMaxZoomPreference(maxZoom: Float)
    fun setMinZoomPreference(minZoom: Float)
    fun setPadding(left: Int, top: Int, right: Int, bottom: Int)

    fun addCircle(circle: CircleOptions): ICircle
    fun addMarker(marker: MarkerOptions): IMarker
    fun addPolygon(polygon: PolygonOptions): IPolygon
    fun addPolyline(polyline: PolylineOptions): IPolyline
    fun addTileOverlay(tileOverlay: TileOverlayOptions): ITileOverlay
    fun addGroundOverlay(groundOverlay: GroundOverlayOptions): IGroundOverlay?

    fun setOnCameraIdleListener(listener: (() -> Unit)?)
    fun setOnCameraMoveCanceledListener(listener: (() -> Unit)?)
    fun setOnCameraMoveStartedListener(listener: ((reason: Int) -> Unit)?)
    fun setOnCameraMoveListener(listener: (() -> Unit)?)
    fun setOnMapClickListener(listener: ((latLng: LatLng) -> Unit)?)
    fun setOnMapLongClickListener(listener: ((latLng: LatLng) -> Unit)?)
    fun setOnMapLoadedCallback(listener: (() -> Unit)?)
    fun setOnMyLocationButtonClickListener(listener: (() -> Unit)?)
    fun setOnMyLocationClickListener(listener: ((location: Location) -> Unit)?)
    fun setOnPoiClickListener(listener: ((point: PointOfInterest) -> Unit)?)
    fun setOnIndoorStateChangeListener(listener: IndoorStateChangeListener?)

    fun setOnCircleClickListener(listener: (ICircle) -> Unit)

    fun setInfoWindowAdapter(adapter: InfoWindowAdapter?)

    interface InfoWindowAdapter {
        fun getInfoContents(marker: IMarker): View?
        fun getInfoWindow(marker: IMarker): View?
    }

}