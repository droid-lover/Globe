package com.nativemobilebits.globe.repository

import com.nativemobilebits.globe.datasource.ResourceState
import com.nativemobilebits.globe.datasource.local.datasource.LocalDataSource
import com.nativemobilebits.globe.datasource.remote.datasource.CountryDataSource
import com.nativemobilebits.globe.datasource.remote.entity.RequestQuery
import com.nativemobilebits.globe.datasource.remote.response.CountriesResponse
import com.nativemobilebits.globe.datasource.remote.response.CountryDetailsResponse
import com.nativemobilebits.globe.datasource.util.NetworkBoundSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class CountryRepository(
    private val countryDataSource: CountryDataSource,
    private val localDataSource: LocalDataSource
) {

    fun getCountries(): Flow<ResourceState<CountriesResponse>> {
        return object : NetworkBoundSource<CountriesResponse, CountriesResponse>() {

            override suspend fun fetchFromRemote(): Response<CountriesResponse> {
                return countryDataSource.getCountries()
            }

            override suspend fun postProcess(originalData: CountriesResponse): CountriesResponse {
                saveCountriesDataLocally(originalData)
                return originalData
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }


    private fun saveCountriesDataLocally(originalData: CountriesResponse) {
        localDataSource.insertCountries(originalData)
    }


    fun getCountryDetails(data: RequestQuery): Flow<ResourceState<CountryDetailsResponse>> {
        return object : NetworkBoundSource<CountryDetailsResponse, CountryDetailsResponse>() {

            override suspend fun fetchFromRemote(): Response<CountryDetailsResponse> {
                return countryDataSource.getCountryDetails(data)
            }

            override suspend fun postProcess(originalData: CountryDetailsResponse): CountryDetailsResponse {
                return originalData
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }

}
