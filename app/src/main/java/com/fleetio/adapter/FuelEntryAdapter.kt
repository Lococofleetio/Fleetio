package com.fleetio.adapter

import android.graphics.Color

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fleetio.R
import com.fleetio.model.FuelData
import kotlinx.android.synthetic.main.row_layout.view.*

class FuelEntryAdapter (private val fuelEntryList : ArrayList<FuelData>, private val listener : Listener) : RecyclerView.Adapter<FuelEntryAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(retroCrypto: FuelData)
    }

    private val colors : Array<String> = arrayOf("#EC407A" , "#d32f2f","#7E57C2", "#42A5F5", "#FFEE58", "#FF7043" , "#26C6DA", "#66BB6A")

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fuelEntryList[position], listener, colors, position)
    }

    override fun getItemCount(): Int = fuelEntryList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(entry: FuelData, listener: Listener, colors : Array<String>, position: Int) {

            itemView.setOnClickListener{ listener.onItemClick(entry) }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.text_id.text = "Id: "      + entry.vehicleId.toString()
            itemView.text_date.text ="Date: "   +entry.date
            itemView.text_vehicle_name.text ="Vehicle Name: "  +entry.vehicleName

            if (entry.costPerHr != null) {
                itemView.text_cost.text = "Cost per hour: "         + entry.costPerHr.toString()
            }
            else {
                itemView.text_cost.text = "Cost per hour: N/A"
            }

            itemView.text_cost_per_mile.text ="Cost per mile: "+entry.costPerMi.toString()
            itemView.text_gallons.text ="Gallons: "            +entry.usGallons.toString()
            itemView.text_fuel_type.text = "Fuel type: "       + entry.fuelTypeName

            if (entry.pricePerVolumeUnit != null) {
                itemView.text_price_per_gallon.text = "Price per gallon: " +entry.pricePerVolumeUnit.toString()
            }
            else {
                itemView.text_price_per_gallon.text = "Price per gallon: N/A"
            }

            if (entry.vendorName != null) {
                itemView.text_vendor.text = "Vendor name: "       +entry.vendorName.toString()
            }
            else {
                itemView.text_vendor.text = "Vendor name: N/A"
            }

            itemView.text_reference_number.text ="Reference name: "       +entry.reference

            if (entry.geolocation.original_vendor!= null) {
                itemView.text_lat.text = "Latitude: "       +entry.geolocation.original_vendor.latitude.toString()
            }
            else {
                itemView.text_lat.text ="Latitude: N/A"
            }

            if (entry.geolocation.original_vendor!= null) {
                itemView.text_long.text = "Longitude: "       +entry.geolocation.original_vendor.longitude.toString()
            }
            else {
                itemView.text_long.text ="Longitude: N/A"
            }


        }
    }
}