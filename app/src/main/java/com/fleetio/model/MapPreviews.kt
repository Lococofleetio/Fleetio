package com.fleetio.model

import com.google.gson.annotations.SerializedName


data class MapPreviews (

    @SerializedName("small") var small : String,
    @SerializedName("large") var large : String,
    @SerializedName("small_short") var smallShort : String,
    @SerializedName("large_short") var largeShort : String

)
