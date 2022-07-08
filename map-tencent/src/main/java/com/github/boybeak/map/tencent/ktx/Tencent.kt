package com.github.boybeak.map.tencent.ktx

import android.graphics.Bitmap
import android.util.Log
import com.github.boybeak.map.base.IBitmapDescriptor
import com.github.boybeak.map.base.model.CameraPosition
import com.github.boybeak.map.base.model.LatLng
import com.github.boybeak.map.base.model.LatLngBounds
import com.github.boybeak.map.base.overlay.*
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory
import com.tencent.tencentmap.mapsdk.maps.model.Tile
import com.tencent.tencentmap.mapsdk.maps.model.TileProvider

fun LatLng.toLocal(): com.tencent.tencentmap.mapsdk.maps.model.LatLng {
    return com.tencent.tencentmap.mapsdk.maps.model.LatLng(latitude, longitude)
}
fun com.tencent.tencentmap.mapsdk.maps.model.LatLng.toRemote(): LatLng {
    return LatLng(this.latitude, this.longitude)
}
fun CameraPosition.toLocal(): com.tencent.tencentmap.mapsdk.maps.model.CameraPosition {
    return com.tencent.tencentmap.mapsdk.maps.model.CameraPosition(target.toLocal(), zoom, tilt, bearing)
}
fun CircleOptions.toLocal(): com.tencent.tencentmap.mapsdk.maps.model.CircleOptions {
    return com.tencent.tencentmap.mapsdk.maps.model.CircleOptions().apply {
        center(this@toLocal.center.toLocal())
        clickable(this@toLocal.clickable)
        fillColor(this@toLocal.fillColor)
        radius(this@toLocal.radius)
        strokeColor(this@toLocal.strokeColor)
        // strokePattern not supported
        strokeWidth(this@toLocal.strokeWidth)
        visible(this@toLocal.visible)
        zIndex(this@toLocal.zIndex.toInt())
    }
}

fun MarkerOptions.toLocal(): com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions {
    return com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions(this.position.toLocal()).apply {
        title(this@toLocal.title)
        snippet(this@toLocal.snippet)
        Log.v("TencentMap", "MarkerOptions.toLocal icon=${this@toLocal.icon}")
        icon(this@toLocal.icon?.toLocal())
        anchor(this@toLocal.anchorU, this@toLocal.anchorV)
        draggable(this@toLocal.draggable)
        visible(this@toLocal.visible)
        flat(this@toLocal.flat)
        rotation(this@toLocal.rotation)
        infoWindowAnchor(this@toLocal.infoWindowAnchorU, this@toLocal.infoWindowAnchorV)
        alpha(this@toLocal.alpha)
        zIndex(this@toLocal.zIndex)
        tag(this@toLocal.tag)
    }
}

// TODO isGeodesic not supported
// TODO strokeJointType not supported
fun PolygonOptions.toLocal(): com.tencent.tencentmap.mapsdk.maps.model.PolygonOptions {
    return com.tencent.tencentmap.mapsdk.maps.model.PolygonOptions().also {
        it.addAll(this.points.map { point -> point.toLocal() })
        it.strokeWidth(this.strokeWidth)
        it.strokeColor(this.strokeColor)
        it.fillColor(this.fillColor)
        it.zIndex(this.zIndex.toInt())
        it.visible(this.isVisible)
        it.clickable(this.isClickable)
        it.pattern(this.strokePattern?.map { item -> item.length.toInt() })
    }
}

// TODO isGeodesic not supported
// TODO startCap, endCap not supported
fun PolylineOptions.toLocal(): com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions {
    return com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions().also {
        it.addAll(this.points.map { point -> point.toLocal() })
        it.width(this.width)
        it.color(this.color)
        it.zIndex(this.zIndex.toInt())
        it.visible(this.isVisible)
        it.clickable(this.isClickable)
        it.pattern(this.pattern?.map { item -> item.length.toInt() })
    }
}

// TODO isVisible not supported
// TODO fadeIn not supported
// TODO transparency not supported
fun TileOverlayOptions.toLocal(): com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions {
    return com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions().also {
        it.tileProvider(this.tileProvider?.let { tp ->
            TileProvider { p0, p1, p2 ->
                tp.getTile(p0, p1, p2)?.run {
                    Tile(width, height, data)
                } ?: Tile.EMPTY_TILE
            }
        })
        it.zIndex(this.zIndex.toInt())
    }
}

// TODO width and height not supported
// TODO bearing not supported
// TODO isClickable not supported
fun GroundOverlayOptions.toLocal(): com.tencent.tencentmap.mapsdk.maps.model.GroundOverlayOptions {
    return com.tencent.tencentmap.mapsdk.maps.model.GroundOverlayOptions().also {
        it.bitmap(this.imageDescriptor.toLocal())
        it.position(this.location.toLocal())
        it.latLngBounds(this.bounds?.toLocal())
        it.zIndex(this.zIndex.toInt())
        it.visible(this.isVisible)
        it.alpha(this.transparency)
        it.anchor(this.anchorU, this.anchorV)
    }
}

fun IBitmapDescriptor.toLocal(): BitmapDescriptor {
    Log.v("TencentMap", "IBitmapDescriptor.toLocal type=${type} data=${get()}")
    return when(this.type) {
        IBitmapDescriptor.TYPE_RESOURCE -> BitmapDescriptorFactory.fromResource(get() as Int)
        IBitmapDescriptor.TYPE_ASSET -> BitmapDescriptorFactory.fromAsset(get() as String)
        IBitmapDescriptor.TYPE_FILE -> BitmapDescriptorFactory.fromFile(get() as String)
        IBitmapDescriptor.TYPE_PATH -> BitmapDescriptorFactory.fromPath(get() as String)
        IBitmapDescriptor.TYPE_BITMAP -> BitmapDescriptorFactory.fromBitmap(get() as Bitmap)
        else -> {
            BitmapDescriptorFactory.defaultMarker()
        }
    }
}

fun LatLngBounds.toLocal(): com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds {
    return com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds(
        northeast.toLocal(),
        southwest.toLocal()
    )
}

fun com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds.toRemote(): LatLngBounds {
    return LatLngBounds(this.southwest.toRemote(), this.northeast.toRemote())
}