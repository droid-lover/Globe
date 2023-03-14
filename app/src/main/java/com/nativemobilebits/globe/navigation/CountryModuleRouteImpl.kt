package com.nativemobilebits.globe.navigation

import androidx.navigation.NavController
import com.nativemobilebits.globe.R
import com.nativemobilebits.globe.datasource.remote.response.Country
import com.nativemobilebits.globe.ui.HomeFragmentDirections

class CountryModuleRouteImpl : CountryModuleRoute {

    override fun showCountryDetails(navController: NavController, country: Country) {
        val action = HomeFragmentDirections.actionHomeFragmentToCountryDetailsFragment(country)
        navController.navigate(action)
    }

}
