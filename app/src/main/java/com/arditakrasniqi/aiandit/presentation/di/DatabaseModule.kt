package com.arditakrasniqi.aiandit.presentation.di

import android.app.Application
import androidx.room.Room
import com.arditakrasniqi.aiandit.data.db.app.AppDatabase
import com.arditakrasniqi.aiandit.data.db.dao.Aianditdao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "Ai_It_Db"

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideOutageDao(appDatabase: AppDatabase): Aianditdao {
        return appDatabase.dao()
    }

}