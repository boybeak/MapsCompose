package com.github.boybeak.map.tencent.overlay

import com.github.boybeak.map.base.overlay.*
import com.github.boybeak.map.tencent.ktx.toLocal
import com.tencent.tencentmap.mapsdk.maps.TencentMap
import com.tencent.tencentmap.mapsdk.maps.model.Marker

class OverlayManager private constructor(private val map: TencentMap) {

    companion object {
        fun create(map: TencentMap): OverlayManager {
            return OverlayManager(map)
        }
    }

    private val markerMap = HashMap<String, IMarker>()
    private val polygonMap = HashMap<String, IPolygon>()
    private val polylineMap = HashMap<String, IPolyline>()
    private val tileOverlayMap = HashMap<String, ITileOverlay>()
    private val groundOverlayMap = HashMap<String, IGroundOverlay>()

    fun addMarker(options: MarkerOptions): IMarker {
        val tOpt = options.toLocal()
        val tMarker = map.addMarker(tOpt)
        val iMarker = TencentMarker(tMarker, options.flat, this)
        markerMap[iMarker.id] = iMarker
        return iMarker
    }
    fun removeMarker(id: String) {
        markerMap.remove(id)
    }

    fun obtainMarker(marker: Marker): IMarker? {
        return markerMap[marker.id]
    }

    fun addPolygon(options: PolygonOptions): IPolygon {
        val tOpt = options.toLocal()
        val tPolygon = map.addPolygon(tOpt)
        val iPolygon = TencentPolygon(tPolygon, this)
        polygonMap[iPolygon.id] = iPolygon
        return iPolygon
    }
    fun removePolygon(id: String) {
        polygonMap.remove(id)
    }

    fun addPolyline(options: PolylineOptions): IPolyline {
        val tOpt = options.toLocal()
        val tPolyline = map.addPolyline(tOpt)
        val iPolyline = TencentPolyline(tPolyline, this)
        polylineMap[iPolyline.id] = iPolyline
        return iPolyline
    }
    fun removePolyline(id: String) {
        polylineMap.remove(id)
    }

    fun addTileOverlay(options: TileOverlayOptions): ITileOverlay {
        val tOpt = options.toLocal()
        val tTileOverlay = map.addTileOverlay(tOpt)
        val iTileOverlay = TencentTileOverlay(tTileOverlay, this)
        tileOverlayMap[iTileOverlay.id] = iTileOverlay
        return iTileOverlay
    }
    fun removeTileOverlay(id: String) {
        tileOverlayMap.remove(id)
    }

    fun addGroundOverlay(options: GroundOverlayOptions): IGroundOverlay? {
        val tOpt = options.toLocal()
        val tGroundOverlay = map.addGroundOverlay(tOpt)
        val iGroundOverlay = TencentGroundOverlay(tGroundOverlay, this)
        groundOverlayMap[iGroundOverlay.id] = iGroundOverlay
        return iGroundOverlay
    }
    fun removeGroundOverlay(id: String) {
        groundOverlayMap.remove(id)
    }

}