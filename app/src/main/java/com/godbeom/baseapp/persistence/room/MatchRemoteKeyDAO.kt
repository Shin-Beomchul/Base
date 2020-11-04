package com.godbeom.baseapp.persistence.room

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.godbeom.baseapp.base.BaseDAO
import com.godbeom.baseapp.persistence.entity.Matchs
@Dao
abstract class MatchRemoteKeyDAO: BaseDAO<Matchs.MatchRemotehKeys>(){



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllList(remoteKey: List<Matchs.MatchRemotehKeys>)

    @Query("SELECT * FROM match_remote_keys WHERE hosp_id = :hosp_id")
    abstract fun remoteKeysByMovieId(hosp_id: String): Matchs.MatchRemotehKeys?

    @Query("DELETE FROM match_remote_keys")
    abstract fun clearRemoteKeys()

}

