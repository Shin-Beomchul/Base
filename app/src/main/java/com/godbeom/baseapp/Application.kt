package com.godbeom.baseapp

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.godbeom.baseapp.di.*
import com.godbeom.baseapp.util.LanguageManager
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application(){

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        startKoin {
            androidContext(baseContext)
            modules(listOf( commModule, PersistenceModule, networkModule, pagingModule, mvvmModule))
        }
    }

    override fun attachBaseContext(base: Context?) {
        languageManager = LanguageManager(base)
        super.attachBaseContext(languageManager.setLocale(base))

    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        languageManager.setLocale(this)
        languageManager.setLocale(applicationContext)

    }

    companion object{
        lateinit var languageManager: LanguageManager

    }

}