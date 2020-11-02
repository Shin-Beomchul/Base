package com.godbeom.baseapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.godbeom.baseapp.persistence.entity.Matchs
import com.godbeom.baseapp.repository.MatchRepository
import com.godbeom.baseapp.repository.MatchRepositoryImpl
import io.reactivex.Flowable

class MatchViewModel(private var matchRepository: MatchRepository) :ViewModel() {

    fun getMatchsPageData(userId:String): Flowable<PagingData<Matchs.Match>>{
       return matchRepository.getMatchsPageData(userId)
    }
}