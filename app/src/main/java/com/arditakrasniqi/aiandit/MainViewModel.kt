package com.arditakrasniqi.aiandit

import androidx.lifecycle.ViewModel
import com.arditakrasniqi.aiandit.presentation.utils.AppSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: AppSharedPreferences
) : ViewModel() {

    fun deleteSharedPreferences() {
        sharedPreferences.deletePrefs()
    }
}