package com.godbeom.baseapp.repository

import androidx.paging.rxjava2.RxPagingSource
import com.godbeom.baseapp.model.MatchMapper
import com.godbeom.baseapp.network.DenJobAPI
import com.godbeom.baseapp.persistence.entity.Matchs
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

// API Service 대신
data class MatchDataSource(var denJobAPI: DenJobAPI, val matchMapper: MatchMapper): RxPagingSource<Int, Matchs.Match>() {
    lateinit var userId:String

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Matchs.Match>> {
        val position = params.key?: 1
        return denJobAPI
            .getMatchOfferList(cur_page = position.toString(), user_id = userId, mem_basic_gbn_cd = "108", reg_id = "token", app_ver = "2.0.4")
            .subscribeOn(Schedulers.io())
            .map { matchMapper.transform(it)}
            .map { matchs -> toLoadResult(matchs,position) }
            .onErrorReturn { LoadResult.Error(it) }

    }

    private fun toLoadResult(data: Matchs, position: Int): LoadResult<Int, Matchs.Match> {
        return LoadResult.Page(
            data = data.matchs,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.cnt) null else position + 1
        )
    }

}