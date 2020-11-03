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
        val page = params.key?: 1
        return denJobAPI
            .getMatchOfferList(cur_page = page.toString(), user_id = userId, mem_basic_gbn_cd = "108", reg_id = "token", app_ver = "2.0.4")
            .subscribeOn(Schedulers.io())
            .map { matchMapper.transform(it)}
            .map { matchs -> toLoadResult(matchs,page,params) }
            .onErrorReturn { LoadResult.Error(it) }

    }

    private fun toLoadResult(data: Matchs, page: Int, params: LoadParams<Int>): LoadResult<Int, Matchs.Match> {
        return LoadResult.Page(
            data = data.matchs,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page == data.cnt) null else page + 1
        )
    }

}