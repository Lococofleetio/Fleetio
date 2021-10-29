package com.fleetio.ui

import com.fleetio.R


interface EntryResource {
    companion object {
        val tabList = listOf(
            R.string.tab1, R.string.tab2
        )
        val pagerFragments = listOf(
            FuelEntriesFragment.create(), MapFragment.create()
            )
    }
}