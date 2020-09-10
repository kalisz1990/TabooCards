package com.example.taboocards.koin

import androidx.room.Room
import com.example.taboocards.data.card.CardDatabase
import com.example.taboocards.data.card.CardRepository
import com.example.taboocards.data.card.CardRepositoryImpl
import com.example.taboocards.ui.game_activity.GameViewModel
import com.example.taboocards.ui.game_activity.dialog.DialogCreator
import com.example.taboocards.ui.game_activity.team.TeamDatabase
import com.example.taboocards.ui.game_activity.team.TeamRepository
import com.example.taboocards.ui.game_activity.team.TeamRepositoryImpl
import com.example.taboocards.ui.game_activity.timer.TimerCoordinator
import com.example.taboocards.ui.menu_activity.MenuViewModel
import com.example.taboocards.ui.menu_activity.settings.SettingsDialog
import com.example.taboocards.ui.menu_activity.start_game.StartGameDialog
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { TimerCoordinator(get()) }
    single { StartGameDialog() }
    single { SettingsDialog() }
    single { DialogCreator() }
}

val viewModule = module {
    viewModel { GameViewModel(get(), get(), get(), get(), get()) }
    viewModel { MenuViewModel(get(), get()) }
}

val databaseModule = module {
    single { TeamRepositoryImpl(get()) as TeamRepository }
    single { CardRepositoryImpl(get()) as CardRepository }

    single {
        Room.databaseBuilder(androidApplication(), TeamDatabase::class.java, "table_team")
            .build()
    }

    single {
        Room.databaseBuilder(androidApplication(), CardDatabase::class.java, "table_cards")
            .build()
    }

    single { get<TeamDatabase>().teamDao() }
    single { get<CardDatabase>().cardDao() }

}



