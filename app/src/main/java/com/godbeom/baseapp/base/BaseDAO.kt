package com.godbeom.baseapp.base

import androidx.room.*

@Dao
abstract class BaseDAO<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertAll(objects: MutableList<T>)



    @Update
    abstract fun update(vararg entity: T)

    @Delete
    abstract fun delete(vararg entity: T)



}