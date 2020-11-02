package com.godbeom.baseapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.godbeom.baseapp.persistence.entity.Matchs
import io.reactivex.Flowable

class MatchRepositoryImpl(private var matchDataSource: MatchDataSource): MatchRepository {
    override fun getMatchsPageData(userId:String): Flowable<PagingData<Matchs.Match>> {
      return Pager(
          config = PagingConfig(
              pageSize = 10,
              enablePlaceholders = true,
              maxSize = 20,
              prefetchDistance = 5,
              initialLoadSize = 20),
          pagingSourceFactory = {
              matchDataSource.userId = userId
              matchDataSource
          }
      ).flowable
    }
}