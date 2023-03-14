package com.nativemobilebits.globe.datasource.remote.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nativemobilebits.globe.datasource.util.TableConstants
import com.squareup.moshi.JsonClass
import java.io.Serializable
@JsonClass(generateAdapter = true)
@Entity(tableName = TableConstants.COUNTRY_STATES_RESPONSE)
data class CountryDetailsResponse(
    var error: Boolean? = null,
    var msg: String? = null,
    var data: DataStates? = DataStates(),
    @PrimaryKey(autoGenerate = true)
    var countryId: Int? = null
) : Serializable

data class States(

    var name: String? = null,
    var state_code: String? = null

) : Serializable


data class DataStates(

    var name: String? = null,
    var iso3: String? = null,
    var iso2: String? = null,
    var states: List<States> = listOf()

) : Serializable