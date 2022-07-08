package com.github.boybeak.map.base

import android.graphics.Bitmap

object BitmapDescriptorFactory {
    fun fromResource(resourceId: Int): IBitmapDescriptor =
        SimpleBitmapDescriptor(IBitmapDescriptor.TYPE_RESOURCE, resourceId)
    fun fromAsset(assetName: String): IBitmapDescriptor =
        SimpleBitmapDescriptor(IBitmapDescriptor.TYPE_ASSET, assetName)
    fun fromFile(fileName: String): IBitmapDescriptor =
        SimpleBitmapDescriptor(IBitmapDescriptor.TYPE_FILE, fileName)
    fun fromPath(absolutePath: String): IBitmapDescriptor =
        SimpleBitmapDescriptor(IBitmapDescriptor.TYPE_PATH, absolutePath)
    fun fromBitmap(image: Bitmap): IBitmapDescriptor =
        SimpleBitmapDescriptor(IBitmapDescriptor.TYPE_BITMAP, image)
    fun defaultMarker(): IBitmapDescriptor =
        SimpleBitmapDescriptor(IBitmapDescriptor.TYPE_DEFAULT, Unit)
}

private class SimpleBitmapDescriptor(override val type: Int, private val data: Any) : IBitmapDescriptor {
    override fun get(): Any {
        return data
    }
}