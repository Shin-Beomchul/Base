package com.godbeom.baseapp.persistence.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.godbeom.baseapp.base.BaseDAO
import com.godbeom.baseapp.persistence.entity.Matchs
@Dao
abstract class MatchDAO : BaseDAO<Matchs.Match>() {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllList(movies: List<Matchs.Match>)

    @Query("SELECT * FROM matchs ORDER BY id ASC")
    abstract fun selectAll(): PagingSource<Int, Matchs.Match>

    @Query("DELETE FROM matchs")
    abstract fun clearMatchs()
}