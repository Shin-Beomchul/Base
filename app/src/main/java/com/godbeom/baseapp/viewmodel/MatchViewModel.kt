package com.godbeom.baseapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.godbeom.baseapp.persistence.entity.Matchs
import com.godbeom.baseapp.repository.MatchRepositoryImpl
import com.godbeom.baseapp.repository.MatchRepositoryRxRemoteImpl

import io.reactivex.Flowable

class MatchViewModel(private var matchRepositoryImpl: MatchRepositoryImpl,private var matchRepositoryRxRemoteImpl: MatchRepositoryRxRemoteImpl) :ViewModel() {

    fun getMatchsPageData(userId:String): Flowable<PagingData<Matchs.Match>>{
//        return matchRepositoryImpl.getMatchsPageData(userId)  // network Only
       return matchRepositoryRxRemoteImpl.getMatchsPageData(userId) // network + DB
    }

    override fun onCleared() {
        super.onCleared()
    }

}