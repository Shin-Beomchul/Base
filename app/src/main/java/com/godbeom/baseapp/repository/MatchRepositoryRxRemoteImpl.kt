package com.godbeom.baseapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.godbeom.baseapp.persistence.entity.Matchs
import com.godbeom.baseapp.persistence.room.AppDatabase
import io.reactivex.Flowable


class MatchRepositoryRxRemoteImpl(val remoteMediator: MatchRemoteMediator, val appDatabase: AppDatabase): MatchRepository {
    override fun getMatchsPageData(userId:String): Flowable<PagingData<Matchs.Match>> {
        remoteMediator.userId = userId
      return Pager(
          config = PagingConfig(
              pageSize = 20),
          remoteMediator = remoteMediator,
          pagingSourceFactory ={ appDatabase.getMatchDAO().selectAll() }
      ).flowable
    }
}

//config = PagingConfig(
//pageSize = 20,
//enablePlaceholders = true,
//maxSize = 40,
//prefetchDistance = 5,
//initialLoadSize = 60),
