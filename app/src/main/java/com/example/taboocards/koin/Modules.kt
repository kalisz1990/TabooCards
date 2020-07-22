package com.example.taboocards.koin

import androidx.room.Room
import com.example.taboocards.ui.game_activity.GameViewModel
import com.example.taboocards.ui.game_activity.dialog.BeforeStartGameDialog
import com.example.taboocards.ui.game_activity.score.ScoreDatabase
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

}

val viewModule = module {

    viewModel { GameViewModel(get(), get()) }
    viewModel { MenuViewModel(get(), get()) }
}

val databaseModule = module {
    single { Room.databaseBuilder(get(), ScoreDatabase::class.java, "table_score").build() }
}

