package com.github.boybeak.map.base

import android.content.Context
import android.os.Bundle
import android.view.View

interface IMapView {
    fun getContext(): Context
    fun getMapView(): View
    fun getMapAsync(callback: (IMap) -> Unit)

    fun onCreate(bundle: Bundle)
    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroy()
    fun onLowMemory()

    fun addView(view: View)
    fun removeView(view: View)
}