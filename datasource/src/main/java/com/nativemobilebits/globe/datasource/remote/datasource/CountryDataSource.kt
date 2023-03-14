package com.nativemobilebits.globe.datasource.remote.datasource

import com.nativemobilebits.globe.datasource.remote.entity.RequestQuery
import com.nativemobilebits.globe.datasource.remote.response.CountriesResponse
import com.nativemobilebits.globe.datasource.remote.response.CountryDetailsResponse
import retrofit2.Response

interface CountryDataSource {

    suspend fun getCountries(): Response<CountriesResponse>
    suspend fun getCountryDetails(data: RequestQuery): Response<CountryDetailsResponse>

}