package com.fleetio.model

import com.google.gson.annotations.SerializedName


class Geolocation (
    @SerializedName("fleetio_fuel") val fleetio_fuel : FleetioFuel,
    @SerializedName("original_vendor") val original_vendor : OriginalVendor
)
