package com.fleetio.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fleetio.R
import com.fleetio.adapter.FuelEntryAdapter
import com.fleetio.FuelEntryDetailActivity

import com.fleetio.model.FuelData
import com.fleetio.model.FuelEntryViewModel
import com.fleetio.requests.ServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_fuel_entries.*
import kotlinx.android.synthetic.main.fragment_fuel_entries.view.*

class FuelEntriesFragment : Fragment(), FuelEntryAdapter.Listener  {

    private var myAdapter: FuelEntryAdapter? = null
    var isCached: Boolean = false

    //Manage connections with CompositeDisposable, deallocating in onDestroy
    private var myCompositeDisposable: CompositeDisposable? = null
    private var fuelEntriesArrayList: ArrayList<FuelData>?=null
    private var progressBar: ProgressBar? = null

    //The viewmodel used to recover from configuration changes
    private lateinit var fuelEntryViewModel: FuelEntryViewModel

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_fuel_entries, container)
        progressBar=view.progress_bar

        myCompositeDisposable = CompositeDisposable()

        fuelEntryViewModel = ViewModelProviders.of(this).get(FuelEntryViewModel::class.java)

        // If the data is not defined, it's a new ViewModel so set it.
        if (fuelEntryViewModel.getFuelEntriesArrayList() == null)
        {
            loadData(container)
        }

        return view
    }

    /*
     Using RxAndroid to handle all api interaction and threading
     */
    private fun loadData(root:ViewGroup?) {

        showProgressBar(true)
        val cm = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if(isConnected) {
            val requestInterface = ServiceGenerator.requestApi

            myCompositeDisposable?.add(
                requestInterface.getFuelEntryData()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::loadFuelEntryList)
            )
        }else{
            Toast.makeText(context,
                "No internet connection, trying again later",
                Toast.LENGTH_LONG).show()
        }
    }

    /*
       Once our network call returns we load our list
        */
    private fun loadFuelEntryList(fuelEntryList: List<FuelData>) {
        fuelEntriesArrayList = ArrayList(fuelEntryList)
        myAdapter = FuelEntryAdapter(fuelEntriesArrayList!!, this)

        entries_list.swapAdapter(myAdapter,true)
        fuelEntryViewModel.setFuelEntriesArrayList(fuelEntriesArrayList)
        showProgressBar(false)
    }

    //Called to display the clicked entry. Bundle is used to pass data and will
    //successfully recover from a configuration change
    private fun loadDetailFuelEntry(entry: FuelData) {
        this.startActivity(
            Intent(activity, FuelEntryDetailActivity::class.java).apply {

                putExtra("id",entry.vehicleId.toString())
                putExtra("date",entry.date)
                putExtra("vehiclename",entry.vehicleName)
                putExtra("costperhr",entry.costPerHr)
                putExtra("costpermi",entry.costPerMi)
                putExtra("usgallons",entry.usGallons)
                putExtra("fueltypename",entry.fuelTypeName)
                putExtra("pricepervolumeunit",entry.pricePerVolumeUnit)
                putExtra("vendorname",entry.vendorName)
                putExtra("reference",entry.reference)
                if(entry.geolocation.original_vendor!=null&&entry.geolocation.original_vendor!=null) {
                    putExtra("lat", entry.geolocation.original_vendor.latitude.toString())
                    putExtra("long", entry.geolocation.original_vendor.longitude.toString())
                }

            }
        )
    }

    /*
    Called when the user clicks a fuel entry from the list. We use RxAndroid to handle all threading,
    and handle the result in the callbacks provided
     */
    override fun onItemClick(entry: FuelData) {
        loadDetailFuelEntry(entry)
    }
    //Show progress bar when retrieving fuel entry data
    private fun showProgressBar(showProgressBar: Boolean) {
        if (showProgressBar) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    //Deallocate all connections
    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable?.clear()
    }
    companion object {
        fun create(): FuelEntriesFragment {
            return FuelEntriesFragment()
        }
    }
}