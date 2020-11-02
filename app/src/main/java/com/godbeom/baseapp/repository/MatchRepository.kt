package com.godbeom.baseapp.repository

import androidx.paging.PagingData
import com.godbeom.baseapp.persistence.entity.Matchs
import io.reactivex.Flowable

interface MatchRepository {

    fun getMatchsPageData(userId:String): Flowable<PagingData<Matchs.Match>>
}