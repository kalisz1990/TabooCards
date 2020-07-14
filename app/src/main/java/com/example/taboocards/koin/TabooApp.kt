package com.example.taboocards.koin

import android.app.Application
import com.example.taboocards.koin.appModule
import com.example.taboocards.koin.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TabooApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TabooApp)
            modules(listOf(
                appModule,
                viewModule
            ))

        }
    }
}