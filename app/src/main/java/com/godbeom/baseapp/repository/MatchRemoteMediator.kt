package com.godbeom.baseapp.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxRemoteMediator
import com.godbeom.baseapp.model.MatchMapper
import com.godbeom.baseapp.network.DenJobAPI
import com.godbeom.baseapp.persistence.entity.Matchs
import com.godbeom.baseapp.persistence.room.AppDatabase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.InvalidObjectException


@OptIn(ExperimentalPagingApi::class)
data class MatchRemoteMediator(
    val denJobAPI: DenJobAPI,
    val database: AppDatabase,
    val matchMapper: MatchMapper)
    : RxRemoteMediator<Int, Matchs.Match>() {
    lateinit var userId:String
    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, Matchs.Match>
    ): Single<MediatorResult> {

        return Single.just(loadType)
            .subscribeOn(Schedulers.io())
            .map {
                when (it) {
                    LoadType.REFRESH -> {
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)

                        remoteKeys?.nextKey?.minus(1) ?: 1
                    }
                    LoadType.PREPEND -> {
                        val remoteKeys = getRemoteKeyForFirstItem(state)
                            ?: throw InvalidObjectException("Result is empty")

                        remoteKeys.prevKey ?: INVALID_PAGE
                    }
                    // 현재로드 된 데이터 세트의 끝에 데이터를로드해야하는 경우
                    LoadType.APPEND -> {
                        val remoteKeys = getRemoteKeyForLastItem(state)
                            ?: throw InvalidObjectException("Result is empty")

                        remoteKeys.nextKey ?: INVALID_PAGE
                    }
                }
            }
            .flatMap { page ->
                if (page == INVALID_PAGE) {
                    Single.just(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    denJobAPI.getMatchOfferList(cur_page = page.toString(), user_id = userId, mem_basic_gbn_cd = "108", reg_id = "token", app_ver = "2.0.4")
                        .map { matchMapper.transform(it) }
                        .map { insertToDb(page, loadType, it) }
                        .map<MediatorResult> { MediatorResult.Success(endOfPaginationReached = it.matchs.isEmpty()) }
                        .onErrorReturn { MediatorResult.Error(it) }
                }

            }
            .onErrorReturn { MediatorResult.Error(it) }
    }
    @Suppress("DEPRECATION")
    private fun insertToDb(page: Int, loadType: LoadType, data: Matchs): Matchs {
        database.beginTransaction()

        try {
            if (loadType == LoadType.REFRESH) {
                database.getMatchRemoteKey().clearRemoteKeys()
                database.getMatchDAO().clearMatchs()
            }

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (data.endOfPage) null else page + 1
            val keys = data.matchs.map {
                Matchs.MatchRemotehKeys(hosp_id = it.hosp_id, prevKey = prevKey, nextKey = nextKey)
            }
            database.getMatchRemoteKey().insertAllList(keys)
            database.getMatchDAO().insertAllList(data.matchs)
            database.setTransactionSuccessful()

        } finally {
            database.endTransaction()
        }

        return data
    }
    //
    private fun getRemoteKeyForLastItem(state: PagingState<Int, Matchs.Match>): Matchs.MatchRemotehKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { repo ->
            database.getMatchRemoteKey().remoteKeysByMovieId(repo.hosp_id)
        }
    }

    private fun getRemoteKeyForFirstItem(state: PagingState<Int, Matchs.Match>): Matchs.MatchRemotehKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { movie ->
            database.getMatchRemoteKey().remoteKeysByMovieId(movie.hosp_id)
        }
    }

    private fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Matchs.Match>): Matchs.MatchRemotehKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.hosp_id?.let { id ->
                database.getMatchRemoteKey().remoteKeysByMovieId(id)
            }
        }
    }

    companion object {
        const val INVALID_PAGE = -1
    }

}