package com.godbeom.baseapp.di

import com.godbeom.baseapp.repository.MatchDataSource
import com.godbeom.baseapp.repository.MatchRepository
import com.godbeom.baseapp.repository.MatchRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * DB관련 Inject
 * @since 2019-11-01
 * @author Beom-chul
 */

val pagingModule = module {
    factory { MatchRepositoryImpl(get()) as MatchRepository }
    factory { MatchDataSource(get(named("denJobAPI")), get()) }

}
