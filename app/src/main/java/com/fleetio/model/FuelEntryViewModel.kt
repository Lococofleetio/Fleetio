package com.fleetio.model

import androidx.lifecycle.ViewModel
import java.util.ArrayList
/*
A ViewModel is being used to help the UI efficiently recover from a configuration change.
 */
class FuelEntryViewModel : ViewModel() {

    private var viewModelEntriesArrayList: ArrayList<FuelData>? = null
    private var fuelEntriesArrayList: ArrayList<FuelData>? = null

    fun getFuelEntriesArrayList(): ArrayList<FuelData>? {
        return fuelEntriesArrayList
    }

    fun setFuelEntriesArrayList(dataList: ArrayList<FuelData>?) {
        this.viewModelEntriesArrayList = dataList
    }
}