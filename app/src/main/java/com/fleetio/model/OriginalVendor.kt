package com.fleetio.model

import com.google.gson.annotations.SerializedName

class OriginalVendor (
    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude : Double
)