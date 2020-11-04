package com.godbeom.baseapp.di

import com.godbeom.baseapp.repository.*
import com.godbeom.baseapp.viewmodel.MatchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * DB관련 Inject
 * @since 2019-11-01
 * @author Beom-chul
 */

val pagingModule = module {
    viewModel { MatchViewModel(get(),get()) } // exception maybe Not Impl loadSingle
    factory { MatchRepositoryImpl(matchDataSource = get())}
    factory { MatchRepositoryRxRemoteImpl(remoteMediator = get(),appDatabase = get())}
    factory { MatchRemoteMediator(denJobAPI = get(named("denJobAPI")),database = get(), matchMapper = get()) }
    factory { MatchDataSource(denJobAPI = get(named("denJobAPI")), matchMapper = get()) }

}
