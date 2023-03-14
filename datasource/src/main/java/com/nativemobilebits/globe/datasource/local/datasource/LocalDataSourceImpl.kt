package com.nativemobilebits.globe.datasource.local.datasource

import android.content.Context
import com.google.gson.Gson
import com.nativemobilebits.globe.datasource.local.AppDB
import com.nativemobilebits.globe.datasource.remote.response.CountriesResponse
import dagger.hilt.android.qualifiers.ApplicationContext

class LocalDataSourceImpl(@ApplicationContext private val context: Context) : LocalDataSource {

  private val appDB: AppDB = AppDB.getDB(context)
  override fun insertCountries(countriesResponse: CountriesResponse) {
    appDB.getCountryDao().insertCountries(countriesResponse)
  }

  override fun getCountries(): CountriesResponse {
    return appDB.getCountryDao().getCountries()
  }


}