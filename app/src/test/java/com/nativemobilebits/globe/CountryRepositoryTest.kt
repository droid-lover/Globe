package com.nativemobilebits.globe

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.nativemobilebits.globe.dataprovider.CountryDataProvider
import com.nativemobilebits.globe.datasource.ResourceState
import com.nativemobilebits.globe.datasource.local.datasource.LocalDataSource
import com.nativemobilebits.globe.datasource.remote.datasource.CountryDataSource
import com.nativemobilebits.globe.repository.CountryRepository
import com.nativemobilebits.globe.ui.CountryViewModel
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import retrofit2.Response
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class CountryRepositoryTest {
  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  @Mock
  lateinit var context: Context

  @Mock
  lateinit var countryDataSource: CountryDataSource

  @Mock
  lateinit var localDataSource: LocalDataSource

  private lateinit var unMockedCountryRepository: CountryRepository
  private lateinit var countryViewModel: CountryViewModel

  @Before
  fun setup() {
    unMockedCountryRepository = CountryRepository(countryDataSource,localDataSource)
    countryViewModel = CountryViewModel(context, unMockedCountryRepository,localDataSource)
  }


  @Test
  fun `should return countriesList on getCountries call success`() = runBlocking {

    println("ComingHere==InTest")
    val gson = Gson()
    val countriesListResModel = CountryDataProvider.getCountriesListResponseObject(gson)

    Mockito.`when`(countryDataSource.getCountries()).thenReturn(
      Response.success(
        countriesListResModel
      )
    )
    var count = 1
    unMockedCountryRepository.getCountries().collect {
      if (count == 1) {
        assertTrue(it is ResourceState.Loading)
        count++
      }else{
        assertEquals(ResourceState.success(CountryDataProvider.getCountriesListResponseObject(gson)), it)
      }
    }
    verify(countryDataSource).getCountries()
    Unit
  }


  @Test
  fun `should return countryStatesList on getCountriesDetails call success`() = runBlocking {
    val gson = Gson()
    val countryStatesListResModel = CountryDataProvider.getCountryStatesListResponseObject(gson)
    val defaultRequestQuery = CountryDataProvider.defaultCountryDetailsRequestBody()

    Mockito.`when`(countryDataSource.getCountryDetails(defaultRequestQuery)).thenReturn(
      Response.success(
        countryStatesListResModel
      )
    )
    var count = 1
    unMockedCountryRepository.getCountryDetails(defaultRequestQuery).collect {
      if (count == 1) {
        assertTrue(it is ResourceState.Loading)
        count++
      }else{
        assertEquals(ResourceState.success(CountryDataProvider.getCountryStatesListResponseObject(gson)), it)
      }
    }
    verify(countryDataSource).getCountryDetails(defaultRequestQuery)
    Unit
  }


  @After
  fun tearDown() {
    Mockito.verifyNoMoreInteractions(
      countryDataSource
    )
  }
}