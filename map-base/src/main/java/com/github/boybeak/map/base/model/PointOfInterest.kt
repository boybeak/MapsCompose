package com.github.boybeak.map.base.model

import android.os.Parcel
import android.os.Parcelable

class PointOfInterest(
    val latlng: LatLng,
    val placeId: String,
    val name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(LatLng::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(latlng, flags)
        parcel.writeString(placeId)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PointOfInterest> {
        override fun createFromParcel(parcel: Parcel): PointOfInterest {
            return PointOfInterest(parcel)
        }

        override fun newArray(size: Int): Array<PointOfInterest?> {
            return arrayOfNulls(size)
        }
    }
}