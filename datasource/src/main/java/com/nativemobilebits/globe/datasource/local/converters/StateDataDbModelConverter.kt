package com.nativemobilebits.globe.datasource.local.converters

import androidx.room.TypeConverter
import com.nativemobilebits.globe.datasource.remote.response.Country
import com.nativemobilebits.globe.datasource.remote.response.States
import com.nativemobilebits.globe.datasource.util.CoreUtility.readArray
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okio.Buffer

class StateDataDbModelConverter {

  private val moshi = Moshi.Builder().build()
  private val membersType = Types.newParameterizedType(List::class.java, States::class.java)
  private val membersAdapter = moshi.adapter<List<States>>(membersType)

  private val memberAdapter = moshi.adapter(States::class.java)

  @TypeConverter
  fun stringToMembers(string: String): List<States> {
    val reader = JsonReader.of(Buffer().writeUtf8(string))
    return sequence {
      reader.readArray {
        yield(memberAdapter.fromJson(reader)!!)
      }
    }.toList()
  }

  @TypeConverter
  fun membersToString(members: List<States>): String {
    return membersAdapter.toJson(members)
  }
}