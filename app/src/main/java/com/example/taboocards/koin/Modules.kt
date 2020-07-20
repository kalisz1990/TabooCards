package com.example.taboocards.koin

import com.example.taboocards.ui.game_activity.GameViewModel
import com.example.taboocards.ui.game_activity.dialog.BeforeStartGameDialog
import com.example.taboocards.ui.game_activity.score.ScoreCoordinator
import com.example.taboocards.ui.game_activity.timer.TimerCoordinator
import com.example.taboocards.ui.menu_activity.MenuViewModel
import com.example.taboocards.ui.menu_activity.settings.SettingsDialog
import com.example.taboocards.ui.menu_activity.start_game.StartGameDialog
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { TimerCoordinator() }
    single { StartGameDialog() }
    single { SettingsDialog() }
    single { BeforeStartGameDialog() }
    single { ScoreCoordinator() }

}

val viewModule = module {

    viewModel { MenuViewModel(get(), get()) }
    viewModel { GameViewModel(get(), get(), get()) }
}

