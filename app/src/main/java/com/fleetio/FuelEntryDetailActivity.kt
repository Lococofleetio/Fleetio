package com.fleetio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_entry_detail.*

class FuelEntryDetailActivity : AppCompatActivity() {
    /*
    Our activity does not require us to recover large sets of data,
    re-establish a network connection, or perform other intensive operations
    This makes it possible possible to completely restore our activity state with the Bundle
    that the system saves with the onSaveInstanceState() callback
    */

    private var id: TextView? = null
    private var date: TextView? = null
    private var vehicleName: TextView? = null
    private var cost: TextView? = null
    private var costPerMile: TextView? = null
    private var gallons: TextView? = null
    private var fuelType: TextView? = null
    private var pricePerGallon: TextView? = null
    private var vendor: TextView? = null
    private var referenceNumber : TextView? = null
    private var latitude : TextView? = null
    private var longitude : TextView? = null
    private var lat:Double = 0.0
    private var long:Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_detail)

        id = findViewById(R.id.vehicle_id)
        date = findViewById(R.id.date)
        vehicleName = findViewById(R.id.vehicle_name)
        cost = findViewById(R.id.cost)
        costPerMile = findViewById(R.id.cost_per_mile)
        gallons = findViewById(R.id.gallons)
        fuelType = findViewById(R.id.fuel_type)

        pricePerGallon = findViewById(R.id.price_per_gallon)
        vendor = findViewById(R.id.vendor)
        referenceNumber = findViewById(R.id.reference_number)
        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)

        id?.text      = "Id: " + intent.getStringExtra("id" )
        date?.text      = "Date: " + intent.getStringExtra("date" )
        vehicleName?.text   = "Vehicle Name: "+intent.getStringExtra("vehiclename" )
        cost?.text      = "Cost: " +intent.getStringExtra("costperhr" )
        costPerMile?.text = "Cost Per Mile: " +intent.getStringExtra("costpermi" )
        gallons?.text = "Gallons: " +intent.getStringExtra("usgallons" )
        fuelType?.text  ="Fuel Type: " +intent.getStringExtra("fueltypename" )
        pricePerGallon?.text   = "Price Per Gallon: "+intent.getStringExtra("pricepervolumeunit" )

        vendor?.text     = "Vendor: "+intent.getStringExtra("vendorname" )
        referenceNumber?.text = "Reference Number: "+intent.getStringExtra("reference" )

        if(intent.getStringExtra("lat" )!=null) {
            lat = intent.getStringExtra("lat").toDouble()
        }
        if(intent.getStringExtra("long" )!=null) {
            long = intent.getStringExtra("long").toDouble()
        }

        latitude?.text = "Latitude: "+intent.getStringExtra("lat" )
        longitude?.text = "Longitude: "+intent.getStringExtra("long" )

        map.setOnClickListener {
            if(lat == 0.0 || long ==0.0) {
                Toast.makeText(
                    applicationContext,
                    "Map coordinates are not available for this entry",
                    Toast.LENGTH_LONG
                ).show()
            }else {

                // Creates an Intent that will load a map of a geolocations
                val gmmIntentUri = Uri.parse("geo:" + lat + "," + long)
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent)
            }
        }
    }

}
