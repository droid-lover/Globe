package com.nativemobilebits.globe.datasource.remote.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nativemobilebits.globe.datasource.util.TableConstants
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
@Entity(tableName = TableConstants.COUNTRIES_RESPONSE)
data class CountriesResponse(
    var error: Boolean? = null,
    var msg: String? = null,
    var data: List<Country> = listOf(),
    @PrimaryKey(autoGenerate = true)
    var countryId:Int?=null
) : Serializable

@JsonClass(generateAdapter = true)
data class Country (

    var name : String? = null,
    var flag : String? = null,
    var iso2 : String? = null,
    var iso3 : String? = null

): Serializable