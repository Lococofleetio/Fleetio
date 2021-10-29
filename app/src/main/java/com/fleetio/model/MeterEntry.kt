package com.fleetio.model

import com.google.gson.annotations.SerializedName


data class MeterEntry (

    @SerializedName("id") var id : Int,
    @SerializedName("auto_voided_at") var autoVoidedAt : String,
    @SerializedName("category") var category : String,
    @SerializedName("date") var date : String,
    @SerializedName("meter_type") var meterType : String,
    @SerializedName("meterable_id") var meterableId : Int,
    @SerializedName("meterable_type") var meterableType : String,
    @SerializedName("value") var value : String,
    @SerializedName("vehicle_id") var vehicleId : Int,
    @SerializedName("void") var void : Boolean,
    @SerializedName("type") var type : String,
    @SerializedName("created_at") var createdAt : String,
    @SerializedName("updated_at") var updatedAt : String

)
