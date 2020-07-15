package com.example.taboocards.koin

import android.os.CountDownTimer
import com.example.taboocards.ui.game_activity.GameViewModel
import com.example.taboocards.ui.game_activity.timer.TimerCoordinator
import com.example.taboocards.ui.menu_activity.MenuViewModel
import com.example.taboocards.ui.menu_activity.settings.SettingsDialog
import com.example.taboocards.ui.menu_activity.start_game.StartGameDialog
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val appModule = module {

    single { TimerCoordinator() }
    single { StartGameDialog() }
    single { SettingsDialog() }

}

val viewModule = module {

    viewModel { GameViewModel(get()) }
    viewModel { MenuViewModel(get(), get()) }
}

