package com.github.boybeak.maps.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Looper
import com.github.boybeak.easypermission.ext.withPermissions
import com.github.boybeak.map.base.LocationSource
import java.lang.ref.WeakReference

class DeviceLocationSource(context: Context) : LocationSource {

    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val contextRef = WeakReference(context)

    private var outListener: LocationSource.OnLocationChangedListener? = null

    private val isActivated get() = outListener != null

    private val locationListener = LocationListener {
        outListener?.onLocationChanged(it)
    }

    @SuppressLint("MissingPermission")
    override fun activate(listener: LocationSource.OnLocationChangedListener) {
        if (isActivated) {
            return
        }
        contextRef.get()?.withPermissions(
            permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            onGranted = {
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 15 * 1000L, 0F,
                    locationListener, Looper.getMainLooper()
                )
                outListener = listener
            }
        )
    }

    override fun deactivate() {
        if (!isActivated) {
            return
        }
        locationManager.removeUpdates(locationListener)
    }
}