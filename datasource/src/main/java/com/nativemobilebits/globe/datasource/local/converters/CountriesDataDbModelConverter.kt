package com.nativemobilebits.globe.datasource.local.converters

import androidx.room.TypeConverter
import com.nativemobilebits.globe.datasource.remote.response.Country
import com.nativemobilebits.globe.datasource.util.CoreUtility.readArray
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okio.Buffer

class CountriesDataDbModelConverter {

  private val moshi = Moshi.Builder().build()
  private val membersType = Types.newParameterizedType(List::class.java, Country::class.java)
  private val membersAdapter = moshi.adapter<List<Country>>(membersType)

  private val memberAdapter = moshi.adapter(Country::class.java)

  @TypeConverter
  fun stringToMembers(string: String): List<Country> {
    val reader = JsonReader.of(Buffer().writeUtf8(string))
    return sequence {
      reader.readArray {
        yield(memberAdapter.fromJson(reader)!!)
      }
    }.toList()
  }

  @TypeConverter
  fun membersToString(members: List<Country>): String {
    return membersAdapter.toJson(members)
  }
}