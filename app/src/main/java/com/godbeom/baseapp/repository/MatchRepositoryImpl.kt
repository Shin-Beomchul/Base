package com.godbeom.baseapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.godbeom.baseapp.persistence.entity.Matchs
import io.reactivex.Flowable
//https://medium.com/@eyegochild/android-paging-library-in-jetpack-1-overview-e40e7ce85d96

/*
    Page size : page에 로드할 item 갯수
    InitialLoadSizeHint : 기본값은 page size의 3배 입니다. 이는 initial load 후에 initial page fetch를 피하기 위해 initial load 값보다 더 크게 설정하는 것입니다.
    Prefetch distance : 기본값은 page size 입니다. 이는 현재 load한 것으로부터 얼마나 더 많이 load할 것인지 설정하는 것입니다. 이 값을 50으로 설정한다면, 이미 접근한 data보다 50개의 item을 더 load 할 것입니다.
    Placeholder presence : 기본값은 true 입니다. Placeholder는 first page를 load할 때 기대할 수 있는 기본 페이징입니다. Placeholder가 true인 경우의 동작은 다음과 같습니다.
                          entire dataset을 RecyclerView에 표시하기 때문에 scroll bar의 크기는 약간 작습니다. scroll 하는 동안, data를 더 load하면서 scroll bar가 jumping하는 현상은 없습니다.
                          그리고 scroll 하면서 load되지 않은 item이 있다면, adapter에서 null로 표시됩니다. null로 표시됐던 item들도 결국 load된 후 표시되므로 animation 효과를 얻을 수 있습니다.
*/
class MatchRepositoryImpl(private var matchDataSource: MatchDataSource): MatchRepository {
    override fun getMatchsPageData(userId:String): Flowable<PagingData<Matchs.Match>> {
      return Pager(
          config = PagingConfig(pageSize = 20),
          pagingSourceFactory = {
              matchDataSource.userId = userId
              matchDataSource
          }
      ).flowable
    }
}


/**스크롤 위 아래 시 모두 리로드됨.*/
//config = PagingConfig(
//pageSize = 20,
//enablePlaceholders = true,
//maxSize = 40,
//prefetchDistance = 5,
//initialLoadSize = 60),