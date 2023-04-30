package com.arditakrasniqi.aiandit.presentation.di

import android.content.Context
import com.arditakrasniqi.aiandit.presentation.utils.AppSharedPreferences
import com.arditakrasniqi.aiandit.presentation.utils.AppSharedPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun bindSharedPreferences(@ApplicationContext context: Context):
            AppSharedPreferences = AppSharedPreferencesImpl(context)
}