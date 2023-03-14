package com.nativemobilebits.globe.navigation

import androidx.navigation.NavController
import com.nativemobilebits.globe.datasource.remote.response.Country

interface CountryModuleRoute {

    fun showCountryDetails(navController: NavController, country: Country)

}