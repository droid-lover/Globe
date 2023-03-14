package com.nativemobilebits.globe.datasource.local.dao

import androidx.room.*

@Dao
interface BaseDao<in T> {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(t: T): Long

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertWithIgnore(t: T): Long

  @Delete
  fun delete(t: T): Int

  @Update
  fun update(t: T): Int


}