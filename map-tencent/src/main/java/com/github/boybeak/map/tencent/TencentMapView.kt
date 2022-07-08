package com.github.boybeak.map.tencent

import android.content.Context
import android.os.Bundle
import android.view.View
import com.github.boybeak.map.base.IMap
import com.github.boybeak.map.base.IMapView
import com.tencent.tencentmap.mapsdk.maps.CameraUpdate
import com.tencent.tencentmap.mapsdk.maps.MapView

class TencentMapView(context: Context) : MapView(context), IMapView {

    private val tencentMap = TencentMap(map, this)

    override fun getMapView(): View {
        return this
    }

    override fun onCreate(bundle: Bundle) {
        tencentMap.onCreate()
    }
    override fun onDestroy() {
        super.onDestroy()
        tencentMap.onDestroy()
    }
    override fun onLowMemory() {}
    override fun getMapAsync(callback: (IMap) -> Unit) {
        callback.invoke(tencentMap)
    }
}