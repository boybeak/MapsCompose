package com.github.boybeak.map.tencent

import androidx.collection.ArrayMap
import com.github.boybeak.map.base.MapContext
import com.tencent.tencentmap.mapsdk.maps.TencentMap

class TencentMapContext : MapContext {
    private val mapTypes = ArrayMap<String, Int>().apply {
        put("NORMAL", TencentMap.MAP_TYPE_NORMAL)
        put("DARK", TencentMap.MAP_TYPE_NORMAL)
        put("SATELLITE", TencentMap.MAP_TYPE_NORMAL)
        put("TRAFFIC_NAVI", TencentMap.MAP_TYPE_NORMAL)
        put("TRAFFIC_NIGHT", TencentMap.MAP_TYPE_NORMAL)
        put("NIGHT", TencentMap.MAP_TYPE_NORMAL)
        put("NAVI", TencentMap.MAP_TYPE_NORMAL)
    }
    private val mapTypeKeys = arrayOf(
        "NORMAL", "DARK", "SATELLITE", "TRAFFIC_NAVI", "TRAFFIC_NIGHT", "NIGHT", "NAVI",
    )

    override fun getAvailableMapTypes(): Array<String> {
        return mapTypeKeys
    }

    override fun getDefaultMapType(): String {
        return mapTypeKeys[0]
    }
}