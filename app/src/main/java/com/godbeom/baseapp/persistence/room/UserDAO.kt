package com.godbeom.baseapp.persistence.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.godbeom.baseapp.base.BaseDAO
import com.godbeom.baseapp.dto.UserDTO
import io.reactivex.Flowable

@Dao
abstract class UserDAO : BaseDAO<UserDTO>() {

    @Query("SELECT * FROM UserDTO ORDER BY userId DESC")
    abstract fun getAll(): Flowable<List<UserDTO>>


    @Query("DELETE FROM UserDTO")
    abstract fun clearAll()


   /**advance */
    // pageing
    @Query("SELECT * FROM UserDTO")
    abstract fun getAllPushEntityDataSource(): DataSource.Factory<Int, UserDTO>




}