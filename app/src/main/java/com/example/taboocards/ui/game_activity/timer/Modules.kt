package com.example.taboocards.ui.game_activity.timer

import org.koin.dsl.module

val appModule = module {

    single { TimerCoordinator() }

}

