package com.fleetio.model

import com.google.gson.annotations.SerializedName


data class CustomFields (

    @SerializedName("expense_type") var expenseType : String,
    @SerializedName("used_card") var usedCard : String,
    @SerializedName("card_number") var cardNumber : String,
    @SerializedName("fuel_unit_number") var fuelUnitNumber : String,
    @SerializedName("test_restricted_custom_fields") var testRestrictedCustomFields : String,
    @SerializedName("fuel-up_state") var fuel_upState : String,
    @SerializedName("second_tank_gallons") var secondTankGallons : String,
    @SerializedName("juan_type_of_fuel") var juanTypeOfFuel : String

)
