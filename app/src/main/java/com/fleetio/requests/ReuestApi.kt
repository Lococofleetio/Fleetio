package com.fleetio.requests

import com.fleetio.model.FuelData
import io.reactivex.Observable
import retrofit2.http.GET

//Define our API calls for retrofit
interface RequestApi {
    @GET("api/v1/fuel_entries/")
    fun getFuelEntryData(): Observable<List<FuelData>>
}