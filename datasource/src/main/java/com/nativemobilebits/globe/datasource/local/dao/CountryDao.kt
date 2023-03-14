package com.nativemobilebits.globe.datasource.local.dao


import androidx.room.*
import com.nativemobilebits.globe.datasource.remote.response.CountriesResponse
import com.nativemobilebits.globe.datasource.util.TableConstants

@Dao
interface CountryDao : BaseDao<CountriesResponse>{

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertCountries(countriesResponse: CountriesResponse)

  @Query("SELECT * FROM ${TableConstants.COUNTRIES_RESPONSE}")
  fun getCountries() : CountriesResponse

}