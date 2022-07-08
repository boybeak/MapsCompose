package com.github.boybeak.map.base

interface IUiSettingsApplier {
    var compassEnabled: Boolean
    var indoorLevelPickerEnabled: Boolean
    var mapToolbarEnabled: Boolean
    var myLocationButtonEnabled: Boolean
    var rotationGesturesEnabled:  Boolean
    var scrollGesturesEnabled:  Boolean
    var isScrollGesturesEnabledDuringRotateOrZoom: Boolean
    var isTiltGesturesEnabled: Boolean
    var isZoomControlsEnabled: Boolean
    var isZoomGesturesEnabled: Boolean
}