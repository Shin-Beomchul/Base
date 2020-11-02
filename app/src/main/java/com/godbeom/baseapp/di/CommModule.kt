package com.godbeom.baseapp.di


import com.godbeom.baseapp.model.MatchMapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 *
 * @since 2019-11-01
 * @author Beom-chul
 */
val commModule = module {
    factory { MatchMapper() }
}
