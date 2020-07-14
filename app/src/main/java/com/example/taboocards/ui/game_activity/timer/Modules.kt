package com.example.taboocards.ui.game_activity.timer

import com.example.taboocards.ui.game_activity.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { TimerCoordinator() }

}

val viewModule = module {

    viewModel { GameViewModel(get()) }
}

