package com.nativemobilebits.globe.datasource.local.datasource

import android.graphics.Color
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nativemobilebits.globe.datasource.remote.response.CountriesResponse

interface LocalDataSource {
  fun insertCountries(countriesResponse: CountriesResponse)
  fun getCountries() : CountriesResponse
}