package com.github.boybeak.map.base.ktx

import com.github.boybeak.map.base.IMap
import com.github.boybeak.map.base.overlay.*

inline fun circleOptions(optionsActions: CircleOptions.() -> Unit): CircleOptions =
    CircleOptions().apply(optionsActions)

inline fun IMap.addCircle(optionsActions: CircleOptions.() -> Unit): ICircle =
    this.addCircle(circleOptions(optionsActions))

inline fun markerOptions(optionsActions: MarkerOptions.() -> Unit): MarkerOptions =
    MarkerOptions().apply(optionsActions)

inline fun IMap.addMarker(optionsActions: MarkerOptions.() -> Unit): IMarker? =
    this.addMarker(
        markerOptions(optionsActions)
    )

inline fun polygonOptions(optionsActions: PolygonOptions.() -> Unit): PolygonOptions =
    PolygonOptions().apply(
        optionsActions
    )
inline fun IMap.addPolygon(optionsActions: PolygonOptions.() -> Unit): IPolygon =
    this.addPolygon(
        polygonOptions(optionsActions)
    )

inline fun polylineOptions(optionsActions: PolylineOptions.() -> Unit): PolylineOptions =
    PolylineOptions().apply(
        optionsActions
    )
inline fun IMap.addPolyline(optionsActions: PolylineOptions.() -> Unit): IPolyline =
    this.addPolyline(
        polylineOptions(optionsActions)
    )

inline fun tileOverlayOptions(optionsActions: TileOverlayOptions.() -> Unit): TileOverlayOptions =
    TileOverlayOptions().apply(
        optionsActions
    )
inline fun IMap.addTileOverlay(optionsActions: TileOverlayOptions.() -> Unit): ITileOverlay? =
    this.addTileOverlay(
        tileOverlayOptions(optionsActions)
    )

inline fun groundOverlayOptions(optionsActions: GroundOverlayOptions.() -> Unit): GroundOverlayOptions =
    GroundOverlayOptions().apply(
        optionsActions
    )
inline fun IMap.addGroundOverlay(optionsActions: GroundOverlayOptions.() -> Unit): IGroundOverlay? =
    this.addGroundOverlay(
        groundOverlayOptions(optionsActions)
    )