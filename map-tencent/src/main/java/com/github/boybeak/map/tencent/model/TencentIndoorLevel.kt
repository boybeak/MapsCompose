package com.github.boybeak.map.tencent.model

import android.util.Log
import com.github.boybeak.map.base.model.IIndoorLevel
import com.tencent.tencentmap.mapsdk.maps.model.IndoorLevel

class TencentIndoorLevel(val indoorLevel: IndoorLevel) : IIndoorLevel {

    companion object {
        private const val TAG = "TencentIndoorLevel"
    }

    override val name: String
        get() = indoorLevel.name
    override val shortName: String
        get() = indoorLevel.name

    override fun activate() {
        Log.e(TAG, "activate not supported for TencentMap")
    }
}