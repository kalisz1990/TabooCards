package com.example.taboocards.ui.game_activity.timer

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class TabooApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TabooApp)
            modules(listOf(appModule))

        }
    }
}